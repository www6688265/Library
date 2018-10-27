package cn.work.util;

import cn.work.pojo.Admin;
import cn.work.pojo.Book;
import cn.work.pojo.Userinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Validator {
    public static final String username_required_error = "姓名不能为空";
    public static final String username_username_error = "只能输入中文或者英文";
    public static final String username_minlength_error = "至少两个字";
    public static final String username_CharAndSum_error = "只能为中文或者英文";
    public static final String idcard_required_error = "身份证号码不能为空";
    public static final String idcard_idcard_error = "身份证格式不正确";
    public static final String sex_required_error = "性别不能为空";
    public static final String sex_sex_error = "只能为男或女";
    public static final String usertele_required_error = "手机号码不能为空";
    public static final String usertele_phon_error = "手机号码格式不正确";
    public static final String access_required_error = "权限不能为空";
    public static final String access_access_error = "权限只能为0或1";
    public static final String pwd_required_error = "密码不能为空";
    public static final String pwd_minlength_error = "至少为8位";
    public static final String required_error = "不能为空";
    public static final String maxlength_error = "最多为";


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
            else {
                if (!Pattern.matches("^男|女$",
                        sex)) {
                    return sex_sex_error;
                }
            }

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

    public static Map<String, Object> bookValidator(Book book) {
        Map<String, Object> error = new HashMap<>();
        List<Map> errors = new ArrayList<>();
        Map<String, Object> booknameError = new HashMap<>();
        Map<String, Object> pressError = new HashMap<>();
        Map<String, Object> totalError = new HashMap<>();
        Map<String, Object> isbnError = new HashMap<>();
        Map<String, Object> leftError = new HashMap<>();
        String bookname = book.getBookname();
        String press = book.getPress();
        Integer total = book.getTotal();
        String isbn = book.getIsbn();
        Integer left = book.getLeft();
        if (bookname != null) {
            if (bookname.equals("")) {
                booknameError.put("name", "bookname");
                booknameError.put("status", required_error);
                errors.add(booknameError);
            } else {
                if (bookname.length() > 50) {
                    booknameError.put("name", "bookname");
                    booknameError.put("status", maxlength_error + "50位");
                    errors.add(booknameError);
                }
            }
        }
        if (press != null) {
            if (press.equals("")) {
                pressError.put("name", "press");
                pressError.put("status", required_error);
                errors.add(booknameError);
            } else {
                if (press.length() > 50) {
                    pressError.put("name", "press");
                    pressError.put("status", maxlength_error + "50位");
                    errors.add(booknameError);
                }
            }
        }
        if (isbn != null) {
            if (isbn.equals("")) {
                isbnError.put("name", "isbn");
                isbnError.put("status", required_error);
                errors.add(isbnError);
            } else {
                if (isbn.length() > 20) {
                    isbnError.put("name", "isbn");
                    isbnError.put("status", maxlength_error + "20位");
                    errors.add(isbnError);
                }
            }
        }
        if (total != null) {
            if (total.equals("")) {
                totalError.put("name", "total");
                totalError.put("status", required_error);
                errors.add(totalError);
            } else {
                if (total.toString().length() > 4) {
                    totalError.put("name", "total");
                    totalError.put("status", maxlength_error + "4位");
                    errors.add(totalError);
                }
            }
        }
        if (left != null) {
            if (left.equals("")) {
                leftError.put("name", "left");
                leftError.put("status", required_error);
                errors.add(leftError);
            } else {
                if (left.toString().length() > 4) {
                    leftError.put("name", "left");
                    leftError.put("status", maxlength_error + "4位");
                    errors.add(leftError);
                }
            }
        }
        if (errors.size() > 0) {
            error.put("fieldErrors", errors);
            return error;
        } else
            return null;
    }
}
