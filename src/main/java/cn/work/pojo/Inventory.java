package cn.work.pojo;

public class Inventory {
    private Integer bookid;

    private Integer leftNum;

    public Inventory(Integer bookid, Integer leftNum) {
        this.bookid = bookid;
        this.leftNum = leftNum;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }
}