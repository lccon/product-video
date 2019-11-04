package cilicili.jz2.controller;

import cilicili.jz2.domain.*;
import cilicili.jz2.service.impl.CommentServiceImpl;
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
		List<Comment> comments = commentService.showComments(videoId, offset);
		PageInfo<Comment> pageInfo = new PageInfo<>(comments);
		result.put("page", pageInfo);
		return result;
	}
	
	@RequestMapping ("/add")
	@ResponseBody
	public Comment addComment(Comment comment, String token) {
		return commentService.addComment(comment, token);
	}
	
	@RequestMapping ("/delete")
	@ResponseBody
	public Boolean deleteComment(Integer id, String token) {
		return commentService.deleteComment(id, token);
	}
	
	@RequestMapping ("/like")
	@ResponseBody
	public Comment likeComment(Integer id) {
		return commentService.updateComment(id);
	}
	
}
