package fun.huanghai.mall.dao;

import fun.huanghai.mall.cms.pojo.CmsTopicCategory;
import fun.huanghai.mall.cms.pojo.CmsTopicCategoryExample;

import java.util.List;

public interface CmsTopicCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmsTopicCategory record);

    int insertSelective(CmsTopicCategory record);

    List<CmsTopicCategory> selectByExample(CmsTopicCategoryExample example);

    CmsTopicCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsTopicCategory record);

    int updateByPrimaryKey(CmsTopicCategory record);
}