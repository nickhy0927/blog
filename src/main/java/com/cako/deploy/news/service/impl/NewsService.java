package com.cako.deploy.news.service.impl;

import org.springframework.stereotype.Service;

import com.cako.deploy.news.entity.News;
import com.cako.deploy.news.service.INewsService;
import com.orm.commons.service.impl.DefaultAbstractService;

/**
 * Created by Davis on 2016/08/26 0026.
 */
@Service
public class NewsService extends DefaultAbstractService<News, String> implements INewsService {
}
