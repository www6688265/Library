package cn.work.service.Impl;

import cn.work.dao.BookMapper;
import cn.work.dao.BooktypeMapper;
import cn.work.dao.InventoryMapper;
import cn.work.pojo.*;
import cn.work.service.BookService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 图书相关的业务层实现类
 * @Author: Aaron Ke
 */
@Service(value = "BookService")
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper bookMapper;

    @Resource
    BooktypeMapper booktypeMapper;

    @Resource
    InventoryMapper inventoryMapper;

    /**
     * @Description: 增加图书
     * @Param: book:图书信息
     * @return: 无
     * @Author: Aaron Ke
     */
    @Override
    public void addBook(BookExt book) {
        //插入图书信息
        bookMapper.insertSelective(book);
        //插入库存信息
        Inventory inventory = new Inventory(book.getBookid(), book.getTotal());
        inventoryMapper.insert(inventory);
    }

    /**
     * @Description: 检查图书是否存在
     * @Param: book:图书信息
     * @return: boolean ,true：表示存在,false:表示不存在
     * @Author: Aaron Ke
     */
    public boolean checkBookExist(Book book) {
        BookExample example = new BookExample();
        //判断依据是该图书的isbn编号是否存在
        example.createCriteria().andIsbnEqualTo(book.getIsbn());
        List<Book> list = bookMapper.selectByExample(example);
        return list.size() <= 0;
    }

    /**
     * @Description: 得到所有图书信息
     * @Param: 无
     * @return: 图书信息
     * @Author: Aaron Ke
     */
    @Override
    public List<BookExt> getAllBooks() {
        return bookMapper.findAllBooks();
    }

    /**
     * @Description: 通过图书类型得到图书
     * @Param: type:类型的Id
     * @return: 图书信息
     * @Author: Aaron Ke
     */
    @Override
    public List<BookExt> getBooksByType(String type) {
        return bookMapper.getBooksByType(type);
    }

    /**
     * @Description: 更新图书信息
     * @Param: Book：图书信息
     * @return: 无
     * @Author: Aaron Ke
     */
    @Override
    public void updateBook(BookExt book) {
        //判断图书是否为空
        if (book != null) {
            bookMapper.updateByPrimaryKeySelective(book);
            Inventory inventory = new Inventory(book.getBookid(), book.getLeft_num());
            inventoryMapper.updateByPrimaryKey(inventory);
        }
    }

    /**
     * @Description: 通过图书编号得到图书信息
     * @Param: id:图书编号
     * @return: 图书信息
     * @Author: Aaron Ke
     */
    public BookExt getBook(int id) {
        return bookMapper.getBook(id);
    }


    /**
     * @Description: 通过isbn编号得到图书信息
     * @Param: isbn:图书ISBN编号
     * @return: 图书信息
     * @Author: Aaron Ke
     */
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

    /**
     * @Description: 通过作者或者书名的查找图书
     * @Param: name:书名，author:作者
     * @return: 图书数组
     * @Author: Aaron Ke
     */
    @Override
    public List<BookExt> getBooksByNameOrAuthor(String name, String author) {
        BookExample example = new BookExample();
        BookExample.Criteria c = example.createCriteria();
        //对书名进行迷糊查询的sql语句拼接
        if (!StringUtil.isEmpty(name)) {
            c.andBooknameLike("%" + name + "%");
        }
        //同上
        if (!StringUtil.isEmpty(author)) {
            c.andAuthorLike("%" + author + "%");
        }
        List<BookExt> list = (List<BookExt>) (Object) bookMapper.selectByExample(example);

        return list;
    }

    /**
     * @Description: 得到所有图书类型
     * @Param: 无
     * @return: 图书类型
     * @Author: Aaron Ke
     */
    @Override
    public List<Booktype> getAllTypes() {
        return booktypeMapper.getAllTypes();
    }

    /**
     * @Description: 模糊查询图书
     * @Param: book：查询条件
     * @return:
     * @Author: Aaron Ke
     */
    @Override
    public List<BookExt> getBooks(BookExt book) {
        BookExample example = new BookExample();
        BookExample.Criteria c = example.createCriteria();
        c.andBooknameIsNotNull();
        //不多说了= =自己看上面
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
