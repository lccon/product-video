package cilicili.jz2.controller;

import cilicili.jz2.domain.VideoCommentPraise;
import cilicili.jz2.service.VideoCommentPraiseService;
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
        return videoCommentPraiseService.getVideoPraiseByVideoId(id, token);
    }

}
