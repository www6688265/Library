package cn.work.controller;


import cn.work.pojo.*;
import cn.work.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
        idcard = idcard.trim();
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


    @RequestMapping(value = "returnBook")
    @ResponseBody
    public Map<String, Object> returnBook(@RequestBody BorrowExt[] returnList) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        if (returnList.length == 0) {
            result.put("error", "服务器忙，请稍候重试！");
            return result;
        }
        int userid = returnList[0].getUserid();
        double sum = 0;
        boolean hasTicket = false;
        boolean access = true;
        for (BorrowExt borrow : returnList) {
            Ticket ticket = null;
            try {
                ticket = barService.returnBook(borrow);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("error", "服务器忙，请稍候重试！");
                return result;
            }
            if (ticket != null) {
                sum += ticket.getFee().doubleValue();
                hasTicket = true;
            }
        }

        if (hasTicket) {
            access = false;
            result.put("hasTicket", hasTicket);
            result.put("fee", sum);
        }

        int overDueNum = userService.getOverDueNum(Integer.toString(userid));
        if (overDueNum > 0) {
            access = false;
        }
        userService.setUserAccess(Integer.toString(userid), access);
        result.put("result", "success");
        return result;
    }
}
