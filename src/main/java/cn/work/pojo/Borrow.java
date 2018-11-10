package cn.work.pojo;

public class Borrow {
    private Integer orderid;

    private Integer userid;

    public Borrow(Integer userid) {
        this.userid = userid;
    }

    public Borrow() {
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}