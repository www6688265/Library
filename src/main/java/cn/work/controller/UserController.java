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

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(UserExt user, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String pwd = "";
        if (user != null) {
            UserExt va_user = userService.getUserAndPwdByIDCard(user.getIdcard());
            if (va_user != null) {
                pwd = getEncrypt(user.getPassword());
            } else {
                result.put("result", "找不到用户名！");
                return result;
            }
            if (va_user.getPassword().equals(pwd)) {
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

    @RequestMapping("logOut")
    public String logOut(HttpServletRequest request) {
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        if (userid != null) {
            request.getSession().removeAttribute("userid");
            return "redirect:/UserLogin";
        } else
            return "redirect:/UserLogin";
    }

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

    @RequestMapping(value = "getAllUsers")
    @ResponseBody
    public Map getAllUsers() {
        Map<String, Object> result = new HashMap<>();
        List<Userinfo> userlist;
        userlist = userService.getAllUsers();
        result.put("data", userlist);
        return result;
    }

    @RequestMapping(value = "addUser")
    @ResponseBody
    public Map<String, Object> addUser(Userinfo userinfo) {
        Map<String, Object> result = new HashMap<>();
        try {
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

    @RequestMapping(value = "showUser")
    @ResponseBody
    public Map<String, Object> showUser(String id, boolean display) {
        Map<String, Object> result = new HashMap<>();
        try {
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


    @RequestMapping(value = "getAccessDetail")
    @ResponseBody
    public Map<String, Object> getAccessDetail(String id) {
        Map<String, Object> result = new HashMap<>();
        String reason = userService.getAccessDetail(id);
        result.put("reason", reason);
        return result;
    }


    @RequestMapping(value = "updateUser")
    @ResponseBody
    public Map<String, Object> updateUser(Userinfo userinfo) {
        Map<String, Object> result = new HashMap<>();
        String msg = userValidator(userinfo);
        if (msg != null) {
            result.put("error", msg);
            return result;
        }
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

    @RequestMapping(value = "getProfile")
    @ResponseBody
    public Profile getProfile(String userid) {
        Profile profile = userService.getProfile(userid);
        return profile;
    }
}
