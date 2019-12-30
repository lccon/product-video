package com.lc.service;

import com.lc.domain.Barrage;

import java.util.List;

public interface BarrageService {
	Barrage addBarrage(Barrage barrage);
	
	List<Barrage> showBarrages(Integer videoId);
}
