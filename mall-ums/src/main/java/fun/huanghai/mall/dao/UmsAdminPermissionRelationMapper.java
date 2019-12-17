package fun.huanghai.mall.dao;

import fun.huanghai.mall.pojo.UmsAdminPermissionRelation;
import fun.huanghai.mall.pojo.UmsAdminPermissionRelationExample;

import java.util.List;

public interface UmsAdminPermissionRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminPermissionRelation record);

    int insertSelective(UmsAdminPermissionRelation record);

    List<UmsAdminPermissionRelation> selectByExample(UmsAdminPermissionRelationExample example);

    UmsAdminPermissionRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdminPermissionRelation record);

    int updateByPrimaryKey(UmsAdminPermissionRelation record);
}