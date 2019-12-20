package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsAdminPermissionRelation;
import fun.huanghai.mall.ums.pojo.UmsAdminPermissionRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsAdminPermissionRelationDaoExpand extends UmsAdminPermissionRelationMapper{

    int insertAll(List<UmsAdminPermissionRelation> records);

    /**
     * 自定义： name=value删除
     * @param name
     * @param value
     * @return
     */
    int deleteBySelective(@Param("name") String name, @Param("val") Long value);
}