package cn.work.service.Impl;

import cn.work.dao.BorrowMapper;
import cn.work.dao.TicketMapper;
import cn.work.dao.UserMapper;
import cn.work.dao.UserinfoMapper;
import cn.work.pojo.*;
import cn.work.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static cn.work.spring.config.LibraryConfig.initPassword;
import static cn.work.spring.config.LibraryConfig.limitBorrowNum;
import static cn.work.util.SHAUtil.getEncrypt;

/**
 * @Description: 读者相关业务层实现类
 * @Author: Aaron Ke
 */
@Service("UserService")
public class UserServiceImpl implements UserService {


    @Resource
    UserinfoMapper userinfoMapper;
    @Resource
    BorrowMapper borrowMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    TicketMapper ticketMapper;

    /**
     * @Description: 得到所有用户信息
     * @Param: 无
     * @return: 用户信息
     * @Author: Aaron Ke
     */
    @Override
    public List<Userinfo> getAllUsers() {
        List<Userinfo> usersList = userinfoMapper.getAllUsers();
        return usersList;
    }


    /**
     * @Description: 增加用户
     * @Param: userinfo:用户信息
     * @return: 操作结果
     * @Author: Aaron Ke
     */
    @Override
    public boolean addUser(Userinfo userinfo) throws Exception {
        //检查是否存在相同的用户
        UserinfoExample example = new UserinfoExample();
        example.createCriteria().andIdcardEqualTo(userinfo.getIdcard());
        List<Userinfo> list = userinfoMapper.selectByExample(example);
        if (list.size() > 0) {
            return false;
        }
        //设置用户借书权限为可以结束
        userinfo.setAccess(1);
        userinfoMapper.insertSelective(userinfo);
        //设置用户初始密码
        User user = new User();
        user.setUserid(userinfo.getUserid());
        user.setPassword(getEncrypt(initPassword));
        userMapper.insert(user);
        return true;
    }

    /**
     * @Description: 设置用户状态
     * @Param: id:用户编号, display:用户的转=状态
     * @return: 无
     * @Author: Aaron Ke
     */
    @Override
    public void showUser(String id, boolean display) throws Exception {
        int userid = Integer.parseInt(id);
        int status = display ? 1 : 0;
        if (!display) {
            String errorMsg = "";
            int notReturnNum = getNotReturnNum(id);
            double notPayNum = getTicketNum(id);
            //判断用户是否有未归还图书
            if (notReturnNum > 0) {
                errorMsg += "有未还图书!";
            }
            //同上
            if (notPayNum > 0) {
                errorMsg += "有未处理罚款！";
            }
            //同上
            if (notReturnNum > 0 || notPayNum > 0) {
                throw new Exception(errorMsg);
            }
        }
        Userinfo userinfo = new Userinfo();
        userinfo.setUserid(userid);
        userinfo.setUserStatus(status);
        userinfoMapper.updateByPrimaryKeySelective(userinfo);
    }

    /**
     * @Description: 得到用户权限详情
     * @Param: id:用户编号
     * @return: 用户权限详情信息
     * @Author: Aaron Ke
     */
    @Override
    public String getAccessDetail(String id) {
        String reason = "";
        int i = 1;
        //查找用户是否有未归还图书
        int borrowNum = getNotReturnNum(id);
        if (borrowNum >= limitBorrowNum) {
            reason += i + ".借书未还数量超过限制数量：" + limitBorrowNum + "</br>";
            i++;
        }
        //查找用户是否有过期未还图书
        int OverDueNum=getOverDueNum(id);
        if (OverDueNum > 0) {
            reason += i + ".有"+OverDueNum+"本过期未还图书 "+ "</br>";
        }
        //查找用户是否有未处理罚款
        double ticketNum = getTicketNum(id);
        if (ticketNum > 0) {
            reason += i + ".有罚款未处理 ";
        }
        return reason;
    }

    /**
     * @Description: 得到未归还图书数量
     * @Param: id:用户编号
     * @return: 未归还图书数量
     * @Author: Aaron Ke
     */
    @Override
    public int getNotReturnNum(String id) {
        int borrowNum = borrowMapper.countUserNotReturn(Integer.parseInt(id));
        return borrowNum;
    }

