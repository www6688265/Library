package cn.work.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
public class BorrowExt extends Borrow {
    String username;
    String bookname;

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
}
