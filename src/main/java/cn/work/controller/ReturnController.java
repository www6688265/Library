package cn.work.controller;


import cn.work.pojo.Book;
import cn.work.pojo.Borrow;
import cn.work.pojo.Ticket;
import cn.work.pojo.Userinfo;
import cn.work.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping(value = "/return")
public class ReturnController {
    @Autowired
    BARService barService;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    TicketRecService ticketService;

    @Autowired
    BorrowRecService borrowRecService;

    @RequestMapping(value = "userCheck")
    @ResponseBody
    public Map<String, Object> userCheck(String idcard) {
        Map<String, Object> result = new HashMap<>();
        Userinfo userinfo = userService.getUserByIDcard(idcard);
        String id = "";
        if (userinfo == null) {
            result.put("result", "2");
            return result;
        }
        id += Integer.toString(userinfo.getUserid());
        int maxReturnNum = userService.getNotReturnNum(id);
        if (maxReturnNum == 0) {
            result.put("result", "3");
            result.put("username", userinfo.getUsername());
            return result;
        }
        result.put("result", "1");
        result.put("username", userinfo.getUsername());
        result.put("userid", id);
        result.put("maxReturnNum", maxReturnNum);
        return result;
    }

    @RequestMapping(value = "bookCheck")
    @ResponseBody
    public Map<String, Object> bookCheck(String[] isbn) {
        Map<String, Object> result = new HashMap<>();
        List booklist = new ArrayList();
        List notFoundlist = new ArrayList();
        for (String id : isbn) {
            if (id != null && !id.equals("")) {
                Book book = bookService.getBookByISBN(id);
                if (book != null) {
                    booklist.add(book);
                } else {
                    notFoundlist.add(id);
                }
            }
        }
        if (notFoundlist.size() > 0) {
            result.put("result", "0");
            result.put("notFoundList", notFoundlist);
            return result;
        }
        result.put("result", "1");
        result.put("booklist", booklist);
        return result;
    }

    @RequestMapping(value = "returnBook")
    @ResponseBody
    public Map<String, Object> returnBook(String userid, String[] bookid) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        List<Borrow> borrowlist = borrowRecService.getNotReturnRec(userid);
        List<Borrow> returnList = new ArrayList();
        List orderNotFoundList = new ArrayList();
        List<Borrow> searchList = borrowlist;
        for (int i = 0; i < bookid.length; i++) {
            for (int j = 0; j < searchList.size(); j++) {
                int tempListBookid = searchList.get(j).getBookid();
                int tempBookid = Integer.parseInt(bookid[i]);
                if (tempBookid == tempListBookid) {
                    returnList.add(searchList.get(j));
                    searchList.remove(j);
                } else {
                    orderNotFoundList.add(i);
                }
            }
        }
        if (orderNotFoundList.size() > 0) {
            result.put("result", "0");
            result.put("orderNotFoundList", orderNotFoundList);
            return result;
        }
        double sum = 0;
        boolean hasTicket = false;
        for (Borrow borrow : returnList) {
            Ticket ticket = barService.returnBook(borrow);
            if (ticket != null) {
                sum+=ticket.getFee();
                hasTicket=true;
            }
        }
        if (hasTicket){
            result.put("hasTicket", hasTicket);
            result.put("fee", sum);
        }
        result.put("result", "success");
        return result;
    }
}
