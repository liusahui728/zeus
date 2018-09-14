package org.com.zeus.shiro;

import java.util.Collection;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.com.zeus.common.model.User;
import org.com.zeus.mapper.UserMapper;
import org.com.zeus.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

//实现AuthorizingRealm接口用户用户认证
public class MyShiroRealm extends AuthorizingRealm {

	// 用于用户查询
	@Autowired
	private UserMapper userMapper;

	// 用于用户查询
	@Autowired
	private IUserService userService;
	
	@Autowired
	ShiroSessionDAO shiroSessionDAO;
	// 角色权限和对应权限添加
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录用户名
		User temp=new User();
		BeanUtils.copyProperties(SecurityUtils.getSubject().getPrincipal(), temp);
		// 查询用户名称
		User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getAccount, temp.getAccount())); // 添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(userService.getRoleByAccount(user));
		simpleAuthorizationInfo.addStringPermissions(userService.getPermissionByAccount(user));
		return simpleAuthorizationInfo;
	}

	// 用户认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
		if (authenticationToken.getPrincipal() == null) {
			return null;
		}
		// 获取用户信息
		String account = authenticationToken.getPrincipal().toString();
		System.out.println(authenticationToken.getCredentials());
		Collection<Session> sessions =shiroSessionDAO.getActiveSessions();
		for(Session session:sessions) {
			session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			System.out.println(session.getId());
		}
		SecurityUtils.getSubject().getPrincipal();
		User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getAccount, account));
		if (user == null) {
			// 这里返回后会报出对应异常
			return null;
		}
		if (user.getStatus() == 1) { // 账户冻结
			throw new LockedAccountException();
		}
		// 这里验证authenticationToken和simpleAuthenticationInfo的信息
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),ByteSource.Util.bytes(user.getAccount()), getName());
		return simpleAuthenticationInfo;
	}
}
