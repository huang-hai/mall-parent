package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsCouponHistory;
import fun.huanghai.mall.sms.pojo.SmsCouponHistoryExample;

import java.util.List;

public interface SmsCouponHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsCouponHistory record);

    int insertSelective(SmsCouponHistory record);

    List<SmsCouponHistory> selectByExample(SmsCouponHistoryExample example);

    SmsCouponHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsCouponHistory record);

    int updateByPrimaryKey(SmsCouponHistory record);
}