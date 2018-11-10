package cn.work.service;

import cn.work.pojo.Borrow;
import cn.work.pojo.BorrowExt;
import cn.work.pojo.Ticket;

import java.text.ParseException;

//借书加还书BorrowAndReturn
public interface BARService {
    int borrowBook(String userid, String[] bookid) throws Exception;

    Ticket returnBook(BorrowExt borrow) throws ParseException, Exception;
}
