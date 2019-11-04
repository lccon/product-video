package cilicili.jz2.controller;

import cilicili.jz2.constant.VideoConstants;
import cilicili.jz2.domain.Video;
import cilicili.jz2.service.impl.VideoServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping ("/video")
public class VideoController {

	@Autowired
	private VideoServiceImpl videoService;

	@RequestMapping ("/findId")
	@ResponseBody
	public Video findVideoById(Integer id) {
		return videoService.findVideoById(id);
	}
	
	@RequestMapping ("/add")
	@ResponseBody
	public Video addVideo(Video video, String token) {
		return videoService.addVideo(video, token);
	}
	
	@RequestMapping ("/play")
	@ResponseBody
	public Video playVideo(Integer id) {
		return videoService.updateVideo(id, VideoConstants.PLAY_COUNT);
	}
	
	@RequestMapping ("/like")
	@ResponseBody
	public Video likeVideo(Integer id) {
		return videoService.updateVideo(id, VideoConstants.LIKE_COUNT);
	}
	
	@RequestMapping ("/show")
	@ResponseBody
	public Map<String, Serializable> showVideos(Integer offset) {
		Map<String, Serializable> result = new HashMap<>();
		if (offset == null) {
			offset = 0;
		}
		PageHelper.startPage(offset, 12);
		ArrayList<Video> videos = (ArrayList<Video>) videoService.showVideos();
		PageInfo<Video> pageInfo = new PageInfo<>(videos);
		result.put("page", pageInfo);
		return result;
	}
	
	@RequestMapping ("/find")
	@ResponseBody
	public Map<String, Serializable> findVideos(Integer offset, String q) {
		Map<String, Serializable> result = new HashMap<>();
		if (offset == null) {
			offset = 0;
		}
		PageHelper.startPage(offset, 12);
		ArrayList<Video> videos = (ArrayList<Video>) videoService.queryVideos(q);
		PageInfo<Video> pageInfo = new PageInfo<>(videos);
		result.put("page", pageInfo);
		return result;
	}
	
}
