package fun.huanghai.mall.dao;

import fun.huanghai.mall.ums.pojo.UmsAdminLoginLog;
import fun.huanghai.mall.ums.pojo.UmsAdminLoginLogExample;

import java.util.List;

public interface UmsAdminLoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminLoginLog record);

    int insertSelective(UmsAdminLoginLog record);

    List<UmsAdminLoginLog> selectByExample(UmsAdminLoginLogExample example);

    UmsAdminLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsAdminLoginLog record);

    int updateByPrimaryKey(UmsAdminLoginLog record);
}