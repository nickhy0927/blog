package com.cako.ionic.service.reply;

import java.util.Map;

import com.cako.content_deply.tribune.reply.entity.ReplyNote;
import com.cako.ionic.common.utils.ResponseData;
import com.orm.commons.service.BaseService;

public interface IAppReplyService extends BaseService<ReplyNote, String> {

	public ResponseData queryReplyNoteList(Map<String, Object> paramsMap);

	public ResponseData insertReplyNote(Map<String, Object> paramsMap);

	public ResponseData queryReplyNoteById(Map<String, Object> paramsMap);
}
