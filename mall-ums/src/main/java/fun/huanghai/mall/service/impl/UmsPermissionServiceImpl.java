package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.UmsAdminPermissionRelationDaoExpand;
import fun.huanghai.mall.dao.UmsPermissionDaoExpand;
import fun.huanghai.mall.dao.UmsPermissionMapper;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.ums.pojo.UmsAdminPermissionRelation;
import fun.huanghai.mall.ums.pojo.UmsPermission;
import fun.huanghai.mall.ums.pojo.UmsPermissionExample;
import fun.huanghai.mall.ums.pojo.UmsPermissionExpand;
import fun.huanghai.mall.ums.service.UmsPermissionService;
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
        @Method(name = "add",retries = 0),
        @Method(name = "addAdminPermissionRelation",retries = 0)
})
public class UmsPermissionServiceImpl extends BaseServiceImpl<UmsPermission> implements UmsPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsPermissionServiceImpl.class);

    private UmsPermissionMapper umsPermissionMapper;

    @Autowired
    @Qualifier("umsPermissionMapper")
    public void setUmsPermissionMapper(UmsPermissionMapper umsPermissionMapper) {
        this.umsPermissionMapper = umsPermissionMapper;
        super.setaClass(UmsPermissionMapper.class);
        super.setExampleClass(UmsPermissionExample.class);
    }

    @Autowired
    @Qualifier("umsPermissionDaoExpand")
    private UmsPermissionDaoExpand umsPermissionDaoExpand;

    @Autowired
    @Qualifier("umsAdminPermissionRelationDaoExpand")
    private UmsAdminPermissionRelationDaoExpand umsAdminPermissionRelationDaoExpand;



    /**
     * 获取用户权限列表
     *
     * @param adminId
     * @return
     */
    @Override
    public List<UmsPermission> queryByAdminId(Long adminId) {
        List<UmsPermission> permissions = umsPermissionDaoExpand.queryByAdminRole(adminId);
        permissions.addAll(umsPermissionDaoExpand.queryByAdmin(adminId));
        return permissions;
    }

    /**
     * 添加后台用户权限关系
     *
     * @param permissionIds
     * @return
     */
    @Override
    public Integer addAdminPermissionRelation(Long adminId,List<Long> permissionIds) {
        try {
            //先删除原有的全部权限
            int row = umsAdminPermissionRelationDaoExpand.deleteBySelective("admin_id",adminId);
            LOGGER.info("UmsPermissionServiceImpl.addAdminPermissionRelation-->受影响行："+row);
            if(permissionIds.size()>0){
                Set<Long> ids = new HashSet<>();
                //去重操作
                ids.addAll(permissionIds);
                List<UmsAdminPermissionRelation> records = new ArrayList<>();
                ids.forEach(id -> {
                    UmsAdminPermissionRelation record = new UmsAdminPermissionRelation();
                    record.setAdminId(adminId);
                    record.setPermissionId(id);
                    records.add(record);
                });
                row = umsAdminPermissionRelationDaoExpand.insertAll(records);
                if(row>0) return SysVariable.SYS_SUCCESS;
                return SysVariable.SYS_FAILURE;
            }
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"addAdminPermissionRelation");
        }
    }

    /**
     * 批量删除权限
     *
     * @param ids
     * @return
     */
    @Override
    public Integer delAll(Long[] ids) {
        try {
            if(ids.length==0) return SysVariable.SYS_FAILURE;
            int row = umsPermissionDaoExpand.delAll(ids);
            if(row>0)return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"delAll");
        }
    }

    /**
     * 查询树形权限列表
     *
     * @return
     */
    @Override
    public List<UmsPermissionExpand> queryTreeList() {
        return umsPermissionDaoExpand.queryTreeByPId(0l);
    }

    @Override
    public Integer add(UmsPermission umsPermission) {
        try {
            UmsPermissionExample example = new UmsPermissionExample();
            example.createCriteria().andNameEqualTo(umsPermission.getName());
            List<UmsPermission> umsPermissions = super.queryByCondition(example);
            if(umsPermissions.size()>0) return SysVariable.PERMISSIONNAME_EXIST;

            return super.add(umsPermission);
        } catch (Exception e) {
            return error(e,"add");
        }
    }

    @Override
    public Integer edit(UmsPermission umsPermission) {
        try {
            UmsPermissionExample example = new UmsPermissionExample();
            example.createCriteria().andNameEqualTo(umsPermission.getName())
            .andIdNotEqualTo(umsPermission.getId());
            List<UmsPermission> umsPermissions = super.queryByCondition(example);
            if(umsPermissions.size()>0) return SysVariable.PERMISSIONNAME_EXIST;

            return super.edit(umsPermission);
        } catch (Exception e) {
            return error(e,"edit");
        }
    }

    /**
     * 根据条件查找
     *
     * @param obj
     * @return
     */
    @Override
    public List<UmsPermission> queryByCondition(Object obj) {
        UmsPermissionExample example = new UmsPermissionExample();
        example.createCriteria().andStatusEqualTo(1);
        return super.queryByCondition(example);
    }
}
