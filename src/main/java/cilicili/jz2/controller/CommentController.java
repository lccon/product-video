package cilicili.jz2.controller;

import cilicili.jz2.component.ThreadVariable;
import cilicili.jz2.domain.*;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.service.impl.CommentServiceImpl;
import cilicili.jz2.vo.CommentVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping ("/comment")
public class CommentController {
	@Autowired
	private CommentServiceImpl commentService;

	@RequestMapping ("/findId")
	@ResponseBody
	public Map<String, Serializable> showComments(@RequestParam ("id") Integer videoId, Integer offset) {
		Map<String, Serializable> result = new HashMap<>();
		if (offset == null) {
			offset = 0;
		}
		List<CommentVO> comments = commentService.findListCommentByVideoId(videoId, offset);
		PageInfo<CommentVO> pageInfo = new PageInfo<>(comments);
		result.put("page", pageInfo);
		return result;
	}
	
	@RequestMapping ("/add")
	@ResponseBody
	public Comment addComment(Comment comment, String token) {
		Session session = ThreadVariable.getSession();
		if (session == null || session.getUserId() == null) {
			throw new BusinessValidationException("请重新登录!");
		}
		comment.setUserId(session.getUserId());
		return commentService.addComment(comment);
	}
	
	@RequestMapping ("/delete")
	@ResponseBody
	public Boolean deleteComment(Integer id, String token) {
        Session session = ThreadVariable.getSession();
        if (session == null || session.getUserId() == null) {
            throw new BusinessValidationException("请重新登录!");
        }
		return commentService.deleteComment(id, session.getUserId());
	}
	
	@RequestMapping ("/like")
	@ResponseBody
	public Comment likeComment(Integer id, String token) {
        Session session = ThreadVariable.getSession();
        if (session == null || session.getUserId() == null) {
            throw new BusinessValidationException("请重新登录!");
        }
		return commentService.updateComment(id, session.getUserId());
	}
	
}
