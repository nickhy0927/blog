package com.cako.deploy.poster.service.impl;

import org.springframework.stereotype.Service;

import com.cako.deploy.poster.entity.Poster;
import com.cako.deploy.poster.service.IPosterService;
import com.orm.commons.service.impl.DefaultAbstractService;

@Service
public class PosterService extends DefaultAbstractService<Poster, String> implements IPosterService {

}
