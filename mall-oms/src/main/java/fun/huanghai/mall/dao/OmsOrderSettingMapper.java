package fun.huanghai.mall.dao;

import fun.huanghai.mall.oms.pojo.OmsOrderSetting;
import fun.huanghai.mall.oms.pojo.OmsOrderSettingExample;

import java.util.List;

public interface OmsOrderSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderSetting record);

    int insertSelective(OmsOrderSetting record);

    List<OmsOrderSetting> selectByExample(OmsOrderSettingExample example);

    OmsOrderSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrderSetting record);

    int updateByPrimaryKey(OmsOrderSetting record);
}