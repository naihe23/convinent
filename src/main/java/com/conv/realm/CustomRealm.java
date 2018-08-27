package com.conv.realm;

import com.conv.model.ActiveUser;
import com.conv.model.User;
import com.conv.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class CustomRealm  extends AuthorizingRealm {

    @Resource
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String userCode = (String) authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getCredentials());

        User user = userService.validateUserExist(userCode);

        if(user==null){
            return null;
        }

        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserId(user.getUserId());
        activeUser.setUserNickname(user.getUserNickname());
        activeUser.setUserEmail(user.getUserEmail());
        activeUser.setUserPassword(password);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser,
                user.getUserPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
        return simpleAuthenticationInfo;
    }

    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
