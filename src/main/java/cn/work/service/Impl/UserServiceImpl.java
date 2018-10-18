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

import static cn.work.util.SHAUtil.getEncrypt;

@Service("UserService")
public class UserServiceImpl implements UserService {
    public static final int limitDay = 30;
    public static final int limitBorrowNum = 3;

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
        user.setPassword(getEncrypt("88888888"));
        userMapper.insert(user);
        return true;
    }

    @Override
    public void delUser(String id) throws Exception {
        int userid = Integer.parseInt(id);
        String errorMsg = "";
        int notReturnNum = getNotReturnNum(id);
        double notPayNum = getTicketNum(id);
        if (notReturnNum > 0) {
            errorMsg += "有未还图书！";
        }
        if (notPayNum > 0) {
            errorMsg += "有未处理罚款！";
        }
        if(notReturnNum > 0||notPayNum > 0){
            throw new Exception(errorMsg);
        }
        userinfoMapper.deleteByPrimaryKey(userid);

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
        BorrowExample borrowExample = new BorrowExample();
        borrowExample.createCriteria().andUseridEqualTo(Integer.parseInt(id))
                .andReturntimeIsNull();
        int borrowNum = borrowMapper.countByExample(borrowExample);
        return borrowNum;
    }

    @Override
    public int getOverDueNum(String id){
        BorrowExample borrowExample = new BorrowExample();
        borrowExample.createCriteria().andUseridEqualTo(Integer.parseInt(id))
                .andReturntimeIsNull().andLimittimeLessThan(new Date());
        int borrowNum = borrowMapper.countByExample(borrowExample);
        return borrowNum;
    }

    @Override
    public int getTicketNum(String id) {
        return ticketMapper.countUserTicket(Integer.parseInt(id));
    }


    @Override
    public Userinfo updateUser(Userinfo userinfo) {
        UserinfoExample example=new UserinfoExample();
        example.createCriteria().andUseridEqualTo(userinfo.getUserid());
        try{
            userinfoMapper.updateByExampleSelective(userinfo,example);
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
        Double [] fees=ticketMapper.getUserFee(Integer.parseInt(id));
        double sum=0;
        for(double fee:fees){
            sum+=fee;
        }
        return sum;
    }


}

