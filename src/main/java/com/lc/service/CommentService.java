package com.lc.service;

import com.lc.domain.Comment;
import com.lc.vo.CommentVO;

import java.util.List;

public interface CommentService {
	Comment findCommentById(Integer id);

	Comment addComment(Comment comment);

	Boolean deleteComment(Integer id, Integer userId);

	Comment updateComment(Integer id, Integer userId);
	
	List<CommentVO> findListCommentByVideoId(Integer videoId, Integer offset);
}
