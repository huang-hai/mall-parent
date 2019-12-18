package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsMemberLevel;
import fun.huanghai.mall.ums.pojo.UmsMemberLevelExample;

import java.util.List;

public interface UmsMemberLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberLevel record);

    int insertSelective(UmsMemberLevel record);

    List<UmsMemberLevel> selectByExample(UmsMemberLevelExample example);

    UmsMemberLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMemberLevel record);

    int updateByPrimaryKey(UmsMemberLevel record);
}