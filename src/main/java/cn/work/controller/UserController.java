package cn.work.controller;

import cn.work.pojo.Profile;
import cn.work.pojo.UserExt;
import cn.work.pojo.Userinfo;
import cn.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
            UserExt va_user = userService.getUserAndPwdByID(user.getIdcard());
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
        if (userid != null && !userid.equals("")) {
            request.getSession().removeAttribute("userid");
            return "redirect:/UserLogin";
        } else
            return "redirect:/UserLogin";
    }

    @RequestMapping(value = "getAllUsers")
    @ResponseBody
    public Map getAllUsers() {
        Map<String, Object> result = new HashMap<>();
        List<Userinfo> userlist = new ArrayList<>();
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

    @RequestMapping(value = "delUser")
    @ResponseBody
    public Map<String, Object> delUser(String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.delUser(id);
            result.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "error");
            result.put("msg", e.getMessage());
        } finally {
            return result;
        }
    }

    @RequestMapping(value = "delUsers")
    @ResponseBody
    public Map<String, Object> delUsers(@RequestBody Userinfo[] userinfos) {
        Map<String, Object> result = new HashMap<>();
        for (Userinfo userinfo : userinfos) {
            try {
                userService.delUser(userinfo.getUserid().toString());
            } catch (Exception e) {
                e.printStackTrace();
                result.put("result", "error");
                result.put("msg", e.getMessage());
                return result;
            }
        }
        result.put("result", "success");
        return result;
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
