package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsHomeRecommendSubject;
import fun.huanghai.mall.sms.pojo.SmsHomeRecommendSubjectExample;

import java.util.List;

public interface SmsHomeRecommendSubjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeRecommendSubject record);

    int insertSelective(SmsHomeRecommendSubject record);

    List<SmsHomeRecommendSubject> selectByExample(SmsHomeRecommendSubjectExample example);

    SmsHomeRecommendSubject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsHomeRecommendSubject record);

    int updateByPrimaryKey(SmsHomeRecommendSubject record);
}