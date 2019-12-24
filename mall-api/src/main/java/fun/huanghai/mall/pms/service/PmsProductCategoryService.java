package fun.huanghai.mall.pms.service;

import fun.huanghai.mall.pms.pojo.PmsProductCategory;
import fun.huanghai.mall.service.BaseService;

import java.util.List;

public interface PmsProductCategoryService extends BaseService<PmsProductCategory>{

    /**
     * 添加产品分类
     * @param productCategory
     * @param productAttributeIdList 产品相关筛选属性集合
     * @return
     */
    Integer addProductCategory(PmsProductCategory productCategory,List<Long> productAttributeIdList);

    /**
     * 更新商品分类
     * @param productCategory
     * @param productAttributeIdList
     * @return
     */
    Integer updateProductCategory(PmsProductCategory productCategory,List<Long> productAttributeIdList);
}
