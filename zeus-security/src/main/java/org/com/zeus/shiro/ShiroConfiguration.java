package org.com.zeus.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
		// 登出
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/jquery/**", "anon");
		filterChainDefinitionMap.put("/doRegister", "anon");
		filterChainDefinitionMap.put("/add", "roles[管理员]");
		// 对所有用户认证
		filterChainDefinitionMap.put("/**", "authc");
		// 登录
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 首页
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}

	// 将自己的验证方式加入容器
	@Bean
	public MyShiroRealm myShiroRealm() {
		return new MyShiroRealm();
	}

	// 权限管理，配置主要是Realm的管理认证
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		// 自定义session管理 使用redis
		// securityManager.setSessionManager(sessionManager());
		// 自定义缓存实现 使用redis
		// securityManager.setCacheManager(cacheManager());
		return securityManager;
	}

	// 自定义sessionManager
	/*
	 * @Bean public SessionManager sessionManager() { MySessionManager
	 * mySessionManager = new MySessionManager();
	 * mySessionManager.setSessionDAO(redisSessionDAO()); return mySessionManager; }
	 */

	/**
	 * 配置shiro redisManager
	 * <p>
	 * 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	/*
	 * public RedisManager redisManager() { RedisManager redisManager = new
	 * RedisManager(); redisManager.setHost(host); redisManager.setPort(port);
	 * redisManager.setExpire(1800);// 配置缓存过期时间 redisManager.setTimeout(timeout);
	 * redisManager.setPassword(password); return redisManager; }
	 */
	/**
	 * cacheManager 缓存 redis实现
	 * <p>
	 * 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	/*
	 * @Bean public RedisCacheManager cacheManager() { RedisCacheManager
	 * redisCacheManager = new RedisCacheManager();
	 * redisCacheManager.setRedisManager(redisManager()); return redisCacheManager;
	 * }
	 */

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis
	 * <p>
	 * 使用的是shiro-redis开源插件
	 */
	/*
	 * @Bean public RedisSessionDAO redisSessionDAO() { RedisSessionDAO
	 * redisSessionDAO = new RedisSessionDAO();
	 * redisSessionDAO.setRedisManager(redisManager()); return redisSessionDAO; }
	 */

	// 加入注解的使用，不加入这个注解不生效
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
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
