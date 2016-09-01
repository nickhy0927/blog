package com.cako.mongo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

@SuppressWarnings("unchecked")
public class MongoService<E, ID extends Serializable> implements IMongoService<E, ID> {
	@Autowired
	protected MongoTemplate mongoTemplate;

	protected Class<E> clazz;

	public MongoService() {
		Class<?> clazz = getClass();
		while (clazz != Object.class) {
			Type t = clazz.getGenericSuperclass();
			if (t instanceof ParameterizedType) {
				Type[] args = ((ParameterizedType) t).getActualTypeArguments();
				if (args[0] instanceof Class) {
					this.clazz = (Class<E>) args[0];
					break;
				}
			}
			clazz = clazz.getSuperclass();
		}
	}

	@Override
	public void mongoSave(E e) {
		mongoTemplate.save(e);
	}

	@Override
	public List<E> mongoFindAll() {
		return null;
	}

	@Override
	public List<E> mongoQueryPageByMap(Map<String, Object> paramsMap) {
		return null;
	}

	@Override
	public void mongoDelete(String _id) {

	}

	@Override
	public List<E> mongoQueryByMap(Map<String, Object> paramsMap) {
		// 根据age查询
		Query query = new Query();
		Set<Entry<String, Object>> entrySet = paramsMap.entrySet();
		for (Iterator<Entry<String, Object>> iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			query.addCriteria(new Criteria(key).is(value));
		}
		List<E> objects = (List<E>) mongoTemplate.find(query, clazz);
		return objects;
	}

	@Override
	public void mongoFileUpload(File file, String dbname) {
		DBCollection collection = mongoTemplate.getCollection(clazz.getSimpleName());
		DB db = collection.getDB();// pictures
		GridFS myFS = new GridFS(db, dbname); // 创建GridFS
		// 保存文件
		try {
			GridFSFile files = myFS.createFile(file);
			files.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public InputStream getMongoFile(String filename, String dbname) {
		DBCollection collection = mongoTemplate.getCollection(clazz.getSimpleName());
		DB db = collection.getDB();// pictures
		GridFS gridFS = new GridFS(db, dbname); // 创建GridFS
		// 输出文件
		GridFSDBFile gridFSDBFile = gridFS.findOne(filename);
		return gridFSDBFile.getInputStream();
	}

}
