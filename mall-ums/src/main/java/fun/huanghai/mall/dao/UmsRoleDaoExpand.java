package fun.huanghai.mall.dao;


import fun.huanghai.mall.ums.pojo.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRoleDaoExpand extends UmsRoleMapper{

    /**
     * 获取用户角色列表
     * @param adminId
     * @return
     */
    List<UmsRole> queryByAdminId(@Param("adminId") Long adminId);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    int delAll(@Param("ids") Long[] ids);
}