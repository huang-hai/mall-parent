package fun.huanghai.mall.service;

import fun.huanghai.mall.pojo.UmsAdmin;

public interface UmsAdminService {

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
    public UmsAdmin getUserInfo(String username);
}
