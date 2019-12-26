package fun.huanghai.mall.pms.service;

import fun.huanghai.mall.pms.pojo.PmsSkuStock;
import fun.huanghai.mall.service.BaseService;

import java.util.List;

public interface PmsSkuStockService extends BaseService<PmsSkuStock> {

    /**
     * 批量更新商品库存
     * @param pmsSkuStocks
     * @return
     */
    Integer updateAll(List<PmsSkuStock> pmsSkuStocks);
}
