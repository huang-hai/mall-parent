package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsHomeRecommendProduct;
import fun.huanghai.mall.sms.pojo.SmsHomeRecommendProductExample;

import java.util.List;

public interface SmsHomeRecommendProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeRecommendProduct record);

    int insertSelective(SmsHomeRecommendProduct record);

    List<SmsHomeRecommendProduct> selectByExample(SmsHomeRecommendProductExample example);

    SmsHomeRecommendProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsHomeRecommendProduct record);

    int updateByPrimaryKey(SmsHomeRecommendProduct record);
}