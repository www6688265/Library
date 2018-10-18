package cn.work.service;

import cn.work.pojo.Book;
import cn.work.pojo.BookExt;
import cn.work.pojo.Bookloc;
import cn.work.pojo.Borrow;

import java.util.List;

public interface BookService {

    void addBook(Book book, Bookloc bookloc);

    boolean checkBookExist(Book book);

    List<BookExt> getAllBooks();

    void updateBook(Book book, Bookloc loc);

    public BookExt getBook(int id);

    void delBook(int id) throws Exception;

    Book getBookByISBN(String isbn);

}
