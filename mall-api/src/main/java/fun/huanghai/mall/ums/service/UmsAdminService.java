package fun.huanghai.mall.ums.service;

import fun.huanghai.mall.service.BaseService;
import fun.huanghai.mall.ums.pojo.UmsAdmin;
import fun.huanghai.mall.ums.pojo.UmsAdminExpand;
import fun.huanghai.mall.ums.pojo.UmsPermission;

import java.util.List;

/**
 * Admin模块服务接口
 */
public interface UmsAdminService extends BaseService<UmsAdmin>{

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    UmsAdmin login(String username, String password);

    /**
     * 获取当前登录用户的信息
     * @param username
     * @return
     */
    UmsAdminExpand queryAdminInfo(String username);

    /**
     * 更新密码
     * @param username
     * @param oldPass
     * @param newPass
     * @return
     */
    Integer updatePassword(String username, String oldPass ,String newPass);
}
