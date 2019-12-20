package cilicili.jz2.service.impl;

import cilicili.jz2.constant.VideoConstants;
import cilicili.jz2.dao.VideoMapper;
import cilicili.jz2.domain.User;
import cilicili.jz2.domain.VideoCommentPraise;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.ServiceValidationException;
import cilicili.jz2.domain.Video;
import cilicili.jz2.service.UserService;
import cilicili.jz2.service.VideoCommentPraiseService;
import cilicili.jz2.service.VideoService;
import cilicili.jz2.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.List;

@Service ("videoService")
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private VideoCommentPraiseService videoCommentPraiseService;

	@Override
	public VideoVO findVideoById(Integer id) {
		if (id == null) {
			throw new BusinessValidationException("主键id不能为空");
		}
		try {
			return videoMapper.findById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("没有该视频！", e);
		}
	}
	
	@Override
	public VideoVO findVideoByUrl(String url) {
	    if(StringUtils.isEmpty(url)) {
	        throw new BusinessValidationException("视频地址不能为空!");
        }
        try {
            return videoMapper.findByUrl(url);
        } catch (Exception e) {
	        throw new ServiceValidationException("获取视频出错!", e);
        }
	}
	
	@Override
	public VideoVO addVideo(Video video, Integer userId) {
		User user = userService.findUserById(userId);
		if (user == null) {
			throw new BusinessValidationException("用户不存在");
		}
		if (StringUtils.isEmpty(video.getTitle())) {
			throw new BusinessValidationException("视频标题为空");
		}
		if (video.getTitle().length() == 0 || video.getTitle().length() > 50) {
			throw new BusinessValidationException("视频标题为空或超过50长度限制");
		}
		if (StringUtils.isEmpty(video.getUrl())) {
			throw new BusinessValidationException("视频地址为空");
		}
		if (video.getUrl().length() == 0 || video.getUrl().length() > 100) {
			throw new BusinessValidationException("视频地址为空或超过100长度限制");
		}
		if (StringUtils.isEmpty(video.getPicUrl())) {
			throw new BusinessValidationException("视频封面地址为空");
		}
		if (video.getPicUrl().length() == 0 || video.getPicUrl().length() > 100) {
			throw new BusinessValidationException("视频封面地址为空或超过100长度限制");
		}
		try {
			video.setUploadUserid(user.getId());
			video.setId(null);
			video.setUploadTime(ZonedDateTime.now());
			video.setCountPlay(0);
			video.setCountLike(0);
			videoMapper.addVideo(video);
			return findVideoByUrl(video.getUrl());
		} catch (Exception e) {
			throw new ServiceValidationException("新增视频出错！", e);
		}
	}
	
	@Override
	public VideoVO updateVideo(Integer id, Integer readCount, Integer userId) {
		VideoVO videoVo = findVideoById(id);
		if(VideoConstants.PLAY_COUNT.equals(readCount)) {
			videoVo.setCountPlay(videoVo.getCountPlay() + 1);
		} else if (VideoConstants.LIKE_COUNT.equals(readCount)) {
			VideoCommentPraise videoPraise = new VideoCommentPraise();
			videoPraise.setVideoId(id);
			videoPraise.setUserId(userId);
			Integer count = videoCommentPraiseService.countVideoPraiseNum(videoPraise);
			if(count > 0) {
				throw new BusinessValidationException("赞过啦！");
			} else {
				videoCommentPraiseService.addVideoCommentPraise(videoPraise);
				videoVo.setCountLike(videoVo.getCountLike() + 1);
			}
		}
		try {
			videoMapper.updateVideo(videoVo);
			return videoVo;
		} catch (Exception e) {
			throw new ServiceValidationException("修改视频信息出错", e);
		}
	}
	
	@Override
	public List<VideoVO> showVideos() {
	    try {
            return videoMapper.findAllVideos();
        } catch (Exception e) {
	        throw new ServiceValidationException("获取所有的视频出错！", e);
        }
	}
	
	@Override
	public List<VideoVO> queryVideos(String keyword) {
	    if (StringUtils.isEmpty(keyword)) {
	        throw new BusinessValidationException("关键词不能为空!");
        }
        try {
            return videoMapper.queryVideo(keyword);
        } catch (Exception e) {
	        throw new ServiceValidationException("获取视频出错!", e);
        }
	}
}
