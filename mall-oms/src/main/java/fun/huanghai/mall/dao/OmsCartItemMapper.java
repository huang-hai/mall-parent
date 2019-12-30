package fun.huanghai.mall.dao;

import fun.huanghai.mall.oms.pojo.OmsCartItem;
import fun.huanghai.mall.oms.pojo.OmsCartItemExample;

import java.util.List;

public interface OmsCartItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OmsCartItem record);

    int insertSelective(OmsCartItem record);

    List<OmsCartItem> selectByExample(OmsCartItemExample example);

    OmsCartItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsCartItem record);

    int updateByPrimaryKey(OmsCartItem record);
}