package cn.work.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BorrowExt extends Borrow {
    String username;
    String bookname;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    Date Borrowtime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    Date Limittime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returntime;

    private Integer bookid;

    public BorrowExt() {
    }

    public BorrowExt(Integer userid) {
        super(userid);
    }

    public Date getReturntime() {
        return returntime;
    }

    public void setReturntime(Date returntime) {
        this.returntime = returntime;
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

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Date getBorrowtime() {
        return Borrowtime;
    }

    public void setBorrowtime(Date borrowtime) {
        Borrowtime = borrowtime;
    }

    public Date getLimittime() {
        return Limittime;
    }

    public void setLimittime(Date limittime) {
        Limittime = limittime;
    }
}
