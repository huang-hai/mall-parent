package fun.huanghai.mall.pms.controller;

import fun.huanghai.mall.pms.exception.PmsWebException;
import fun.huanghai.mall.pms.pojo.PmsProductExpand;
import fun.huanghai.mall.pms.qo.QueryPageExpandParam;
import fun.huanghai.mall.pms.service.PmsProductService;
import fun.huanghai.mall.pms.vo.*;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.to.CommonResult;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /**
     * 批量更新删除状态
     * @param param
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/deleteStatus")
    public CommonResult updateDelStatus(@Valid DelStatusParam param,
                                        BindingResult result) throws PmsWebException {
        Integer res = pmsProductService.updateDelStatus(param.getIds(), param.getDeleteStatus());
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        return new CommonResult().failed();
    }

    /**
     * 批量更新上新状态
     * @param param
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/newStatus")
    public CommonResult updateNewStatus(@Valid NewStatusParam param,
                                        BindingResult result) throws PmsWebException {
        Integer res = pmsProductService.updateNewStatus(param.getIds(), param.getNewStatus());
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        return new CommonResult().failed();
    }

    /**
     * 批量更新上下架状态
     * @param param
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/publishStatus")
    public CommonResult updatePublishStatus(@Valid PublishStatusParam param,
                                        BindingResult result) throws PmsWebException {
        Integer res = pmsProductService.updatePublishStatus(param.getIds(), param.getPublishStatus());
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        return new CommonResult().failed();
    }

    /**
     * 批量更新推荐状态
     * @param param
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/recommendStatus")
    public CommonResult updatePublishStatus(@Valid RecommendStatusParam param,
                                            BindingResult result) throws PmsWebException {
        Integer res = pmsProductService.updateRecommendStatus(param.getIds(), param.getRecommendStatus());
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        return new CommonResult().failed();
    }

    /**
     * 批量更新审核状态
     * @param param
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/verifyStatus")
    public CommonResult updateVerifyStatus(@Valid VerifyStatusParam param,
                                            BindingResult result) throws PmsWebException {
        Integer res = pmsProductService.updateVerifyStatus(param.getIds(), param.getVerifyStatus(),param.getDetail());
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        return new CommonResult().failed();
    }
}
