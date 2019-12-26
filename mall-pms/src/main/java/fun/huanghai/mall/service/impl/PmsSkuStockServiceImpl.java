package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.PmsSkuStockDaoExpand;
import fun.huanghai.mall.dao.PmsSkuStockMapper;
import fun.huanghai.mall.pms.pojo.PmsSkuStock;
import fun.huanghai.mall.pms.pojo.PmsSkuStockExample;
import fun.huanghai.mall.pms.service.PmsSkuStockService;
import fun.huanghai.mall.sys.SysVariable;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * 商品库存服务
 */
@Service(methods = {
        @Method(name = "add",retries = 0)
})
public class PmsSkuStockServiceImpl extends BaseServiceImpl<PmsSkuStock> implements PmsSkuStockService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsSkuStockServiceImpl.class);

    private PmsSkuStockMapper pmsSkuStockMapper;

    @Autowired
    @Qualifier("pmsSkuStockMapper")
    public void setPmsSkuStockMapper(PmsSkuStockMapper pmsSkuStockMapper) {
        this.pmsSkuStockMapper = pmsSkuStockMapper;
        super.setaClass(PmsSkuStockMapper.class);
        super.setExampleClass(PmsSkuStockExample.class);
    }

    @Autowired
    @Qualifier("pmsSkuStockDaoExpand")
    private PmsSkuStockDaoExpand pmsSkuStockDaoExpand;

    /**
     * 批量更新商品库存
     *
     * @param pmsSkuStocks
     * @return
     */
    @Override
    public Integer updateAll(List<PmsSkuStock> pmsSkuStocks) {
        try {
            int row = pmsSkuStockDaoExpand.replaceAll(pmsSkuStocks);
            if(row>0) return SysVariable.SYS_SUCCESS;
            return SysVariable.SYS_FAILURE;
        } catch (Exception e) {
            return error(e,"updateAll");
        }
    }

    /**
     * 根据条件查找
     *
     * @param obj
     * @return
     */
    @Override
    public List<PmsSkuStock> queryByCondition(Object obj) {
        Map<String,Object> map = (Map<String, Object>) obj;
        PmsSkuStockExample example = new PmsSkuStockExample();
        Long pid= (Long) map.get("pid");
        PmsSkuStockExample.Criteria criteria = example.createCriteria().andProductIdEqualTo(pid);
        if(map.containsKey("skuCode")) criteria.andSkuCodeLike("%"+map.get("skuCode")+"%");
        return super.queryByCondition(example);
    }
}
