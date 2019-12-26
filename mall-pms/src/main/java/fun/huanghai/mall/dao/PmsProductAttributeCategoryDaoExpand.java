package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductAttributeCategoryExpand;

import java.util.List;

public interface PmsProductAttributeCategoryDaoExpand extends PmsProductAttributeCategoryMapper{

    /**
     * 获取所有商品属性分类及旗下属性
     * @return
     */
    List<PmsProductAttributeCategoryExpand> queryAll();
}