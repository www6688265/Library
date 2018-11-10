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

    @Override
    public List<Userinfo> getAllUsers() {
        List<Userinfo> usersList = userinfoMapper.getAllUsers();
        return usersList;
    }


    @Override
    public boolean addUser(Userinfo userinfo) throws Exception {
        UserinfoExample example = new UserinfoExample();
        example.createCriteria().andIdcardEqualTo(userinfo.getIdcard());
        List<Userinfo> list = userinfoMapper.selectByExample(example);
        if (list.size() > 0) {
            return false;
        }
        userinfo.setAccess(1);
        userinfoMapper.insertSelective(userinfo);
        User user = new User();
        user.setUserid(userinfo.getUserid());
        user.setPassword(getEncrypt(initPassword));
        userMapper.insert(user);
        return true;
    }

    @Override
    public void showUser(String id, boolean display) throws Exception {
        int userid = Integer.parseInt(id);
        int status = display ? 1 : 0;
        if (!display) {
            String errorMsg = "";
            int notReturnNum = getNotReturnNum(id);
            double notPayNum = getTicketNum(id);
            if (notReturnNum > 0) {
                errorMsg += "有未还图书！";
            }
            if (notPayNum > 0) {
                errorMsg += "有未处理罚款！";
            }
            if (notReturnNum > 0 || notPayNum > 0) {
                throw new Exception(errorMsg);
            }
        }
        Userinfo userinfo = new Userinfo();
        userinfo.setUserid(userid);
        userinfo.setUserStatus(status);
        userinfoMapper.updateByPrimaryKeySelective(userinfo);
    }

    @Override
    public String getAccessDetail(String id) {
        String reason = "";
        int i = 1;
        int borrowNum = getNotReturnNum(id);
        if (borrowNum == limitBorrowNum) {
            reason += i + ".借书未还数量超过限制数量：" + limitBorrowNum + "</br>";
            i++;
        }
        int OverDueNum=getOverDueNum(id);
        if (OverDueNum > 0) {
            reason += i + ".有"+OverDueNum+"本过期未还图书 "+ "</br>";
        }
        double ticketNum = getTicketNum(id);
        if (ticketNum > 0) {
            reason += i + ".有罚款未处理 ";
        }
        return reason;
    }


    @Override
    public int getNotReturnNum(String id) {
        int borrowNum = borrowMapper.countUserNotReturn(Integer.parseInt(id));
        return borrowNum;
    }

    @Override
    public int getOverDueNum(String id){
        int borrowNum = borrowMapper.countUserOverDueNum(Integer.parseInt(id));
        return borrowNum;
    }

    @Override
    public int getTicketNum(String id) {
        return ticketMapper.countUserTicket(Integer.parseInt(id));
    }



    @Override
    public Userinfo updateUser(Userinfo userinfo) {
        try{
            userinfoMapper.updateByPrimaryKeySelective(userinfo);
        }
        catch (DuplicateKeyException e)
        {
            e.printStackTrace();
            return null;
        }
        userinfo=userinfoMapper.selectByPrimaryKey(userinfo.getUserid());
        return userinfo;
    }

    @Override
    public Userinfo getUserByIDcard(String idcard) {
        UserinfoExample example=new UserinfoExample();
        example.createCriteria().andIdcardEqualTo(idcard);
        List<Userinfo> list=userinfoMapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }
        else
            return null;
    }

    @Override
    public double getUserFee(String id) {
        List<Ticket> list = ticketMapper.getUserFee(Integer.parseInt(id));
        double sum=0;
        for (Ticket ticket : list) {
            sum = sum + ticket.getFee().doubleValue();
        }
        return sum;
    }

    @Override
    public UserExt getUserAndPwdByIDCard(String idcard) {
        return userMapper.getUserAndPwdByID(idcard);
    }

    @Override
    public User getUserAndPwdByID(String userid) {
        return userMapper.selectByPrimaryKey(Integer.parseInt(userid));
    }

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

    @Override
    public void setUserAccess(String id, boolean access) {
        int userAccess = access ? 1 : 0;
        Userinfo userinfo = new Userinfo();
        userinfo.setUserid(Integer.parseInt(id));
        userinfo.setAccess(userAccess);
        userinfoMapper.updateByPrimaryKeySelective(userinfo);
    }

    @Override
    public void updatePwd(User user) {
        String pwd = user.getPassword();
        if (pwd != null && !pwd.equals("")) {
            pwd = getEncrypt(pwd);
            user.setPassword(pwd);
        }
        userMapper.updateByPrimaryKey(user);
    }


}

