package cilicili.jz2.dao;

import cilicili.jz2.domain.Comment;
import org.apache.ibatis.annotations.Param;

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
    int insertComment(Comment record);

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
    Comment getCommentById(@Param("id") Integer id);

    /**
     * 获取视频所有评论
     * @param videoId
     * @return
     */
    List<Comment> findListCommentByVideoId(@Param ("id") int videoId);
}