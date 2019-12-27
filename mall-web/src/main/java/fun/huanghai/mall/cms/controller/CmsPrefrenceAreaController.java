package fun.huanghai.mall.cms.controller;

import fun.huanghai.mall.cms.service.CmsPrefrenceAreaService;
import fun.huanghai.mall.to.CommonResult;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/prefrenceArea")
@RestController
public class CmsPrefrenceAreaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsPrefrenceAreaController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private CmsPrefrenceAreaService cmsPrefrenceAreaService;

    /**
     * 获取所有商品优选
     * @return
     */
    @GetMapping("/listAll")
    public CommonResult listAll(){
        return new CommonResult().success(cmsPrefrenceAreaService.queryByCondition(null));
    }
}
