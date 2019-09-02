package com.shiro.realm;

import com.webservice.entity.Sysuser;
import com.webservice.entity.User;
import com.webservice.service.IPermissionService;
import com.webservice.service.IRoleService;
import com.webservice.service.IUserService;
import com.webservice.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserRealm extends AuthorizingRealm{
    private static  final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    IUserService userService;
    @Autowired
    IPermissionService permissionService;
    @Autowired
    IRoleService roleService;

    /**
     * 授权，在配有缓存的情况下，只加载一次(角色)
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //能进去到这里，标识账号已经通过验证了
        String name = (String) principalCollection.getPrimaryPrincipal();
        //获取账号的角色和权限信息
        Set<String> roles = roleService.listRoleName(name);
        Set<String> permissions = permissionService.listPermissionName(name);
        //授权对象信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //将token转换为 UsernamePasswordToken
        UsernamePasswordToken userToke = (UsernamePasswordToken) token;
        String name = userToke.getPrincipal().toString();
        System.err.println(name);
        //获取指定用户数据库中的密码，盐
        User user = userService.getUserByName(name);
        String pwdInDb = user.getPassword();
        String salt = user.getSalt();

        //认证信息，存放账号，密码，盐，getName是当前realm的继承方法，通常返回当前类名
        //通过shiro配置的HashedCredentialsMatcher 进行自动校验
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                name,pwdInDb, ByteSource.Util.bytes(salt),getName());
        return authenticationInfo;
    }
}
