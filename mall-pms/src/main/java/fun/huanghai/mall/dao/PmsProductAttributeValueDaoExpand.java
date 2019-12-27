package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsProductAttributeValue;
import fun.huanghai.mall.pms.pojo.PmsProductAttributeValueExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductAttributeValueDaoExpand extends PmsProductAttributeValueMapper{

    /**
     * 批量新增
     * @param records
     * @return
     */
    int insertAll(@Param("records") List<PmsProductAttributeValue> records);

}