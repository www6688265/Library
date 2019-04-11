package cn.work.shiro;

import cn.work.dao.AdminMapper;
import cn.work.dao.UserMapper;
import cn.work.pojo.Admin;
import cn.work.pojo.UserExt;
import cn.work.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: libraryOs
 * @description: 自定义Realm
 * @author: Aaron Ke
 * @create: 2018-11-17 12:22
 **/
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    UserMapper userMapper;
    @Autowired
    AdminService adminService;

    public ShiroRealm() {
        super();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Admin admin = (Admin) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Admin admin1 = adminService.getAdminByCardId(admin.getIdcard());
        Integer admright = admin1.getAdmright();
        if (admright == 0) {
            authorizationInfo.addRole("manager");
        } else {
            authorizationInfo.addRole("admin");
        }
        return authorizationInfo;

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
