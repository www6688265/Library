package cn.work.dao;

import cn.work.pojo.Borrowedbooks;
import cn.work.pojo.BorrowedbooksExample;
import cn.work.pojo.BorrowedbooksKey;
import cn.work.pojo.dto.CountDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BorrowedbooksMapper {
    int countByExample(BorrowedbooksExample example);

    int deleteByExample(BorrowedbooksExample example);

    int deleteByPrimaryKey(BorrowedbooksKey key);

    int insert(Borrowedbooks record);

    int insertSelective(Borrowedbooks record);

    List<Borrowedbooks> selectByExample(BorrowedbooksExample example);

    Borrowedbooks selectByPrimaryKey(BorrowedbooksKey key);

    int updateByExampleSelective(@Param("record") Borrowedbooks record, @Param("example") BorrowedbooksExample example);

    int updateByExample(@Param("record") Borrowedbooks record, @Param("example") BorrowedbooksExample example);

    int updateByPrimaryKeySelective(Borrowedbooks record);

    int updateByPrimaryKey(Borrowedbooks record);

    @Select("select Book.bookName name,count(*) value from BorrowedBooks,book where BorrowedBooks.bookid=Book.bookID GROUP BY bookName ORDER BY value desc")
    List<CountDTO> getBorrowBooksCount();

    @Select("SELECT\n" +
            "\tbooktype NAME,\n" +
            "\tcount( * ) \n" +
            "VALUE\n" +
            "\t\n" +
            "FROM\n" +
            "\tBorrowedBooks,\n" +
            "\tbook,\n" +
            "\tbooktype \n" +
            "WHERE\n" +
            "\tBorrowedBooks.bookid = Book.bookID \n" +
            "\tAND book.booktypeid = booktype.id\n" +
            "GROUP BY\n" +
            "NAME \n" +
            "ORDER BY\n" +
            "VALUE\n" +
            "DESC")
    List<CountDTO> getBorrowBookTypesCount();
}