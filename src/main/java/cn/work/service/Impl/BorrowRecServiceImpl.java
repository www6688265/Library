package cn.work.service.Impl;

import cn.work.dao.BorrowMapper;
import cn.work.pojo.Borrow;
import cn.work.pojo.BorrowExample;
import cn.work.pojo.BorrowExt;
import cn.work.service.BorrowRecService;
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
    public List<Borrow> getNotReturnRec(String userid) {
        BorrowExample example = new BorrowExample();
        example.createCriteria().andUseridEqualTo(Integer.parseInt(userid)).andReturntimeIsNull();
        List<Borrow> list = borrowMapper.selectByExample(example);
        return  list;
    }

    @Override
    public List<BorrowExt> getBorrowRecByUserID(String userid) {
        return borrowMapper.getBorrowRecByUserID(Integer.parseInt(userid));
    }

    @Override
    public boolean renewBorrow(String orderid) {
        Borrow borrow = borrowMapper.selectByPrimaryKey(Integer.parseInt(orderid));
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
            borrowMapper.updateByPrimaryKeySelective(borrow);
            return true;
        }
    }

}
