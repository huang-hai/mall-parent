package fun.huanghai.mall.pms.controller;

import fun.huanghai.mall.pms.exception.PmsWebException;
import fun.huanghai.mall.pms.pojo.PmsProductCategory;
import fun.huanghai.mall.pms.service.PmsProductCategoryService;
import fun.huanghai.mall.pms.vo.PmsProductCategoryParam;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.to.CommonResult;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@CrossOrigin
@RequestMapping("/productCategory")
@RestController
public class PmsProductCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductCategoryController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0),/*新增操作只允许执行一次*/
            @Method(name="addProductCategory",retries = 0)/*新增操作只允许执行一次*/
    })
    private PmsProductCategoryService pmsProductCategoryService;

    /**
     * 创建商品分类
     * @param productCategoryParam
     * @param result
     * @return
     */
    @PostMapping("/create")
    public CommonResult create(@Valid @RequestBody PmsProductCategoryParam productCategoryParam,
                               BindingResult result) throws PmsWebException {
        try {
            PmsProductCategory pmsProductCategory = new PmsProductCategory();
            BeanUtils.copyProperties(productCategoryParam,pmsProductCategory);
            Integer res = pmsProductCategoryService.addProductCategory(pmsProductCategory, Arrays.asList(productCategoryParam.getProductAttributeIdList()));
            if(res== SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
            else if(res==SysVariable.PRODUCTCATEGORYNAME_EXIST) return new CommonResult().validateFailed(SysVariable.PRODUCTCATEGORYNAME_EXIST_MES);
            return new CommonResult().failed();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        }
    }

    /**
     * 更新商品分类信息
     * @param id
     * @param productCategoryParam
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id")Long id,@Valid @RequestBody PmsProductCategoryParam productCategoryParam,
                               BindingResult result) throws PmsWebException {
        try {
            PmsProductCategory pmsProductCategory = new PmsProductCategory();
            BeanUtils.copyProperties(productCategoryParam,pmsProductCategory);
            pmsProductCategory.setId(id);
            Integer res = pmsProductCategoryService.updateProductCategory(pmsProductCategory, Arrays.asList(productCategoryParam.getProductAttributeIdList()));
            if(res== SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
            else if(res==SysVariable.PRODUCTCATEGORYNAME_EXIST) return new CommonResult().validateFailed(SysVariable.PRODUCTCATEGORYNAME_EXIST_MES);
            return new CommonResult().failed();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        }
    }

    /**
     * 删除商品分类
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public CommonResult del(@PathVariable("id") Long id) throws PmsWebException {
        Integer res = pmsProductCategoryService.del(id);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 获取指定商品分类信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult productCategoryInfo(@PathVariable("id") Long id){
        return new CommonResult().success(pmsProductCategoryService.findById(id));
    }
}
