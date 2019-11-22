package cilicili.jz2.dao;

import cilicili.jz2.domain.Comment;
import cilicili.jz2.vo.CommentVO;

import java.util.List;

public interface CommentMapper {

    /**
     * 删除评论
     * @param id
     * @return
     */
    Integer deleteComment(Integer id);

    /**
     * 新增评论
     * @param record
     * @return
     */
    int addComment(Comment record);

    /**
     * 修改评论
     * @param record
     * @return
     */
    int updateComment(Comment record);

    /**
     * 获取视频评论
     * @param id
     * @return
     */
    Comment getCommentById(Integer id);

    /**
     * 获取视频所有评论
     * @param videoId
     * @return
     */
    List<CommentVO> findListCommentByVideoId(Integer videoId);
}