package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.UmsAdminPermissionRelationDaoExpand;
import fun.huanghai.mall.dao.UmsPermissionDaoExpand;
import fun.huanghai.mall.dao.UmsPermissionMapper;
import fun.huanghai.mall.ums.pojo.UmsAdminPermissionRelation;
import fun.huanghai.mall.ums.pojo.UmsPermission;
import fun.huanghai.mall.ums.pojo.UmsPermissionExample;
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
                return umsAdminPermissionRelationDaoExpand.insertAll(records);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("UmsPermissionServiceImpl.addAdminPermissionRelation-->Exception,{}",e.getStackTrace());
            return -1;
        }
    }
}
