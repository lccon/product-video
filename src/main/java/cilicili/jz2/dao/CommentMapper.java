package cilicili.jz2.dao;

import cilicili.jz2.domain.Comment;

public interface CommentMapper {

    Integer deleteComment(Integer id);

    int insertComment(Comment record);

    int updateByPrimaryKeySelective(Comment record);
}