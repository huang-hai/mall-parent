package fun.huanghai.mall.service.impl;

import fun.huanghai.mall.dao.SmsCouponMapper;
import fun.huanghai.mall.dao.SmsCouponProductCategoryRelationDaoExpand;
import fun.huanghai.mall.dao.SmsCouponProductRelationDaoExpand;
import fun.huanghai.mall.sms.pojo.*;
import fun.huanghai.mall.sms.service.SmsCouponService;
import fun.huanghai.mall.sys.SysVariable;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 优惠券服务
 */
@Service(methods = {
        @Method(name = "add",retries = 0)
})
public class SmsCouponServiceImpl extends BaseServiceImpl<SmsCoupon> implements SmsCouponService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsCouponServiceImpl.class);

    private SmsCouponMapper smsCouponMapper;

    @Autowired
    @Qualifier("smsCouponMapper")
    public void setSmsCouponMapper(SmsCouponMapper smsCouponMapper) {
        this.smsCouponMapper = smsCouponMapper;
        super.setaClass(SmsCouponMapper.class);
        super.setExampleClass(SmsCouponExample.class);
    }

    @Autowired
    @Qualifier("smsCouponProductCategoryRelationDaoExpand")
    private SmsCouponProductCategoryRelationDaoExpand smsCouponProductCategoryRelationDaoExpand;

    @Autowired
    @Qualifier("smsCouponProductRelationDaoExpand")
    private SmsCouponProductRelationDaoExpand smsCouponProductRelationDaoExpand;

    @Override
    @Transactional
    public Integer add(SmsCoupon smsCoupon) {
        SmsCouponExpand expand = (SmsCouponExpand) smsCoupon;
        SmsCoupon coupon = new SmsCoupon();
        BeanUtils.copyProperties(smsCoupon,coupon);

        Integer res = super.add(coupon);
        if(res!=SysVariable.SYS_SUCCESS) return SysVariable.SYS_FAILURE;

        List<SmsCouponProductCategoryRelation> productCategoryRelations = expand.getProductCategoryRelationList();
        productCategoryRelations.forEach(obj -> {
            obj.setCouponId(coupon.getId());
        });

        int row = smsCouponProductCategoryRelationDaoExpand.insertAll(productCategoryRelations);
        LOGGER.info("SmsCouponServiceImpl.add--smsCouponProductCategoryRelationDaoExpand.insertAll:受影响行{}-->",row);

        List<SmsCouponProductRelation> productRelations = expand.getProductRelationList();
        productRelations.forEach(obj -> {
            obj.setCouponId(coupon.getId());
        });

        row = smsCouponProductRelationDaoExpand.insertAll(productRelations);
        LOGGER.info("SmsCouponServiceImpl.add--smsCouponProductRelationDaoExpand.insertAll:受影响行{}-->",row);
        return SysVariable.SYS_SUCCESS;
    }

    @Override
    public Integer del(Long id) {
        Integer res = super.del(id);
        LOGGER.info("SmsCouponServiceImpl.del---->:运行结果{}-->",res==SysVariable.SYS_SUCCESS?"成功":"失败");
        Integer row = smsCouponProductCategoryRelationDaoExpand.delByCondition("coupon_id",id);
        LOGGER.info("SmsCouponServiceImpl.del--smsCouponProductCategoryRelationDaoExpand.delByCondition:受影响行{}-->",row);
        row = smsCouponProductRelationDaoExpand.delByCondition("coupon_id",id);
        LOGGER.info("SmsCouponServiceImpl.del--smsCouponProductRelationDaoExpand.delByCondition:受影响行{}-->",row);
        return SysVariable.SYS_SUCCESS;
    }
}