    /**
     * @Description: 得到过期未还图书数量
     * @Param: id:用户编号
     * @return: 过期未还图书数量
     * @Author: Aaron Ke
     */
    @Override
    public int getOverDueNum(String id){
        int borrowNum = borrowMapper.countUserOverDueNum(Integer.parseInt(id));
        return borrowNum;
    }

    /**
     * @Description: 得到未处理罚单数量
     * @Param: id: 用户编号
     * @return: 未处理罚单数量
     * @Author: Aaron Ke
     */
    @Override
    public int getTicketNum(String id) {
        return ticketMapper.countUserTicket(Integer.parseInt(id));
    }

    /**
     * @Description: 更新用户信息
     * @Param: userinfo:用户信息
     * @return: 更新完的用户信息
     * @Author: Aaron Ke
     */
    @Override
    public Userinfo updateUser(Userinfo userinfo) {
        try{
            userinfoMapper.updateByPrimaryKeySelective(userinfo);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return null;
        }
        userinfo=userinfoMapper.selectByPrimaryKey(userinfo.getUserid());
        return userinfo;
    }

    /**
     * @Description: 通过身份证得到用户信息
     * @Param: idcard:用户身份证
     * @return: 用户信息
     * @Author: Aaron Ke
     */
    @Override
    public Userinfo getUserByIDcard(String idcard) {
        UserinfoExample example=new UserinfoExample();
        example.createCriteria().andIdcardEqualTo(idcard);
        List<Userinfo> list=userinfoMapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        } else
            return null;
    }

    /**
     * @Description: 得到用户的总共的罚款金额
     * @Param: id：用户编号
     * @return: 用户罚款金额
     * @Author: Aaron Ke
     */
    @Override
    public double getUserFee(String id) {
        List<Ticket> list = ticketMapper.getUserFee(Integer.parseInt(id));
        double sum=0;
        for (Ticket ticket : list) {
            sum = sum + ticket.getFee().doubleValue();
        }
        return sum;
    }

    /**
     * @Description: 通过身份证得到用户id和密码
     * @Param: idcard:身份证
     * @return: 用户id和密码
     * @Author: Aaron Ke
     */
    @Override
    public UserExt getUserAndPwdByIDCard(String idcard) {
        return userMapper.getUserAndPwdByID(idcard);
    }

    /**
     * @Description: 通过用户编号得到用户身份证和密码
     * @Param: userid:用户编号
     * @return: 用户id和密码
     * @Author: Aaron Ke
     */
    @Override
    public User getUserAndPwdByID(String userid) {
        return userMapper.selectByPrimaryKey(Integer.parseInt(userid));
    }

    /**
     * @Description: 得到用户的个人详细信息
     * @Param: id:用户编号
     * @return: 用户详细信息
     * @Author: Aaron Ke
     */
    @Override
    public Profile getProfile(String id) {
        Profile profile = new Profile();
        Userinfo user = userinfoMapper.selectByPrimaryKey(Integer.parseInt(id));
        profile.setAccess(user.getAccess());
        profile.setIdcard(user.getIdcard());
        profile.setUsername(user.getUsername());
        profile.setSex(user.getSex());

        profile.setOverDueNum(getOverDueNum(id));
        profile.setNotReturnNum(getNotReturnNum(id));
        profile.setUserFee(getUserFee(id));

        profile.setAccessDetail(getAccessDetail(id));
        return profile;
    }

    /**
     * @Description: 设置用户借书权限
     * @Param: id:用户编号，access:用户借书权限
     * @return: 无
     * @Author: Aaron Ke
     */
    @Override
    public void setUserAccess(String id, boolean access) {
        int userAccess = access ? 1 : 0;
        Userinfo userinfo = new Userinfo();
        userinfo.setUserid(Integer.parseInt(id));
        userinfo.setAccess(userAccess);
        userinfoMapper.updateByPrimaryKeySelective(userinfo);
    }

    /**
     * @Description: 更新用户密码
     * @Param: user:用户信息
     * @return: 无
     * @Author: Aaron Ke
     */
    @Override
    public void updatePwd(User user) {
        String pwd = user.getPassword();
        if (pwd != null && !pwd.equals("")) {
            //密码加密
            pwd = getEncrypt(pwd);
            user.setPassword(pwd);
        }
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<Userinfo> searchUsers(String username, String idcard, String usertele) {
        List<Userinfo> usersList = userinfoMapper.searchUsers(username, idcard, usertele);
        return usersList;

    }


}

