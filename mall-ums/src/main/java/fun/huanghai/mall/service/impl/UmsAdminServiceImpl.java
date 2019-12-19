package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.UmsAdminDaoExpand;
import fun.huanghai.mall.dao.UmsAdminMapper;
import fun.huanghai.mall.dao.UmsPermissionDaoExpand;
import fun.huanghai.mall.exception.UmsException;
import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.ums.pojo.UmsAdmin;
import fun.huanghai.mall.ums.pojo.UmsAdminExample;
import fun.huanghai.mall.ums.pojo.UmsAdminExpand;
import fun.huanghai.mall.ums.pojo.UmsPermission;
import fun.huanghai.mall.ums.service.UmsAdminService;
import fun.huanghai.mall.vo.PageInfoVo;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service(methods = {
        @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
})
public class UmsAdminServiceImpl extends BaseServiceImpl<UmsAdmin> implements UmsAdminService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

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

    @Autowired
    @Qualifier("umsPermissionDaoExpand")
    private UmsPermissionDaoExpand umsPermissionDaoExpand;

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
    public UmsAdminExpand queryAdminInfo(String username) {
        UmsAdminExpand adminExpand = umsAdminDaoExpand.findByUsername(username);
        return adminExpand;
    }

    /**
     * 获取用户权限列表
     *
     * @param adminId
     * @return
     */
    @Override
    public List<UmsPermission> queryByAdminId(Long adminId) {
        List<UmsPermission> permissions = umsPermissionDaoExpand.queryByAdmin(adminId);
        permissions.addAll(umsPermissionDaoExpand.queryByAdminRole(adminId));
        return permissions;
    }

    @Override
    public Integer add(UmsAdmin umsAdmin) {
        String password = umsAdmin.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        umsAdmin.setPassword(md5Password);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);

        //查找用户名是否存在
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> datas = super.queryByCondition(example);
        if(datas.size() > 0){
            return -1;
        }
        return super.add(umsAdmin);
    }

    @Override
    public PageInfoVo queryPages(QueryPageParam queryPageParam) {
        String name = (String) queryPageParam.getObj();
        UmsAdminExample example = new UmsAdminExample();
        if(StringUtils.isNotEmpty(name)){
            example.createCriteria().andUsernameLike("%"+name+"%");
            example.or(example.createCriteria().andNickNameLike("%"+name+"%"));
        }
        return super.queryPages(queryPageParam);
    }
}
