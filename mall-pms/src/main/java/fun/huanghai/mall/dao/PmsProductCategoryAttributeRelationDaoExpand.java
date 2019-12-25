package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductCategoryAttributeRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductCategoryAttributeRelationDaoExpand extends PmsProductCategoryAttributeRelationMapper{

    /**
     * 批量增加
     * @param records
     * @return
     */
    int insertAll(@Param("records") List<PmsProductCategoryAttributeRelation> records);

    /**
     * 自定义删除
     * name = value
     * @param id
     * @param name
     * @return
     */
    int deleteBySelective(@Param("val")Long id,@Param("name") String name);
}