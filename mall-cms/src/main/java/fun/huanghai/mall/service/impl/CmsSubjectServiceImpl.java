package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.cms.pojo.CmsSubject;
import fun.huanghai.mall.cms.pojo.CmsSubjectExample;
import fun.huanghai.mall.cms.service.CmsSubjectService;
import fun.huanghai.mall.dao.CmsSubjectMapper;
import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.vo.PageInfoVo;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 商品专题服务
 */
@Service(methods = {
        @Method(name = "add",retries = 0)
})
public class CmsSubjectServiceImpl extends BaseServiceImpl<CmsSubject> implements CmsSubjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsSubjectServiceImpl.class);

    private CmsSubjectMapper cmsSubjectMapper;

    @Autowired
    @Qualifier("cmsSubjectMapper")
    public void setCmsSubjectMapper(CmsSubjectMapper cmsSubjectMapper) {
        this.cmsSubjectMapper = cmsSubjectMapper;
        super.setaClass(CmsSubjectMapper.class);
        super.setExampleClass(CmsSubjectExample.class);
    }

    @Override
    public PageInfoVo queryPages(QueryPageParam queryPageParam) {
        CmsSubjectExample example = new CmsSubjectExample();
        example.createCriteria().andTitleLike("%"+queryPageParam.getObj()+"%");
        queryPageParam.setObj(example);
        return super.queryPages(queryPageParam);
    }
}
