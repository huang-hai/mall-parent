package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsHomeAdvertise;
import fun.huanghai.mall.sms.pojo.SmsHomeAdvertiseExample;

import java.util.List;

public interface SmsHomeAdvertiseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeAdvertise record);

    int insertSelective(SmsHomeAdvertise record);

    List<SmsHomeAdvertise> selectByExample(SmsHomeAdvertiseExample example);

    SmsHomeAdvertise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsHomeAdvertise record);

    int updateByPrimaryKey(SmsHomeAdvertise record);
}