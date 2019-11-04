package cilicili.jz2.service;

import cilicili.jz2.domain.Video;

import java.util.List;

public interface VideoService {
	Video findVideoById(Integer id);
	
	Video findVideoByUrl(String url);

	Video addVideo(Video video,  String token);

	Video updateVideo(Integer id, Integer readCount);
	
	List<Video> showVideos();
	
	List<Video> queryVideos(String keyword);
}
