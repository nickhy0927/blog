package com.cako.basic.tribune.reply.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cako.basic.tribune.reply.entity.ReplyNote;
import com.cako.basic.tribune.reply.service.ReplyNoteService;
import com.orm.commons.service.impl.DefaultAbstractService;

@Component
@Transactional(readOnly = true)
public class ReplyNoteServiceImpl extends DefaultAbstractService<ReplyNote, String> implements ReplyNoteService {
	// 间隔5秒执行
	@Override
	public void myTask() {
		System.out.println("无主题(www.wuzhuti.cn) <span style=\"color: #000000;\">专注于前端开发技术和程序开发研究的技术博客</span>");
	}
}
