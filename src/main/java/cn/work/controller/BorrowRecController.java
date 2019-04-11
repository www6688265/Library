package cn.work.controller;

import cn.work.pojo.BorrowExt;
import cn.work.pojo.dto.BorrowRecQuery;
import cn.work.service.BorrowRecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description: 处理借书记录的控制器
 * @Author: Aaron Ke
 */
@Controller
@RequestMapping(value = "/borrowRec")
public class BorrowRecController {
    @Autowired
    BorrowRecService borrowService;

    /**
     * @Description: 得到所有借书记录
     * @Param: 无
     * @return: 借书记录结果集
     * @Author: Aaron Ke
     */
    @Deprecated
    @RequestMapping(value = "getAllBorrowRec")
    @ResponseBody
    public Map getAllBorrowRec() {
        Map<String, Object> result = new HashMap<>();
        //得到所有借书记录
        List<BorrowExt> borrowList = borrowService.getAllBorrowRec();
        result.put("data", borrowList);
        return result;
    }

    @Deprecated
    @RequestMapping(value = "getAllBorrowRecs")
    @ResponseBody
    public Map getAllBorrowRecs(@RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "10") int pageSize,
                                @RequestParam(defaultValue = "orderid asc") String order,
                                BorrowRecQuery borrowRecQuery) {
        Map<String, Object> result = new HashMap<>();
        List<BorrowExt> borrowList;
        if (borrowRecQuery != null) {
            PageHelper.startPage(pageNum, pageSize, order);
            borrowList = borrowService.getBorrowRecs(borrowRecQuery);
        } else {
            PageHelper.startPage(pageNum, pageSize, order);
            borrowList = borrowService.getAllBorrowRec();
        }
        //得到所有借书记录
        PageInfo pageInfo = new PageInfo<BorrowExt>(borrowList);
        result.put("data", pageInfo);
        return result;
    }


    /**
     * @Description: 得到用户没有归还的书籍记录
     * @Param: userid:用户编号
     * @return: 没有归还图书的结果集
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "getNotReturnList")
    @ResponseBody
    public Map<String, Object> getNotReturnList(String userid) {
        Map<String, Object> result = new HashMap<>();
        //得到用户未归还图书
        List<BorrowExt> borrowList = borrowService.getNotReturnRec(userid);
        result.put("data", borrowList);
        return result;
    }

    //废弃的方法
//    @RequestMapping(value = "delBorrowRec")
//    @ResponseBody
//    public Map delBorrowRec(String id){
//        Map<String, Object> result = new HashMap<>();
//        borrowService.delBorrowRec(id);
//        result.put("result","success");
//        return result;
//    }

    /**
     * @Description: 得到用户所有的借书记录
     * @Param: id: 用户编号
     * @return: 借书记录结果集
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "getBorrowRecByUserID")
    @ResponseBody
    public Map getBorrowRecByUserID(String id) {
        Map<String, Object> result = new HashMap<>();
        //得到用户所有的借书记录
        List<BorrowExt> borrowList = borrowService.getBorrowRecByUserID(id);
        result.put("data", borrowList);
        return result;
    }

    /**
     * @Description: 续借图书
     * @Param: order:借书记录的编号，bookid:图书的编号
     * @return: 操作结果
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "renew")
    @ResponseBody
    public Map renew(String orderid, String bookid) {
        Map<String, Object> result = new HashMap<>();
        //调用续借方法
        boolean done = borrowService.renewBorrow(orderid, bookid);
        if (done) {
            result.put("result", "success");
        } else {
            result.put("msg", "已达到最大续借时长");
        }
        return result;
    }
}
