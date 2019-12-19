package fun.huanghai.mall.ums.service;

import fun.huanghai.mall.service.BaseService;
import fun.huanghai.mall.ums.pojo.UmsAdmin;
import fun.huanghai.mall.ums.pojo.UmsAdminExpand;
import fun.huanghai.mall.ums.pojo.UmsPermission;

import java.util.List;

public interface UmsAdminService extends BaseService<UmsAdmin>{

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    public UmsAdmin login(String username, String password);

    /**
     * 获取当前登录用户的信息
     * @param username
     * @return
     */
    public UmsAdminExpand queryAdminInfo(String username);

    /**
     * 获取用户权限列表
     * @param adminId
     * @return
     */
    public List<UmsPermission> queryByAdminId(Long adminId);
}
