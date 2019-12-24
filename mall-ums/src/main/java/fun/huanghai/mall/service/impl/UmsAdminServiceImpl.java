package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.UmsAdminDaoExpand;
import fun.huanghai.mall.dao.UmsAdminMapper;
import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.ums.pojo.UmsAdmin;
import fun.huanghai.mall.ums.pojo.UmsAdminExample;
import fun.huanghai.mall.ums.pojo.UmsAdminExpand;
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
     * 更新密码
     *
     * @param username
     * @param oldPass
     * @param newPass
     * @return
     */
    @Override
    public Integer updatePassword(String username, String oldPass, String newPass) {
        try {
            UmsAdminExample example = new UmsAdminExample();
            example.createCriteria().andUsernameEqualTo(username);
            List<UmsAdmin> umsAdmins = super.queryByCondition(example);
            if(umsAdmins.size()>0){
                UmsAdmin umsAdmin = umsAdmins.get(0);
                String password = umsAdmin.getPassword();
                String md5OldPass = DigestUtils.md5DigestAsHex(oldPass.getBytes());
                if(md5OldPass.equals(password)){
                    String md5NewPass = DigestUtils.md5DigestAsHex(newPass.getBytes());
                    umsAdmin.setPassword(md5NewPass);
                    return super.edit(umsAdmin);
                } else return SysVariable.PASSWORD_ERROR;
            } else return SysVariable.USERNAME_ERROR;
        } catch (Exception e) {
            return super.error(e,"updatePassword");
        }
    }

    @Override
    public Integer add(UmsAdmin umsAdmin) {
        try {
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
                return SysVariable.USERNAME_EXIST;
            }
            int row = super.add(umsAdmin);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return super.error(e,"add");
        }
    }

    @Override
    public PageInfoVo queryPages(QueryPageParam queryPageParam) {
        String name = (String) queryPageParam.getObj();
        UmsAdminExample example = new UmsAdminExample();
        if(StringUtils.isNotEmpty(name)){
            example.createCriteria().andUsernameLike("%"+name+"%");
            example.or(example.createCriteria().andNickNameLike("%"+name+"%"));
        }
        queryPageParam.setObj(example);
        return super.queryPages(queryPageParam);
    }
}
