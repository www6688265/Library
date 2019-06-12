package cn.work.dao;

import cn.work.pojo.Book;
import cn.work.pojo.BookExample;
import cn.work.pojo.BookExcel;
import cn.work.pojo.BookExt;
import cn.work.pojo.dto.CountDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {
    int countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Integer bookid);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);

    Book selectByPrimaryKey(Integer bookid);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<BookExt> findAllDisplayBooks();

    List<BookExt> findAllBooks();

    BookExt getBook(int id);

    List<BookExt> getBooksByType(String id);

    List<BookExt> getBooks(BookExample example);

    BookExt getBookByISBN(@Param("isbn") String isbn);

}