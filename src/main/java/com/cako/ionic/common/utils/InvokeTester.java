package com.cako.ionic.common.utils;

import java.lang.reflect.Method;

public class InvokeTester {
	public InvokeTester() {
	}

	String str;

	public InvokeTester(String str) {
		this.str = str;
	}

	public int add(int param1, int param2) {
		return param1 + param2;
	}

	public String echo(String msg) {
		return "echo: " + msg;
	}

	public String getStr() {
		return "one param ctor" + str;
	}

	public static void main(String[] args) throws Exception {
		// 直接获取类
		// Class<?> classType = InvokeTester.class;
		// 通过完整的类型路径获取类
		Class<?> classType = Class.forName("com.top.utils.InvokeTester");
		// 使用newInstance创建对象
		// Object invokeTester = classType.newInstance();
		// 使用默认构造函数获取对象
		Object invokeTester = classType.getConstructor(new Class[] {}).newInstance(new Object[] {});
		// 获取InvokeTester类的add()方法
		Method addMethod = classType.getMethod("add", new Class[] { int.class, int.class });
		// 调用invokeTester对象上的add()方法
		Object result = addMethod.invoke(invokeTester, new Object[] { new Integer(100), new Integer(200) });
		System.out.println(result);
		// 获取InvokeTester类的echo()方法
		Method echoMethod = classType.getMethod("echo", new Class[] { String.class });
		// 调用invokeTester对象的echo()方法
		result = echoMethod.invoke(invokeTester, new Object[] { "Hello" });
		System.out.println((String) result);
		// 创建有参构造函数的类对象
		Object invokeTester1 = classType.getConstructor(new Class[] { String.class })
				.newInstance(new Object[] { new String("测试一个带参数的构造调用") });
		// 获取方法方式相同
		Method getStrMethod = classType.getMethod("getStr");
		Object str = getStrMethod.invoke(invokeTester1);
		System.out.println(str);
	}
}