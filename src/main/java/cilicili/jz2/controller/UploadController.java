package cilicili.jz2.controller;

import cilicili.jz2.utils.BaseUtil;
import cilicili.jz2.utils.RandomUtil;
import cilicili.jz2.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {

	@RequestMapping (value = "/upload/{token}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Serializable> upload(MultipartFile file, @PathVariable ("token") String token, HttpServletRequest request) throws IOException {

		String realPath = request.getSession().getServletContext().getRealPath(BaseUtil.STORAGE_DIR);

		Map<String, Serializable> result = new HashMap<>();
		do {
			result.put("status", "failure");
			try {
				TokenUtil.checkToken(token, TokenUtil.TokenUssage.UPLOAD_FILE);
			} catch (TokenUtil.TokenExpired | TokenUtil.TokenNotFound | TokenUtil.TokenOverAuthed | TokenUtil.TokenUssageNotMatched tokenError) {
				result.put("msg", tokenError.getMessage());
				break;
			}
			if (file == null || file.isEmpty()) {
				result.put("msg", "没有选择文件");
			} else {
				long fileSize = file.getSize();
				if (fileSize >= BaseUtil.MAX_FILE_SIZE) {
					result.put("msg", "文件体积超过上限");
					break;
				}
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
					result.put("status", "success");
					result.put("msg", "上传成功");
					result.put("url", storageFilename);
				} catch (IOException e) {
					Logger logger = LoggerFactory.getLogger(this.getClass());
					logger.error(e.getMessage());
					logger.error(e.getLocalizedMessage());
					result.put("msg", "上传失败");
				}
			}
		} while (false);
		return result;
	}
	
	@RequestMapping (value = "/404", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Serializable> handleError() throws Exception {
		throw new Exception();
	}
	
}
