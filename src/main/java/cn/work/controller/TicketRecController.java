package cn.work.controller;


import cn.work.pojo.BorrowExt;
import cn.work.pojo.TicketExt;
import cn.work.service.TicketRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/ticketRec")
public class TicketRecController {

    @Autowired
    TicketRecService ticketRecService;

    @RequestMapping(value="getAllTicketRec")
    @ResponseBody
    public Map<String,Object> getAllTicketRec(){
        Map<String, Object> result = new HashMap<>();
        List<TicketExt> list=  ticketRecService.getAllTicketRec();
        result.put("data", list);
        return result;
    }

    @RequestMapping(value="delTicketRec")
    @ResponseBody
    public Map<String,Object> delTicketRec(String id){
        Map<String, Object> result = new HashMap<>();
        ticketRecService.delTicketRec(Integer.parseInt(id));
        result.put("result","success");
        return result;

    }
}
