package cn.work.dao;

import cn.work.pojo.Borrowedbooks;
import cn.work.pojo.BorrowedbooksExample;
import cn.work.pojo.BorrowedbooksKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowedbooksMapper {
    int countByExample(BorrowedbooksExample example);

    int deleteByExample(BorrowedbooksExample example);

    int deleteByPrimaryKey(BorrowedbooksKey key);

    int insert(Borrowedbooks record);

    int insertSelective(Borrowedbooks record);

    List<Borrowedbooks> selectByExample(BorrowedbooksExample example);

    Borrowedbooks selectByPrimaryKey(BorrowedbooksKey key);

    int updateByExampleSelective(@Param("record") Borrowedbooks record, @Param("example") BorrowedbooksExample example);

    int updateByExample(@Param("record") Borrowedbooks record, @Param("example") BorrowedbooksExample example);

    int updateByPrimaryKeySelective(Borrowedbooks record);

    int updateByPrimaryKey(Borrowedbooks record);
}