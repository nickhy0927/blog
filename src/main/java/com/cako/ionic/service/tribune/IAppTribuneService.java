package com.cako.ionic.service.tribune;

import java.util.Map;

import com.cako.deploy.tribune.note.entity.UserNote;
import com.cako.ionic.common.utils.ResponseData;
import com.orm.commons.service.BaseService;

public interface IAppTribuneService extends BaseService<UserNote, String> {

	public ResponseData queryTribuneList(Map<String, Object> paramsMap);

	public ResponseData queryTribuneById(Map<String, Object> paramsMap);
}
