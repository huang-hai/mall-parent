package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.UmsAdminRoleRelationDaoExpand;
import fun.huanghai.mall.dao.UmsRoleDaoExpand;
import fun.huanghai.mall.dao.UmsRoleMapper;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.ums.pojo.UmsAdminRoleRelation;
import fun.huanghai.mall.ums.pojo.UmsRole;
import fun.huanghai.mall.ums.pojo.UmsRoleExample;
import fun.huanghai.mall.ums.service.UmsRoleService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(methods = {
        @Method(name = "add",retries = 0)
})
public class UmsRoleServiceImpl extends BaseServiceImpl<UmsRole> implements UmsRoleService{


    private static final Logger LOGGER = LoggerFactory.getLogger(UmsRoleServiceImpl.class);

    private UmsRoleMapper umsRoleMapper;

    @Autowired
    @Qualifier("umsRoleDaoExpand")
    private UmsRoleDaoExpand umsRoleDaoExpand;

    @Autowired
    @Qualifier("umsAdminRoleRelationDaoExpand")
    private UmsAdminRoleRelationDaoExpand umsAdminRoleRelationDaoExpand;

    @Autowired
    @Qualifier("umsRoleMapper")
    public void setUmsRoleMapper(UmsRoleMapper umsRoleMapper) {
        this.umsRoleMapper = umsRoleMapper;
        super.setaClass(UmsRoleMapper.class);
        super.setExampleClass(UmsRoleExample.class);
    }

    /**
     * 查询用户角色列表
     *
     * @param adminId
     * @return
     */
    @Override
    public List<UmsRole> queryByAdminId(Long adminId) {
        return umsRoleDaoExpand.queryByAdminId(adminId);
    }

    /**
     * 添加用户角色关系
     *
     * @param adminId
     * @param roleIds
     * @return
     */
    @Override
    public Integer addAdminRoleRelation(Long adminId, List<Long> roleIds) {
        try {
            int row = umsAdminRoleRelationDaoExpand.deleteBySelective("admin_id", adminId);
            LOGGER.info("UmsRoleServiceImpl.addAdminRoleRelation-->受影响行："+row);
            if(roleIds.size()>0){
                Set<Long> ids = new HashSet<>();
                //去重操作
                ids.addAll(roleIds);
                List<UmsAdminRoleRelation> records = new ArrayList<>();
                ids.forEach(id -> {
                    UmsAdminRoleRelation record = new UmsAdminRoleRelation();
                    record.setAdminId(adminId);
                    record.setRoleId(id);
                    records.add(record);
                });

                row = umsAdminRoleRelationDaoExpand.insertAll(records);
                if(row>0) return SysVariable.SYS_SUCCESS;
                return SysVariable.SYS_FAILURE;
            }
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("UmsRoleServiceImpl.addAdminRoleRelation-->Exception,{}",e.getStackTrace());
            return SysVariable.SYS_ERROR;
        }
    }

    @Override
    public Integer delAll(Long[] ids) {
        try {
            int row = umsRoleDaoExpand.delAll(ids);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("UmsRoleServiceImpl.delAll-->Exception,{}",e.getStackTrace());
            return SysVariable.SYS_ERROR;
        }
    }

    @Override
    public Integer add(UmsRole role) {
        try {
            UmsRoleExample example = new UmsRoleExample();
            example.createCriteria().andNameEqualTo(role.getName());
            List<UmsRole> umsRoles = super.queryByCondition(example);
            if(umsRoles.size()>0){
                return SysVariable.USERNAME_EXIST;
            }

            int row = super.add(role);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("UmsRoleServiceImpl.add-->Exception,{}",e.getStackTrace());
            return SysVariable.SYS_ERROR;
        }
    }


}
