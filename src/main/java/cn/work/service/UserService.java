package cn.work.service;

import cn.work.pojo.Profile;
import cn.work.pojo.User;
import cn.work.pojo.UserExt;
import cn.work.pojo.Userinfo;

import java.util.List;

public interface UserService {
    List<Userinfo> getAllUsers();

    boolean addUser(Userinfo userinfo) throws Exception;

    void showUser(String id, boolean display) throws Exception;

    String getAccessDetail(String id);

    int getNotReturnNum(String id);

    int getOverDueNum(String id);

    int getTicketNum(String id);

    Userinfo updateUser(Userinfo userinfo);

    Userinfo getUserByIDcard(String idcard);

    double getUserFee(String id);

    UserExt getUserAndPwdByIDCard(String idcard);

    User getUserAndPwdByID(String userid);

    Profile getProfile(String id);

    void setUserAccess(String id, boolean access);

    void updatePwd(User user);

    List<Userinfo> searchUsers(String username, String idcard, String usertele);

}
