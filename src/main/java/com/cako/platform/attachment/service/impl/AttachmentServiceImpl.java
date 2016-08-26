package com.cako.platform.attachment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cako.platform.attachment.dao.AttachmentDao;
import com.cako.platform.attachment.entity.Attachment;
import com.cako.platform.attachment.service.AttachmentService;
import com.orm.commons.service.impl.DefaultAbstractService;

@Service
@Transactional(readOnly = false)
public class AttachmentServiceImpl extends DefaultAbstractService<Attachment, String> implements AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public List<Attachment> getVersions(List<String> versionIds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_in", versionIds);
		return attachmentDao.queryByMap(paramMap);
	}

}
