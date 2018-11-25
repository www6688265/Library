package cn.work.pojo;


import java.util.Arrays;

public class BookExcel implements java.io.Serializable {

    private Integer bookid;

    private String bookname;

    private String booktypeid;

    private String press;

    private String brief;

    private Integer total;

    private String pic;

    private String author;

    private String isbn;

    private Integer floor;

    private Integer bookcase;

    private Integer layer;

    private Integer display;


    public BookExcel(String bookname, String isbn, String booktype, String press, String author, Integer total, Integer floor, Integer bookcase, Integer layer, String brief) {
        this.bookname = bookname;
        this.booktypeid = booktype;
        this.press = press;
        this.brief = brief;
        this.total = total;
        this.author = author;
        this.isbn = isbn;
        this.floor = floor;
        this.bookcase = bookcase;
        this.layer = layer;
    }

    public String getBooktypeid() {
        return booktypeid;
    }

    public void setBooktypeid(String booktypeid) {
        this.booktypeid = booktypeid;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }


    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

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

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }
}
