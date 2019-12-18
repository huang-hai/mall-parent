package fun.huanghai.mall.ums.service;

import fun.huanghai.mall.service.BaseService;
import fun.huanghai.mall.ums.pojo.UmsAdmin;
import fun.huanghai.mall.ums.pojo.UmsAdminExpand;

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
    public UmsAdminExpand getAdminInfo(String username);
}
