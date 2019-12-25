package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.PmsProductAttributeCategoryDaoExpand;
import fun.huanghai.mall.dao.PmsProductAttributeCategoryMapper;
import fun.huanghai.mall.pms.pojo.PmsProductAttributeCategory;
import fun.huanghai.mall.pms.pojo.PmsProductAttributeCategoryExample;
import fun.huanghai.mall.pms.pojo.PmsProductAttributeCategoryExpand;
import fun.huanghai.mall.pms.service.PmsProductAttributeCategoryService;
import fun.huanghai.mall.sys.SysVariable;
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
public class PmsProductAttributeCategoryServiceImpl extends BaseServiceImpl<PmsProductAttributeCategory> implements PmsProductAttributeCategoryService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductAttributeCategoryServiceImpl.class);

    private PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;

    @Autowired
    @Qualifier("pmsProductAttributeCategoryMapper")
    public void setPmsProductAttributeCategoryMapper(PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper) {
        this.pmsProductAttributeCategoryMapper = pmsProductAttributeCategoryMapper;
        super.setaClass(PmsProductAttributeCategoryMapper.class);
        super.setExampleClass(PmsProductAttributeCategoryExample.class);
    }

    @Autowired
    @Qualifier("pmsProductAttributeCategoryDaoExpand")
    private PmsProductAttributeCategoryDaoExpand pmsProductAttributeCategoryDaoExpand;

    @Override
    public Integer add(PmsProductAttributeCategory pmsProductAttributeCategory) {
        try {
            PmsProductAttributeCategoryExample example = new PmsProductAttributeCategoryExample();
            example.createCriteria().andNameEqualTo(pmsProductAttributeCategory.getName());
            List<PmsProductAttributeCategory> pmsProductAttributeCategories = super.queryByCondition(example);
            if(pmsProductAttributeCategories.size()>0) return SysVariable.PRODUCTATTRCATENAME_EXIST;
            return super.add(pmsProductAttributeCategory);
        } catch (Exception e) {
            return error(e,"add");
        }
    }

    @Override
    public Integer edit(PmsProductAttributeCategory pmsProductAttributeCategory) {
        try {
            PmsProductAttributeCategoryExample example = new PmsProductAttributeCategoryExample();
            example.createCriteria().andNameEqualTo(pmsProductAttributeCategory.getName())
            .andIdNotEqualTo(pmsProductAttributeCategory.getId());
            List<PmsProductAttributeCategory> pmsProductAttributeCategories = super.queryByCondition(example);
            if(pmsProductAttributeCategories.size()>0) return SysVariable.PRODUCTATTRCATENAME_EXIST;
            return super.edit(pmsProductAttributeCategory);
        } catch (Exception e) {
            return error(e,"edit");
        }
    }

    /**
     * 获取所有商品属性分类及旗下属性
     *
     * @return
     */
    @Override
    public List<PmsProductAttributeCategoryExpand> queryAll() {
        return pmsProductAttributeCategoryDaoExpand.queryAll();
    }
}
