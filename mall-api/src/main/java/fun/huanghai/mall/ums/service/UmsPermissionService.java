package fun.huanghai.mall.ums.service;

import fun.huanghai.mall.service.BaseService;
import fun.huanghai.mall.ums.pojo.UmsAdminPermissionRelation;
import fun.huanghai.mall.ums.pojo.UmsPermission;
import fun.huanghai.mall.ums.pojo.UmsPermissionExpand;

import java.util.List;

/**
 * 权限模块服务接口
 */
public interface UmsPermissionService extends BaseService<UmsPermission> {

    /**
     * 获取用户权限列表
     * @param adminId
     * @return
     */
    public List<UmsPermission> queryByAdminId(Long adminId);

    /**
     * 添加后台用户权限关系(批量)
     * @param permissionIds
     * @return
     */
    public Integer addAdminPermissionRelation(Long adminId,List<Long> permissionIds);

    /**
     * 批量删除权限
     * @param ids
     * @return
     */
    Integer delAll(Long[] ids);

    /**
     * 查询树形权限列表
     * @return
     */
    List<UmsPermissionExpand> queryTreeList();
}
