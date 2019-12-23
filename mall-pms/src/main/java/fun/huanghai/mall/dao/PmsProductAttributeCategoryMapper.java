package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductAttributeCategory;
import fun.huanghai.mall.pms.pojo.PmsProductAttributeCategoryExample;

import java.util.List;

public interface PmsProductAttributeCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttributeCategory record);

    int insertSelective(PmsProductAttributeCategory record);

    List<PmsProductAttributeCategory> selectByExample(PmsProductAttributeCategoryExample example);

    PmsProductAttributeCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductAttributeCategory record);

    int updateByPrimaryKey(PmsProductAttributeCategory record);
}