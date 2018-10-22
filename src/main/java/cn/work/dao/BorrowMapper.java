package cn.work.dao;

import cn.work.pojo.Borrow;
import cn.work.pojo.BorrowExample;
import cn.work.pojo.BorrowExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowMapper {
    int countByExample(BorrowExample example);

    int deleteByExample(BorrowExample example);

    int deleteByPrimaryKey(Integer orderid);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    List<Borrow> selectByExample(BorrowExample example);

    Borrow selectByPrimaryKey(Integer orderid);

    int updateByExampleSelective(@Param("record") Borrow record, @Param("example") BorrowExample example);

    int updateByExample(@Param("record") Borrow record, @Param("example") BorrowExample example);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);

    List<BorrowExt> getAllBorrowRec();

    List<BorrowExt> getBorrowRecByUserID(int id);
}