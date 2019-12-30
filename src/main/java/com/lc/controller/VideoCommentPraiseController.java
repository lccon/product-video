package com.lc.controller;

import com.lc.component.ThreadVariable;
import com.lc.domain.Session;
import com.lc.domain.VideoCommentPraise;
import com.lc.exception.base.BusinessValidationException;
import com.lc.service.VideoCommentPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @Date:2019/11/21
 * @Author:lc
 */
@Controller
@RequestMapping("videoCommentPraise")
public class VideoCommentPraiseController {

    @Autowired
    private VideoCommentPraiseService videoCommentPraiseService;

    @RequestMapping("/getVideoPraise")
    @ResponseBody
    public VideoCommentPraise getVideoCommentPraise(Integer id, String token) {
        Session session = ThreadVariable.getSession();
        if (session == null) {
            throw new BusinessValidationException("请重新登录!");
        }
        return videoCommentPraiseService.getVideoPraiseByVideoId(id, session.getUserId());
    }

}
