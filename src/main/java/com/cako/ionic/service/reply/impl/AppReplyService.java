package com.cako.ionic.service.reply.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.cako.content_deply.tribune.note.dao.UserNoteDao;
import com.cako.content_deply.tribune.note.entity.UserNote;
import com.cako.content_deply.tribune.reply.dao.ReplyNoteDao;
import com.cako.content_deply.tribune.reply.entity.ReplyNote;
import com.cako.ionic.common.utils.ResponseData;
import com.cako.ionic.common.utils.ResponseData.CALLCODE;
import com.cako.ionic.service.reply.IAppReplyService;
import com.cako.platform.user.dao.UserDao;
import com.cako.platform.user.entity.User;
import com.cako.platform.utils.SysContants;
import com.orm.commons.service.impl.DefaultAbstractService;
import com.orm.commons.spring.SpringContextHolder;

@Service
public class AppReplyService extends DefaultAbstractService<ReplyNote, String> implements IAppReplyService {
	private static ReplyNoteDao replyNoteDao;
	private static UserDao userDao;
	private static UserNoteDao userNoteDao;

	static {
		if (replyNoteDao == null) {
			replyNoteDao = SpringContextHolder.getBean(ReplyNoteDao.class);
		}
		if (userDao == null) {
			userDao = SpringContextHolder.getBean(UserDao.class);
		}
		if (userNoteDao == null) {
			userNoteDao = SpringContextHolder.getBean(UserNoteDao.class);
		}
	}

	@Override
	public ResponseData queryReplyNoteList(Map<String, Object> paramsMap) {
		ResponseData response = new ResponseData();
		try {
			Object userNoteObject = paramsMap.get("userNoteId");
			if (userNoteObject == null) {
				response.setResponseCode(CALLCODE.FAIL, false);
				return response;
			}
			paramsMap.put("userNote.id", userNoteObject.toString());
			paramsMap.remove("userNoteId");
			List<ReplyNote> replyNotes = replyNoteDao.queryByMap(paramsMap);
			response.setResponseCode(CALLCODE.SUCCESS, true);
			response.setResultMap(replyNotes);
		} catch (Exception e) {
			e.printStackTrace();
			response.setResponseCode(CALLCODE.FAIL, false);
		}
		return response;
	}

	@Override
	public ResponseData queryReplyNoteById(Map<String, Object> paramsMap) {
		return null;
	}

	/**
	 * 添加回复
	 */
	@Override
	public ResponseData insertReplyNote(Map<String, Object> paramsMap) {
		ResponseData response = new ResponseData();
		try {
			ReplyNote replyNote = new ReplyNote();
			BeanUtils.populate(replyNote, paramsMap);
			Object userObject = paramsMap.get("userId");
			Object userNoteObject = paramsMap.get("userNoteId");
			if (userObject == null) {
				response.setResponseCode(CALLCODE.NOLOGIN, false);
				return response;
			}
			if (userNoteObject == null) {
				response.setResponseCode(CALLCODE.FAIL, false);
				return response;
			}
			User user = userDao.getOne(userObject.toString());
			UserNote userNote = userNoteDao.getOne(userNoteObject.toString());
			replyNote.setUser(user);
			replyNote.setUserNote(userNote);
			replyNote.setStatus(SysContants.RecordType.VALID);
			replyNoteDao.save(replyNote);
			// paramsMap.put("replyNote", userNote);
			response.setResultMap(paramsMap);
			response.setResponseCode(CALLCODE.SUCCESS, true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			response.setResponseCode(CALLCODE.FAIL, false);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			response.setResponseCode(CALLCODE.FAIL, false);
		}
		return response;
	}
}
