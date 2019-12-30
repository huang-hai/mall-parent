package fun.huanghai.mall.dao;

import fun.huanghai.mall.oms.pojo.OmsCompanyAddress;
import fun.huanghai.mall.oms.pojo.OmsCompanyAddressExample;

import java.util.List;

public interface OmsCompanyAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OmsCompanyAddress record);

    int insertSelective(OmsCompanyAddress record);

    List<OmsCompanyAddress> selectByExample(OmsCompanyAddressExample example);

    OmsCompanyAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsCompanyAddress record);

    int updateByPrimaryKey(OmsCompanyAddress record);
}