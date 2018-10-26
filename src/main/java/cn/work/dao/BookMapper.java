package cn.work.dao;

import cn.work.pojo.Book;
import cn.work.pojo.BookExample;
import cn.work.pojo.BookExt;
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

    @Select("SELECT book.bookid,bookname,booktype.type,press,brief,total,`LEFT`,pic,author,isbn,floor,bookcase,display,`level`\n" +
            "FROM book,bookloc,booktype\n" +
            "WHERE book.bookid = bookloc.bookid and book.type=booktype.id and book.display=1")
    List<BookExt> findAllDisplayBooks();

    @Select("SELECT book.bookid,bookname,booktype.type,press,brief,total,`LEFT`,pic,author,isbn,floor,bookcase,display,`level`\n" +
            "FROM book,bookloc,booktype\n" +
            "WHERE book.bookid = bookloc.bookid and book.type=booktype.id")
    List<BookExt> findAllBooks();

    BookExt getBook(int id);

    @Select("SELECT book.bookid,bookname,booktype.type,press,brief,total,`LEFT`,pic,author,isbn,floor,bookcase,display,`level`\n" +
            "FROM book,bookloc,booktype\n" +
            "WHERE book.bookid = bookloc.bookid and book.type=booktype.id and book.type=#{id} and book.display=1")
    List<BookExt> getBooksByType(String id);

    List<BookExt> getBooks(BookExample example);
}