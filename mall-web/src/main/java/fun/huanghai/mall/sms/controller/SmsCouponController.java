package fun.huanghai.mall.sms.controller;

import fun.huanghai.mall.sms.exception.SmsWebException;
import fun.huanghai.mall.sms.pojo.SmsCouponExpand;
import fun.huanghai.mall.sms.service.SmsCouponService;
import fun.huanghai.mall.sys.SysVariable;
import fun.huanghai.mall.to.CommonResult;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/coupon")
@RestController
public class SmsCouponController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsCouponController.class);

    @Reference(methods = {
            @Method(name="add",retries = 0)/*新增操作只允许执行一次*/
    })
    private SmsCouponService smsCouponService;

    /**
     * 创建优惠券
     * @param couponExpand
     * @return
     * @throws SmsWebException
     */
    @PostMapping("/create")
    public CommonResult create(SmsCouponExpand couponExpand) throws SmsWebException {
        Integer res = smsCouponService.add(couponExpand);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new SmsWebException(SysVariable.SYS_ERROR_MES);
        return new CommonResult().failed();
    }

    /**
     * 删除优惠券
     * @param id
     * @return
     * @throws SmsWebException
     */
    @PostMapping("/delete/{id}")
    public CommonResult del(@PathVariable("id") Long id) throws SmsWebException {
        Integer res = smsCouponService.del(id);
        if(res==SysVariable.SYS_SUCCESS) return new CommonResult().success(res);
        else if(res==SysVariable.SYS_ERROR) throw new SmsWebException(SysVariable.SYS_ERROR_MES);
        return new CommonResult().failed();
    }
}
