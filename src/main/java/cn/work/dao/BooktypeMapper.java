package cn.work.dao;

import cn.work.pojo.Booktype;
import cn.work.pojo.BooktypeExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BooktypeMapper {
    int countByExample(BooktypeExample example);

    int deleteByExample(BooktypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Booktype record);

    int insertSelective(Booktype record);

    List<Booktype> selectByExample(BooktypeExample example);

    Booktype selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Booktype record, @Param("example") BooktypeExample example);

    int updateByExample(@Param("record") Booktype record, @Param("example") BooktypeExample example);

    int updateByPrimaryKeySelective(Booktype record);

    int updateByPrimaryKey(Booktype record);

    List<Booktype> getAllTypes();
}