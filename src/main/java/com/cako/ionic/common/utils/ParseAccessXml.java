package com.cako.ionic.common.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@SuppressWarnings("unchecked")
public class ParseAccessXml {

	public static Map<String, Object> map = new HashMap<String, Object>();

	public static Map<String, Object> getMap() {
		return map;
	}

	private ParseAccessXml() {
	}

	public Map<String, Object> getServiceObject() {
		System.out.println("正在初始化...");
		List<URL> list = loadResourcesByClassLoader("access.xml");
		if (list != null && list.size() > 0) {
			URL url = list.get(0);
			SAXReader reader = new SAXReader();
			// 读取文件 转换成Document
			try {
				Document document = reader.read(url);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();
				for (Element element : elements) {
					if (element.getName().equals("path")) {
						Attribute serviceCodeAttribute = element.attribute("serviceCode");
						Attribute serviceNameAttribute = element.attribute("serviceName");
						map.put(serviceCodeAttribute.getValue(), serviceNameAttribute.getValue());
						List<Element> methodEles = element.elements();
						for (Element methodEle : methodEles) {
							if (methodEle.getName().equals("method")) {
								map.put(methodEle.attributeValue("methodCode"), methodEle.getText());
							}
						}
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		System.out.println(map.toString());
		return map;
	}

	/**
	 * 根据ClassLoader加载资源文件(多个),使用默认的ClassLoader!
	 * 
	 * @param path
	 *            不能以'/'开头的路径
	 * @return
	 */
	public static List<URL> loadResourcesByClassLoader(String path) {
		return loadResourcesByClassLoader(null, path);
	}

	/**
	 * 根据ClassLoader加载资源文件(多个),使用指定的ClassLoader!
	 *
	 * @param path
	 *            不能以'/'开头的路径
	 * @return
	 */
	public static List<URL> loadResourcesByClassLoader(ClassLoader cl, String path) {
		if (cl == null) {
			cl = Thread.currentThread().getContextClassLoader();
		}
		List<URL> urlLst = new ArrayList<URL>();
		try {
			Enumeration<URL> urlsEnum = cl.getResources(path);
			while (urlsEnum.hasMoreElements()) {
				URL url = urlsEnum.nextElement();
				urlLst.add(url);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return urlLst;
	}

}
