package cn.work.pojo.dto;

import java.util.Date;

/**
 * @program: libraryOs
 * @description:
 * @author: Aaron Ke
 * @create: 2019-03-31 22:48
 **/
public class TicketQuery {
    private Integer ticketid;
    private String username;
    private String bookname;
    private Date r_StartTime;
    private Date r_EndTime;
    private Date d_StartTime;
    private Date d_EndTime;
    private Integer ticketStatus;

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
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

    public Date getD_StartTime() {
        return d_StartTime;
    }

    public void setD_StartTime(Date d_StartTime) {
        this.d_StartTime = d_StartTime;
    }

    public Date getD_EndTime() {
        return d_EndTime;
    }

    public void setD_EndTime(Date d_EndTime) {
        this.d_EndTime = d_EndTime;
    }

    public Integer getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
