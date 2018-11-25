package cn.work.service;

import cn.work.pojo.*;

import java.util.List;

public interface BookService {

    void addBook(BookExt book);

    boolean checkBookExist(Book book);

    List<BookExt> getAllBooks();

    List<BookExt> getBooksByType(String id);

    void updateBook(BookExt book);

    BookExt getBook(int id);

    Book getBookByISBN(String isbn);

    List<BookExt> getBooksByNameOrAuthor(String name, String author);

    List<Booktype> getAllTypes();

    List<BookExt> getBooks(BookExt book);

}
