package cn.work.controller;

import cn.work.pojo.Admin;
import cn.work.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.work.util.SHAUtil.getEncrypt;
import static cn.work.util.Validator.adminValidator;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("login")
    @ResponseBody
    public Map<String, Object> login(Admin admin, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String pwd = "";
        if (admin != null) {
            Admin va_admin = adminService.getAdminByCardId(admin.getIdcard());
            if (va_admin != null) {
                pwd = getEncrypt(admin.getAdmpassword());
            } else {
                result.put("result", "找不到用户名！");
                return result;
            }
            if (va_admin.getAdmpassword().equals(pwd)) {
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


    @RequestMapping("logOut")
    public String logOut(HttpServletRequest request) {
        Integer admid = (Integer) request.getSession().getAttribute("admid");
        if (admid != null && !admid.equals("")) {
            request.getSession().removeAttribute("admid");
            return "redirect:/login";
        } else
            return "redirect:/login";
    }

    @RequestMapping("getAllAdmins")
    @ResponseBody
    public Map<String, Object> getALlAdmins() {
        HashMap<String, Object> result = new HashMap<>();
        List<Admin> list = adminService.getAllAdmins();
        result.put("data", list);
        return result;
    }

    @RequestMapping(value = "addAdmin")
    @ResponseBody
    public Map<String, Object> addAdmin(Admin admin) {
        Map<String, Object> result = new HashMap<>();
        try {
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

    @RequestMapping(value = "updateAdmin")
    @ResponseBody
    public Map<String, Object> updateUser(Admin admin) {
        Map<String, Object> result = new HashMap<>();
        String msg = adminValidator(admin);
        if (msg != null) {
            result.put("error", msg);
            return result;
        }
        admin = adminService.updateAdmin(admin);
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

    @RequestMapping(value = "changePwd")
    @ResponseBody
    public Map<String, Object> changePwd(String pwd, String newPwd, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Integer admid = (Integer) request.getSession().getAttribute("admid");
        pwd = getEncrypt(pwd);
        if (admid != null) {
            Admin admin = adminService.getAdminByAdmid(Integer.toString(admid));
            if (pwd.equals(admin.getAdmpassword())) {
                admin.setAdmpassword(newPwd);
                adminService.updateAdmin(admin);
                result.put("result", "success");

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
