package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsAdminExpand;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminDaoExpand extends UmsAdminMapper{

    /**
     * 根据用户名查询角色列表
     * @param username
     * @return
     */
    UmsAdminExpand findByUsername(@Param("username") String username);
}
