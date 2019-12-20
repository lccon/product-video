package cilicili.jz2.controller;

import cilicili.jz2.component.ThreadVariable;
import cilicili.jz2.domain.Barrage;
import cilicili.jz2.domain.Session;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.service.impl.BarrageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
		Session session = ThreadVariable.getSession();
		if(session == null) {
			throw new BusinessValidationException("请重新登录!");
		}
		barrage.setUserId(session.getUserId());
		return barrageService.addBarrage(barrage);
	}
	
}
