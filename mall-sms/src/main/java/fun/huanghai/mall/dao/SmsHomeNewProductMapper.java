package fun.huanghai.mall.dao;

import fun.huanghai.mall.sms.pojo.SmsHomeNewProduct;
import fun.huanghai.mall.sms.pojo.SmsHomeNewProductExample;

import java.util.List;

public interface SmsHomeNewProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeNewProduct record);

    int insertSelective(SmsHomeNewProduct record);

    List<SmsHomeNewProduct> selectByExample(SmsHomeNewProductExample example);

    SmsHomeNewProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsHomeNewProduct record);

    int updateByPrimaryKey(SmsHomeNewProduct record);
}