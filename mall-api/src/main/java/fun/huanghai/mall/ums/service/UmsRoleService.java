package fun.huanghai.mall.ums.service;

import fun.huanghai.mall.service.BaseService;
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
}
