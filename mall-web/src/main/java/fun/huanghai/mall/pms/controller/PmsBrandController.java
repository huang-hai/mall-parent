package fun.huanghai.mall.pms.controller;

import fun.huanghai.mall.pms.exception.PmsWebException;
import fun.huanghai.mall.pms.pojo.PmsBrand;
import fun.huanghai.mall.pms.service.PmsBrandService;
import fun.huanghai.mall.pms.vo.PmsBrandParam;
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
        else if(res==SysVariable.BRANDNAME_EXIST) return new CommonResult().validateFailed("品牌名已存在!");
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException("系统错误！");
        else return new CommonResult().failed();
    }
}
