package cilicili.jz2.dao;

import cilicili.jz2.domain.Token;
import cilicili.jz2.domain.TokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TokenMapper {
    long countByExample(TokenExample example);

    int deleteByExample(Integer userId);

    int deleteByPrimaryKey(Integer id);

    int insert(Token record);

    int insertSelective(Token record);

    List<Token> selectByExample(TokenExample example);

    Token selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param ("record") Token record, @Param ("example") TokenExample example);

    int updateByExample(@Param ("record") Token record, @Param ("example") TokenExample example);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}