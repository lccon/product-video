package cilicili.jz2.dao;

import cilicili.jz2.domain.Barrage;

import java.util.List;

public interface BarrageMapper {

    /**
     * 新增弹幕
     * @param record
     * @return
     */
    int addBarrage(Barrage record);

    /**
     * 查询弹幕列表
     * @param videoId
     * @return
     */
    List<Barrage> findBarrageList(Integer videoId);

    /**
     * 修改弹幕
     * @param record
     * @return
     */
    int updateBarrage(Barrage record);
}