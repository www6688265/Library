package cn.work.dao;

import cn.work.pojo.User;
import cn.work.pojo.UserExample;
import cn.work.pojo.UserExt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("select user.userid,idcard,`password` from user,userinfo where userinfo.userID=user.userID and userinfo.IDcard=#{idcard}")
    UserExt getUserAndPwdByID(String idcard);

}