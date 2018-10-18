package cn.work.service.Impl;

import cn.work.dao.AdminMapper;
import cn.work.pojo.Admin;
import cn.work.pojo.AdminExample;
import cn.work.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    AdminMapper adminMapper;

    @Override
    public Admin getAdminByCardId(String id) {
        AdminExample adminExample=new AdminExample();
        adminExample.createCriteria().andIdcardEqualTo(id);
        List<Admin> list= null;
        try {
            list = adminMapper.selectByExample(adminExample);
        } catch (Exception e) {
            return null;
        }
        if(list.size()>0){
            Admin admin=list.get(0);
            return admin;
        }
        else
            return null;
    }
}
