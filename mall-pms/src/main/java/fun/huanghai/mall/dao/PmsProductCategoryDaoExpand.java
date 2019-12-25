package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductCategoryExpand;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PmsProductCategoryDaoExpand extends PmsProductCategoryMapper{

    /**
     * 批量自定义更新状态
     * name=value
     * @param ids
     * @param name 字段名
     * @param status 字段值
     * @return
     */
    int updateAllStatus(@Param("ids") Long[] ids,
                        @Param("name") String name,
                        @Param("val") Integer status);

    /**
     * 查询所有商品分类及子分类
     * 利用mybatis的select属性递归调用
     * @param parentId
     * @return
     */
    List<PmsProductCategoryExpand> queryByParent(@Param("parentId") Long parentId);
}