package fun.huanghai.mall.dao;

import fun.huanghai.mall.oms.pojo.OmsOrderReturnApply;
import fun.huanghai.mall.oms.pojo.OmsOrderReturnApplyExample;

import java.util.List;

public interface OmsOrderReturnApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderReturnApply record);

    int insertSelective(OmsOrderReturnApply record);

    List<OmsOrderReturnApply> selectByExample(OmsOrderReturnApplyExample example);

    OmsOrderReturnApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrderReturnApply record);

    int updateByPrimaryKey(OmsOrderReturnApply record);
}