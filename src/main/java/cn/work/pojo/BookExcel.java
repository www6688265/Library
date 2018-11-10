package cn.work.pojo;

import org.jeecgframework.poi.excel.annotation.Excel;

public class BookExcel implements java.io.Serializable {

    private String id;

    private Integer bookid;

    private Integer left;

    @Excel(name = "书名", orderNum = "0")
    private String bookname;

    public BookExcel(String id, Integer bookid, Integer left, String bookname, String type, String press, String brief, Integer total, String author, String isbn, Integer floor, Integer bookcase, Integer level) {
        this.id = id;
        this.bookid = bookid;
        this.left = left;
        this.bookname = bookname;
        this.type = type;
        this.press = press;
        this.brief = brief;
        this.total = total;
        this.author = author;
        this.isbn = isbn;
        this.floor = floor;
        this.bookcase = bookcase;
        this.level = level;
    }

    @Excel(name = "图书类型", orderNum = "1")
    private String type;

    @Excel(name = "出版社", orderNum = "2")
    private String press;

    @Excel(name = "图书简介", orderNum = "10")
    private String brief;

    @Excel(name = "图书总量", orderNum = "4")
    private Integer total;

//    @Excel(name = "封面图片", orderNum = "5",type = 2 ,width = 30 , height = 40,imageType = 1)
//    private byte[] pic;

    @Excel(name = "作者", orderNum = "3")
    private String author;

    @Excel(name = "图书ISBN编号", orderNum = "6")
    private String isbn;

    @Excel(name = "图书所在楼层", orderNum = "7")
    private Integer floor;


    @Excel(name = "图书所在书架", orderNum = "8")
    private Integer bookcase;

    @Excel(name = "图书所在书架层数", orderNum = "9")
    private Integer level;

    public BookExcel() {
    }

    @Override
    public String toString() {
        return "BookExcel{" +
                "bookid=" + bookid +
                ", left=" + left +
                ", bookname='" + bookname + '\'' +
                ", type='" + type + '\'' +
                ", press='" + press + '\'' +
                ", brief='" + brief + '\'' +
                ", total=" + total +
//                ", pic='" + pic + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", floor=" + floor +
                ", bookcase=" + bookcase +
                ", level=" + level +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

//    public byte[] getPic() {
//        return pic;
//    }
//
//    public void setPic(byte[] pic) {
//        this.pic = pic;
//    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getBookcase() {
        return bookcase;
    }

    public void setBookcase(Integer bookcase) {
        this.bookcase = bookcase;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
