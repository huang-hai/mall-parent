package fun.huanghai.mall.dao;

import fun.huanghai.mall.oms.pojo.OmsOrderOperateHistory;
import fun.huanghai.mall.oms.pojo.OmsOrderOperateHistoryExample;

import java.util.List;

public interface OmsOrderOperateHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderOperateHistory record);

    int insertSelective(OmsOrderOperateHistory record);

    List<OmsOrderOperateHistory> selectByExample(OmsOrderOperateHistoryExample example);

    OmsOrderOperateHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrderOperateHistory record);

    int updateByPrimaryKey(OmsOrderOperateHistory record);
}