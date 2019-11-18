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
     * 统计用户视频或评论点赞的
     * @param videoCommentPraise
     * @return
     */
    Integer countVideoCommentPraiseNum(VideoCommentPraise videoCommentPraise);
}
