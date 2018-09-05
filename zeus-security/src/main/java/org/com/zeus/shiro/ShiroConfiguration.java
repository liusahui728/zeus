package org.com.zeus.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {

	// Filter工厂，设置对应的过滤条件和跳转条件
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		// 没有登陆的用户只能访问登陆页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		// 登出
		filterChainDefinitionMap.put("/logout", "logout");
		// 自定义拦截器
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		// 限制同一帐号同时在线的个数。
		// filtersMap.put("kickout", kickoutSessionControlFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);
		// authc 需要登录 anon 不需要登录 user记住密码或者登陆可以访问
		filterChainDefinitionMap.put("/jquery/**", "anon");
		filterChainDefinitionMap.put("/doRegister", "anon");
		filterChainDefinitionMap.put("/add", "roles[管理员]");
		filterChainDefinitionMap.put("/edit", "roles[开发]");
		filterChainDefinitionMap.put("/order", "authc");
		// 配置记住我或认证通过可以访问的地址
		filterChainDefinitionMap.put("/**", "user");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	// 权限管理，配置主要是Realm的管理认证
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(cacheManager());
		// 注入记住我管理器
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	// 将自己的验证方式加入容器
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(credentialsMatcher());
		return myShiroRealm;
	}

	/**
	 * cacheManager 缓存 redis实现
	 * <p>
	 * 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	@Bean
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * 配置shiro redisManager
	 * <p>
	 * 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost("127.0.0.1");
		redisManager.setPort(6379);
		redisManager.setExpire(1800);// 配置缓存过期时间
		redisManager.setTimeout(3600);
		// redisManager.setPassword(password);
		return redisManager;
	}

	// 自定义sessionManager

	@Bean
	public SessionManager sessionManager() {
		// 从请求头中或者cookie中获取sessionid
		MySessionManager mySessionManager = new MySessionManager();
		// 注入redisSessionDAO
		mySessionManager.setSessionDAO(redisSessionDAO());
		// 设置过期时间
		mySessionManager.setGlobalSessionTimeout(60000);
		// 是否定时检查session
		mySessionManager.setSessionValidationSchedulerEnabled(true);
		// 删除过期session
		mySessionManager.setDeleteInvalidSessions(true);

		return mySessionManager;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis
	 * <p>
	 * 使用的是shiro-redis开源插件
	 */

	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		/*
		 * User user=new User();
		 * BeanUtils.copyProperties(SecurityUtils.getSubject().getPrincipal(), user);
		 * redisSessionDAO.setKeyPrefix(user.getUsername());
		 */
		//redisSessionDAO.setSessionIdGenerator(new MySessionIdGenerator());
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * 限制同一账号登录同时登录人数控制
	 *
	 * @return
	 */
	/*
	 * @Bean public KickoutSessionControlFilter kickoutSessionControlFilter() {
	 * KickoutSessionControlFilter kickoutSessionControlFilter = new
	 * KickoutSessionControlFilter();
	 * kickoutSessionControlFilter.setCacheManager(cacheManager());
	 * kickoutSessionControlFilter.setSessionManager(sessionManager());
	 * kickoutSessionControlFilter.setKickoutAfter(false);
	 * kickoutSessionControlFilter.setMaxSession(1);
	 * kickoutSessionControlFilter.setKickoutUrl("/auth/kickout"); return
	 * kickoutSessionControlFilter; }
	 */

	@Bean
	public HashedCredentialsMatcher credentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(md5(""));
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}

	// 加入注解的使用，不加入这个注解不生效
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * cookie对象; rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
	 * 
	 * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		// System.out.println("ShiroConfiguration.rememberMeCookie()");
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// <!-- 记住我cookie生效时间30天 ,单位秒;-->
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}

	/**
	 * cookie管理对象;
	 * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
	 * 
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		// System.out.println("ShiroConfiguration.rememberMeManager()");
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		// rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
		return cookieRememberMeManager;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	/**
	 * 注册全局异常处理
	 * 
	 * @return
	 */
	/*
	 * @Bean(name = "exceptionHandler") public HandlerExceptionResolver
	 * handlerExceptionResolver() { return new MyExceptionHandler(); }
	 */
}
