package cn.work.service.Impl;

import cn.work.dao.BorrowMapper;
import cn.work.pojo.Borrow;
import cn.work.pojo.BorrowExample;
import cn.work.pojo.BorrowExt;
import cn.work.service.BorrowRecService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
