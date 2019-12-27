package fun.huanghai.mall.dao;

import fun.huanghai.mall.cms.pojo.CmsMemberReport;
import fun.huanghai.mall.cms.pojo.CmsMemberReportExample;

import java.util.List;

public interface CmsMemberReportMapper {
    int insert(CmsMemberReport record);

    int insertSelective(CmsMemberReport record);

    List<CmsMemberReport> selectByExample(CmsMemberReportExample example);
}