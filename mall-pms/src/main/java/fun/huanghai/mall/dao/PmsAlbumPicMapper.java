package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsAlbumPic;
import fun.huanghai.mall.pms.pojo.PmsAlbumPicExample;

import java.util.List;

public interface PmsAlbumPicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsAlbumPic record);

    int insertSelective(PmsAlbumPic record);

    List<PmsAlbumPic> selectByExample(PmsAlbumPicExample example);

    PmsAlbumPic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsAlbumPic record);

    int updateByPrimaryKey(PmsAlbumPic record);
}