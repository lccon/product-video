package cilicili.jz2.dao;

import cilicili.jz2.domain.User;
import cilicili.jz2.domain.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param ("record") User record, @Param ("example") UserExample example);

    int updateByExample(@Param ("record") User record, @Param ("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // LC
    User getUserByUsernamePassword(@Param("username") String username, @Param("password") String password);

    // LC
    User getUserByUsername(String username);
}