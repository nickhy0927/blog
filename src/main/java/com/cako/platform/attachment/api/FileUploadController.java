package com.cako.platform.attachment.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

import com.cako.ionic.common.utils.ResponseData;
import com.cako.ionic.common.utils.ResponseData.CALLCODE;
import com.cako.platform.attachment.entity.Attachment;
import com.cako.platform.attachment.service.AttachmentService;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.FileTools;
import com.orm.commons.utils.JsonMapper;
import com.orm.commons.utils.MyConfig;

@Controller
public class FileUploadController {

	@Autowired
	private AttachmentService attachmentService;
	private static String tempDir = "";

	@RequestMapping(value = "attachment/uploadFile", method = { RequestMethod.POST, RequestMethod.GET })
	public void upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		tempDir = request.getSession().getServletContext().getRealPath("/upload/temp");
		HashMap<String, Object> hashMap = MyConfig.getConfig();
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
		String filename = request.getParameter("filename");
		InputStream inputStream = attachmentService.getMongoFile(filename, "pictures");
		try {
			int i = inputStream.available(); // 得到文件大小
			byte data[] = new byte[i];
			inputStream.read(data); // 读数据
			inputStream.close();
			response.setContentType("image/*"); // 设置返回的文件类型
			OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
			toClient.write(data); // 输出数据
			toClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
