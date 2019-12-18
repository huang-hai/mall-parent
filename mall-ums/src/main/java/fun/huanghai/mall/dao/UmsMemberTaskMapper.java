package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsMemberTask;
import fun.huanghai.mall.ums.pojo.UmsMemberTaskExample;

import java.util.List;

public interface UmsMemberTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberTask record);

    int insertSelective(UmsMemberTask record);

    List<UmsMemberTask> selectByExample(UmsMemberTaskExample example);

    UmsMemberTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMemberTask record);

    int updateByPrimaryKey(UmsMemberTask record);
}