package cilicili.jz2.controller;

import cilicili.jz2.constant.VideoConstants;
import cilicili.jz2.exception.base.BusinessValidationException;
import cilicili.jz2.exception.base.ServiceValidationException;
import cilicili.jz2.utils.RandomUtil;
import cilicili.jz2.utils.TokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Controller
public class UploadController {

	@RequestMapping (value = "/upload/{token}", method = RequestMethod.POST)
	@ResponseBody
	public String upload(MultipartFile file, @PathVariable ("token") String token, HttpServletRequest request) throws IOException {

		TokenUtil.checkToken(token, TokenUtil.TokenUssage.UPLOAD_FILE);
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
		do {
			storageFilename = RandomUtil.getRandomFilename(extension, filename, token);
			storageFile = new File(realPath + "/" + storageFilename);
		} while (storageFile.exists());
		try {
			file.transferTo(storageFile);
			return storageFilename;
		} catch (IOException e) {
			throw new ServiceValidationException("上传失败！", e);
		}
	}
	
	@RequestMapping (value = "/404", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Serializable> handleError() throws Exception {
		throw new Exception();
	}
	
}
