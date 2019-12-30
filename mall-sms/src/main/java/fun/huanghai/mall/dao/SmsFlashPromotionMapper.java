package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsFlashPromotion;
import fun.huanghai.mall.sms.pojo.SmsFlashPromotionExample;

import java.util.List;

public interface SmsFlashPromotionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsFlashPromotion record);

    int insertSelective(SmsFlashPromotion record);

    List<SmsFlashPromotion> selectByExample(SmsFlashPromotionExample example);

    SmsFlashPromotion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsFlashPromotion record);

    int updateByPrimaryKey(SmsFlashPromotion record);
}