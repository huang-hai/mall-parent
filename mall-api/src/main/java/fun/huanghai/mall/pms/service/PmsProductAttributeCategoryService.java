package fun.huanghai.mall.pms.service;

import fun.huanghai.mall.pms.pojo.PmsProductAttributeCategory;
import fun.huanghai.mall.pms.pojo.PmsProductAttributeCategoryExpand;
import fun.huanghai.mall.service.BaseService;

import java.util.List;

public interface PmsProductAttributeCategoryService extends BaseService<PmsProductAttributeCategory> {

    /**
     * 获取所有商品属性分类及旗下属性
     * @return
     */
    List<PmsProductAttributeCategoryExpand> queryAll();
}
