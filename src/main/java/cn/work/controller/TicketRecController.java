package cn.work.controller;

import cn.work.pojo.BorrowExt;
import cn.work.pojo.TicketExt;
import cn.work.pojo.dto.TicketQuery;
import cn.work.service.TicketRecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Deprecated
    @RequestMapping(value = "getAllTicketRec")
    @ResponseBody
    public Map<String, Object> getAllTicketRec() {
        Map<String, Object> result = new HashMap<>();
        //得到所有罚单信息
        List<TicketExt> list = ticketRecService.getAllTicketRec();
        result.put("data", list);
        return result;
    }

    @RequestMapping(value = "getAllTicketRecs")
    @ResponseBody
    public Map<String, Object> getAllTicketRecs(@RequestParam(defaultValue = "1") int pageNum,
                                                @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "ticketid asc") String order,
                                                TicketQuery ticketQuery) {
        Map<String, Object> result = new HashMap<>();
        List<TicketExt> list;
        if (ticketQuery != null) {
            PageHelper.startPage(pageNum, pageSize, order);
            list = ticketRecService.getTicketRecs(ticketQuery);
        } else {
            //得到所有罚单信息
            PageHelper.startPage(pageNum, pageSize, order);
            list = ticketRecService.getAllTicketRec();
        }
        PageInfo pageInfo = new PageInfo<>(list);
        result.put("data", pageInfo);
        return result;
    }


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
