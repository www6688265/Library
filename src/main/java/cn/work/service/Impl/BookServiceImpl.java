package cn.work.service.Impl;

import cn.work.dao.BookMapper;
import cn.work.dao.BooklocMapper;
import cn.work.dao.BorrowMapper;
import cn.work.pojo.*;
import cn.work.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service(value = "BookService")
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper bookMapper;

    @Resource
    BooklocMapper booklocMapper;

    @Resource
    BorrowMapper borrowMapper;

    @Override
    public void addBook(Book book, Bookloc bookloc) {
        bookMapper.insertSelective(book);
        bookloc.setBookid(book.getBookid());
        booklocMapper.insert(bookloc);
    }

    public boolean checkBookExist(Book book){
        BookExample example = new BookExample();
        example.createCriteria().andBooknameEqualTo(book.getBookname())
                .andPressEqualTo(book.getPress())
                .andAuthorEqualTo(book.getAuthor()).andIsbnEqualTo(book.getIsbn());
        List<Book> list = bookMapper.selectByExample(example);
        if (list.size() > 0) {
            return false;
        }
        else return true;
    }

    @Override
    public List<BookExt> getAllBooks() {
        List<BookExt> list=bookMapper.findAllBooks();
        return list;
    }

    @Override
    public void updateBook(Book book, Bookloc loc) {
        bookMapper.updateByPrimaryKeySelective(book);
        booklocMapper.updateByPrimaryKeySelective(loc);
    }

    public BookExt getBook(int id){
        BookExt bookExt=bookMapper.getBook(id);
        return bookExt;
    }

    @Override
    public void delBook(int id) throws Exception {
        BorrowExample borrowExample=new BorrowExample();
        borrowExample.createCriteria().andBookidEqualTo(id).andReturntimeIsNull();
        int notReturncount=borrowMapper.countByExample(borrowExample);
        if(notReturncount>0)
            throw new Exception("删除失败！有借出图书未还");
        else{
            bookMapper.deleteByPrimaryKey(id);
            booklocMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Book getBookByISBN(String isbn) {
        BookExample example = new BookExample();
        example.createCriteria().andIsbnEqualTo(isbn);
        List<Book> list=bookMapper.selectByExample(example);
        if(list.size()>0)
            return list.get(0);
        else
            return null;
    }


}
