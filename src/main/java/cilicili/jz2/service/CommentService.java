package cilicili.jz2.service;

import cilicili.jz2.domain.Comment;

import java.util.List;

public interface CommentService {
	Comment findCommentById(Integer id);

	Comment addComment(Comment comment, String token);

	Boolean deleteComment(Integer id, String token);

	Comment updateComment(Integer id);
	
	List<Comment> showComments(Integer videoId, Integer offset);
}
