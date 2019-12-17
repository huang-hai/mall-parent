package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.UmsAdminMapper;
import fun.huanghai.mall.pojo.UmsAdmin;
import fun.huanghai.mall.pojo.UmsAdminExample;
import fun.huanghai.mall.service.UmsAdminService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService{

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    /**
     * 登录功能
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public UmsAdmin login(String username, String password) {
        UmsAdminExample example = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        //spring自带md5加密
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        criteria.andPasswordEqualTo(md5Password);
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(example);
        if(umsAdmins.size() > 0) return umsAdmins.get(0);
        return null;
    }

    /**
     * 获取当前登录用户的信息
     *
     * @param username
     * @return
     */
    @Override
    public UmsAdmin getUserInfo(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(example);
        if(umsAdmins.size() > 0) return umsAdmins.get(0);
        return null;
    }
}
