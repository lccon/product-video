package cilicili.jz2.controller;

import cilicili.jz2.domain.Barrage;
import cilicili.jz2.service.impl.BarrageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping ("/barrage")
public class BarrageController {
	@Autowired
	private BarrageServiceImpl barrageService;

	@RequestMapping ("/findId")
	@ResponseBody
	public List<Barrage> showBarrages(@RequestParam ("id") Integer videoId) {
		return barrageService.showBarrages(videoId);
	}

	@RequestMapping ("/add")
	@ResponseBody
	public Barrage addBarrage(Barrage barrage, String token) {
		return barrageService.addBarrage(barrage, token);
	}
	
}
