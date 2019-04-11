package cn.work.service;

import cn.work.pojo.Ticket;
import cn.work.pojo.TicketExt;
import cn.work.pojo.dto.TicketQuery;

import java.util.List;

public interface TicketRecService {
    List<TicketExt> getAllTicketRec();

//    void delTicketRec(int id);

    void dealTicketByUserid(String id);

    void addTicketRec(Ticket ticket);

    List<TicketExt> getTicketByUserId(String id);

    List<TicketExt> getTicketRecs(TicketQuery ticketQuery);
}
