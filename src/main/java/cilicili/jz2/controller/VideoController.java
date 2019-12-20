package cilicili.jz2.controller;

import cilicili.jz2.component.ThreadVariable;
import cilicili.jz2.domain.Session;
import cilicili.jz2.domain.Video;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.service.impl.VideoServiceImpl;
import cilicili.jz2.vo.VideoVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping ("/video")
public class VideoController {

	@Autowired
	private VideoServiceImpl videoService;

	@RequestMapping ("/findId")
	@ResponseBody
	public VideoVO findVideoById(Integer id) {
		return videoService.findVideoById(id);
	}
	
	@RequestMapping ("/add")
	@ResponseBody
	public VideoVO addVideo(Video video, String token) {
		Session session = ThreadVariable.getSession();
		if (session == null) {
			throw new BusinessValidationException("请重新登录!");
		}
		return videoService.addVideo(video, session.getUserId());
	}
	
	@RequestMapping ("/play")
	@ResponseBody
	public VideoVO playVideo(Integer id, Integer operateState) {
		Session session = ThreadVariable.getSession();
		if (session == null) {
			throw new BusinessValidationException("请重新登录!");
		}
		return videoService.updateVideo(id, operateState, session.getUserId());
	}
	
	@RequestMapping ("/show")
	@ResponseBody
	public Map<String, Serializable> showVideos(Integer offset) {
		Map<String, Serializable> result = new HashMap<>();
		if (offset == null) {
			offset = 0;
		}
		PageHelper.startPage(offset, 12);
		List<VideoVO> videos = videoService.showVideos();
		PageInfo<VideoVO> pageInfo = new PageInfo<>(videos);
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
		List<VideoVO> videos = videoService.queryVideos(q);
		PageInfo<VideoVO> pageInfo = new PageInfo<>(videos);
		result.put("page", pageInfo);
		return result;
	}
	
}
