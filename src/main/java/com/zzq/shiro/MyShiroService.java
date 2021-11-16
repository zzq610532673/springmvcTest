package com.zzq.shiro;

import com.zzq.bean.User;
import com.zzq.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyShiroService extends AuthorizingRealm {
    
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MyShiroService.class);

    /**
     * 授权方法
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(userService.getRoles());
        simpleAuthorizationInfo.setStringPermissions(userService.getPermission(username));
        return simpleAuthorizationInfo;*/
        return null;
    }

    /**
     * 认证方法
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token1 = (UsernamePasswordToken)token;
        String username = token1.getUsername();
        String password="";
        for (int i = 0; i < token1.getPassword().length; i++) {
            password+= token1.getPassword()[i];
        }
        //在数据库查询是否存在此对象
        User user = userService.queryUser(username);
        if (user == null) {
            logger.error("用户名或密码错误");
            throw new UnknownAccountException("用户名或密码错误");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password, getName());
        logger.debug("登录成功，登录用户为：",username);
        return info;
    }
}
