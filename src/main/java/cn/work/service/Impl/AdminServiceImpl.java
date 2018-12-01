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

    /**
     * @Description: 通过管理员身份证获得管理员信息
     * @Param: id:管理员身份证
     * @return: 管理员信息
     * @Author: Aaron Ke
     */
    @Override
    public Admin getAdminByCardId(String id) {
        //创建sql的where条件
        AdminExample adminExample = new AdminExample();
        //idcard=id
        adminExample.createCriteria().andIdcardEqualTo(id);
        List<Admin> list = null;
        try {
            list = adminMapper.selectByExample(adminExample);
        } catch (Exception e) {
            return null;
        }
        if (list.size() > 0) {
            return list.get(0);
        } else
            return null;
    }

    /**
     * @Description: 得到所有管理员信息
     * @Param: 无
     * @return: 管理员信息
     * @Author: Aaron Ke
     */
    @Override
    public List<Admin> getAllAdmins() {
        return adminMapper.getAllAdmins();
    }

    /**
     * @Description: 增加管理员
     * @Param: admin:管理员信息
     * @return: 操作结果
     * @Author: Aaron Ke
     */
    @Override
    public boolean addAdmin(Admin admin) {
        String pwd = admin.getAdmpassword();
        //对密码进行加密
        pwd = getEncrypt(pwd);
        admin.setAdmpassword(pwd);
        AdminExample example = new AdminExample();
        example.createCriteria().andIdcardEqualTo(admin.getIdcard());
        //查看是否有相同的管理员信息：依据是管理员的身份证
        List list = adminMapper.selectByExample(example);
        if (list.size() > 0) {
            return false;
        }
        adminMapper.insertSelective(admin);
        return true;
    }

    /**
     * @Description: 删除管理员
     * @Param: id:管理员的编号
     * @return: 无
     * @Author: Aaron Ke
     */
    @Override
    public void delAdmin(String id) {
        adminMapper.deleteByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * @Description: 更新管理员信息
     * @Param: admin:管理员的信息
     * @return: 更新后的管理员信息
     * @Author: Aaron Ke
     */
    @Override
    public Admin updateAdmin(Admin admin) {
        String pwd = admin.getAdmpassword();
        if (pwd != null && !pwd.equals("")) {
            //密码加密
            pwd = getEncrypt(pwd);
            admin.setAdmpassword(pwd);
        }
        //更新管理员信息
        adminMapper.updateByPrimaryKeySelective(admin);
        admin = adminMapper.selectByPrimaryKey(admin.getAdmid());
        return admin;
    }

    /**
     * @Description: 通过管理员编号得到管理员信息
     * @Param: id:管理员编号
     * @return: 管理员信息
     * @Author: Aaron Ke
     */
    @Override
    public Admin getAdminByAdmid(String id) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andAdmidEqualTo(Integer.parseInt(id));
        List<Admin> list = null;
        try {
            //得到管理员信息
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

}
