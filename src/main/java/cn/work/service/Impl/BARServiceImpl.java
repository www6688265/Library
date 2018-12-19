package cn.work.service.Impl;

import cn.work.dao.*;
import cn.work.pojo.*;
import cn.work.service.BARService;
import cn.work.service.UserService;
import org.junit.platform.commons.util.StringUtils;
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
import static cn.work.spring.config.LibraryConfig.ticketFee;


/**
 * @Description: 处理借书还书的业务层实现类
 * @Author: Aaron Ke
 */
@Service(value = "BARService")
public class BARServiceImpl implements BARService {
    @Resource
    BorrowMapper borrowMapper;
    @Resource
    TicketMapper ticketMapper;
    @Autowired
    UserService userService;
    @Resource
    BorrowedbooksMapper borrowedbooksMapper;
    @Resource
    InventoryMapper inventoryMapper;

    /**
     * @Description: 借书
     * @Param: userid：用户编号，bookid[]:要借的图书编号数组
     * @return: 用户借书数量
     * @Author: Aaron Ke
     */
    @Override
    //开启事务管理
    @Transactional(rollbackFor = Exception.class)
    public int borrowBook(String userid, String[] bookid) throws Exception {
        //插入一条借书记录
        Borrow userRec = new Borrow(Integer.parseInt(userid));
        borrowMapper.insertSelective(userRec);

        //得到刚刚插入记录的主键
        int orderid = userRec.getOrderid();

        //得到当前日期
        Date BorrowTime = new Date();

        Calendar ca = Calendar.getInstance();
        ca.setTime(BorrowTime);
        //将日期增加一个月
        ca.add(Calendar.MONTH, borrowMonthPeriod);
        //设置应还日期变量
        Date LimitTime = ca.getTime();

        //将借图书的数量
        int length = bookid.length;
        for (int i = 0; i < length; i++) {
            //对字符串进行前后去空格操作
            bookid[i] = bookid[i].trim();
            //新建一条借书书籍记录
            Borrowedbooks borrow = new Borrowedbooks();
            borrow.setOrderid(orderid);
            borrow.setBookid(Integer.parseInt(bookid[i]));
            borrow.setBorrowtime(BorrowTime);
            borrow.setLimittime(LimitTime);
            //插入借书书籍记录
            borrowedbooksMapper.insertSelective(borrow);

            //通过图书id找到该图书的库存
            Inventory bookInventory = inventoryMapper.selectByPrimaryKey(Integer.parseInt(bookid[i]));
            //得到图书剩余数量
            int leftNum = bookInventory.getLeftNum();
            bookInventory.setLeftNum(leftNum - 1);
            //更新库存信息
            inventoryMapper.updateByPrimaryKey(bookInventory);
        }

        //得到图书未归还数量
        int NotReturnNum = userService.getNotReturnNum(userid);
        //判断是否大于等于最大借书数量
        if (NotReturnNum >= limitBorrowNum) {
            //设置用户的权限
            userService.setUserAccess(userid, false);
        }
        return NotReturnNum;
    }

    /**
     * @Description: 还书
     * @Param: borrow:借书的记录
     * @return: 罚单信息
     * @Author: Aaron Ke
     */
    @Override
    //开启事务
    @Transactional(rollbackFor = Exception.class)
    public Ticket returnBook(BorrowExt borrow) throws Exception {
        //得到当前时间
        Date now = new Date();
        borrow.setReturntime(now);
        //新建借书书籍记录
        Borrowedbooks returnRec = new Borrowedbooks();
        //将borrow的属性拷贝到returnRec中
        BeanUtils.copyProperties(borrow, returnRec);
        //更新借书记录
        borrowedbooksMapper.updateByPrimaryKey(returnRec);

        //得到图书的库存并修改
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

        //检查此次还书是否已经超过应还时间
        if (limitTime.before(now)) {
            //得到超过的时间
            Long day = (now.getTime() - limitTime.getTime()) / (24 * 60 * 60 * 1000);
            int OverDueTime = day.intValue();
            //计算费用
            double fee = OverDueTime * ticketFee;
            BigDecimal num = new BigDecimal(fee);

            //新建罚款记录
            Ticket ticket = new Ticket();
            ticket.setFee(num);
            ticket.setOrderid(borrow.getOrderid());
            ticket.setBookid(borrow.getBookid());
            ticket.setOverduetime(OverDueTime);
            ticket.setTicketStatus(0);
            //插入罚单记录
            ticketMapper.insertSelective(ticket);
            //返回罚单信息
            return ticket;
        } else
            return null;
    }
}
