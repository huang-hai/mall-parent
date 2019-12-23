package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductAttributeValue;
import fun.huanghai.mall.pms.pojo.PmsProductAttributeValueExample;

import java.util.List;

public interface PmsProductAttributeValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttributeValue record);

    int insertSelective(PmsProductAttributeValue record);

    List<PmsProductAttributeValue> selectByExample(PmsProductAttributeValueExample example);

    PmsProductAttributeValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductAttributeValue record);

    int updateByPrimaryKey(PmsProductAttributeValue record);
}