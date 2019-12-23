package fun.huanghai.mall.dao;

import fun.huanghai.mall.pms.pojo.PmsAlbum;
import fun.huanghai.mall.pms.pojo.PmsAlbumExample;

import java.util.List;

public interface PmsAlbumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmsAlbum record);

    int insertSelective(PmsAlbum record);

    List<PmsAlbum> selectByExample(PmsAlbumExample example);

    PmsAlbum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsAlbum record);

    int updateByPrimaryKey(PmsAlbum record);
}