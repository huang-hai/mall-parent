package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsPermission;
import fun.huanghai.mall.ums.pojo.UmsPermissionExpand;
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

    /**
     * 根据角色查找权限
     * @param roleId
     * @return
     */
    List<UmsPermission> queryByRole(@Param("roleId")Long roleId);

    /**
     * 获取数形列表(使用mybatis递归)
     * @param pid
     * @return
     */
    List<UmsPermissionExpand> queryTreeByPId(@Param("pid") Long pid);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int delAll(@Param("ids") Long[] ids);
}
