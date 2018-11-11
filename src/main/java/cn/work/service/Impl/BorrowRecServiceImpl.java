package cn.work.service.Impl;

import cn.work.dao.BorrowMapper;
import cn.work.dao.BorrowedbooksMapper;
import cn.work.pojo.BorrowExt;
import cn.work.pojo.Borrowedbooks;
import cn.work.pojo.BorrowedbooksKey;
import cn.work.service.BorrowRecService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static cn.work.spring.config.LibraryConfig.borrowMonthPeriod;
import static cn.work.spring.config.LibraryConfig.limitMonth;

/**
 * @Description: 借书记录的业务层实现类
 * @Author: Aaron Ke
 */
@Service("BorrowRecService")
public class BorrowRecServiceImpl implements BorrowRecService {
    @Resource
    BorrowMapper borrowMapper;
    @Resource
    BorrowedbooksMapper borrowedbooksMapper;

    /**
     * @Description: 得到所有借书记录
     * @Param: 无
     * @return: 借书记录
     * @Author: Aaron Ke
     */
    @Override
    public List<BorrowExt> getAllBorrowRec() {
        List<BorrowExt> list = borrowMapper.getAllBorrowRec();
        return list;
    }

    //废弃的方法
//    @Override
//    public void delBorrowRec(String id) {
//        borrowMapper.deleteByPrimaryKey(Integer.parseInt(id));
//    }

    /**
     * @Description: 通过userid得到未归还的记录
     * @Param: userid:用户编号
     * @return: 借书几率
     * @Author: Aaron Ke
     */
    @Override
    public List<BorrowExt> getNotReturnRec(String userid) {
        List<BorrowExt> list = borrowMapper.getNotReturnRec(Integer.parseInt(userid));
        return  list;
    }

    /**
     * @Description: 得到用户所有的借书记录
     * @Param: userid:用户编号
     * @return: 借书记录
     * @Author: Aaron Ke
     */
    @Override
    public List<BorrowExt> getBorrowRecByUserID(String userid) {
        return borrowMapper.getBorrowRecByUserID(Integer.parseInt(userid));
    }

    /**
     * @Description: 续借图书
     * @Param: orderid:订单编号 ，bookid ：图书编号
     * @return: 操作结果
     * @Author: Aaron Ke
     */
    @Override
    public boolean renewBorrow(String orderid, String bookid) {
        //新建借书书籍记录的主键
        BorrowedbooksKey key = new BorrowedbooksKey();
        key.setOrderid(Integer.parseInt(orderid));
        key.setBookid(Integer.parseInt(bookid));

        //找到该借书记录
        Borrowedbooks borrow = borrowedbooksMapper.selectByPrimaryKey(key);

        //得到借书时间
        Date borrowTime = borrow.getBorrowtime();
        Calendar ca = Calendar.getInstance();
        ca.setTime(borrowTime);

        //借书时间增加最大借书周期
        ca.add(Calendar.MONTH, limitMonth);

        //得到最大借书时间
        Date maxLimitTime = ca.getTime();
        Date limitTime = borrow.getLimittime();
        //判断是否已经达到最大借书时间
        if (limitTime.equals(maxLimitTime)) {
            return false;
        } else {
            Calendar ca2 = Calendar.getInstance();
            ca2.setTime(limitTime);
            //增加一个续借周期
            ca2.add(Calendar.MONTH, borrowMonthPeriod);
            borrow.setLimittime(ca2.getTime());
            //更新借书几率
            borrowedbooksMapper.updateByPrimaryKeySelective(borrow);
            return true;
        }
    }

}
