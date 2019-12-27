package fun.huanghai.mall.dao;

import fun.huanghai.mall.cms.pojo.CmsSubjectCategory;
import fun.huanghai.mall.cms.pojo.CmsSubjectCategoryExample;

import java.util.List;

public interface CmsSubjectCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmsSubjectCategory record);

    int insertSelective(CmsSubjectCategory record);

    List<CmsSubjectCategory> selectByExample(CmsSubjectCategoryExample example);

    CmsSubjectCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsSubjectCategory record);

    int updateByPrimaryKey(CmsSubjectCategory record);
}