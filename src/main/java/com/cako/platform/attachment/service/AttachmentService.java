package com.cako.platform.attachment.service;

import com.cako.platform.attachment.entity.Attachment;
import com.orm.commons.service.BaseService;

import java.util.List;

public interface AttachmentService extends BaseService<Attachment,String>{

	public List<Attachment> getVersions(List<String> versionIds);

}
