package cilicili.jz2.service.impl;

import cilicili.jz2.dao.CommentMapper;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.ServiceValidationException;
import cilicili.jz2.domain.*;
import cilicili.jz2.service.CommentService;
import cilicili.jz2.service.UserService;
import cilicili.jz2.service.VideoCommentPraiseService;
import cilicili.jz2.service.VideoService;
import cilicili.jz2.vo.CommentVO;
import cilicili.jz2.vo.VideoVO;
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
	private VideoService videoService;
	@Autowired
	private VideoCommentPraiseService videoCommentPraiseService;

	@Override
	public Comment findCommentById(Integer id) {
		return commentMapper.getCommentById(id);
	}
	
	@Override
	public Comment addComment(Comment comment) {
		if (comment.getContent() == null) {
			throw new BusinessValidationException("评论内容为空");
		}
		if (comment.getContent().length() == 0 || comment.getContent().length() > 250) {
			throw new BusinessValidationException("评论内容为空或超过250长度限制");
		}
		if (comment.getVideoId() == null) {
			throw new BusinessValidationException("视频id为空");
		}
		VideoVO videoVo = videoService.findVideoById(comment.getVideoId());
		if (videoVo == null) {
			throw new BusinessValidationException("视频不存在");
		}
		comment.setSendtime(ZonedDateTime.now());
		comment.setCountLike(0);
		try {
			commentMapper.addComment(comment);
			return comment;
		} catch (Exception e) {
			throw new ServiceValidationException("新增视频评论出错！", e);
		}

	}
	
	@Override
	public Boolean deleteComment(Integer id, Integer userId) {
		Comment comment = findCommentById(id);
		if (comment == null) {
			throw new BusinessValidationException("没有该评论");
		}
		if (!userId.equals(comment.getUserId())) {
			throw new BusinessValidationException("非本人操作，拒绝授权");
		}
		try {
			Integer count = commentMapper.deleteComment(id);
			return count > 0;
		} catch (Exception e) {
			throw new ServiceValidationException("删除视频评论出错！", e);
		}
	}
	
	@Override
	public Comment updateComment(Integer id, Integer userId) {
		Comment comment = findCommentById(id);
		VideoCommentPraise commentPraise = new VideoCommentPraise();
		commentPraise.setUserId(userId);
		commentPraise.setCommentId(id);
		VideoCommentPraise commentPraiseInfo = videoCommentPraiseService.getCommentPraiseInfo(commentPraise);
		if (commentPraiseInfo != null) {
			if (commentPraiseInfo.getHasCommentPraise() == 1) {
				commentPraise.setHasCommentPraise(0);
				if (comment.getCountLike() > 0) {
					comment.setCountLike(comment.getCountLike() - 1);
				} else {
					comment.setCountLike(0);
				}
			} else {
				commentPraise.setHasCommentPraise(1);
				comment.setCountLike(comment.getCountLike() + 1);
			}
			videoCommentPraiseService.updateCommentPraise(commentPraise);
		} else {
			commentPraise.setHasCommentPraise(1);
			comment.setCountLike(comment.getCountLike() + 1);
			videoCommentPraiseService.addVideoCommentPraise(commentPraise);
		}
		try {
			commentMapper.updateComment(comment);
			return comment;
		} catch (Exception e) {
			throw new ServiceValidationException("评论点赞出错！", e);
		}
	}
	
	@Override
	public List<CommentVO> findListCommentByVideoId(Integer videoId, Integer offset) {
		if(videoId == null) {
			throw new BusinessValidationException("视频id不能为空!");
		}
		try {
			PageHelper.startPage(offset, 10);
			return commentMapper.findListCommentByVideoId(videoId);
		} catch (Exception e) {
			throw new ServiceValidationException("获取视频评论出错", e);
		}
	}
}
