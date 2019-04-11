package cn.work.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: libraryOs
 * @description:
 * @author: Aaron Ke
 * @create: 2019-03-31 21:13
 **/
public class BorrowRecQuery {
    private Integer orderid;
    private String username;
    private String bookname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date b_StartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date b_EndTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date r_StartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date r_EndTime;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Date getB_StartTime() {
        return b_StartTime;
    }

    public void setB_StartTime(Date b_StartTime) {
        this.b_StartTime = b_StartTime;
    }

    public Date getB_EndTime() {
        return b_EndTime;
    }

    public void setB_EndTime(Date b_EndTime) {
        this.b_EndTime = b_EndTime;
    }

    public Date getR_StartTime() {
        return r_StartTime;
    }

    public void setR_StartTime(Date r_StartTime) {
        this.r_StartTime = r_StartTime;
    }

    public Date getR_EndTime() {
        return r_EndTime;
    }

    public void setR_EndTime(Date r_EndTime) {
        this.r_EndTime = r_EndTime;
    }
}
