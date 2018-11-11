package cn.work.controller;

import cn.work.pojo.Admin;
import cn.work.service.AdminService;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static cn.work.util.SHAUtil.getEncrypt;
import static cn.work.util.Validator.adminValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 用户处理管理员的控制器
 * @Author: Aaron Ke
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    /**
     * @Description: 该方法用于登录
     * @Param: 第一个参数，用于接受前端传的账号和密码，并用admin类包装。第二个参数是一个request类，用于在方法内取到Session
     * 并将用户信息存入Session
     * @return: 返回Map，SpringBoot会将其转化为JSON对象。
     * @Author: Aaron Ke
     */
    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(Admin admin, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        //初始化存密码的变量
        String pwd = "";
        if (admin != null) {
            //得到用户正确的密码
            Admin va_admin = adminService.getAdminByCardId(admin.getIdcard());
            if (va_admin != null) {
                //将传过来的密码进行加密操作。
                pwd = getEncrypt(admin.getAdmpassword());
            } else {
                //返回错误信息
                result.put("result", "找不到用户名！");
                return result;
            }
            if (va_admin.getAdmpassword().equals(pwd)) {
                //将用户信息存入Session，方便进行用户登录拦截
                request.getSession().setAttribute("admid", va_admin.getAdmid());
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
     * @Description: 用于退出登录的方法
     * @Param: request用于得到Session对象
     * @return: 返回的界面地址
     * @Author: Aaron Ke
     */
    @RequestMapping("logOut")
    public String logOut(HttpServletRequest request) {
        //取到用户存在Session的对象
        Integer admid = (Integer) request.getSession().getAttribute("admid");
        if (admid != null) {
            //将用户的Session对象销毁
            request.getSession().removeAttribute("admid");
            //重定向至登录界面
            return "redirect:/login";
        } else
            return "redirect:/login";
    }

    /**
     * @Description: 取到所有管理员信息
     * @Param: 无
     * @return: 返回所有管理员的信息
     * @Author: Aaron Ke
     */
    @RequestMapping("getAllAdmins")
    @ResponseBody
    public Map<String, Object> getALlAdmins() {
        HashMap<String, Object> result = new HashMap<>();
        //得到所有管理员信息
        List<Admin> list = adminService.getAllAdmins();
        result.put("data", list);
        return result;
    }

    /**
     * @Description: 增加管理管理员
     * @Param: Admin：一个管理员对象
     * @return: 返回结果信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "addAdmin")
    @ResponseBody
    public Map<String, Object> addAdmin(Admin admin) {
        Map<String, Object> result = new HashMap<>();
        try {
            //增加管理员，并判断是否添加成功
            if (adminService.addAdmin(admin)) {
                result.put("result", "success");
                return result;
            } else {
                result.put("result", "error");
                result.put("msg", "已有相同用户名存在");
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
     * @Description: 删除管理员信息
     * @Param: 管理员的id
     * @return: 返回结果信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "delAdmin")
    @ResponseBody
    public Map<String, Object> delAdmin(String id) {
        Map<String, Object> result = new HashMap<>();
        try {
            adminService.delAdmin(id);
            result.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result", "error");
            result.put("msg", e.getMessage());
        } finally {
            return result;
        }
    }

    /**
     * @Description: 更新管理员信息
     * @Param: 一个管理员对象
     * @return: 返回结果信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "updateAdmin")
    @ResponseBody
    public Map<String, Object> updateAdmin(Admin admin) {
        Map<String, Object> result = new HashMap<>();
        //验证前端传过来的值是否正确。有无非法参数，返回错误信息
        String msg = adminValidator(admin);
        if (msg != null) {
            result.put("error", msg);
            return result;
        }
        //todo 此处要更新一下逻辑
        //更新管理员信息信息，并得到返回的admin
        admin = adminService.updateAdmin(admin);
        //如果为空说明有相同的admin被使用
        if (admin == null) {
            result.put("error", "该身份证已有人使用！");
            return result;

        } else {
            List<Admin> list = new ArrayList<>();
            list.add(admin);
            result.put("data", list);
            return result;
        }
    }

    /**
     * @Description: 用于修改管理员密码
     * @Param: pwd:用户输入的当前的密码，newPwd：用户希望修改的新密码，request:用于取到Session
     * @return: 返回错误信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "changePwd")
    @ResponseBody
    public Map<String, Object> changePwd(String pwd, String newPwd, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Integer admid = (Integer) request.getSession().getAttribute("admid");
        pwd = getEncrypt(pwd);
        if (admid != null) {
            //得到正确的用户密码
            Admin admin = adminService.getAdminByAdmid(Integer.toString(admid));
            if (newPwd.equals(pwd)) {
                result.put("result", "新密码不能与旧密码相同！");
                return result;
            }
            //判断用户输入的当前密码是否和正确密码匹配
            if (pwd.equals(admin.getAdmpassword())) {
                admin.setAdmpassword(newPwd);
                //更新用户信息
                adminService.updateAdmin(admin);
                result.put("result", "success");
                //删除用户的Session对象
                request.getSession().removeAttribute("admid");
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

}
