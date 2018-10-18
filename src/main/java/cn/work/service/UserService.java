package cn.work.service;

import cn.work.pojo.Userinfo;

import java.util.List;

public interface UserService {
    List<Userinfo> getAllUsers();

    boolean addUser(Userinfo userinfo) throws Exception;

    void delUser(String id) throws Exception;

    String getAccessDetail(String id);

    int getNotReturnNum(String id);

    int getOverDueNum(String id);

    int getTicketNum(String id);

    Userinfo updateUser(Userinfo userinfo);

    Userinfo getUserByIDcard(String idcard);

    double getUserFee(String id);

}
