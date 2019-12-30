package fun.huanghai.mall.dao;

import fun.huanghai.mall.oms.pojo.OmsOrderReturnReason;
import fun.huanghai.mall.oms.pojo.OmsOrderReturnReasonExample;

import java.util.List;

public interface OmsOrderReturnReasonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderReturnReason record);

    int insertSelective(OmsOrderReturnReason record);

    List<OmsOrderReturnReason> selectByExample(OmsOrderReturnReasonExample example);

    OmsOrderReturnReason selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrderReturnReason record);

    int updateByPrimaryKey(OmsOrderReturnReason record);
}