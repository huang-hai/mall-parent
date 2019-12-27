package fun.huanghai.mall.dao;

import fun.huanghai.mall.cms.pojo.CmsTopicComment;
import fun.huanghai.mall.cms.pojo.CmsTopicCommentExample;

import java.util.List;

public interface CmsTopicCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmsTopicComment record);

    int insertSelective(CmsTopicComment record);

    List<CmsTopicComment> selectByExample(CmsTopicCommentExample example);

    CmsTopicComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsTopicComment record);

    int updateByPrimaryKey(CmsTopicComment record);
}