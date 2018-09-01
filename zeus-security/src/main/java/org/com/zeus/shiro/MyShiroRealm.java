package org.com.zeus.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.com.zeus.common.model.User;
import org.com.zeus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

//实现AuthorizingRealm接口用户用户认证
public class MyShiroRealm extends AuthorizingRealm {

	// 用于用户查询
	@Autowired
	private UserMapper userMapper;

	// 角色权限和对应权限添加
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		// 获取登录用户名
		String username = (String) principalCollection.getPrimaryPrincipal();
		// 查询用户名称
		User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username)); // 添加角色和权限
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		/*
		 * for (Role role : user.getRoles()) { // 添加角色
		 * simpleAuthorizationInfo.addRole(role.getRoleName()); for (Permission
		 * permission : role.getPermissions()) { // 添加权限
		 * simpleAuthorizationInfo.addStringPermission(permission.getPermission()); } }
		 */

		return new SimpleAuthorizationInfo();
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
		String username = authenticationToken.getPrincipal().toString();
		User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
		if (user == null) {
			// 这里返回后会报出对应异常
			return null;
		}
		if (user.getStatus() == 1) { // 账户冻结
			throw new LockedAccountException();
		}
		ByteSource salt=ByteSource.Util.bytes(username);
		// 这里验证authenticationToken和simpleAuthenticationInfo的信息
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,
				user.getPassword().toString(),salt, getName());
		return simpleAuthenticationInfo;
	}
}
