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

/**
 * @Description: 罚单记录的业务层实现类
 * @Author: Aaron Ke
 */
@Service(value="TicketRecService")
public class TicketRecServiceImpl implements TicketRecService {
    @Resource
    TicketMapper ticketMapper;

    @Resource
    UserinfoMapper userinfoMapper;

    /**
     * @Description: 得到所有罚单记录
     * @Param: 无
     * @return: 罚单记录
     * @Author: Aaron Ke
     */
    @Override
    public List<TicketExt> getAllTicketRec() {
        return ticketMapper.getAllTicketRec();
    }

//    @Override
//    public void delTicketRec(int id) {
//        ticketMapper.deleteByPrimaryKey(id);
//    }

    /**
     * @Description: 处理用户罚单
     * @Param: id:用户编号
     * @return: 无
     * @Author: Aaron Ke
     */
    @Override
    public void dealTicketByUserid(String id) {
        //更新罚款处理状态
        ticketMapper.dealTicketByUserid(Integer.parseInt(id));
        //更新用户借书权限
        UserinfoExample example=new UserinfoExample();
        example.createCriteria().andUseridEqualTo(Integer.parseInt(id));
        Userinfo userinfo=new Userinfo();
        userinfo.setAccess(1);
        userinfoMapper.updateByExampleSelective(userinfo,example);
    }

    /**
     * @Description: 新增罚款记录
     * @Param: ticket:罚款信息
     * @return: 无
     * @Author: Aaron Ke
     */
    @Override
    public void addTicketRec(Ticket ticket) {
        ticketMapper.insertSelective(ticket);
    }

    /**
     * @Description: 得到用户的罚款信息
     * @Param: id：用户编号
     * @return: 罚款信息
     * @Author: Aaron Ke
     */
    @Override
    public List<TicketExt> getTicketByUserId(String id) {
        return ticketMapper.getTicketByUserId(Integer.parseInt(id));
    }


}
