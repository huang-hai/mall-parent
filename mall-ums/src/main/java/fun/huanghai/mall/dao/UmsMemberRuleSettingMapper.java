package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsMemberRuleSetting;
import fun.huanghai.mall.ums.pojo.UmsMemberRuleSettingExample;

import java.util.List;

public interface UmsMemberRuleSettingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberRuleSetting record);

    int insertSelective(UmsMemberRuleSetting record);

    List<UmsMemberRuleSetting> selectByExample(UmsMemberRuleSettingExample example);

    UmsMemberRuleSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMemberRuleSetting record);

    int updateByPrimaryKey(UmsMemberRuleSetting record);
}