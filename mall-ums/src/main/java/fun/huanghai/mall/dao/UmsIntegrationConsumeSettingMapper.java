package fun.huanghai.mall.dao;

import fun.huanghai.mall.pojo.UmsIntegrationConsumeSetting;
import fun.huanghai.mall.pojo.UmsIntegrationConsumeSettingExample;

import java.util.List;

public interface UmsIntegrationConsumeSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsIntegrationConsumeSetting record);

    int insertSelective(UmsIntegrationConsumeSetting record);

    List<UmsIntegrationConsumeSetting> selectByExample(UmsIntegrationConsumeSettingExample example);

    UmsIntegrationConsumeSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsIntegrationConsumeSetting record);

    int updateByPrimaryKey(UmsIntegrationConsumeSetting record);
}