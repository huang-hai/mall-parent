package fun.huanghai.mall.dao;

import fun.huanghai.mall.pojo.UmsMemberLoginLog;
import fun.huanghai.mall.pojo.UmsMemberLoginLogExample;

import java.util.List;

public interface UmsMemberLoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberLoginLog record);

    int insertSelective(UmsMemberLoginLog record);

    List<UmsMemberLoginLog> selectByExample(UmsMemberLoginLogExample example);

    UmsMemberLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMemberLoginLog record);

    int updateByPrimaryKey(UmsMemberLoginLog record);
}