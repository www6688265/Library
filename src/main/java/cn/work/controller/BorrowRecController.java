package cn.work.controller;

import cn.work.pojo.BorrowExt;
import cn.work.service.BorrowRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "delBorrowRec")
    @ResponseBody
    public Map delBorrowRec(String id){
        Map<String, Object> result = new HashMap<>();
        borrowService.delBorrowRec(id);
        result.put("result","success");
        return result;
    }


}
