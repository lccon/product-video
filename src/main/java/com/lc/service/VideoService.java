package com.lc.service;

import com.lc.domain.Video;
import com.lc.vo.VideoVO;

import java.util.List;

public interface VideoService {
	VideoVO findVideoById(Integer id);
	
	VideoVO findVideoByUrl(String url);

	VideoVO addVideo(Video video, Integer userId);

	VideoVO updateVideo(Integer id, Integer readCount, Integer userId);
	
	List<VideoVO> showVideos();
	
	List<VideoVO> queryVideos(String keyword);
}
