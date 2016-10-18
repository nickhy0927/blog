package com.cako.ionic.service.tribune.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cako.deploy.pictures.entity.Picture;
import com.cako.deploy.pictures.service.PictureService;
import com.cako.deploy.tribune.note.entity.UserNote;
import com.cako.deploy.tribune.note.entity.UserNoteDto;
import com.cako.deploy.tribune.note.service.UserNoteService;
import com.cako.deploy.tribune.reply.entity.ReplyNote;
import com.cako.deploy.tribune.reply.service.ReplyNoteService;
import com.cako.ionic.common.utils.ResponseData;
import com.cako.ionic.common.utils.ResponseData.CALLCODE;
import com.cako.ionic.service.tribune.CategoryTribune;
import com.cako.ionic.service.tribune.IAppTribuneService;
import com.cako.platform.category.entity.Category;
import com.cako.platform.category.service.CategoryService;
import com.cako.platform.utils.SysContants;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.service.impl.DefaultAbstractService;
import com.orm.commons.spring.SpringContextHolder;
import com.orm.commons.utils.ObjectTools;

@Service
public class AppTribuneService extends DefaultAbstractService<UserNote, String> implements IAppTribuneService {

	private static UserNoteService userNoteService;
	private static ReplyNoteService replyNoteService;
	private static PictureService pictureService;
	private static CategoryService categoryService;

	static {
		if (replyNoteService == null) {
			replyNoteService = SpringContextHolder.getBean(ReplyNoteService.class);
		}
		if (userNoteService == null) {
			userNoteService = SpringContextHolder.getBean(UserNoteService.class);
		}
		if (pictureService == null) {
			pictureService = SpringContextHolder.getBean(PictureService.class);
		}
		if (categoryService == null) {
			categoryService = SpringContextHolder.getBean(CategoryService.class);
		}
	}

	@Override
	public ResponseData queryTribuneList(Map<String, Object> paramsMap) {
		ResponseData response = new ResponseData();
		paramsMap.put("status_eq", SysContants.RecordType.VALID);
		String currentPage = paramsMap.get("currentPage").toString();
		Sort sort = new Sort(Sort.Direction.DESC, "createTime");
		paramsMap.remove("currentPage");
		try {
			ObjectTools<UserNote> objectTools = userNoteService.queryPageByMap(paramsMap, currentPage, sort);
			List<UserNote> entities = objectTools.getEntities();
			List<UserNoteDto> userNotes = new ArrayList<UserNoteDto>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			for (UserNote userNote : entities) {
				paramMap.put("userNote.id_eq", userNote.getId());
				List<ReplyNote> replyNotes = replyNoteService.queryByMap(paramMap);
				userNotes.add(new UserNoteDto(userNote, replyNotes.size()));

			}
			response.setResultMap(userNotes);
			response.setResponseCode(ResponseData.CALLCODE.SUCCESS, true);
		} catch (ServiceException e) {
			e.printStackTrace();
			response.setResponseCode(ResponseData.CALLCODE.FAIL, false);
		}
		return response;
	}

	@Override
	public ResponseData queryTribuneById(Map<String, Object> paramsMap) {
		ResponseData response = new ResponseData();
		paramsMap.put("status_eq", SysContants.RecordType.VALID);
		String id = paramsMap.get("id").toString();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			UserNote userNote = userNoteService.get(id);
			paramMap.put("userNote.id_eq", userNote.getId());
			// 获得评论数
			List<ReplyNote> replyNotes = replyNoteService.queryByMap(paramMap);
			paramsMap.put("status_eq", SysContants.RecordType.VALID);
			String currentPage = "";
			if (paramsMap.get("currentPage") != null) {
				currentPage = paramsMap.get("currentPage").toString();
			} else
				currentPage = "1";
			Sort sort = new Sort(Sort.Direction.DESC, "createTime");
			ObjectTools<ReplyNote> objectTools = replyNoteService.queryPageByMap(paramMap, currentPage, sort);
			UserNoteDto userNoteDto = new UserNoteDto(userNote, replyNotes.size());
			userNoteDto.setReplyNotes(objectTools.getEntities());
			response.setResultMap(userNoteDto);
			response.setResponseCode(ResponseData.CALLCODE.SUCCESS, true);
		} catch (ServiceException e) {
			e.printStackTrace();
			response.setResponseCode(ResponseData.CALLCODE.FAIL, false);
		}
		return response;
	}

	@Override
	public ResponseData queryPicturesByUserId(Map<String, Object> paramsMap) {
		ResponseData response = new ResponseData();
		paramsMap.put("status_eq", SysContants.RecordType.VALID);
		try {
			paramsMap.put("user.id_eq", paramsMap.get("userId"));
			paramsMap.remove("userId");
			Sort sort = new Sort(Sort.Direction.DESC, "createTime");
			List<Picture> pictures = pictureService.queryByMap(paramsMap, sort);
			response.setResponseCode(CALLCODE.SUCCESS, true);
			response.setResultMap(pictures);
		} catch (ServiceException e) {
			response.setResponseCode(CALLCODE.FAIL, false);
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ResponseData queryTribuneByCategoryList(Map<String, Object> paramsMap) {
		ResponseData response = new ResponseData();
		Sort sort = new Sort(Sort.Direction.DESC, "createTime");
		Map<String, Object> cateMap = new HashMap<String, Object>();
		cateMap.put("status_eq", SysContants.RecordType.VALID);
		try {
			List<CategoryTribune> categoryTribunes = new ArrayList<CategoryTribune>();
			List<Category> categories = categoryService.queryByMap(cateMap, sort);
			for (Category category : categories) {
				paramsMap.put("category.id_eq", category.getId());
				paramsMap.put("status_eq", SysContants.RecordType.VALID);
				ObjectTools<UserNote> objectTools = userNoteService.queryPageByMap(paramsMap, "1", sort);
				categoryTribunes.add(new CategoryTribune(category, objectTools.getEntities()));
			}
			response.setResultMap(categoryTribunes);
			response.setResponseCode(CALLCODE.SUCCESS, true);
		} catch (ServiceException e) {
			e.printStackTrace();
			response.setResponseCode(CALLCODE.FAIL, false);
		}
		return response;
	}

}
