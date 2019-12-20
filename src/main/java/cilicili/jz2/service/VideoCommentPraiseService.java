package cilicili.jz2.service;

import cilicili.jz2.domain.VideoCommentPraise;

/**
 * Description:
 *
 * @Date:2019/11/18
 * @Author:lc
 */
public interface VideoCommentPraiseService {

    /**
     * 新增视频和评论点赞数据
     * @param videoCommentPraise
     */
    void addVideoCommentPraise(VideoCommentPraise videoCommentPraise);

    /**
     * 统计视频点赞的数量
     * @param videoPraise
     * @return
     */
    Integer countVideoPraiseNum(VideoCommentPraise videoPraise);

    /**
     * 获取评论点赞的信息
     * @param commentPraise
     * @return
     */
    VideoCommentPraise getCommentPraiseInfo(VideoCommentPraise commentPraise);

    /**
     * 修改评论点赞的信息
     * @param commentPraise
     * @return
     */
    void updateCommentPraise(VideoCommentPraise commentPraise);

    /**
     * 查询视频点赞状态
     * @param id
     * @param userId
     * @return
     */
    VideoCommentPraise getVideoPraiseByVideoId(Integer id, Integer userId);
}
