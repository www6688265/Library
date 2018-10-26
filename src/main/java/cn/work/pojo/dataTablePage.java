package cn.work.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.core.io.ClassPathResource;

import java.lang.reflect.ParameterizedType;

public class dataTablePage {
    int draw;
    int start;
    int length;
    String order;
    Book book;
    Ticket ticket;
    Borrow borrow;

    public dataTablePage(JSONObject jsonParam, String type) {
        start = jsonParam.getInteger("start");
        length = jsonParam.getInteger("length");
        draw = jsonParam.getInteger("draw");
        JSONArray orderJson = jsonParam.getJSONArray("order");
        int column = orderJson.getJSONObject(0).getInteger("column");
        String orderName = jsonParam.getJSONArray("columns").getJSONObject(column).getString("data");
        String orderType = orderJson.getJSONObject(0).getString("dir");
        order = "`" + orderName + "`" + " " + orderType;
        JSONObject object = jsonParam.getJSONObject("search").getJSONObject("value");
        if (object != null && type.equals("Book"))
            book = JSON.parseObject(object.toString(), new TypeReference<Book>() {
            });
        if (object != null && type.equals("Ticket"))
            ticket = JSON.parseObject(object.toString(), new TypeReference<Ticket>() {
            });
        if (object != null && type.equals("Borrow"))
            borrow = JSON.parseObject(object.toString(), new TypeReference<Borrow>() {
            });
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
