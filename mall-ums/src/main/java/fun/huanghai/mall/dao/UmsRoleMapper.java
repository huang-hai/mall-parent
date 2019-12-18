package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsRole;
import fun.huanghai.mall.ums.pojo.UmsRoleExample;

import java.util.List;

public interface UmsRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsRole record);

    int insertSelective(UmsRole record);

    List<UmsRole> selectByExample(UmsRoleExample example);

    UmsRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsRole record);

    int updateByPrimaryKey(UmsRole record);
}