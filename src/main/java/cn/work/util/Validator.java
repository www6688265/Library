package cn.work.util;

import cn.work.dao.BooktypeMapper;
import cn.work.pojo.*;
import cn.work.pojo.Error;

import javax.annotation.Resource;
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
    public static final String digtal_error = "只能为正整数";


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
                if (!Pattern.matches("^[0-9a-zA-Z]+$", username)) {
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

    public static Map<String, Object> bookValidator(BookExt book) {
        Error error = new Error();
        String bookname = book.getBookname();
        String press = book.getPress();
        Integer total = book.getTotal();
        Integer left_num = book.getLeft_num();
        String isbn = book.getIsbn();
        if (bookname != null) {
            if (bookname.equals("")) {
                error.addError("bookname", required_error);
            } else {
                if (bookname.length() > 50) {
                    error.addError("bookname", maxlength_error + "50位");
                }
            }
        }
        if (press != null) {
            if (press.equals("")) {
                error.addError("press", required_error);
            } else {
                if (press.length() > 50) {
                    error.addError("press", maxlength_error + "50位");
                }
            }
        }
        if (isbn != null) {
            if (isbn.equals("")) {
                error.addError("isbn", required_error);
            } else if (isbn.length() > 20) {
                error.addError("isbn", maxlength_error + "20位");
            } else if (!Pattern.matches("^\\d+$", isbn)) {
                error.addError("isbn", digtal_error);
            }
        }

        if (total != null) {
            if (Pattern.matches("^//d+$", Integer.toString(total)) || total < 0) {
                error.addError("total", digtal_error);
            } else if (total.toString().length() > 4) {
                error.addError("total", maxlength_error + "4位");
            }
        } else {
            error.addError("total", required_error);
        }
        if (left_num != null) {
            if (Pattern.matches("^//d+$", Integer.toString(left_num)) || left_num < 0) {
                error.addError("left_num", digtal_error);
            } else if (total.toString().length() > 4) {
                error.addError("left_num", maxlength_error + "4位");
            }
        } else {
            error.addError("left_num", required_error);
        }
        if (error.getCount() > 0) {
            return error.getFieldErrors();
        } else
            return null;
    }

    public static boolean bookValidator(BookExcel book, int row, ExcelResult result, Map<String, String> booktypeMap) {
        String bookname = book.getBookname();
        String press = book.getPress();
        Integer total = book.getTotal();
        String isbn = book.getIsbn();
        Integer floor = book.getFloor();
        Integer bookcase = book.getBookcase();
        Integer layer = book.getLayer();
        String booktype = book.getBooktypeid();
        boolean isPass = true;

        if (bookname == null || bookname.equals("")) {
            result.addRowError(row, "图书名" + required_error);
            isPass = false;
        } else {
            if (bookname.length() > 50) {
                result.addRowError(row, "图书名" + maxlength_error + "50位");
                isPass = false;
            }
        }

        if (press == null || press.equals("")) {
            result.addRowError(row, "出版社" + required_error);
            isPass = false;
        } else {
            if (press.length() > 50) {
                result.addRowError(row, "出版社" + maxlength_error + "50位");
                isPass = false;
            }
        }

        if (booktype == null || booktype.equals("")) {
            result.addRowError(row, "图书类型" + required_error);
            isPass = false;
        } else {
            if (booktypeMap.get(booktype) == null) {
                result.addRowError(row, "图书类型" + "不正确");
                isPass = false;
            } else {
                book.setBooktypeid(booktypeMap.get(booktype));
            }
        }

        if (isbn == null || isbn.equals("")) {
            result.addRowError(row, "ISBN编号" + required_error);
            isPass = false;
        } else {
            if (isbn.length() > 20) {
                result.addRowError(row, "ISBN编号" + maxlength_error + "20位");
                isPass = false;
            }
            if (!Pattern.matches("^\\d+$", isbn)) {
                result.addRowError(row, "ISBN编号" + digtal_error);
                isPass = false;
            }
        }

        if (total != null) {
            if (Pattern.matches("^//d+$", Integer.toString(total)) || total < 0) {
                result.addRowError(row, "库存" + digtal_error);
                isPass = false;
            } else if (total.toString().length() > 4) {
                result.addRowError(row, "库存" + maxlength_error + "4位");
                isPass = false;
            }
        } else {
            result.addRowError(row, "库存" + required_error);
            isPass = false;
        }

        if (floor != null) {
            if (Pattern.matches("^//d+$", Integer.toString(floor)) || floor < 0) {
                result.addRowError(row, "楼层" + digtal_error);
                isPass = false;
            } else if (floor.toString().length() > 2) {
                result.addRowError(row, "楼层" + maxlength_error + "2位");
                isPass = false;
            }
        } else {
            result.addRowError(row, "楼层" + required_error);
            isPass = false;
        }

        if (bookcase != null) {
            if (Pattern.matches("^//d+$", Integer.toString(bookcase)) || bookcase < 0) {
                result.addRowError(row, "书架数" + digtal_error);
                isPass = false;
            } else if (bookcase.toString().length() > 2) {
                result.addRowError(row, "书架数" + maxlength_error + "2位");
                isPass = false;
            }
        } else {
            result.addRowError(row, "书架数" + required_error);
            isPass = false;
        }

        if (layer != null) {
            if (Pattern.matches("^//d+$", Integer.toString(layer)) || layer < 0) {
                result.addRowError(row, "书架层数" + digtal_error);
                isPass = false;
            } else if (layer.toString().length() > 2) {
                result.addRowError(row, "书架层数" + maxlength_error + "2位");
                isPass = false;
            }
        } else {
            result.addRowError(row, "书架层数" + required_error);
            isPass = false;
        }
        return isPass;
    }
}
