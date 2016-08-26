package com.cako.basic.index;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cako.basic.topic.column.entity.Column;
import com.cako.basic.topic.column.service.ColumnService;
import com.cako.ionic.common.utils.ResponseData;
import com.cako.platform.attachment.entity.Attachment;
import com.cako.platform.attachment.service.AttachmentService;
import com.cako.platform.user.entity.User;
import com.cako.platform.user.service.UserService;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.FileTools;
import com.orm.commons.utils.JsonMapper;
import com.orm.commons.utils.MessageObject;
import com.orm.commons.utils.MyConfig;
import com.orm.commons.utils.WebUtils;
import com.orm.enums.SysEnum;

@Controller
public class IndexController {
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ColumnService columnService;

	@Autowired
	private UserService userService;

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(value = "/ionic/post", method = { RequestMethod.POST})
	public void ionicLogin(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = WebUtils.getParamsToMap(request);
		System.out.println(map.toString());
		MessageObject messageObject = new MessageObject();
		messageObject.setInforMessage("获取数据成功");
		try {
			Map<String, Object> userMap = new HashMap<String,Object>();
			userMap.put("realName", "黄园");
			userMap.put("created", new Date());
			messageObject.setObject(userMap);
			response.getWriter().write(messageObject.getJsonMapper(messageObject));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/ionic/get", method = { RequestMethod.GET})
	public void get(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = WebUtils.getParamsToMap(request);
		System.out.println(map.toString());
		MessageObject messageObject = new MessageObject();
		messageObject.setInforMessage("获取数据成功");
		try {
			Map<String, Object> userMap = new HashMap<String,Object>();
			userMap.put("realName", "黄园");
			userMap.put("created", new Date());
			messageObject.setObject(userMap);
			response.getWriter().write(messageObject.getJsonMapper(messageObject));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/outLink/login.html")
	public void login(HttpServletResponse response, HttpServletRequest request) {
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		User user = userService.findUserByLoginNameAndPassword(loginName, password);
		if (user != null) {
			request.getSession().setAttribute("user", user);
		}
		MessageObject message = new MessageObject();
		message.setInforMessage("登录成功");
		response.setHeader("ContentType", "text/html;charset=UTF-8");
		try {
			response.getWriter().write(message.getJsonMapper(message));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/outLink/outerPage.html", method = RequestMethod.GET)
	public String outerPage(Model model) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		try {
			paramsMap.put("displayStatus_eq", SysEnum.Display.DISPLAY);
			paramsMap.put("deleteStatus_eq", SysEnum.DeleteStatus.NO);
			paramsMap.put("parent_eq", 1);
			List<Column> columns = columnService.queryByMap(paramsMap, new Sort(Sort.Direction.DESC, "createTime"));
			Map<String, Object> childernMap = new HashMap<String, Object>();
			for (Column column : columns) {
				paramsMap.put("parent_eq", 2);
				paramsMap.put("column.id_eq", column.getId());
				List<Column> childerns = columnService.queryByMap(paramsMap);
				childernMap.put(column.getId(), childerns);
			}
			model.addAttribute("childernMap", childernMap);
			model.addAttribute("columns", columns);
		} catch (ServiceException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		return "bbs/index";
	}

	@RequestMapping(value = "/outLink/index", method = RequestMethod.GET)
	public String test() {

		return "inner/childern";
	}

	@RequestMapping(value = "/outLink/getColumnList.json", method = RequestMethod.GET)
	public void getColumnList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		List<Column> columns = new ArrayList<Column>();
		String callbackFunName = request.getParameter("callback");
		StringBuilder stringBuilder = new StringBuilder();
		if (StringUtils.isNotEmpty(callbackFunName)) {
			stringBuilder.append(callbackFunName);
		}
		try {
			paramsMap.put("displayStatus_eq", SysEnum.Display.DISPLAY);
			paramsMap.put("deleteStatus_eq", SysEnum.DeleteStatus.NO);
			paramsMap.put("parent_eq", 1);
			columns = columnService.queryByMap(paramsMap, new Sort(Sort.Direction.DESC, "createTime"));
		} catch (ServiceException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				// 这句话的意思，是让浏览器用utf8来解析返回的数据
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				/*
				 * response.getWriter().write(new JsonMapper().toJson(columns));
				 */
				PrintWriter out = response.getWriter();
				stringBuilder.append("(");
				stringBuilder.append(new JsonMapper().toJson(columns));
				stringBuilder.append(")");
				out.print(stringBuilder.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String tempDir = "";

	@RequestMapping(value = "/outLink/getColumnById/{columnId}.json", method = RequestMethod.GET)
	public void getColumnById(@PathVariable("columnId") String columnId, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		Map<String, Object> childernMap = new HashMap<String, Object>();
		String callbackFunName = request.getParameter("callback");
		StringBuilder stringBuilder = new StringBuilder();
		if (StringUtils.isNotEmpty(callbackFunName)) {
			stringBuilder.append(callbackFunName);
		}
		try {
			paramsMap.put("displayStatus_eq", SysEnum.Display.DISPLAY);
			paramsMap.put("deleteStatus_eq", SysEnum.DeleteStatus.NO);
			paramsMap.put("parent_eq", 1);
			paramsMap.put("parent_eq", 2);
			paramsMap.put("column.id_eq", columnId);
			List<Column> childerns = columnService.queryByMap(paramsMap);
			childernMap.put(columnId, childerns);
		} catch (ServiceException e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				// 这句话的意思，是让浏览器用utf8来解析返回的数据
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				/*
				 * response.getWriter().write(new
				 * JsonMapper().toJson(childernMap));
				 */
				PrintWriter out = response.getWriter();
				stringBuilder.append("(");
				stringBuilder.append(new JsonMapper().toJson(childernMap));
				stringBuilder.append(")");
				out.print(stringBuilder.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/outLink/fileupload.html")
	public String fileUpload() {
		return "fileupload/fileupload";
	}

	@RequestMapping(value = "/outLink/upload", method = RequestMethod.POST)
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
		List<Attachment> versions = new ArrayList<Attachment>();
		while (iterator.hasNext()) {
			MultipartFile multipartFile = request.getFile((String) iterator.next());
			String name = multipartFile.getOriginalFilename();
			String filename = multipartFile.getOriginalFilename();
			String suffix = FileTools.getFileExtension(name);// 文件的后缀
			name = name.substring(0, name.lastIndexOf("."));
			name = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
			float size = (float) (multipartFile.getSize() / (1024));
			String fileSize = "";
			if (size > 1024 && size < 1024 * 1024) {
				fileSize = (size / 1024) + "MB";
			} else if (size >= 1024 * 1024) {
				fileSize = (size / 1024 / 1024) + "GB";
			} else {
				fileSize = size + "KB";
			}
			// 上传至服务器数据库
			String realPath = request.getSession().getServletContext().getRealPath("/upload/attachment");
			try {
				FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(realPath, filename));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (!new File(realPath).exists()) {
				new File(realPath).mkdirs();
			}
			String contentType = multipartFile.getContentType();
			Attachment attachment = new Attachment(filename, "/upload/attachment/" + filename, contentType, fileSize,
					suffix);
			attachment.setFilename(filename);
			try {
				attachment = attachmentService.save(attachment);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			versions.add(attachment);
		}
		try {
			// response.setHeader("Access-Control-Allow-Methods", "POST, PUT,
			// GET, OPTIONS, DELETE");
			// response.setHeader("Access-Control-Max-Age", "3600"); //设置过期时间
			// response.setHeader("Access-Control-Allow-Headers", "Origin,
			// X-Requested-With, Content-Type, Accept, client_id, uuid,
			// Authorization");
			// response.setHeader("Cache-Control", "no-cache, no-store,
			// must-revalidate"); // 支持HTTP 1.1.
			response.getWriter().write(new JsonMapper().toJson(versions));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/outLink/userLogin")
	public void userLogin(HttpServletRequest request, HttpServletResponse response, ResponseData responseData) {
		Map<String, Object> toMap = WebUtils.getParamsToMap(request);
		String callbackFunName = request.getParameter("callback");
		StringBuilder stringBuilder = new StringBuilder();
		try {
			if (StringUtils.isNotEmpty(callbackFunName)) {
				stringBuilder.append(callbackFunName);
			}
		} finally {
			try {
				// 这句话的意思，是让浏览器用utf8来解析返回的数据
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				/*
				 * response.getWriter().write(new
				 * JsonMapper().toJson(childernMap));
				 */
				PrintWriter out = response.getWriter();
				stringBuilder.append("(");
				responseData.setResultMap(toMap);
				stringBuilder.append(new JsonMapper().toJson(responseData));
				stringBuilder.append(")");
				out.print(stringBuilder.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
