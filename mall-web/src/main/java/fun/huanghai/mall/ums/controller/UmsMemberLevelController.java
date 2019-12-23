package fun.huanghai.mall.ums.controller;

import fun.huanghai.mall.to.CommonResult;
import fun.huanghai.mall.ums.service.UmsMemberLevelService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/memberLevel")
@RestController
public class UmsMemberLevelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsRoleController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)
    })
    private UmsMemberLevelService umsMemberLevelService;

    /**
     * 查询所有会员等级
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(){
        return new CommonResult().success(umsMemberLevelService.queryByCondition(null));
    }
}
