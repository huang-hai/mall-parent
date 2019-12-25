package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.UmsMemberLevelMapper;
import fun.huanghai.mall.ums.pojo.UmsMemberLevel;
import fun.huanghai.mall.ums.pojo.UmsMemberLevelExample;
import fun.huanghai.mall.ums.service.UmsMemberLevelService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Service(methods = {
        @Method(name = "add",retries = 0)
})
public class UmsMemberLevelServiceImpl extends BaseServiceImpl<UmsMemberLevel> implements UmsMemberLevelService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberLevelServiceImpl.class);

    private UmsMemberLevelMapper umsMemberLevelMapper;

    @Autowired
    @Qualifier("umsMemberLevelMapper")
    public void setUmsMemberLevelMapper(UmsMemberLevelMapper umsMemberLevelMapper) {
        this.umsMemberLevelMapper = umsMemberLevelMapper;
        super.setaClass(UmsMemberLevelMapper.class);
        super.setExampleClass(UmsMemberLevelExample.class);
    }

    /**
     * 根据条件查找
     *
     * @param obj
     * @return
     */
    @Override
    public List<UmsMemberLevel> queryByCondition(Object obj) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        return super.queryByCondition(example);
    }
}
