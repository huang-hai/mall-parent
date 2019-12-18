package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.UmsAdminDaoExpand;
import fun.huanghai.mall.dao.UmsAdminMapper;
import fun.huanghai.mall.ums.pojo.UmsAdmin;
import fun.huanghai.mall.ums.pojo.UmsAdminExample;
import fun.huanghai.mall.ums.pojo.UmsAdminExpand;
import fun.huanghai.mall.ums.service.UmsAdminService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UmsAdminServiceImpl extends BaseServiceImpl<UmsAdmin> implements UmsAdminService{

    private UmsAdminMapper umsAdminMapper;

    @Autowired
    @Qualifier("umsAdminDaoExpand")
    private UmsAdminDaoExpand umsAdminDaoExpand;

    @Autowired
    @Qualifier("umsAdminMapper")
    public void setUmsAdminMapper(UmsAdminMapper umsAdminMapper) {
        this.umsAdminMapper = umsAdminMapper;
        super.setaClass(UmsAdminMapper.class);
        super.setExampleClass(UmsAdminExample.class);
    }


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
    public UmsAdminExpand getAdminInfo(String username) {
        UmsAdminExpand adminExpand = umsAdminDaoExpand.findByUsername(username);
        return adminExpand;
    }
}
