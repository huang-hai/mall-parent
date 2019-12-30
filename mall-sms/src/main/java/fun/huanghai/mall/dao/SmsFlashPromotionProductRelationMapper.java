package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsFlashPromotionProductRelation;
import fun.huanghai.mall.sms.pojo.SmsFlashPromotionProductRelationExample;

import java.util.List;

public interface SmsFlashPromotionProductRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsFlashPromotionProductRelation record);

    int insertSelective(SmsFlashPromotionProductRelation record);

    List<SmsFlashPromotionProductRelation> selectByExample(SmsFlashPromotionProductRelationExample example);

    SmsFlashPromotionProductRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsFlashPromotionProductRelation record);

    int updateByPrimaryKey(SmsFlashPromotionProductRelation record);
}