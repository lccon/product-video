package cilicili.jz2.service.impl;

import cilicili.jz2.constant.VideoConstants;
import cilicili.jz2.dao.MyVideoMapper;
import cilicili.jz2.dao.VideoMapper;
import cilicili.jz2.domain.Token;
import cilicili.jz2.domain.User;
import cilicili.jz2.domain.VideoCommentPraise;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.ServiceValidationException;
import cilicili.jz2.domain.Video;
import cilicili.jz2.service.UserService;
import cilicili.jz2.service.VideoCommentPraiseService;
import cilicili.jz2.service.VideoService;
import cilicili.jz2.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service ("videoService")
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private MyVideoMapper myVideoMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private VideoCommentPraiseService videoCommentPraiseService;

	@Override
	public Video findVideoById(Integer id) {
		if (id == null) {
			throw new BusinessValidationException("主键id不能为空");
		}
		try {
			return myVideoMapper.findById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("没有该视频！", e);
		}
	}
	
	@Override
	public Video findVideoByUrl(String url) {
		return myVideoMapper.findByUrl(url);
	}
	
	@Override
	public Video addVideo(Video video, String token) {
		Token tokenCheck = TokenUtil.checkToken(token, TokenUtil.TokenUssage.DEFAULT);
		User user = userService.findUserById(tokenCheck.getUserId());
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
			videoMapper.insertVideo(video);
			return findVideoByUrl(video.getUrl());
		} catch (Exception e) {
			throw new ServiceValidationException("新增视频出错！", e);
		}
	}
	
	@Override
	public Video updateVideo(Integer id, Integer readCount, String token) {
		Video video = findVideoById(id);
		if(VideoConstants.PLAY_COUNT.equals(readCount)) {
			video.setCountPlay(video.getCountPlay() + 1);
		} else if (VideoConstants.LIKE_COUNT.equals(readCount)) {
			Token tokenCheck = TokenUtil.checkToken(token, TokenUtil.TokenUssage.DEFAULT);
			User user = userService.findUserById(tokenCheck.getUserId());
			VideoCommentPraise videoPraise = new VideoCommentPraise();
			videoPraise.setVideoId(id);
			videoPraise.setUserId(user.getId());
			Integer count = videoCommentPraiseService.countVideoPraiseNum(videoPraise);
			if(count > 0) {
				throw new BusinessValidationException("赞过啦！");
			} else {
				videoPraise.setCreateDate(new Date());
				videoCommentPraiseService.addVideoCommentPraise(videoPraise);
				video.setCountLike(video.getCountLike() + 1);
			}
		}
		try {
			videoMapper.updateVideo(video);
			return video;
		} catch (Exception e) {
			throw new ServiceValidationException("修改视频信息出错", e);
		}
	}
	
	@Override
	public List<Video> showVideos() {
		return myVideoMapper.findAllVideos();
	}
	
	@Override
	public List<Video> queryVideos(String keyword) {
		return myVideoMapper.queryVideo("%" + keyword + "%");
	}
}
