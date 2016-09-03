package com.cako.deploy.news.service;

import com.cako.deploy.news.entity.News;
import com.cako.mongo.IMongoService;
import com.orm.commons.service.BaseService;

/**
 * Created by Davis on 2016/08/26 0026.
 */
public interface INewsService extends BaseService<News, String>, IMongoService<News, String> {
}
