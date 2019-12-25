package fun.huanghai.mall.pms.controller;

import com.google.common.collect.Maps;
import fun.huanghai.mall.pms.exception.PmsWebException;
import fun.huanghai.mall.pms.pojo.PmsProductAttribute;
import fun.huanghai.mall.pms.service.PmsProductAttributeService;
import fun.huanghai.mall.pms.vo.PmsProductAttributeParam;
import fun.huanghai.mall.pms.vo.ProductAttrInfoVo;
import fun.huanghai.mall.pms.vo.QueryPageProductAttrExpandParam;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/productAttribute")
@RestController
public class PmsProductAttributeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductAttributeController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private PmsProductAttributeService pmsProductAttributeService;

    /**
     * 创建商品属性
     * @param param
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/create")
    public CommonResult create(@Valid @RequestBody PmsProductAttributeParam param,
                               BindingResult result) throws PmsWebException {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(param,pmsProductAttribute);
        Integer res = pmsProductAttributeService.add(pmsProductAttribute);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else if(res==SysVariable.PRODUCTATTRNAME_EXIST)
            return new CommonResult().validateFailed(SysVariable.PRODUCTATTRNAME_EXIST_MES);
        else return new CommonResult().failed();
    }

    /**
     * 更新商品属性信息
     * @param id
     * @param param
     * @param result
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @Valid @RequestBody PmsProductAttributeParam param,
                               BindingResult result) throws PmsWebException {
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(param,pmsProductAttribute);
        pmsProductAttribute.setId(id);
        Integer res = pmsProductAttributeService.edit(pmsProductAttribute);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else if(res==SysVariable.PRODUCTATTRNAME_EXIST)
            return new CommonResult().validateFailed(SysVariable.PRODUCTATTRNAME_EXIST_MES);
        else return new CommonResult().failed();
    }

    /**
     * 获取指定商品属性信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResult productAttrInfo(@PathVariable("id")Long id){
        return new CommonResult().success(pmsProductAttributeService.findById(id));
    }

    /**
     * 批量删除
     * @param ids
     * @return
     * @throws PmsWebException
     */
    @PostMapping("/delete")
    public CommonResult delAll(@RequestParam("ids") Long[] ids) throws PmsWebException {
        Integer res = pmsProductAttributeService.delAll(ids);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new PmsWebException(SysVariable.SYS_ERROR_MES);
        else return new CommonResult().failed();
    }

    /**
     * 分页查询分类属性列表或参数列表
     * @param cid
     * @param param
     * @param result
     * @return
     */
    @GetMapping("/list/{cid}")
    public CommonResult list(@PathVariable("cid")Long cid,
                             @Valid QueryPageProductAttrExpandParam param,
                             BindingResult result){
        Map<String,Object> map = Maps.newHashMap();
        map.put("cid",cid);
        map.put("type",param.getType());
        param.setObj(map);
        return new CommonResult().success(pmsProductAttributeService.queryPages(param));
    }

    /**
     * 按分类id获取商品属性和属性分类
     * @param pid
     * @return
     */
    @GetMapping("/attrInfo/{productCategoryId}")
    public CommonResult attrInfo(@PathVariable("productCategoryId")Long pid){
        List<PmsProductAttribute> pmsProductAttributes = pmsProductAttributeService.queryByPid(pid);
        List<ProductAttrInfoVo> list = new ArrayList<>();
        pmsProductAttributes.forEach(obj -> {
            list.add(new ProductAttrInfoVo(obj.getProductAttributeCategoryId(),obj.getId()));
        });
        return new CommonResult().success(list);
    }
}
