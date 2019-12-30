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

    /**
     * 批量保存
     * @param skuStocks
     * @return
     */
    int insertAll(@Param("skuStocks") List<PmsSkuStock> skuStocks);

    /**
     * 按单条件删除
     * @param name
     * @param id
     * @return
     */
    int delByCondition(@Param("name") String name,@Param("val") Long id);
}