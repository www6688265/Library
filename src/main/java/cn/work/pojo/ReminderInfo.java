package cn.work.pojo;

import java.util.Date;

/**
 * @program: libraryOs
 * @description: 存储需要超期提醒的用户信息
 * @author: Aaron Ke
 * @create: 2018-12-01 11:26
 **/
public class ReminderInfo {
    private String username;

    private String usertele;

    private int bookNum;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertele() {
        return usertele;
    }

    public void setUsertele(String usertele) {
        this.usertele = usertele;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }
}
