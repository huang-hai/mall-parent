package fun.huanghai.mall.pms.controller;

import fun.huanghai.mall.pms.exception.PmsWebException;
import fun.huanghai.mall.pms.pojo.PmsProductAttributeCategory;
import fun.huanghai.mall.pms.service.PmsProductAttributeCategoryService;
import fun.huanghai.mall.qo.QueryPageParam;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.to.CommonResult;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/productAttribute/category")
@RestController
public class PmsProductAttributeCategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductAttributeCategoryController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private PmsProductAttributeCategoryService pmsProductAttributeCategoryService;

    /**
     * 创建商品属性分类
     * @param name
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/create")
    public CommonResult create(@RequestParam(name = "name",required = true) String name)
            throws PmsWebException {
        PmsProductAttributeCategory pmsProductAttributeCategory = new PmsProductAttributeCategory();
        pmsProductAttributeCategory.setName(name);
        Integer res = pmsProductAttributeCategoryService.add(pmsProductAttributeCategory);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else if(res==SysVariable.PRODUCTATTRCATENAME_EXIST)
            return new CommonResult().validateFailed(SysVariable.PRODUCTATTRCATENAME_EXIST_MES);
        return new CommonResult().failed();
    }

    /**
     * 更新商品属性分类信息
     * @param name
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id")Long id,
                               @RequestParam(name = "name",required = true) String name)
            throws PmsWebException {
        PmsProductAttributeCategory pmsProductAttributeCategory = new PmsProductAttributeCategory();
        pmsProductAttributeCategory.setName(name);
        pmsProductAttributeCategory.setId(id);
        Integer res = pmsProductAttributeCategoryService.edit(pmsProductAttributeCategory);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else if(res==SysVariable.PRODUCTATTRCATENAME_EXIST)
            return new CommonResult().validateFailed(SysVariable.PRODUCTATTRCATENAME_EXIST_MES);
        return new CommonResult().failed();
    }

    /**
     * 分页获取商品属性分类信息
     * @param queryPageParam
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(QueryPageParam queryPageParam){
        return new CommonResult().success(pmsProductAttributeCategoryService.queryPages(queryPageParam));
    }

    /**
     * 获取指定商品属性分类信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult attrCateInfo(@PathVariable("id")Long id){
        return new CommonResult().success(pmsProductAttributeCategoryService.findById(id));
    }

    /**
     * 删除商品属性分类信息
     * @param id
     * @return
     * @throws PmsWebException
     */
    @GetMapping("/delete/{id}")
    public CommonResult del(@PathVariable("id")Long id) throws PmsWebException {
        Integer res = pmsProductAttributeCategoryService.del(id);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        return new CommonResult().failed();
    }

    /**
     * 获取所有商品属性分类及旗下属性
     * @return
     */
    @GetMapping("/list/withAttr")
    public CommonResult queryAll(){
        return new CommonResult().success(pmsProductAttributeCategoryService.queryAll());
    }
}
