package com.cako.platform.attachment.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cako.deploy.pictures.entity.Picture;
import com.cako.deploy.pictures.service.PictureService;
import com.cako.ionic.common.utils.ResponseData;
import com.cako.ionic.common.utils.ResponseData.CALLCODE;
import com.cako.platform.attachment.entity.Attachment;
import com.cako.platform.attachment.service.AttachmentService;
import com.cako.platform.user.entity.User;
import com.cako.platform.user.service.UserService;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.FileOperateUtil;
import com.orm.commons.utils.FileTools;
import com.orm.commons.utils.JsonMapper;
import com.orm.commons.utils.MyConfig;
import com.orm.commons.utils.WebUtils;

@Controller
public class FileUploadController {

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private PictureService pictureService;

	private static String tempDir = "";

	@RequestMapping(value = "attachment/uploadFile", method = { RequestMethod.POST, RequestMethod.GET })
	public void upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		tempDir = request.getSession().getServletContext().getRealPath("/upload/temp");
		HashMap<String, Object> hashMap = MyConfig.getConfig();
		Map<String, Object> paramsToMap = WebUtils.getParamsToMap(request);
		Object object = hashMap.get("upload");
		if (object != null) {
			tempDir = object.toString();
		}
		if (!new File(tempDir).exists()) {
			new File(tempDir).mkdirs();
		}
		Iterator<String> iterator = request.getFileNames();
		List<Attachment> attachments = new ArrayList<Attachment>();
		while (iterator.hasNext()) {
			MultipartFile multipartFile = request.getFile((String) iterator.next());
			String type = multipartFile.getContentType();
			String name = multipartFile.getOriginalFilename();
			float size = (float) (multipartFile.getSize() / (1024));
			String fileSize = "";
			if (size > 1024 && size < 1024 * 1024) {
				fileSize = (size / 1024) + "MB";
			} else if (size >= 1024 * 1024) {
				fileSize = (size / 1024 / 1024) + "GB";
			} else {
				fileSize = size + "KB";
			}
			String filename = UUID.randomUUID().toString() + "." + FileTools.getFileExtension(name);
			Attachment attachment = new Attachment(filename, null, type, fileSize, FileTools.getFileExtension(name));
			File file = new File(tempDir, filename);
			try {
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
				attachmentService.mongoFileUpload(file, "pictures");
				attachment = attachmentService.save(attachment);
				attachments.add(attachment);
				Object idObject = paramsToMap.get("id");
				Object flag = paramsToMap.get("flag");
				if (idObject != null) {
					User user = userService.get(idObject.toString());
					Picture picture = new Picture();
					picture.setUser(user);
					picture.setAttachment(attachment);
					if (flag != null && "1".equals(flag.toString())) {
						user.setAttachment(attachment);
						userService.save(user);
					} else {
						pictureService.save(picture);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		FileTools.delFolder(tempDir);
		try {
			ResponseData responseData = new ResponseData();
			responseData.setResponseCode(CALLCODE.SUCCESS);
			responseData.setResponseMessage("上传成功");
			responseData.setResultMap(attachments);
			response.getWriter().write(new JsonMapper().toJson(responseData));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/attachment/viewPicture.html")
	public void viewPicture(HttpServletResponse response, HttpServletRequest request) {
		String downloadfFileName = request.getParameter("filename");
		InputStream inputStream = attachmentService.getMongoFile(downloadfFileName, "pictures");
		try {
			downloadfFileName = new String(downloadfFileName.getBytes("iso-8859-1"), "utf-8");
			String fileName = downloadfFileName.substring(downloadfFileName.indexOf("_") + 1);
			String userAgent = request.getHeader("User-Agent");
			byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8");
			fileName = new String(bytes, "ISO-8859-1");
			response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
			FileOperateUtil.download(inputStream, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
