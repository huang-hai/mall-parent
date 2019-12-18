package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsAdminRoleRelation;
import fun.huanghai.mall.ums.pojo.UmsAdminRoleRelationExample;

import java.util.List;

public interface UmsAdminRoleRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminRoleRelation record);

    int insertSelective(UmsAdminRoleRelation record);

    List<UmsAdminRoleRelation> selectByExample(UmsAdminRoleRelationExample example);

    UmsAdminRoleRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdminRoleRelation record);

    int updateByPrimaryKey(UmsAdminRoleRelation record);
}