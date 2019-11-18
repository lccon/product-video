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
     * 统计视频点赞或评论点赞的数量
     * @param videoCommentPraise
     * @return
     */
    Integer countVideoCommentPraiseNum(VideoCommentPraise videoCommentPraise);
}
