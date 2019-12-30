package com.lc.service.impl;

import com.lc.dao.VideoCommentPraiseMapper;
import com.lc.domain.VideoCommentPraise;
import com.lc.exception.base.BusinessValidationException;
import com.lc.exception.base.ServiceValidationException;
import com.lc.service.UserService;
import com.lc.service.VideoCommentPraiseService;
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
    @Autowired
    private UserService userService;

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
    public Integer countVideoPraiseNum(VideoCommentPraise videoPraise) {
        if (videoPraise == null) {
            throw new BusinessValidationException("参数不能为空!");
        }
        try {
            return videoCommentPraiseMapper.countVideoPraiseNum(videoPraise);
        } catch (Exception e) {
            throw new ServiceValidationException("统计视频点赞数量出错!", e);
        }
    }

    @Override
    public VideoCommentPraise getCommentPraiseInfo(VideoCommentPraise commentPraise) {
        if (commentPraise == null) {
            throw new BusinessValidationException("参数不能为空!");
        }
        try {
            return videoCommentPraiseMapper.getCommentPraiseInfo(commentPraise);
        } catch (Exception e) {
            throw new ServiceValidationException("统计评论点赞的数量出错!", e);
        }
    }

    @Override
    public void updateCommentPraise(VideoCommentPraise commentPraise) {
        if (commentPraise == null) {
            throw new BusinessValidationException("参数不能为空!");
        }
        try {
            videoCommentPraiseMapper.updateCommentPraise(commentPraise);
        } catch (Exception e) {
            throw new ServiceValidationException("修改评论点赞的信息!", e);
        }
    }

    @Override
    public VideoCommentPraise getVideoPraiseByVideoId(Integer id, Integer userId) {
        VideoCommentPraise videoPraise = new VideoCommentPraise();
        videoPraise.setVideoId(id);
        videoPraise.setUserId(userId);
        try {
            return videoCommentPraiseMapper.getVideoPraiseByVideoId(videoPraise);
        }catch (Exception e) {
            throw new ServiceValidationException("查询视频点赞失败!", e);
        }
    }

}
