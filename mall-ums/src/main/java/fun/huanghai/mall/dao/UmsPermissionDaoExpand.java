package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 扩展权限mapper
 */
public interface UmsPermissionDaoExpand extends UmsPermissionMapper {

    /**
     * 根据用户查找权限
     * @param adminId
     * @return
     */
    List<UmsPermission> queryByAdmin(@Param("adminId") Long adminId);

    /**
     * 根据用户角色查找权限
     * @param adminId
     * @return
     */
    List<UmsPermission> queryByAdminRole(@Param("adminId")Long adminId);

}
