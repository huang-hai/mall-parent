package fun.huanghai.mall.pms.controller;

import fun.huanghai.mall.pms.exception.PmsWebException;
import fun.huanghai.mall.pms.pojo.PmsBrand;
import fun.huanghai.mall.pms.service.PmsBrandService;
import fun.huanghai.mall.pms.vo.PmsBrandParam;
import fun.huanghai.mall.vo.QueryPageExpandParam;
import fun.huanghai.mall.pms.vo.UpdateFactoryStatusParam;
import fun.huanghai.mall.pms.vo.UpdateShowStatusParam;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.to.CommonResult;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RequestMapping("/brand")
@RestController
public class PmsBrandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private PmsBrandService pmsBrandService;

    /**
     * 创建品牌
     * @param brandParam
     * @param result
     * @return
     */
    @PostMapping("/create")
    public CommonResult create(@Valid @RequestBody PmsBrandParam brandParam,
                               BindingResult result) throws PmsWebException {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(brandParam,pmsBrand);
        Integer res = pmsBrandService.add(pmsBrand);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.BRANDNAME_EXIST) return new CommonResult().validateFailed(SysVariable.BRANDNAME_EXIST_MES);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 更新品牌
     * @param brandParam
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id")Long id,
                               @Valid @RequestBody PmsBrandParam brandParam,
                               BindingResult result) throws PmsWebException {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(brandParam,pmsBrand);
        pmsBrand.setId(id);
        Integer res = pmsBrandService.edit(pmsBrand);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.BRANDNAME_EXIST) return new CommonResult().validateFailed(SysVariable.BRANDNAME_EXIST_MES);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 批量删除品牌
     * @param ids
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/delete/batch")
    public CommonResult delAll(@RequestParam("ids") Long[] ids) throws PmsWebException {
        Integer res = pmsBrandService.delAll(ids);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 删除品牌
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public CommonResult del(@PathVariable("id")Long id){
        Integer res = pmsBrandService.del(id);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        return new CommonResult().failed();
    }

    /**
     * 获取指定品牌
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult brandInfo(@PathVariable("id")Long id){
        return new CommonResult().success(pmsBrandService.findById(id));
    }

    /**
     * 获取品牌分页列表
     * @param queryPageExpandParam
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(QueryPageExpandParam queryPageExpandParam){
        queryPageExpandParam.setObj(queryPageExpandParam.getKeyword());
        return new CommonResult().success(pmsBrandService.queryPages(queryPageExpandParam));
    }

    /**
     * 获取所有品牌
     * @return
     */
    @GetMapping("/listAll")
    public CommonResult listAll(){
        return new CommonResult().success(pmsBrandService.queryByCondition(null));
    }

    /**
     * 批量更新显示状态
     * @param param
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/factoryStatus")
    public CommonResult updateFactoryStatus(@Valid UpdateFactoryStatusParam param,
                                         BindingResult result) throws PmsWebException {
        Integer res = pmsBrandService.updateFactoryStatus(param.getIds(), param.getFactoryStatus());
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 批量更新厂家制造商状态
     * @param param
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/showStatus")
    public CommonResult updateShowStatus(@Valid UpdateShowStatusParam param,
                                         BindingResult result) throws PmsWebException {
        Integer res = pmsBrandService.updateShowStatus(param.getIds(), param.getShowStatus());
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }
}
