package cn.work.service.Impl;

import cn.work.dao.BorrowMapper;
import cn.work.dao.BorrowedbooksMapper;
import cn.work.pojo.Borrow;
import cn.work.pojo.BorrowExt;
import cn.work.pojo.Borrowedbooks;
import cn.work.pojo.BorrowedbooksKey;
import cn.work.service.BorrowRecService;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static cn.work.spring.config.LibraryConfig.borrowMonthPeriod;
import static cn.work.spring.config.LibraryConfig.limitMonth;

@Service("BorrowRecService")
public class BorrowRecServiceImpl implements BorrowRecService {
    @Resource
    BorrowMapper borrowMapper;
    @Resource
    BorrowedbooksMapper borrowedbooksMapper;

    @Override
    public List<BorrowExt> getAllBorrowRec() {
        List<BorrowExt> list = borrowMapper.getAllBorrowRec();
        return list;
    }

    @Override
    public void delBorrowRec(String id) {
        borrowMapper.deleteByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public List<BorrowExt> getNotReturnRec(String userid) {
        List<BorrowExt> list = borrowMapper.getNotReturnRec(Integer.parseInt(userid));
        return  list;
    }

    @Override
    public List<BorrowExt> getBorrowRecByUserID(String userid) {
        return borrowMapper.getBorrowRecByUserID(Integer.parseInt(userid));
    }

    @Override
    public boolean renewBorrow(String orderid, String bookid) {
        BorrowedbooksKey key = new BorrowedbooksKey();
        key.setOrderid(Integer.parseInt(orderid));
        key.setBookid(Integer.parseInt(bookid));
        Borrowedbooks borrow = borrowedbooksMapper.selectByPrimaryKey(key);
        Date borrowTime = borrow.getBorrowtime();
        Calendar ca = Calendar.getInstance();
        ca.setTime(borrowTime);
        ca.add(Calendar.MONTH, limitMonth);
        Date maxLimitTime = ca.getTime();
        Date limitTime = borrow.getLimittime();
        if (limitTime.equals(maxLimitTime)) {
            return false;
        } else {
            Calendar ca2 = Calendar.getInstance();
            ca2.setTime(limitTime);
            ca2.add(Calendar.MONTH, borrowMonthPeriod);
            borrow.setLimittime(ca2.getTime());
            borrowedbooksMapper.updateByPrimaryKeySelective(borrow);
            return true;
        }
    }

}
