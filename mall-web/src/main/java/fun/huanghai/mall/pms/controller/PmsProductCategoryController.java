package fun.huanghai.mall.pms.controller;

import fun.huanghai.mall.pms.exception.PmsWebException;
import fun.huanghai.mall.pms.pojo.PmsProductCategory;
import fun.huanghai.mall.pms.service.PmsProductCategoryService;
import fun.huanghai.mall.pms.vo.PmsProductCategoryParam;
import fun.huanghai.mall.pms.vo.QueryPageExpandParam;
import fun.huanghai.mall.pms.vo.UpdateNavStatusParam;
import fun.huanghai.mall.pms.vo.UpdateShowStatusParam;
import fun.huanghai.mall.qo.QueryPageParam;
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

    /**
     * 批量更新显示状态
     * @param param
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/showStatus")
    public CommonResult updateShowStatus(@Valid UpdateShowStatusParam param,BindingResult result) throws PmsWebException {
        Integer res = pmsProductCategoryService.updateShowStatus(param.getIds(), param.getShowStatus());
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 批量更新状态栏显示状态
     * @param param
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/navStatus")
    public CommonResult updateNavStatus(@Valid UpdateNavStatusParam param, BindingResult result) throws PmsWebException {
        Integer res = pmsProductCategoryService.updateNavStatus(param.getIds(), param.getNavStatus());
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 分页获取商品分类信息
     * @param parentId
     * @param queryPageExpandParam
     * @return
     */
    @GetMapping("/list/{parentId}")
    public CommonResult list(@PathVariable("parentId")Long parentId,
                                  QueryPageExpandParam queryPageExpandParam){
        queryPageExpandParam.setObj(parentId);
        return new CommonResult().success(pmsProductCategoryService.queryPages(queryPageExpandParam));
    }

    /**
     * 获取商品一级分类及子分类
     * @return
     */
    @GetMapping("/list/withChildren")
    public CommonResult withChildren(){
        return new CommonResult().success(pmsProductCategoryService.queryWithChildren());
    }

}
