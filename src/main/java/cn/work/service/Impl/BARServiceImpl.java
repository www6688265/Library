package cn.work.service.Impl;

import cn.work.dao.*;
import cn.work.pojo.*;
import cn.work.service.BARService;
import cn.work.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static cn.work.spring.config.LibraryConfig.borrowMonthPeriod;
import static cn.work.spring.config.LibraryConfig.limitBorrowNum;

@Service(value = "BARService")
public class BARServiceImpl implements BARService {
    @Resource
    BorrowMapper borrowMapper;

    @Resource
    BookMapper bookMapper;

    @Resource
    TicketMapper ticketMapper;

    @Autowired
    UserService userService;

    @Resource
    BorrowedbooksMapper borrowedbooksMapper;

    @Resource
    InventoryMapper inventoryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int borrowBook(String userid, String[] bookid) throws Exception {
        Borrow userRec = new Borrow(Integer.parseInt(userid));
        borrowMapper.insertSelective(userRec);

        int orderid = userRec.getOrderid();
        Date BorrowTime = new Date();

        Calendar ca = Calendar.getInstance();
        ca.setTime(BorrowTime);
        ca.add(Calendar.MONTH, borrowMonthPeriod);
        Date LimitTime = ca.getTime();

        int length = bookid.length;
        for (int i = 0; i < length; i++) {
            bookid[i] = bookid[i].trim();
            Borrowedbooks borrow = new Borrowedbooks();
            borrow.setOrderid(orderid);
            borrow.setBookid(Integer.parseInt(bookid[i]));
            borrow.setBorrowtime(BorrowTime);
            borrow.setLimittime(LimitTime);
            borrowedbooksMapper.insertSelective(borrow);

            Inventory bookInventory = inventoryMapper.selectByPrimaryKey(Integer.parseInt(bookid[i]));
            int leftNum = bookInventory.getLeftNum();
            bookInventory.setLeftNum(leftNum - 1);
            inventoryMapper.updateByPrimaryKey(bookInventory);
        }


        int NotReturnNum = userService.getNotReturnNum(userid);
        if (NotReturnNum >= limitBorrowNum) {
            userService.setUserAccess(userid, false);
        }
        return NotReturnNum;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Ticket returnBook(BorrowExt borrow) throws Exception {
        Date now = new Date();
        borrow.setReturntime(now);
        Borrowedbooks returnRec = new Borrowedbooks();
        BeanUtils.copyProperties(borrow, returnRec);
        borrowedbooksMapper.updateByPrimaryKey(returnRec);

        Inventory inventory = inventoryMapper.selectByPrimaryKey(borrow.getBookid());
        int left = inventory.getLeftNum() + 1;
        inventory.setLeftNum(left);
        inventoryMapper.updateByPrimaryKey(inventory);

        //去除时分秒，例如2018-1-1 1:1:1 变为2018-1-1
        // 这样的过期时期才不会受到借书时间的时分秒影响
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date limitTime = borrow.getLimittime();
        now = df.parse(df.format(now));
        limitTime = df.parse(df.format(limitTime));

        if (limitTime.before(now)) {
            Long day = (now.getTime() - limitTime.getTime()) / (24 * 60 * 60 * 1000);
            int OverDueTime = day.intValue();
            double fee = OverDueTime * 0.5;
            BigDecimal num = new BigDecimal(fee);
            Ticket ticket = new Ticket();
            ticket.setFee(num);
            ticket.setOrderid(borrow.getOrderid());
            ticket.setBookid(borrow.getBookid());
            ticket.setOverduetime(OverDueTime);
            ticket.setTicketStatus(0);
            ticketMapper.insertSelective(ticket);
            return ticket;
        } else
            return null;
    }
}
