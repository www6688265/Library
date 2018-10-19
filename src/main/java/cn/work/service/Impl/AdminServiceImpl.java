package cn.work.service.Impl;

import cn.work.dao.AdminMapper;
import cn.work.pojo.Admin;
import cn.work.pojo.AdminExample;
import cn.work.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static cn.work.util.SHAUtil.getEncrypt;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    AdminMapper adminMapper;

    @Override
    public Admin getAdminByCardId(String id) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andIdcardEqualTo(id);
        List<Admin> list = null;
        try {
            list = adminMapper.selectByExample(adminExample);
        } catch (Exception e) {
            return null;
        }
        if (list.size() > 0) {
            Admin admin = list.get(0);
            return admin;
        } else
            return null;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminMapper.getAllAdmins();
    }

    @Override
    public boolean addAdmin(Admin admin) {
        String pwd = admin.getAdmpassword();
        pwd = getEncrypt(pwd);
        admin.setAdmpassword(pwd);
        AdminExample example = new AdminExample();
        example.createCriteria().andIdcardEqualTo(admin.getIdcard());
        List list = adminMapper.selectByExample(example);
        if (list.size() > 0) {
            return false;
        }
        adminMapper.insertSelective(admin);
        return true;
    }

    @Override
    public void delAdmin(String id) {
        adminMapper.deleteByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        String pwd = admin.getAdmpassword();
        if (pwd != null && !pwd.equals("")) {
            pwd = getEncrypt(pwd);
            admin.setAdmpassword(pwd);
        }
        adminMapper.updateByPrimaryKeySelective(admin);
        admin = adminMapper.selectByPrimaryKey(admin.getAdmid());
        return admin;
    }

}
