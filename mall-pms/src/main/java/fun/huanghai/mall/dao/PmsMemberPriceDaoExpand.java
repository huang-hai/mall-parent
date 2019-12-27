package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsMemberPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PmsMemberPriceDaoExpand extends PmsMemberPriceMapper{

    /**
     * 批量新增
     * @param records
     * @return
     */
    int insertAll(@Param("memberPrices") List<PmsMemberPrice> records);

}