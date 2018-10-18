package cn.work.service;

import cn.work.pojo.Borrow;
import cn.work.pojo.Ticket;

import java.text.ParseException;

//借书加还书BorrowAndReturn
public interface BARService {
    void borrowBook(Borrow borrow);

    Ticket returnBook(Borrow borrow) throws ParseException;
}
