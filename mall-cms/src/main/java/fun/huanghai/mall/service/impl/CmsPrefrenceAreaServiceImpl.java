package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.cms.pojo.CmsPrefrenceArea;
import fun.huanghai.mall.cms.pojo.CmsPrefrenceAreaExample;
import fun.huanghai.mall.cms.service.CmsPrefrenceAreaService;
import fun.huanghai.mall.dao.CmsPrefrenceAreaMapper;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 商品优选服务
 */
@Service(methods = {
        @Method(name = "add",retries = 0)
})
public class CmsPrefrenceAreaServiceImpl extends BaseServiceImpl<CmsPrefrenceArea> implements CmsPrefrenceAreaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsPrefrenceAreaServiceImpl.class);

    private CmsPrefrenceAreaMapper cmsPrefrenceAreaMapper;

    @Autowired
    @Qualifier("cmsPrefrenceAreaMapper")
    public void setCmsPrefrenceAreaMapper(CmsPrefrenceAreaMapper cmsPrefrenceAreaMapper) {
        this.cmsPrefrenceAreaMapper = cmsPrefrenceAreaMapper;
        super.setaClass(CmsPrefrenceAreaMapper.class);
        super.setExampleClass(CmsPrefrenceAreaExample.class);
    }
}
