package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsSkuStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsSkuStockDaoExpand extends PmsSkuStockMapper{

    /**
     * 按商品批量更新库存
     * @param skuStocks
     * @return
     */
    int replaceAll(@Param("skuStocks") List<PmsSkuStock> skuStocks);
}