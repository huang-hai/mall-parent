package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsFlashPromotionLog;
import fun.huanghai.mall.sms.pojo.SmsFlashPromotionLogExample;

import java.util.List;

public interface SmsFlashPromotionLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SmsFlashPromotionLog record);

    int insertSelective(SmsFlashPromotionLog record);

    List<SmsFlashPromotionLog> selectByExample(SmsFlashPromotionLogExample example);

    SmsFlashPromotionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmsFlashPromotionLog record);

    int updateByPrimaryKey(SmsFlashPromotionLog record);
}