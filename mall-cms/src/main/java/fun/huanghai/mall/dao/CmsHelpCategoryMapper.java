package fun.huanghai.mall.dao;

import fun.huanghai.mall.cms.pojo.CmsHelpCategory;
import fun.huanghai.mall.cms.pojo.CmsHelpCategoryExample;

import java.util.List;

public interface CmsHelpCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmsHelpCategory record);

    int insertSelective(CmsHelpCategory record);

    List<CmsHelpCategory> selectByExample(CmsHelpCategoryExample example);

    CmsHelpCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsHelpCategory record);

    int updateByPrimaryKey(CmsHelpCategory record);
}