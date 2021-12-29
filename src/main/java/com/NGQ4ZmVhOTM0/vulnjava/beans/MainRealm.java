package com.NGQ4ZmVhOTM0.vulnjava.beans;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


public class MainRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char [])authenticationToken.getCredentials());
        // password is admin1234 + UVWXYZ crossing from the beginning.
        if (username.equals("admin") && password.equals("aZdYmXiWnV1U234")) {
            return new SimpleAuthenticationInfo(username, password, getName());
        } else {
            throw new IncorrectCredentialsException("Username or password is incorrect.");
        }
    }
}
