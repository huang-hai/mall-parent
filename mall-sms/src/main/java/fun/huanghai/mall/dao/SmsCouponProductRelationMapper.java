package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsCouponProductRelation;
import fun.huanghai.mall.sms.pojo.SmsCouponProductRelationExample;

import java.util.List;

public interface SmsCouponProductRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsCouponProductRelation record);

    int insertSelective(SmsCouponProductRelation record);

    List<SmsCouponProductRelation> selectByExample(SmsCouponProductRelationExample example);

    SmsCouponProductRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsCouponProductRelation record);

    int updateByPrimaryKey(SmsCouponProductRelation record);
}