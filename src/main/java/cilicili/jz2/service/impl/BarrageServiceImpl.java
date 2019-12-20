package cilicili.jz2.service.impl;

import cilicili.jz2.dao.BarrageMapper;
import cilicili.jz2.domain.*;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.ServiceValidationException;
import cilicili.jz2.service.BarrageService;
import cilicili.jz2.service.UserService;
import cilicili.jz2.service.VideoService;
import cilicili.jz2.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.List;

@Service ("barrageService")
public class BarrageServiceImpl implements BarrageService {
	@Autowired
	private BarrageMapper barrageMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private VideoService videoService;

	@Override
	public Barrage addBarrage(Barrage barrage) {
		User user = userService.findUserById(barrage.getUserId());
		if (user == null) {
			throw new BusinessValidationException("用户不存在");
		}
		if (barrage.getVideoId() == null) {
			throw new BusinessValidationException("视频id为空");
		}
		VideoVO videoVO = videoService.findVideoById(barrage.getVideoId());
		if (videoVO == null) {
			throw new BusinessValidationException("视频不存在！");
		}
		if (StringUtils.isEmpty(barrage.getContent())) {
			throw new BusinessValidationException("弹幕内容为空");
		}
		if (barrage.getContent().length() == 0 || barrage.getContent().length() > 250) {
			throw new BusinessValidationException("弹幕内容为空或超过250长度限制");
		}
		try {
			if (barrage.getColor() == null || barrage.getColor().length() == 0 || barrage.getColor().length() > 10) {
				barrage.setColor("#ffffff");
			}
			if (barrage.getOfftime() == null || barrage.getOfftime() < 0) {
				barrage.setOfftime(0);
			}
			if (barrage.getPosition() == null || barrage.getPosition() < 0) {
				barrage.setPosition(Byte.valueOf("0"));
			}
			barrage.setSendtime(ZonedDateTime.now());
			barrageMapper.addBarrage(barrage);
			return barrage;
		} catch (Exception e) {
			throw new ServiceValidationException("新增弹幕出错", e);
		}
	}
	
	@Override
	public List<Barrage> showBarrages(Integer videoId) {
		if(videoId == null) {
			throw new BusinessValidationException("视频id不能为空！");
		}
		try {
			return barrageMapper.findBarrageList(videoId);
		} catch (Exception e) {
			throw new ServiceValidationException("查询弹幕失败！", e);
		}
	}
}
