package cn.work.dao;

import cn.work.pojo.Ticket;
import cn.work.pojo.TicketExample;
import cn.work.pojo.TicketExt;
import cn.work.pojo.dto.TicketQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TicketMapper {
    int countByExample(TicketExample example);

    int deleteByExample(TicketExample example);

    int deleteByPrimaryKey(Integer ticketid);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    List<Ticket> selectByExample(TicketExample example);

    Ticket selectByPrimaryKey(Integer ticketid);

    int updateByExampleSelective(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByExample(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);

    //自己的语句

    List<TicketExt> getAllTicketRec();

    int countUserTicket(int id);

    List<Ticket> getUserFee(int id);

    void dealTicketByUserid(int id);

    List<TicketExt> getTicketByUserId(int id);

    List<TicketExt> getTicketRecs(TicketQuery ticketQuery);
}