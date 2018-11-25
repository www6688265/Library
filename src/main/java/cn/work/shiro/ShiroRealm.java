package cn.work.shiro;

import cn.work.dao.AdminMapper;
import cn.work.dao.UserMapper;
import cn.work.pojo.Admin;
import cn.work.pojo.UserExt;
import cn.work.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import javax.annotation.Resource;

/**
 * @program: libraryOs
 * @description: 自定义Realm
 * @author: Aaron Ke
 * @create: 2018-11-17 12:22
 **/
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    UserMapper userMapper;
    @Autowired
    AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        Admin user = adminService.getAdminByCardId(username);
        if (user == null) {
            return null;
        } else {
            String password = user.getAdmpassword();
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
            return info;
        }


    }
}
