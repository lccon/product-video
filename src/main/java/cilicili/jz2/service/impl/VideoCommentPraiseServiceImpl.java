package cilicili.jz2.service.impl;

import cilicili.jz2.dao.VideoCommentPraiseMapper;
import cilicili.jz2.domain.VideoCommentPraise;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.ServiceValidationException;
import cilicili.jz2.service.VideoCommentPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: 视频和评论点赞
 *
 * @Date:2019/11/18
 * @Author:lc
 */
@Service
public class VideoCommentPraiseServiceImpl implements VideoCommentPraiseService {

    @Autowired
    private VideoCommentPraiseMapper videoCommentPraiseMapper;

    @Override
    public void addVideoCommentPraise(VideoCommentPraise videoCommentPraise) {
        if (videoCommentPraise == null) {
            throw new BusinessValidationException("参数不能为空!");
        }
        try {
            videoCommentPraiseMapper.addVideoCommentPraise(videoCommentPraise);
        } catch (Exception e) {
            throw new ServiceValidationException("新增视频和评论点赞数据出错", e);
        }
    }

    @Override
    public Integer countVideoCommentPraiseNum(VideoCommentPraise videoCommentPraise) {
        if (videoCommentPraise == null) {
            throw new BusinessValidationException("参数不能为空!");
        }
        try {
            return videoCommentPraiseMapper.countVideoCommentPraiseNum(videoCommentPraise);
        } catch (Exception e) {
            throw new ServiceValidationException("统计视频点赞或评论点赞的数量!", e);
        }
    }
}
