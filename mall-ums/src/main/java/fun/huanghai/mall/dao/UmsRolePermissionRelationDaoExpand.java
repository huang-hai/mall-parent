package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsRolePermissionRelation;
import fun.huanghai.mall.ums.pojo.UmsRolePermissionRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRolePermissionRelationDaoExpand extends UmsRolePermissionRelationMapper{

    /**
     * 自定义： name=value删除
     * @param name
     * @param value
     * @return
     */
    int deleteBySelective(@Param("name") String name, @Param("val") Long value);

    int insertAll(List<UmsRolePermissionRelation> records);


}