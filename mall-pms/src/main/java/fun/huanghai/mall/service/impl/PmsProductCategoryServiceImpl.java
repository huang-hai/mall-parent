package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.PmsProductCategoryAttributeRelationDaoExpand;
import fun.huanghai.mall.dao.PmsProductCategoryMapper;
import fun.huanghai.mall.pms.pojo.PmsProductCategory;
import fun.huanghai.mall.pms.pojo.PmsProductCategoryAttributeRelation;
import fun.huanghai.mall.pms.pojo.PmsProductCategoryExample;
import fun.huanghai.mall.pms.service.PmsProductCategoryService;
import fun.huanghai.mall.sys.SysVariable;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 商品分类服务(dubbo提供者)
 */
@Service(methods = {
        @Method(name = "add",retries = 0)
})
public class PmsProductCategoryServiceImpl extends BaseServiceImpl<PmsProductCategory> implements PmsProductCategoryService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductCategoryServiceImpl.class);

    private PmsProductCategoryMapper pmsProductCategoryMapper;

    @Autowired
    @Qualifier("pmsProductCategoryMapper")
    public void setPmsProductCategoryMapper(PmsProductCategoryMapper pmsProductCategoryMapper) {
        this.pmsProductCategoryMapper = pmsProductCategoryMapper;
        super.setaClass(PmsProductCategoryMapper.class);
        super.setExampleClass(PmsProductCategoryExample.class);
    }

    @Autowired
    @Qualifier("pmsProductCategoryAttributeRelationDaoExpand")
    private PmsProductCategoryAttributeRelationDaoExpand pmsProductCategoryAttributeRelationDaoExpand;


    /**
     * 添加产品分类
     *
     * @param productCategory
     * @param productAttributeIdList 产品相关筛选属性集合
     * @return
     */
    @Transactional
    @Override
    public Integer addProductCategory(PmsProductCategory productCategory, List<Long> productAttributeIdList) {
        try {
            PmsProductCategoryExample example = new PmsProductCategoryExample();
            example.createCriteria().andNameEqualTo(productCategory.getName());
            List<PmsProductCategory> pmsProductCategories = super.queryByCondition(example);
            if(pmsProductCategories.size()>0) return SysVariable.PRODUCTCATEGORYNAME_EXIST;
            Integer row = super.add(productCategory);
            if(row>0){
                Set<Long> attrIds = new HashSet<>();
                attrIds.addAll(productAttributeIdList);
                List<PmsProductCategoryAttributeRelation> list = new ArrayList<>();
                attrIds.forEach(attrId -> {
                    if(attrId > 0){
                        PmsProductCategoryAttributeRelation pmsProductCategoryAttributeRelation = new PmsProductCategoryAttributeRelation();
                        pmsProductCategoryAttributeRelation.setProductAttributeId(attrId);
                        pmsProductCategoryAttributeRelation.setProductCategoryId(productCategory.getId());
                        list.add(pmsProductCategoryAttributeRelation);
                    }
                });
                row = pmsProductCategoryAttributeRelationDaoExpand.insertAll(list);
                if(row>0) return SysVariable.SYS_SUCCESS;
                return SysVariable.SYS_FAILURE;
            }
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            error(e,"addProductCategory");
            throw new RuntimeException(SysVariable.SYS_ERROR_MES);
        }
    }

    /**
     * 更新商品分类
     *
     * @param productCategory
     * @param productAttributeIdList
     * @return
     */
    @Override
    public Integer updateProductCategory(PmsProductCategory productCategory, List<Long> productAttributeIdList) {
        try {
            PmsProductCategoryExample example = new PmsProductCategoryExample();
            example.createCriteria().andNameEqualTo(productCategory.getName())
            .andIdNotEqualTo(productCategory.getId());
            List<PmsProductCategory> pmsProductCategories = super.queryByCondition(example);
            if(pmsProductCategories.size()>0) return SysVariable.PRODUCTCATEGORYNAME_EXIST;
            Integer row = super.edit(productCategory);
            if(row>0){
                row = pmsProductCategoryAttributeRelationDaoExpand.deleteBySelective(productCategory.getId(),"product_category_id");
                LOGGER.info("PmsProductCategoryServiceImpl.updateProductCategory-->受影响行："+row);
                Set<Long> attrIds = new HashSet<>();
                attrIds.addAll(productAttributeIdList);
                List<PmsProductCategoryAttributeRelation> list = new ArrayList<>();
                attrIds.forEach(attrId -> {
                    if(attrId > 0){
                        PmsProductCategoryAttributeRelation pmsProductCategoryAttributeRelation = new PmsProductCategoryAttributeRelation();
                        pmsProductCategoryAttributeRelation.setProductAttributeId(attrId);
                        pmsProductCategoryAttributeRelation.setProductCategoryId(productCategory.getId());
                        list.add(pmsProductCategoryAttributeRelation);
                    }
                });
                row = pmsProductCategoryAttributeRelationDaoExpand.insertAll(list);
                if(row>0) return SysVariable.SYS_SUCCESS;
                return SysVariable.SYS_FAILURE;
            }
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            error(e,"updateProductCategory");
            throw new RuntimeException(SysVariable.SYS_ERROR_MES);
        }
    }

    @Override
    public Integer del(Long id) {
        try {
            //删除分类和属性的关系
            int row = pmsProductCategoryAttributeRelationDaoExpand.deleteBySelective(id, "product_category_id");
            LOGGER.info("PmsProductCategoryServiceImpl.del-->受影响行："+row);
            row = super.del(id);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"del");
        }
    }


}
