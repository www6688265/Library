package cn.work.service.Impl;

import cn.work.dao.BookMapper;
import cn.work.dao.BooklocMapper;
import cn.work.dao.BooktypeMapper;
import cn.work.dao.BorrowMapper;
import cn.work.pojo.*;
import cn.work.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "BookService")
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper bookMapper;

    @Resource
    BooklocMapper booklocMapper;

    @Resource
    BorrowMapper borrowMapper;

    @Resource
    BooktypeMapper booktypeMapper;

    @Override
    public void addBook(Book book, Bookloc bookloc) {
        bookMapper.insertSelective(book);
        bookloc.setBookid(book.getBookid());
        booklocMapper.insert(bookloc);
    }

    public boolean checkBookExist(Book book) {
        BookExample example = new BookExample();
        example.createCriteria()
//                .andBooknameEqualTo(book.getBookname())
//                .andPressEqualTo(book.getPress())
//                .andAuthorEqualTo(book.getAuthor())
                .andIsbnEqualTo(book.getIsbn());
        List<Book> list = bookMapper.selectByExample(example);
        if (list.size() > 0) {
            return false;
        } else return true;
    }

    @Override
    public List<BookExt> getAllBooks() {
        return bookMapper.findAllBooks();
    }

    @Override
    public List<BookExt> getBooksByType(String type) {
        return bookMapper.getBooksByType(type);
    }

    @Override
    public void updateBook(Book book, Bookloc loc) {
        if (book != null)
            bookMapper.updateByPrimaryKeySelective(book);
        if (loc != null)
            booklocMapper.updateByPrimaryKeySelective(loc);
    }

    public BookExt getBook(int id) {
        BookExt bookExt = bookMapper.getBook(id);
        return bookExt;
    }


    @Override
    public void delBook(int id) throws Exception {
        BorrowExample borrowExample = new BorrowExample();
        borrowExample.createCriteria().andBookidEqualTo(id).andReturntimeIsNull();
        int notReturncount = borrowMapper.countByExample(borrowExample);
        if (notReturncount > 0)
            throw new Exception("删除失败！有借出图书未还");
        else {
            bookMapper.deleteByPrimaryKey(id);
            booklocMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Book getBookByISBN(String isbn) {
        BookExample example = new BookExample();
        example.createCriteria().andIsbnEqualTo(isbn);
        List<Book> list = bookMapper.selectByExample(example);
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    @Override
    public List<BookExt> getBooksByNameOrAuthor(String name, String author) {
        BookExample example = new BookExample();
        BookExample.Criteria c = example.createCriteria();
        if (!StringUtil.isEmpty(name)) {
            c.andBooknameLike("%" + name + "%");
        }
        if (!StringUtil.isEmpty(author)) {
            c.andAuthorLike("%" + author + "%");
        }
        List<BookExt> list = (List<BookExt>) (Object) bookMapper.selectByExample(example);

        return list;
    }

    @Override
    public List<Booktype> getAllTypes() {
        return booktypeMapper.getAllTypes();
    }

    @Override
    public List<BookExt> getBooks(Book book) {
        BookExample example = new BookExample();
        BookExample.Criteria c = example.createCriteria();
        c.andBooknameIsNotNull();
        if (!StringUtil.isEmpty(book.getBookname())) {
            c.andBooknameLike("%" + book.getBookname() + "%");
        }
        if (!StringUtil.isEmpty(book.getAuthor())) {
            c.andAuthorLike("%" + book.getAuthor() + "%");
        }
        if (!StringUtil.isEmpty(book.getType())) {
            c.andTypeEqualTo(book.getType());
        }
        if (!StringUtil.isEmpty(book.getPress())) {
            c.andPressLike("%" + book.getPress() + "%");
        }
        if (!StringUtil.isEmpty(book.getIsbn())) {
            c.andIsbnLike("%" + book.getIsbn() + "%");
        }
        if (book.getTotal() != null) {
            c.andTotalEqualTo(book.getTotal());
        }
        if (book.getLeft() != null) {
            c.andTotalEqualTo(book.getLeft());
        }
        if (book.getDisplay() != null) {
            c.andDisplayEqualTo(book.getDisplay());
        }
        return bookMapper.getBooks(example);
    }


}
