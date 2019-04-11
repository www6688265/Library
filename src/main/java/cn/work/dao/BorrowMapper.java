package cn.work.dao;

import cn.work.pojo.Borrow;
import cn.work.pojo.BorrowExample;
import cn.work.pojo.BorrowExt;
import cn.work.pojo.ReminderInfo;
import cn.work.pojo.dto.BorrowRecQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    //自己写的语句
    List<BorrowExt> getAllBorrowRec();

    List<BorrowExt> getBorrowRecs(BorrowRecQuery brc);

    List<BorrowExt> getBorrowRecByUserID(int id);

    @Select("SELECT\n" +
            "\tcount( * ) \n" +
            "FROM\n" +
            "\tborrow,\n" +
            "\tBorrowedBooks \n" +
            "WHERE\n" +
            "\tborrow.userID = #{id} \n" +
            "\tAND BorrowedBooks.ReturnTime is null \n" +
            "\tAND borrow.orderId = BorrowedBooks.orderId")
    int countUserNotReturn(int id);

    @Select("SELECT\n" +
            "\tcount( * ) \n" +
            "FROM\n" +
            "\tborrow,\n" +
            "\tBorrowedBooks \n" +
            "WHERE\n" +
            "\tborrow.userID = #{id} \n" +
            "\tAND BorrowedBooks.ReturnTime is null \n" +
            "\tAnd LimitTime<now()\n" +
            "\tAND borrow.orderId = BorrowedBooks.orderId")
    int countUserOverDueNum(int id);

    List<BorrowExt> getNotReturnRec(int id);

    List<ReminderInfo> getAboutToOverDueRec(int num);

}