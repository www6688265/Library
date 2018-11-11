package cn.work.service;

import cn.work.pojo.BorrowExt;

import java.util.List;

public interface BorrowRecService {
    List<BorrowExt> getAllBorrowRec();

//    void delBorrowRec(String id);

    List<BorrowExt> getNotReturnRec(String userid);

    List<BorrowExt> getBorrowRecByUserID(String userid);

    boolean renewBorrow(String orderid, String bookid);

}
