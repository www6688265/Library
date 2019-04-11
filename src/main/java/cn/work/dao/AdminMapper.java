package cn.work.dao;

import cn.work.pojo.Admin;
import cn.work.pojo.AdminExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer admid);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer admid);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    //自己写的yuju
    @Select("select  admid,idcard,admright from Admin")
    List<Admin> getAllAdmins();

    @Select("select admid,idcard,admright from admin where IDcard like CONCAT ( '%',#{username} ,'%' )")
    List<Admin> getAdmins(String username);

}