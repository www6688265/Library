package cn.work.controller;


import cn.work.Enum.UserStatusEnum;
import cn.work.pojo.*;
import cn.work.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;

/**
 * @Description: 还书的控制器
 * @Author: Aaron Ke
 */
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

    /**
     * @Description: 检查用户状态
     * @Param:
     * @return:
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "userCheck")
    @ResponseBody
    public Map<String, Object> userCheck(String idcard) {
        idcard = idcard.trim();
        Map<String, Object> result = new HashMap<>();
        //得到用户信息
        Userinfo userinfo = userService.getUserByIDcard(idcard);
        String id = "";
        if (userinfo == null) {
            result.put("result", UserStatusEnum.NOTEXIST.getCode());
            return result;
        }
        //得到用户编号
        id += Integer.toString(userinfo.getUserid());
        //得到用户借书数量
        int maxReturnNum = userService.getNotReturnNum(id);
        //判断是否为0
        if (maxReturnNum == 0) {
            result.put("result", UserStatusEnum.NONEEDTORETURN.getCode());
            result.put("username", userinfo.getUsername());
            return result;
        }
        result.put("result", UserStatusEnum.NORMAL.getCode());
        result.put("username", userinfo.getUsername());
        result.put("userid", id);
        return result;
    }


    /**
     * @Description: 还书
     * @Param: returnList[] :借书的记录
     * @return: 操作结果集
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "returnBook")
    @ResponseBody
    public Map<String, Object> returnBook(@RequestBody BorrowExt[] returnList) throws ParseException {
        Map<String, Object> result = new HashMap<>();
        //判断是否有选择借书记录
        if (returnList.length == 0) {
            result.put("error", "服务器忙，请稍候重试！");
            return result;
        }
        //得到用户编号
        int userid = returnList[0].getUserid();
        double sum = 0;
        //标记此次借书是否是罚款产生
        boolean hasTicket = false;
        //标记用户的借书权限
        boolean access = true;
        for (BorrowExt borrow : returnList) {
            Ticket ticket = null;
            try {
                //调用还书方法
                ticket = barService.returnBook(borrow);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("error", "服务器忙，请稍候重试！");
                return result;
            }
            //如果产生罚单，进行累计
            if (ticket != null) {
                sum += ticket.getFee().doubleValue();
                hasTicket = true;
            }
        }

        //判断此次是否产生罚单
        if (hasTicket) {
            access = false;
            result.put("hasTicket", true);
            result.put("fee", sum);
        }

        //判断用户是否有过期未还图书
        int overDueNum = userService.getOverDueNum(Integer.toString(userid));
        if (overDueNum > 0) {
            access = false;
        }
        //设置用户的借书权限
        userService.setUserAccess(Integer.toString(userid), access);
        result.put("result", "success");
        return result;
    }
}
