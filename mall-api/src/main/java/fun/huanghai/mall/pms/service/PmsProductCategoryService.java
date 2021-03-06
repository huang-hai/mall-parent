package fun.huanghai.mall.pms.service;

import fun.huanghai.mall.pms.pojo.PmsProductCategory;
import fun.huanghai.mall.pms.pojo.PmsProductCategoryExpand;
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

    /**
     * 更改显示状态
     * @param ids
     * @param status
     * @return
     */
    Integer updateShowStatus(Long[] ids,Integer status);

    /**
     * 更改导航栏显示状态
     * @param ids
     * @param status
     * @return
     */
    Integer updateNavStatus(Long[] ids,Integer status);

    /**
     * 获取所有分类以及子分类
     * @return
     */
    List<PmsProductCategoryExpand> queryWithChildren();
}
