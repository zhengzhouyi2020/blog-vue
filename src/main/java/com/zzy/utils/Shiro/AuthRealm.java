package com.zzy.utils.Shiro;

import com.zzy.entity.SysUser;
import com.zzy.service.UserService;
import com.zzy.utils.constant.CommonEnum;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Zzy
 * @Date 2020/12/27
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username= (String) token.getPrincipal();
        if(username==null){
            throw new AuthenticationException(CommonEnum.TOKEN_ERROR.getMsg());
        }
        String password= new String((char[])token.getCredentials());
        SysUser sysUser=userService.findByName(username);
        if (sysUser == null || !sysUser.getPassword().equals(password)) {
            throw new IncorrectCredentialsException(CommonEnum.LOGIN_ERROR.getMsg());
        }

        return new SimpleAuthenticationInfo(
                sysUser,
                password,
                getName()
        );
    }
}
