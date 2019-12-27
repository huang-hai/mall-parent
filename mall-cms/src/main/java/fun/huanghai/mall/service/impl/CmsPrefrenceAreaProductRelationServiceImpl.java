package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.cms.pojo.CmsPrefrenceAreaProductRelation;
import fun.huanghai.mall.cms.pojo.CmsPrefrenceAreaProductRelationExample;
import fun.huanghai.mall.cms.service.CmsPrefrenceAreaProductRelationService;
import fun.huanghai.mall.dao.CmsPrefrenceAreaProductRelationDaoExpand;
import fun.huanghai.mall.dao.CmsPrefrenceAreaProductRelationMapper;
import fun.huanghai.mall.sys.SysVariable;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * 优选专区和产品关系服务
 */
@Service(methods = {
        @Method(name = "add",retries = 0),
        @Method(name = "addAll",retries = 0)
})
public class CmsPrefrenceAreaProductRelationServiceImpl extends BaseServiceImpl<CmsPrefrenceAreaProductRelation> implements CmsPrefrenceAreaProductRelationService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsPrefrenceAreaProductRelationServiceImpl.class);

    private CmsPrefrenceAreaProductRelationMapper cmsPrefrenceAreaProductRelationMapper;

    @Autowired
    @Qualifier("cmsPrefrenceAreaProductRelationMapper")
    public void setCmsPrefrenceAreaProductRelationMapper(CmsPrefrenceAreaProductRelationMapper cmsPrefrenceAreaProductRelationMapper) {
        this.cmsPrefrenceAreaProductRelationMapper = cmsPrefrenceAreaProductRelationMapper;
        super.setaClass(CmsPrefrenceAreaProductRelationMapper.class);
        super.setExampleClass(CmsPrefrenceAreaProductRelationExample.class);
    }

    @Autowired
    @Qualifier("cmsPrefrenceAreaProductRelationDaoExpand")
    private CmsPrefrenceAreaProductRelationDaoExpand cmsPrefrenceAreaProductRelationDaoExpand;

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    @Override
    public Integer addAll(List<CmsPrefrenceAreaProductRelation> list) {
        try {
            if(list.size()==0) return SysVariable.SYS_FAILURE;
            int row = cmsPrefrenceAreaProductRelationDaoExpand.insertAll(list);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"addAll");
        }
    }
}
