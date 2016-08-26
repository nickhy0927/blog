package com.cako.content_deply.news.service.impl;

import com.cako.content_deply.news.entity.News;
import com.cako.content_deply.news.service.INewsService;
import com.orm.commons.service.impl.DefaultAbstractService;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 2016/08/26 0026.
 */
@Service
public class NewsService extends DefaultAbstractService<News, String> implements INewsService {
}
