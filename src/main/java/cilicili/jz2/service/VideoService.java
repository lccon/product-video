package cilicili.jz2.service;

import cilicili.jz2.domain.Video;
import cilicili.jz2.vo.VideoVO;

import java.util.List;

public interface VideoService {
	VideoVO findVideoById(Integer id);
	
	VideoVO findVideoByUrl(String url);

	VideoVO addVideo(Video video, Integer userId);

	VideoVO updateVideo(Integer id, Integer readCount, Integer userId);
	
	List<VideoVO> showVideos();
	
	List<VideoVO> queryVideos(String keyword);
}
