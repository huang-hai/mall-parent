package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsAdminRoleRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsAdminRoleRelationDaoExpand extends UmsAdminRoleRelationMapper{

    int insertAll(List<UmsAdminRoleRelation> records);

    /**
     * 自定义： name=value删除
     * @param name
     * @param value
     * @return
     */
    int deleteBySelective(@Param("name") String name,@Param("val") Long value);
}