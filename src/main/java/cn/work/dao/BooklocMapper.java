package cn.work.dao;

import cn.work.pojo.Bookloc;
import cn.work.pojo.BooklocExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooklocMapper {
    int countByExample(BooklocExample example);

    int deleteByExample(BooklocExample example);

    int deleteByPrimaryKey(Integer bookid);

    int insert(Bookloc record);

    int insertSelective(Bookloc record);

    List<Bookloc> selectByExample(BooklocExample example);

    Bookloc selectByPrimaryKey(Integer bookid);

    int updateByExampleSelective(@Param("record") Bookloc record, @Param("example") BooklocExample example);

    int updateByExample(@Param("record") Bookloc record, @Param("example") BooklocExample example);

    int updateByPrimaryKeySelective(Bookloc record);

    int updateByPrimaryKey(Bookloc record);
}