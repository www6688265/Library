package cn.work.util;

import cn.work.pojo.Admin;
import cn.work.pojo.Userinfo;

import java.util.regex.Pattern;

public class Validator {
    public static final String username_required_error = "姓名不能为空";
    public static final String username_username_error = "只能输入中文或者英文";
    public static final String username_minlength_error = "至少两个字";
    public static final String username_CharAndSum_error = "只能为中文或者英文";
    public static final String idcard_required_error = "身份证号码不能为空";
    public static final String idcard_idcard_error = "身份证格式不正确";
    public static final String sex_required_error = "性别不能为空";
    public static final String usertele_required_error = "手机号码不能为空";
    public static final String usertele_phon_error = "手机号码格式不正确";
    public static final String access_required_error = "权限不能为空";
    public static final String access_access_error = "权限只能为0或1";
    public static final String pwd_required_error = "密码不能为空";
    public static final String pwd_minlength_error = "至少为8位";


    public static String userValidator(Userinfo userinfo) {
        String username = userinfo.getUsername();
        String idcard = userinfo.getIdcard();
        String sex = userinfo.getSex();
        String usertele = userinfo.getUsertele();
        Integer access = userinfo.getAccess();
        if (username != null) {
            if (username.equals(""))
                return username_required_error;
            else {
                if (username.length() < 2)
                    return username_minlength_error;
                if (!Pattern.matches("^[\\u4E00-\\u9FA5A-Za-z]+$", username)) {
                    return username_username_error;
                }
            }
        }

        if (idcard != null) {
            if (idcard.equals(""))
                return idcard_required_error;
            else {
                if (!Pattern.matches("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$",
                        idcard)) {
                    return idcard_idcard_error;
                }
            }
        }
        if (sex != null) {
            if (sex.equals(""))
                return sex_required_error;

        }
        if (usertele != null) {
            if (usertele.equals(""))
                return usertele_required_error;
            else {
                if (!Pattern.matches("^1[34578]\\d{9}$",
                        usertele)) {
                    return usertele_phon_error;
                }
            }
        }
        if (access != null) {
            if (!Pattern.matches("^1|0$",
                    access.toString())) {
                return access_access_error;
            }
        }
        return null;
    }

    public static String adminValidator(Admin admin) {
        String username = admin.getIdcard();
        String pwd = admin.getAdmpassword();
        Integer access = admin.getAdmright();
        if (username != null) {
            if (username.equals(""))
                return username_required_error;
            else {
                if (!Pattern.matches("^[0-9a-zA_Z]+$", username)) {
                    return username_CharAndSum_error;
                }
            }
        }
        if (pwd != null) {
            if (pwd.equals(""))
                return pwd_required_error;
            else {
                if (pwd.length() < 8)
                    return pwd_minlength_error;
            }
        }
        if (access != null) {
            if (!Pattern.matches("^1|0$",
                    access.toString())) {
                return access_access_error;
            }
        }
        return null;
    }
}
