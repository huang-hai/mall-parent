package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsCouponProductCategoryRelation;
import fun.huanghai.mall.sms.pojo.SmsCouponProductCategoryRelationExample;

import java.util.List;

public interface SmsCouponProductCategoryRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsCouponProductCategoryRelation record);

    int insertSelective(SmsCouponProductCategoryRelation record);

    List<SmsCouponProductCategoryRelation> selectByExample(SmsCouponProductCategoryRelationExample example);

    SmsCouponProductCategoryRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsCouponProductCategoryRelation record);

    int updateByPrimaryKey(SmsCouponProductCategoryRelation record);
}