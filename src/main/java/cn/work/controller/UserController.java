package cn.work.controller;

import cn.work.pojo.Profile;
import cn.work.pojo.User;
import cn.work.pojo.UserExt;
import cn.work.pojo.Userinfo;
import cn.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import static cn.work.util.SHAUtil.getEncrypt;
import static cn.work.util.Validator.userValidator;

/**
 * @Description: 处理读者操作控制器
 * @Author: Aaron Ke
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @Description: 读者登录
     * @Param: user:接受用户编号，密码等信息，request:取Session
     * @return: 返回登录结果
     * @Author: Aaron Ke
     */
    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(UserExt user, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String pwd = "";
        if (user != null) {
            //通过用户身份证得到用户信息
            UserExt va_user = userService.getUserAndPwdByIDCard(user.getIdcard());
            if (va_user != null) {
                //对密码进行加密
                pwd = getEncrypt(user.getPassword());
            } else {
                result.put("result", "找不到用户名！");
                return result;
            }
            if (va_user.getPassword().equals(pwd)) {
                //向Session添加用户信息，方便验证
                request.getSession().setAttribute("userid", va_user.getUserid());
                result.put("result", "success");
                return result;
            } else {
                result.put("result", "密码错误！");
                return result;
            }
        } else {
            result.put("result", "未输入信息！");
            return result;
        }
    }

    /**
     * @Description: 退出登录
     * @Param: requset:取到Session对象
     * @return: 要跳转的页面
     * @Author: Aaron Ke
     */
    @RequestMapping("logOut")
    public String logOut(HttpServletRequest request) {
        //得到Session中的用户信息
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        if (userid != null) {
            request.getSession().removeAttribute("userid");
            //重定向页面
            return "redirect:/UserLogin";
        } else
            return "redirect:/UserLogin";
    }

    /**
     * @Description: 更改用户密码 ,详细见AdminController的changPwd
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "changePwd")
    @ResponseBody
    public Map<String, Object> changePwd(String pwd, String newPwd, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        pwd = getEncrypt(pwd);
        if (userid != null) {
            User user = userService.getUserAndPwdByID(Integer.toString(userid));
            if (newPwd.equals(pwd)) {
                result.put("result", "新密码不能与旧密码相同！");
                return result;
            }
            if (pwd.equals(user.getPassword())) {
                user.setPassword(newPwd);
                userService.updatePwd(user);
                result.put("result", "success");
                request.getSession().removeAttribute("userid");
                return result;
            } else {
                result.put("result", "当前密码不正确");
                return result;
            }
        } else {
            result.put("error", "您尚未登录!");
            return result;
        }
    }

    /**
     * @Description: 得到所有用户的信息
     * @Param: 无
     * @return: 用户信息结果集
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "getAllUsers")
    @ResponseBody
    public Map getAllUsers() {
        Map<String, Object> result = new HashMap<>();
        List<Userinfo> userlist;
        //得到用户信息
        userlist = userService.getAllUsers();
        result.put("data", userlist);
        return result;
    }

    /**
     * @Description: 增加用户
     * @Param: useriinfo:接受用户信息
     * @return: 返回处理结果
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "addUser")
    @ResponseBody
    public Map<String, Object> addUser(Userinfo userinfo) {
        Map<String, Object> result = new HashMap<>();
        try {
            //调用增加用户的方法
            if (userService.addUser(userinfo)) {
                result.put("result", "success");
                return result;
            } else {
                result.put("result", "error");
                result.put("msg", "已有相同用户存在");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "error");
            result.put("msg", "发生未知错误");
            return result;
        }
    }

    /**
     * @Description: 处理用户状态
     * @Param: id :用户编号，display:用户的状态。true：表示启用,false:表示注销
     * @return: 返回处理结果集
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "showUser")
    @ResponseBody
    public Map<String, Object> showUser(String id, boolean display) {
        Map<String, Object> result = new HashMap<>();
        try {
            //调用处理用户状态方法
            userService.showUser(id, display);
            result.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "error");
            result.put("msg", "注销失败！原因：" + e.getMessage());
        } finally {
            return result;
        }
    }

    /**
     * @Description: 得到用户权限的详细信息 ，（既不能借书的原因）
     * @Param: id:用户编号
     * @return: 用户权限详细信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "getAccessDetail")
    @ResponseBody
    public Map<String, Object> getAccessDetail(String id) {
        Map<String, Object> result = new HashMap<>();
        String reason = userService.getAccessDetail(id);
        result.put("reason", reason);
        return result;
    }


    /**
     * @Description: 更新用户信息
     * @Param: userinfo:用户信息
     * @return: 处理结果
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "updateUser")
    @ResponseBody
    public Map<String, Object> updateUser(Userinfo userinfo) {
        Map<String, Object> result = new HashMap<>();
        //验证用户信息是否合法
        String msg = userValidator(userinfo);
        if (msg != null) {
            result.put("error", msg);
            return result;
        }
        //更新用户信息
        userinfo = userService.updateUser(userinfo);
        if (userinfo == null) {
            result.put("error", "该身份证已有人使用！");
            return result;
        } else {
            List<Userinfo> userlist = new ArrayList<>();
            userlist.add(userinfo);
            result.put("data", userlist);
            return result;
        }
    }

    /**
     * @Description: 得到用户各种详细信息
     * @Param: userid:用户编号
     * @return: 用户的详细信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "getProfile")
    @ResponseBody
    public Profile getProfile(String userid) {
        Profile profile = userService.getProfile(userid);
        return profile;
    }
}
