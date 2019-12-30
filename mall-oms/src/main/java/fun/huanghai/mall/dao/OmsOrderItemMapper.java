package fun.huanghai.mall.dao;

import fun.huanghai.mall.oms.pojo.OmsOrderItem;
import fun.huanghai.mall.oms.pojo.OmsOrderItemExample;

import java.util.List;

public interface OmsOrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderItem record);

    int insertSelective(OmsOrderItem record);

    List<OmsOrderItem> selectByExample(OmsOrderItemExample example);

    OmsOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrderItem record);

    int updateByPrimaryKey(OmsOrderItem record);
}