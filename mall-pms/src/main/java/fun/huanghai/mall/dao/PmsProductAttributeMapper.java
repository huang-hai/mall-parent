package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductAttribute;
import fun.huanghai.mall.pms.pojo.PmsProductAttributeExample;

import java.util.List;

public interface PmsProductAttributeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttribute record);

    int insertSelective(PmsProductAttribute record);

    List<PmsProductAttribute> selectByExample(PmsProductAttributeExample example);

    PmsProductAttribute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductAttribute record);

    int updateByPrimaryKey(PmsProductAttribute record);
}