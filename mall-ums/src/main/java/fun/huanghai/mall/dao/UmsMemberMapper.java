package fun.huanghai.mall.dao;

import fun.huanghai.mall.pojo.UmsMember;
import fun.huanghai.mall.pojo.UmsMemberExample;

import java.util.List;

public interface UmsMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMember record);

    int insertSelective(UmsMember record);

    List<UmsMember> selectByExample(UmsMemberExample example);

    UmsMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMember record);

    int updateByPrimaryKey(UmsMember record);
}