package cn.work.service;


import cn.work.pojo.Admin;

import java.util.List;

public interface AdminService {
    Admin getAdminByCardId(String id);

    List<Admin> getAllAdmins();

    boolean addAdmin(Admin admin);

    void delAdmin(String id);

    Admin updateAdmin(Admin admin);

    Admin getAdminByAdmid(String id);

    List<Admin> getAdmins(String username);
}
