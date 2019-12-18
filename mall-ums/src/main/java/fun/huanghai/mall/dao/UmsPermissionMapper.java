package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsPermission;
import fun.huanghai.mall.ums.pojo.UmsPermissionExample;

import java.util.List;

public interface UmsPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsPermission record);

    int insertSelective(UmsPermission record);

    List<UmsPermission> selectByExample(UmsPermissionExample example);

    UmsPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsPermission record);

    int updateByPrimaryKey(UmsPermission record);
}