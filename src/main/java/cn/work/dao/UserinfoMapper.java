package cn.work.dao;

import cn.work.pojo.Userinfo;
import cn.work.pojo.UserinfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserinfoMapper {
    int countByExample(UserinfoExample example);

    int deleteByExample(UserinfoExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    List<Userinfo> selectByExample(UserinfoExample example);

    Userinfo selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Userinfo record, @Param("example") UserinfoExample example);

    int updateByExample(@Param("record") Userinfo record, @Param("example") UserinfoExample example);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);

    //自己的语句
    List<Userinfo> getAllUsers();

    @Select("select * from userInfo where username like CONCAT('%',#{username},'%') " +
            "and idcard like CONCAT('%',#{idcard},'%') " +
            "and usertele like CONCAT('%',#{usertele},'%')")
    List<Userinfo> searchUsers(@Param("username") String username, @Param("idcard") String idcard, @Param("usertele") String usertele);
}