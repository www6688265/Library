package cn.work.controller;

import cn.work.pojo.Book;
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

/**
 * @Description: 管理借书的类
 * @Author: Aaron Ke
 */
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

    /**
     * @Description: 检查用户信息
     * 注：返回信息1：表示用户状态正常，2：用户已经达到最大借书数量，3：用户有未处理得罚单，4：用户不存在，5：有过期未还图书
     * @Param: idcard:用户的身份证
     * @return: 结果信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "userCheck")
    @ResponseBody
    public Map<String, Object> userCheck(String idcard) {
        idcard = idcard.trim();
        Map<String, Object> result = new HashMap<>();
        //得到用户的信息
        Userinfo userinfo = userService.getUserByIDcard(idcard);
        String id = "";
        //如果为空，返回用户不存在代码4
        if (userinfo == null) {
            result.put("result", "4");
            return result;
        }
        id += Integer.toString(userinfo.getUserid());
        //检查用户的借书权限，0表示不可以借书，1表示可以结束
        if (userinfo.getAccess() == 0) {
            //得到用户未归还的图书数量
            int borrowNum = userService.getNotReturnNum(id);
            //判断是否等于最大借书数量
            if (borrowNum == limitBorrowNum) {
                result.put("result", "2");
                result.put("username", userinfo.getUsername());
                return result;
            }
            //得到过期未还数量
            int overDueNum = userService.getOverDueNum(id);
            if (overDueNum > 0) {
                result.put("result", "5");
                result.put("username", userinfo.getUsername());
                return result;
            }
            //得到罚单数量
            int TicketNum = userService.getTicketNum(id);
            if (TicketNum > 0) {
                result.put("result", "3");
                result.put("userid", id);
                result.put("username", userinfo.getUsername());
                //得到罚款的金额
                double fee = userService.getUserFee(id);
                result.put("fee", fee);
                return result;
            }
        }
        //得到用户借书数量
        int notReturnNum = userService.getNotReturnNum(id);
        //得到当前用户最大借书数量
        int maxBorrowNum = limitBorrowNum - notReturnNum;
        result.put("result", "1");
        result.put("username", userinfo.getUsername());
        result.put("userid", id);
        result.put("maxBorrowNum", maxBorrowNum);
        return result;
    }

    /**
     * @Description: 处理用户罚款信息
     * @Param: userid：用户的编号
     * @return: 处理状态
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "dealTicket")
    @ResponseBody
    public Map<String, Object> dealTicket(String userid) {
        //处理userid的前后空格
        userid = userid.trim();
        Map<String, Object> result = new HashMap<>();
        //更新罚单状态
        ticketService.dealTicketByUserid(userid);
        result.put("result", "success");
        return result;
    }

    /**
     * @Description: 检查图书是否存在
     * 注：返回信息1：表示存在此图书，2:表示不存在此图书
     * @Param: String []isbn: 表示所要借书的ISBN编号，为一个数组
     * @return: 结果信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "bookCheck")
    @ResponseBody
    public Map<String, Object> bookCheck(String[] isbn) {
        Map<String, Object> result = new HashMap<>();
        List<Book> bookList = new ArrayList<>();
        List<String> notFoundList = new ArrayList<>();
        for (String id : isbn) {
            id = id.trim();
            if (id != null && !id.equals("")) {
                //查找是否存在此书
                Book book = bookService.getBookByISBN(id);
                if (book != null) {
                    bookList.add(book);
                } else {
                    //如果找不到，加入到找不到图书的数组
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

    /**
     * @Description: 处理借书方法
     * @Param: userid:用户的id, bookid[]:所要借的图书id
     * @return: 结果信息
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "borrowBook")
    @ResponseBody
    public Map<String, Object> borrowBook(String userid, String[] bookid) {
        Map<String, Object> result = new HashMap<>();

        //第一种写法
//        for (String id : bookid) {
//            bookList.add(id);
//        }
        //第二种写法
        //        bookList.addAll(Arrays.asList(bookid));

        //第三种，更好的性能。原因如下。

        //使用Set数组来检查是否存在有重复的编号
        Set<String> bookList = new HashSet<>(Arrays.asList(bookid));
        //Reports Collection.addAll() and Map.putAll() calls after instantiation of a collection using a constructor call without arguments.
        // Such constructs can be replaced with a single call to a parametrized constructor which simplifies code. Also for some collections
        // the replacement might be more performant. For example:
        //  Set<String> set = new HashSet<>();
        //  set.addAll(Arrays.asList("alpha", "beta", "gamma"));
        //can be replaced with:
        //  Set<String> set = new HashSet<>(Arrays.asList("alpha", "beta", "gamma"));


        if (bookList.size() < bookid.length) {
            result.put("error", "不能借两本相同的图书！");
            return result;
        }

        userid = userid.trim();
        int NotReturnNum = 0;
        try {
            //调用借书方法
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