package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsCommentReplay;
import fun.huanghai.mall.pms.pojo.PmsCommentReplayExample;

import java.util.List;

public interface PmsCommentReplayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsCommentReplay record);

    int insertSelective(PmsCommentReplay record);

    List<PmsCommentReplay> selectByExample(PmsCommentReplayExample example);

    PmsCommentReplay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsCommentReplay record);

    int updateByPrimaryKey(PmsCommentReplay record);
}