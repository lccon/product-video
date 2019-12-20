package cilicili.jz2.controller;

import cilicili.jz2.component.ThreadVariable;
import cilicili.jz2.constant.VideoConstants;
import cilicili.jz2.domain.Session;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.ServiceValidationException;
import cilicili.jz2.utils.RandomUtil;
import cilicili.jz2.utils.AttachConvertUtil;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.Serializable;
import java.util.Map;

@Controller
public class UploadController {

	@RequestMapping (value = "/upload/{bgsound}", method = RequestMethod.POST)
	@ResponseBody
	public String upload(MultipartFile file, @PathVariable ("bgsound") String bgsound,
						 HttpServletRequest request) throws Exception {
		Session session = ThreadVariable.getSession();
		if(session == null) {
			throw new BusinessValidationException("请重新登录!");
		}
		if (file == null || file.isEmpty()) {
			throw new BusinessValidationException("没有选择文件");
		}
		if (file.getSize() >= VideoConstants.MAX_FILE_SIZE) {
			throw new BusinessValidationException("文件体积超过上限");
		}

		String realPath = request.getSession().getServletContext().getRealPath(VideoConstants.STORAGE_DIR);
		String fileOriginalName = file.getOriginalFilename();
		String[] fileOriginalNameArr = fileOriginalName.split("\\.");
		String filename = fileOriginalName.substring(0, fileOriginalName.lastIndexOf("."));
		String extension = fileOriginalNameArr[fileOriginalNameArr.length - 1];
		String storageFilename;
		File storageFile;
		try {
			storageFilename = RandomUtil.getRandomFilename(extension, filename, session.getSessionId());
			storageFile = new File(realPath + File.separator + storageFilename);
			file.transferTo(storageFile);
			if(bgsound != null && !"null".equals(bgsound) && !"0".equals(bgsound)) {
				Encoder encoder =new Encoder();
				MultimediaInfo m = encoder.getInfo(storageFile);
				long seconds = m.getDuration()/1000;

				String videoConvertUrl = null;
				if(bgsound.equals("1")) {
					videoConvertUrl = AttachConvertUtil.existSoundVideoConvert(storageFilename, seconds, request);
				} else if (bgsound.equals("2")) {
					videoConvertUrl = AttachConvertUtil.noSoundVideoConvert(storageFilename, seconds, request);
				}
				File originalFile = new File(realPath + File.separator + storageFilename);
				if (originalFile.exists()) {
					originalFile.delete();
				}
				return videoConvertUrl;
			}
			return storageFilename;
		} catch (Exception e) {
			throw new ServiceValidationException("上传失败！", e);
		}
	}
	
	@RequestMapping (value = "/404", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Serializable> handleError() throws Exception {
		throw new Exception();
	}
	
}
