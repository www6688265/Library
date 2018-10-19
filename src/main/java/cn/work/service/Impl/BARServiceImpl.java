package cn.work.service.Impl;

import cn.work.dao.BookMapper;
import cn.work.dao.BorrowMapper;
import cn.work.dao.TicketMapper;
import cn.work.pojo.Book;
import cn.work.pojo.Borrow;
import cn.work.pojo.Ticket;
import cn.work.service.BARService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static cn.work.spring.config.LibraryConfig.borrowMonthPeriod;

@Service(value = "BARService")
public class BARServiceImpl implements BARService {
    @Resource
    BorrowMapper borrowMapper;

    @Resource
    BookMapper bookMapper;

    @Resource
    TicketMapper ticketMapper;

    @Override
    public void borrowBook(Borrow borrow) {
        Date BorrowTime = new Date();
        borrow.setBorrowtime(BorrowTime);
        Calendar ca = Calendar.getInstance();
        ca.setTime(BorrowTime);
        ca.add(Calendar.MONTH, borrowMonthPeriod);
        Date LimitTime = ca.getTime();
        borrow.setLimittime(LimitTime);
        borrowMapper.insertSelective(borrow);

        Book book = bookMapper.selectByPrimaryKey(borrow.getBookid());
        int left = book.getLeft() - 1;
        book.setLeft(left);
        bookMapper.updateByPrimaryKey(book);
    }

    @Override
    public Ticket returnBook(Borrow borrow) throws ParseException {
        Date now = new Date();
        borrow.setReturntime(now);
        borrowMapper.updateByPrimaryKey(borrow);

        Book book = bookMapper.selectByPrimaryKey(borrow.getBookid());
        int left = book.getLeft() - 1;
        book.setLeft(left);
        bookMapper.updateByPrimaryKey(book);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date limitTime = borrow.getLimittime();
        now = df.parse(df.format(now));
        limitTime = df.parse(df.format(limitTime));
        if (limitTime.before(now)) {
            Long day = (now.getTime() - limitTime.getTime()) / (24 * 60 * 60 * 1000);
            int OverDueTime = day.intValue();
            double fee = OverDueTime * 0.5;
            Ticket ticket = new Ticket();
            ticket.setFee(fee);
            ticket.setOrderid(borrow.getOrderid());
            ticket.setOverduetime(OverDueTime);
            ticket.setStatus(0);
            ticketMapper.insertSelective(ticket);
            return ticket;
        } else
            return null;
    }
}
