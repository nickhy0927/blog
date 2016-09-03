package com.cako.deploy.tribune.note.service.impl;

import org.springframework.stereotype.Service;

import com.cako.deploy.tribune.note.entity.UserNote;
import com.cako.deploy.tribune.note.service.UserNoteService;
import com.orm.commons.service.impl.DefaultAbstractService;

@Service
public class UserNoteServiceImpl extends DefaultAbstractService<UserNote, String> implements UserNoteService {

}
