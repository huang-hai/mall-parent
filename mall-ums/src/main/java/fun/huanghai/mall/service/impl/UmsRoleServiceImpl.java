package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.UmsAdminRoleRelationDaoExpand;
import fun.huanghai.mall.dao.UmsRoleDaoExpand;
import fun.huanghai.mall.dao.UmsRoleMapper;
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
                return umsAdminRoleRelationDaoExpand.insertAll(records);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("UmsRoleServiceImpl.addAdminRoleRelation-->Exception,{}",e.getStackTrace());
            return -1;
        }
    }
}
