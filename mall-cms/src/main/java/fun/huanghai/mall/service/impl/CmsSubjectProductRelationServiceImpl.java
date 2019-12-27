package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.cms.pojo.CmsSubjectProductRelation;
import fun.huanghai.mall.cms.pojo.CmsSubjectProductRelationExample;
import fun.huanghai.mall.cms.service.CmsSubjectProductRelationService;
import fun.huanghai.mall.dao.CmsSubjectProductRelationDaoExpand;
import fun.huanghai.mall.dao.CmsSubjectProductRelationMapper;
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
public class CmsSubjectProductRelationServiceImpl extends BaseServiceImpl<CmsSubjectProductRelation> implements CmsSubjectProductRelationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsSubjectProductRelationServiceImpl.class);

    private CmsSubjectProductRelationMapper cmsSubjectProductRelationMapper;

    @Autowired
    @Qualifier("cmsSubjectProductRelationMapper")
    public void setCmsSubjectProductRelationMapper(CmsSubjectProductRelationMapper cmsSubjectProductRelationMapper) {
        this.cmsSubjectProductRelationMapper = cmsSubjectProductRelationMapper;
        super.setaClass(CmsSubjectProductRelationMapper.class);
        super.setExampleClass(CmsSubjectProductRelationExample.class);
    }

    @Autowired
    @Qualifier("cmsSubjectProductRelationDaoExpand")
    private CmsSubjectProductRelationDaoExpand cmsSubjectProductRelationDaoExpand;


    /**
     * 批量保存
     *
     * @param list
     * @return
     */
    @Override
    public Integer addAll(List<CmsSubjectProductRelation> list) {
        try {
            if(list.size()==0) return SysVariable.SYS_FAILURE;
            int row = cmsSubjectProductRelationDaoExpand.insertAll(list);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"addAll");
        }
    }
}
