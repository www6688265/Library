package cn.work.controller;


import cn.work.pojo.Book;
import cn.work.pojo.Borrow;
import cn.work.pojo.BorrowExt;
import cn.work.pojo.Userinfo;
import cn.work.service.BARService;
import cn.work.service.BookService;
import cn.work.service.TicketRecService;
import cn.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static cn.work.spring.config.LibraryConfig.limitBorrowNum;

@Controller
@RequestMapping(value = "/borrow")
public class BorrowController {
    @Autowired
    BARService barService;

    @Autowired
    UserService userService;

    @Autowired
    TicketRecService ticketService;

    @Autowired
    BookService bookService;

    @RequestMapping(value = "userCheck")
    @ResponseBody
    public Map<String, Object> userCheck(String idcard) {
        idcard = idcard.trim();
        Map<String, Object> result = new HashMap<>();
        Userinfo userinfo = userService.getUserByIDcard(idcard);
        String id = "";
        if (userinfo == null) {
            result.put("result", "4");
            return result;
        }
        id += Integer.toString(userinfo.getUserid());
        if (userinfo.getAccess() == 0) {
            int borrowNum = userService.getNotReturnNum(id);
            if (borrowNum == limitBorrowNum) {
                result.put("result", "2");
                result.put("username", userinfo.getUsername());
                return result;
            }
            int overDueNum = userService.getOverDueNum(id);
            if (overDueNum > 0) {
                result.put("result", "5");
                result.put("username", userinfo.getUsername());
                return result;
            }
            int TicketNum = userService.getTicketNum(id);
            if (TicketNum > 0) {
                result.put("result", "3");
                result.put("userid", id);
                result.put("username", userinfo.getUsername());
                double fee = userService.getUserFee(id);
                result.put("fee", fee);
                return result;
            }
        }
        int notReturnNum = userService.getNotReturnNum(id);
        int maxBorrowNum = limitBorrowNum - notReturnNum;
        result.put("result", "1");
        result.put("username", userinfo.getUsername());
        result.put("userid", id);
        result.put("maxBorrowNum", maxBorrowNum);
        return result;
    }

    @RequestMapping(value = "dealTicket")
    @ResponseBody
    public Map<String, Object> dealTicket(String userid) {
        userid = userid.trim();
        Map<String, Object> result = new HashMap<>();
        ticketService.dealTicketByUserid(userid);
        result.put("result", "success");
        return result;
    }

    @RequestMapping(value = "bookCheck")
    @ResponseBody
    public Map<String, Object> bookCheck(String[] isbn) {
        Map<String, Object> result = new HashMap<>();
        List<Book> bookList = new ArrayList<>();
        List<String> notFoundList = new ArrayList<>();
        for (String id : isbn) {
            id = id.trim();
            if (id != null && !id.equals("")) {
                Book book = bookService.getBookByISBN(id);
                if (book != null) {
                    bookList.add(book);
                } else {
                    notFoundList.add(id);
                }
            }
        }
        if (notFoundList.size() > 0) {
            result.put("result", "0");
            result.put("notFoundList", notFoundList);
            return result;
        }
        result.put("result", "1");
        result.put("booklist", bookList);
        return result;
    }

    @RequestMapping(value = "borrowBook")
    @ResponseBody
    public Map<String, Object> borrowBook(String userid, String[] bookid) {
        Map<String, Object> result = new HashMap<>();
        Set<String> bookList = new HashSet<>();
        for (String id : bookid) {
            bookList.add(id);
        }
        if (bookList.size() < bookid.length) {
            result.put("error", "不能借两本相同的图书！");
            return result;
        }

        userid = userid.trim();
        int NotReturnNum = 0;
        try {
            NotReturnNum = barService.borrowBook(userid, bookid);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", "服务器忙，请稍候重试");
            return result;
        }
        result.put("borrowNum", NotReturnNum);
        result.put("result", "success");
        return result;
    }
}