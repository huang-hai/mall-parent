package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsFlashPromotionSession;
import fun.huanghai.mall.sms.pojo.SmsFlashPromotionSessionExample;

import java.util.List;

public interface SmsFlashPromotionSessionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsFlashPromotionSession record);

    int insertSelective(SmsFlashPromotionSession record);

    List<SmsFlashPromotionSession> selectByExample(SmsFlashPromotionSessionExample example);

    SmsFlashPromotionSession selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsFlashPromotionSession record);

    int updateByPrimaryKey(SmsFlashPromotionSession record);
}