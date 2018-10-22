package cn.work.service.Impl;

import cn.work.dao.TicketMapper;
import cn.work.dao.UserMapper;
import cn.work.dao.UserinfoMapper;
import cn.work.pojo.Ticket;
import cn.work.pojo.TicketExt;
import cn.work.pojo.Userinfo;
import cn.work.pojo.UserinfoExample;
import cn.work.service.TicketRecService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value="TicketRecService")
public class TicketRecServiceImpl implements TicketRecService {
    @Resource
    TicketMapper ticketMapper;

    @Resource
    UserinfoMapper userinfoMapper;

    @Override
    public List<TicketExt> getAllTicketRec() {
        return ticketMapper.getAllTicketRec();
    }

    @Override
    public void delTicketRec(int id) {
        ticketMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void dealTicketByUserid(String id) {
        ticketMapper.dealTicketByUserid(Integer.parseInt(id));
        UserinfoExample example=new UserinfoExample();
        example.createCriteria().andUseridEqualTo(Integer.parseInt(id));
        Userinfo userinfo=new Userinfo();
        userinfo.setAccess(1);
        userinfoMapper.updateByExampleSelective(userinfo,example);
    }

    @Override
    public void addTicketRec(Ticket ticket) {
        ticketMapper.insertSelective(ticket);
    }

    @Override
    public List<TicketExt> getTicketByUserId(String id) {
        return ticketMapper.getTicketByUserId(Integer.parseInt(id));
    }


}
