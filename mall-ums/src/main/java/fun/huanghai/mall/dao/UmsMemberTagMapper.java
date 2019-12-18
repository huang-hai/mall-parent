package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsMemberTag;
import fun.huanghai.mall.ums.pojo.UmsMemberTagExample;

import java.util.List;

public interface UmsMemberTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberTag record);

    int insertSelective(UmsMemberTag record);

    List<UmsMemberTag> selectByExample(UmsMemberTagExample example);

    UmsMemberTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMemberTag record);

    int updateByPrimaryKey(UmsMemberTag record);
}