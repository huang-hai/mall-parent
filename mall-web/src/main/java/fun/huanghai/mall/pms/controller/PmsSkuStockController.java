package fun.huanghai.mall.pms.controller;

import com.google.common.collect.Maps;
import fun.huanghai.mall.pms.exception.PmsWebException;
import fun.huanghai.mall.pms.pojo.PmsSkuStock;
import fun.huanghai.mall.pms.service.PmsSkuStockService;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.to.CommonResult;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@CrossOrigin
@RequestMapping("/sku")
@RestController
public class PmsSkuStockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsSkuStockController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private PmsSkuStockService pmsSkuStockService;

    /**
     * 按商品批量更新库存
     * @param pid
     * @param skuStockList
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/{pid}")
    public CommonResult update(@PathVariable("pid")Long pid,
                               @RequestBody PmsSkuStock[] skuStockList) throws PmsWebException {
        Integer res = pmsSkuStockService.updateAll(Arrays.asList(skuStockList));
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        return new CommonResult().failed();
    }

    /**
     * 按商品id或sku编码模糊查询
     * @param pid
     * @param skuCode
     * @return
     */
    @GetMapping("/{pid}")
    public CommonResult list(@PathVariable("pid")Long pid,@RequestParam(name = "keyword",defaultValue = "") String skuCode){
        Map<String,Object> map = Maps.newHashMap();
        map.put("pid",pid);
        if(!"".equals(skuCode)) map.put("skuCode",skuCode);
        return new CommonResult().success(pmsSkuStockService.queryByCondition(map));
    }
}
