package cilicili.jz2.service;

import cilicili.jz2.domain.Comment;
import cilicili.jz2.vo.CommentVO;

import java.util.List;

public interface CommentService {
	Comment findCommentById(Integer id);

	Comment addComment(Comment comment);

	Boolean deleteComment(Integer id, Integer userId);

	Comment updateComment(Integer id, Integer userId);
	
	List<CommentVO> findListCommentByVideoId(Integer videoId, Integer offset);
}
