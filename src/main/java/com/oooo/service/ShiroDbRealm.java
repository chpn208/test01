package com.oooo.service;

import com.oooo.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import static java.awt.SystemColor.info;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ShiroDbRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userService.findByName(token.getUsername());
        if (user != null) {
            if (user.getStatus() == -1) {
                throw new DisabledAccountException();
            }

           /* ShiroUser shiroUser = new ShiroUser(user.getLoginName(),user.getNickName(), user.getMenu(), user.getDept());
            shiroUser.setUserid(user.getId());*/
            getName();
            //SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(shiroUser, user.getPassword(), ByteSource.Util.bytes(salt), getName());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getName(),user.getPassword(),getName());
            //清除当前用户的授权缓存
            clearCachedAuthorizationInfo(info.getPrincipals());
            return info;
        } else {
            return null;
        }
    }
}
