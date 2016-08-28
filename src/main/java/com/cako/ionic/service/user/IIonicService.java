package com.cako.ionic.service.user;

import java.util.Map;

import com.cako.ionic.common.utils.ResponseData;
import com.cako.platform.user.entity.User;
import com.orm.commons.service.BaseService;

public interface IIonicService extends BaseService<User, String> {

	public ResponseData findUserByLoginNameAndPassword(Map<String, Object> paramsMap);

	public ResponseData userLogin(Map<String, Object> paramsMap);

	public ResponseData updateUserTag(Map<String, Object> paramsMap);
}
