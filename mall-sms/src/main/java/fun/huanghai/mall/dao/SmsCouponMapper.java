package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsCoupon;
import fun.huanghai.mall.sms.pojo.SmsCouponExample;

import java.util.List;

public interface SmsCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsCoupon record);

    int insertSelective(SmsCoupon record);

    List<SmsCoupon> selectByExample(SmsCouponExample example);

    SmsCoupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsCoupon record);

    int updateByPrimaryKey(SmsCoupon record);
}