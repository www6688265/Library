package cn.work.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
public class Borrowedbooks extends BorrowedbooksKey {
    private Date returntime;

    private Date borrowtime;

    private Date limittime;

    public Date getReturntime() {
        return returntime;
    }

    public void setReturntime(Date returntime) {
        this.returntime = returntime;
    }

    public Date getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(Date borrowtime) {
        this.borrowtime = borrowtime;
    }

    public Date getLimittime() {
        return limittime;
    }

    public void setLimittime(Date limittime) {
        this.limittime = limittime;
    }
}