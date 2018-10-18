package cn.work.service;

import cn.work.pojo.Borrow;
import cn.work.pojo.BorrowExt;

import java.util.List;

public interface BorrowRecService {
    List<BorrowExt> getAllBorrowRec();

    void delBorrowRec(String id);

    List<Borrow> getNotReturnRec(String userid);
}
