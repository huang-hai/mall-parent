package fun.huanghai.mall.dao;

import fun.huanghai.mall.pojo.UmsRolePermissionRelation;
import fun.huanghai.mall.pojo.UmsRolePermissionRelationExample;

import java.util.List;

public interface UmsRolePermissionRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsRolePermissionRelation record);

    int insertSelective(UmsRolePermissionRelation record);

    List<UmsRolePermissionRelation> selectByExample(UmsRolePermissionRelationExample example);

    UmsRolePermissionRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsRolePermissionRelation record);

    int updateByPrimaryKey(UmsRolePermissionRelation record);
}