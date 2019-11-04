package cilicili.jz2.dao;

import cilicili.jz2.domain.Barrage;
import cilicili.jz2.domain.BarrageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BarrageMapper {
    long countByExample(BarrageExample example);

    int deleteByExample(BarrageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Barrage record);

    int insertSelective(Barrage record);

    List<Barrage> findBarrageList(Integer videoId);

    Barrage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param ("record") Barrage record, @Param ("example") BarrageExample example);

    int updateByExample(@Param ("record") Barrage record, @Param ("example") BarrageExample example);

    int updateByPrimaryKeySelective(Barrage record);

    int updateByPrimaryKey(Barrage record);
}