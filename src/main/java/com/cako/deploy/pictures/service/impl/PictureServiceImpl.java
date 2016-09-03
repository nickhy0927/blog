package com.cako.deploy.pictures.service.impl;

import org.springframework.stereotype.Service;

import com.cako.deploy.pictures.entity.Picture;
import com.cako.deploy.pictures.service.PictureService;
import com.orm.commons.service.impl.DefaultAbstractService;

@Service
public class PictureServiceImpl extends DefaultAbstractService<Picture, String> implements PictureService {

}
