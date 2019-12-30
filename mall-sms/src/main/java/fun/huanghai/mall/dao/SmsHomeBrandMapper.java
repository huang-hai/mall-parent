package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsHomeBrand;
import fun.huanghai.mall.sms.pojo.SmsHomeBrandExample;

import java.util.List;

public interface SmsHomeBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeBrand record);

    int insertSelective(SmsHomeBrand record);

    List<SmsHomeBrand> selectByExample(SmsHomeBrandExample example);

    SmsHomeBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsHomeBrand record);

    int updateByPrimaryKey(SmsHomeBrand record);
}