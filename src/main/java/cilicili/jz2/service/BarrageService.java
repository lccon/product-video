package cilicili.jz2.service;

import cilicili.jz2.domain.Barrage;

import java.util.List;

public interface BarrageService {
	Barrage addBarrage(Barrage barrage);
	
	List<Barrage> showBarrages(Integer videoId);
}
