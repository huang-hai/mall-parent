package fun.huanghai.mall.ums.service;

import fun.huanghai.mall.service.BaseService;
import fun.huanghai.mall.ums.pojo.UmsPermission;
import fun.huanghai.mall.ums.pojo.UmsRole;

import java.util.List;

public interface UmsRoleService extends BaseService<UmsRole>{

    /**
     * 查询用户角色列表
     * @param adminId
     * @return
     */
    List<UmsRole> queryByAdminId(Long adminId);

    /**
     * 添加用户角色关系
     * @param adminId
     * @param roleIds
     * @return
     */
    Integer addAdminRoleRelation(Long adminId,List<Long> roleIds);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Integer delAll(Long[] ids);

    /**
     * 根据角色ID查找
     * @param roleId
     * @return
     */
    List<UmsPermission> queryByRoleId(Long roleId);

    /**
     * 添加(更新)角色权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    Integer addRolePermissionRelation(Long roleId,List<Long> permissionIds);

}
