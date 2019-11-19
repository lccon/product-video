package cilicili.jz2.dao;

import cilicili.jz2.domain.Video;

public interface VideoMapper {

    /**
     * 新增视频
     * @param record
     * @return
     */
    int insertVideo(Video record);

    /**
     * 修改视频信息
     * @param record
     * @return
     */
    int updateVideo(Video record);
}