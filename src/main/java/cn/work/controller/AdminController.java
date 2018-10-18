package cn.work.controller;

import cn.work.pojo.Admin;
import cn.work.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static cn.work.util.SHAUtil.getEncrypt;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "login")
    @ResponseBody
    public Map<String,Object> login(Admin admin, HttpServletRequest request) {
        Map<String,Object> result=new HashMap<>();
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
    public String logOut(HttpServletRequest request)
    {
        Integer admid= (Integer) request.getSession().getAttribute("admid");
        if(admid!=null&&!admid.equals(""))
        {
            request.getSession().removeAttribute("admid");
            return "redirect:/login";
        }
        else
            return "redirect:/login";
    }
}
