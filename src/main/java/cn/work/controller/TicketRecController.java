package cn.work.controller;

import cn.work.pojo.TicketExt;
import cn.work.service.TicketRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 处理罚单记录的控制器
 * @Author: Aaron Ke
 */
@Controller
@RequestMapping(value = "/ticketRec")
public class TicketRecController {

    @Autowired
    TicketRecService ticketRecService;

    /**
     * @Description: 得到所有罚单信息
     * @Param: 无
     * @return: 罚单结果集
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "getAllTicketRec")
    @ResponseBody
    public Map<String, Object> getAllTicketRec() {
        Map<String, Object> result = new HashMap<>();
        //得到所有罚单信息
        List<TicketExt> list = ticketRecService.getAllTicketRec();
        result.put("data", list);
        return result;
    }

//      废弃的方法
//    @RequestMapping(value = "delTicketRec")
//    @ResponseBody
//    public Map<String, Object> delTicketRec(String id) {
//        Map<String, Object> result = new HashMap<>();
//        ticketRecService.delTicketRec(Integer.parseInt(id));
//        result.put("result", "success");
//        return result;
//
//    }

    /**
     * @Description: 得到用户的罚单信息
     * @Param: userid:用户编号
     * @return: 罚单信息结果集
     * @Author: Aaron Ke
     */
    @RequestMapping(value = "getTicket")
    @ResponseBody
    public Map<String, Object> getTicket(String userid) {
        Map<String, Object> result = new HashMap<>();
        //得到用户的罚单信息
        List<TicketExt> list = ticketRecService.getTicketByUserId(userid);
        result.put("data", list);
        return result;
    }
}
