package com.cako.mongo;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IMongoService<E, ID extends Serializable> {

	void mongoSave(E e);

	List<E> mongoFindAll();

	List<E> mongoQueryPageByMap(Map<String, Object> paramsMap);

	void mongoDelete(String _id);

	List<E> mongoQueryByMap(Map<String, Object> paramsMap);

	void mongoFileUpload(File file, String dbname);

	InputStream getMongoFile(String filename, String dbname);
}
