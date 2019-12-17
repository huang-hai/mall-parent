package fun.huanghai.mall.dao;

import fun.huanghai.mall.pojo.UmsMemberReceiveAddress;
import fun.huanghai.mall.pojo.UmsMemberReceiveAddressExample;

import java.util.List;

public interface UmsMemberReceiveAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberReceiveAddress record);

    int insertSelective(UmsMemberReceiveAddress record);

    List<UmsMemberReceiveAddress> selectByExample(UmsMemberReceiveAddressExample example);

    UmsMemberReceiveAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMemberReceiveAddress record);

    int updateByPrimaryKey(UmsMemberReceiveAddress record);
}