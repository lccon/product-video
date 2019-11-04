package cilicili.jz2.service.impl;

import cilicili.jz2.dao.CommentMapper;
import cilicili.jz2.dao.MyCommentMapper;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.ServiceValidationException;
import cilicili.jz2.domain.*;
import cilicili.jz2.service.CommentService;
import cilicili.jz2.service.UserService;
import cilicili.jz2.service.VideoService;
import cilicili.jz2.utils.TokenUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private MyCommentMapper myCommentMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private VideoService videoService;

	@Override
	public Comment findCommentById(Integer id) {
		return myCommentMapper.findById(id);
	}
	
	@Override
	public Comment addComment(Comment comment, String token) {
		Token tokenCheck = TokenUtil.checkToken(token, TokenUtil.TokenUssage.DEFAULT);
		User user = userService.findUserById(tokenCheck.getUserId());
		if(user == null) {
			throw new BusinessValidationException("用户不存在");
		}
		if (comment.getContent() == null) {
			throw new BusinessValidationException("评论内容为空");
		}
		if (comment.getContent().length() == 0 || comment.getContent().length() > 250) {
			throw new BusinessValidationException("评论内容为空或超过250长度限制");
		}
		if (comment.getVideoId() == null) {
			throw new BusinessValidationException("视频id为空");
		}
		Video video = videoService.findVideoById(comment.getVideoId());
		if (video == null) {
			throw new BusinessValidationException("视频不存在");
		}
		comment.setUserId(user.getId());
		comment.setSendtime(ZonedDateTime.now());
		comment.setCountLike(0);
		try {
			commentMapper.insert(comment);
			return comment;
		} catch (Exception e) {
			throw new ServiceValidationException("新增视频评论出错！", e);
		}

	}
	
	@Override
	public Boolean deleteComment(Integer id, String token) {
		Token tokenCheck = TokenUtil.checkToken(token, TokenUtil.TokenUssage.DEFAULT);
		User user = userService.findUserById(tokenCheck.getUserId());
		if (user == null) {
			throw new BusinessValidationException("用户不存在");
		}
		Comment comment = findCommentById(id);
		if (comment == null) {
			throw new BusinessValidationException("没有该评论");
		}
		if (!user.getId().equals(comment.getUserId())) {
			throw new BusinessValidationException("非本人操作，拒绝授权");
		}
		try {
			Integer count = commentMapper.deleteByPrimaryKey(id);
			return count > 0;
		} catch (Exception e) {
			throw new ServiceValidationException("删除视频评论出错！", e);
		}
	}
	
	@Override
	public Comment updateComment(Integer id) {
		Comment comment = findCommentById(id);
		comment.setCountLike(comment.getCountLike() + 1);
		try {
			commentMapper.updateByPrimaryKeySelective(comment);
			return comment;
		} catch (Exception e) {
			throw new ServiceValidationException("点赞出错！", e);
		}
	}
	
	@Override
	public List<Comment> showComments(Integer videoId, Integer offset) {
		if(videoId == null) {
			throw new BusinessValidationException("视频id不能为空!");
		}
		try {
			PageHelper.startPage(offset, 10);
			return myCommentMapper.showComments(videoId);
		} catch (Exception e) {
			throw new ServiceValidationException("获取视频评论出错", e);
		}
	}
}
