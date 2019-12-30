package fun.huanghai.mall.dao;

import fun.huanghai.mall.oms.pojo.OmsOrder;
import fun.huanghai.mall.oms.pojo.OmsOrderExample;

import java.util.List;

public interface OmsOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrder record);

    int insertSelective(OmsOrder record);

    List<OmsOrder> selectByExample(OmsOrderExample example);

    OmsOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrder record);

    int updateByPrimaryKey(OmsOrder record);
}