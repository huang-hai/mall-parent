package fun.huanghai.mall.pms.controller;

import fun.huanghai.mall.pms.exception.PmsWebException;
import fun.huanghai.mall.pms.pojo.PmsProductExpand;
import fun.huanghai.mall.pms.qo.QueryPageExpandParam;
import fun.huanghai.mall.pms.service.PmsProductService;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.to.CommonResult;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/product")
@RestController
public class PmsProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private PmsProductService pmsProductService;

    /**
     * 创建商品
     * @param productParam
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/create")
    public CommonResult create(@RequestBody PmsProductExpand productParam)
            throws PmsWebException {
        try {
            Integer res = pmsProductService.add(productParam);
            if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
            else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
            else if(res==SysVariable.PRODUCTNAME_EXIST) return new CommonResult().validateFailed(SysVariable.PRODUCTNAME_EXIST_MES);
            return new CommonResult().failed();
        } catch (PmsWebException e) {
            throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        }
    }

    /**
     * 按搜索条件获取分页数据
     * @param queryPageParam
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(QueryPageExpandParam queryPageParam){
        return new CommonResult().success(pmsProductService.queryPages(queryPageParam));
    }

    /**
     * 按商品名称或货号模糊查询
     * @param keyword
     * @return
     */
    @GetMapping("/simpleList")
    public CommonResult simpleList(@RequestParam("keyword") String keyword){
        return new CommonResult().success(pmsProductService.queryByCondition(keyword));
    }
}
