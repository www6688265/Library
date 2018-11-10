package cn.work.controller;

import cn.work.pojo.BorrowExt;
import cn.work.service.BorrowRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/borrowRec")
public class BorrowRecController {
    @Autowired
    BorrowRecService borrowService;

    @RequestMapping(value = "getAllBorrowRec")
    @ResponseBody
    public Map getAllBorrowRec() {
        Map<String, Object> result = new HashMap<>();
        List<BorrowExt> borrowList = borrowService.getAllBorrowRec();
        result.put("data", borrowList);
        return result;
    }

    @RequestMapping(value = "getNotReturnList")
    @ResponseBody
    public Map<String, Object> getNotReturnList(String userid) {
        Map<String, Object> result = new HashMap<>();
        List<BorrowExt> borrowList = borrowService.getNotReturnRec(userid);
        result.put("data", borrowList);
        return result;
    }

    @RequestMapping(value = "delBorrowRec")
    @ResponseBody
    public Map delBorrowRec(String id){
        Map<String, Object> result = new HashMap<>();
        borrowService.delBorrowRec(id);
        result.put("result","success");
        return result;
    }

    @RequestMapping(value = "getBorrowRecByUserID")
    @ResponseBody
    public Map getBorrowRecByUserID(String id) {
        Map<String, Object> result = new HashMap<>();
        List<BorrowExt> borrowList = borrowService.getBorrowRecByUserID(id);
        result.put("data", borrowList);
        return result;
    }

    @RequestMapping(value = "renew")
    @ResponseBody
    public Map renew(String orderid, String bookid) {
        Map<String, Object> result = new HashMap<>();
        boolean done = borrowService.renewBorrow(orderid, bookid);
        if (done) {
            result.put("result", "success");
        } else {
            result.put("msg", "已达到最大续借时长");
        }
        return result;
    }
}
