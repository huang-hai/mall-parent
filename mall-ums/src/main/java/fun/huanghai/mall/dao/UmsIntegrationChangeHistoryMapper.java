package fun.huanghai.mall.dao;

import fun.huanghai.mall.pojo.UmsIntegrationChangeHistory;
import fun.huanghai.mall.pojo.UmsIntegrationChangeHistoryExample;

import java.util.List;

public interface UmsIntegrationChangeHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsIntegrationChangeHistory record);

    int insertSelective(UmsIntegrationChangeHistory record);

    List<UmsIntegrationChangeHistory> selectByExample(UmsIntegrationChangeHistoryExample example);

    UmsIntegrationChangeHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsIntegrationChangeHistory record);

    int updateByPrimaryKey(UmsIntegrationChangeHistory record);
}