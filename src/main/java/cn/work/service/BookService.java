package cn.work.service;

import cn.work.pojo.*;

import java.util.List;

public interface BookService {

    void addBook(Book book, Bookloc bookloc);

    boolean checkBookExist(Book book);

    List<BookExt> getAllBooks();

    List<BookExt> getBooksByType(String id);

    void updateBook(Book book, Bookloc loc);

    BookExt getBook(int id);

    void delBook(int id) throws Exception;

    Book getBookByISBN(String isbn);

    List<BookExt> getBooksByNameOrAuthor(String name, String author);

    List<Booktype> getAllTypes();

    List<BookExt> getBooks(Book book);
}
