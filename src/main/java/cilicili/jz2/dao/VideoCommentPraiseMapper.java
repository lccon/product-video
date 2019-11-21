package cilicili.jz2.dao;

import cilicili.jz2.domain.VideoCommentPraise;

/**
 * Description:
 *
 * @Date:2019/11/18
 * @Author:lc
 */
public interface VideoCommentPraiseMapper {

    /**
     * 新增视频和评论点赞数据
     * @param videoCommentPraise
     */
    void addVideoCommentPraise(VideoCommentPraise videoCommentPraise);

    /**
     * 统计用户视频数量
     * @param videoPraise
     * @return
     */
    Integer countVideoPraiseNum(VideoCommentPraise videoPraise);

    /**
     * 获取用户评论点赞信息
     * @param commentPraise
     * @return
     */
    VideoCommentPraise getCommentPraiseInfo(VideoCommentPraise commentPraise);

    /**
     * 修改评论点赞信息
     * @param commentPraise
     * @return
     */
    void updateCommentPraise(VideoCommentPraise commentPraise);

    /**
     * 查询视频点赞状态
     * @param videoPraise
     * @return
     */
    VideoCommentPraise getVideoPraiseByVideoId(VideoCommentPraise videoPraise);
}
