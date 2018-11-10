package cn.work.service.Impl;

import cn.work.dao.BookMapper;
import cn.work.dao.BooktypeMapper;
import cn.work.dao.BorrowMapper;
import cn.work.dao.InventoryMapper;
import cn.work.pojo.*;
import cn.work.service.BookService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "BookService")
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper bookMapper;

    @Resource
    BorrowMapper borrowMapper;

    @Resource
    BooktypeMapper booktypeMapper;

    @Resource
    InventoryMapper inventoryMapper;

    @Override
    public void addBook(BookExt book) {
        bookMapper.insertSelective(book);
        Inventory inventory = new Inventory(book.getBookid(), book.getTotal());
        inventoryMapper.insert(inventory);
    }

    public boolean checkBookExist(Book book) {
        BookExample example = new BookExample();
        example.createCriteria().andIsbnEqualTo(book.getIsbn());
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
    public void updateBook(BookExt book) {
        if (book != null) {
            bookMapper.updateByPrimaryKeySelective(book);
            Inventory inventory = new Inventory(book.getBookid(), book.getLeft_num());
            inventoryMapper.updateByPrimaryKey(inventory);
        }
    }

    public BookExt getBook(int id) {
        BookExt bookExt = bookMapper.getBook(id);
        return bookExt;
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
    public List<BookExt> getBooks(BookExt book) {
        BookExample example = new BookExample();
        BookExample.Criteria c = example.createCriteria();
        c.andBooknameIsNotNull();
        if (!StringUtil.isEmpty(book.getBookname())) {
            c.andBooknameLike("%" + book.getBookname() + "%");
        }
        if (!StringUtil.isEmpty(book.getAuthor())) {
            c.andAuthorLike("%" + book.getAuthor() + "%");
        }
        if (!StringUtil.isEmpty(book.getBooktypeid())) {
            c.andBooktypeidEqualTo(book.getBooktypeid());
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
        if (book.getDisplay() != null) {
            c.andDisplayEqualTo(book.getDisplay());
        }
        if (book.getLeft_num() != null) {
            c.andleftNumEqualTo(book.getLeft_num());
        }
        return bookMapper.getBooks(example);
    }


}
