package cn.work.pojo;

public class Ticket {
    private Integer ticketid;

    private Integer orderid;

    private Integer overduetime;

    private Double fee;

    private Integer status;

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getOverduetime() {
        return overduetime;
    }

    public void setOverduetime(Integer overduetime) {
        this.overduetime = overduetime;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}