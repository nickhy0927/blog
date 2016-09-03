package com.cako.ionic.mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cako.ionic.common.reflex.CommonReflex;
import com.cako.ionic.mobile.service.JpushService;

import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

@Controller
@RequestMapping(value = "/outLink")
public class MobileCotroller {

	@Autowired
	public CommonReflex commonsReflex;

	@Autowired
	public JpushService jpushService;

	@RequestMapping(value = "/mobileApp.app", method = { RequestMethod.POST, RequestMethod.GET })
	public void mobileApp(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		// 这句话的意思，是让浏览器用utf8来解析返回的数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter out;
		try {
			result = commonsReflex.getObject(request);
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String TITLE = "Test from API example";
	public static String ALERT = "Test from API Example - alert";
	public static String MSG_CONTENT = "Test from API Example - msgContent";
	// public static String REGISTRATION_ID = "0900e8d85ef";

	@RequestMapping(value = "/jpushTest")
	public ModelAndView jpushTest(Model model) {
		model.addAttribute("title", "你好，这是极光推送的测试页面");
		return new ModelAndView("push/jpushTest");
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public void send(HttpServletRequest request) {
		String msgContent = request.getParameter("msgContent");
		MobileCotroller.TITLE = request.getParameter("msgTitle");
		MobileCotroller.ALERT = msgContent;
		MobileCotroller.MSG_CONTENT = msgContent;
		// HttpProxy proxy = new HttpProxy("localhost", 3128);
		// Can use this https proxy: https://github.com/Exa-Networks/exaproxy
		ClientConfig config = ClientConfig.getInstance();
		config.setMaxRetryTimes(5);
		config.setConnectionTimeout(10 * 1000); // 10 seconds
		config.setTimeToLive(60 * 60 * 24); // one day
		config.setSSLVersion("TLSv1.1"); // JPush server supports SSLv3, TLSv1,
		// TLSv1.1, TLSv1.2
		// For push, all you need do is to build PushPayload object.
		Set<Object> tagsValues = new HashSet<Object>();
		tagsValues.add("825be9711341ecbb5ed2bfff681f8c99");
		tagsValues.add("93ced8f8d44cd5d21f37fa013be19192");
		String[] tags = new String[tagsValues.size()];
		int i = 0;
		for (Iterator<Object> iterator = tagsValues.iterator(); iterator.hasNext();) {
			Object object = iterator.next();
			String value = String.valueOf(object);
			tags[i] = value;
			i++;
		}
		jpushService.sendPushNotice(null, tags, TITLE, "modal", msgContent, config);
	}

	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
				.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.tag("tag1", "tag2")).addAudienceTarget(AudienceTarget.alias("alias1", "alias2")).build())
				.setMessage(Message.newBuilder().setMsgContent(MSG_CONTENT).addExtra("from", "JPush").build()).build();
	}

	/**
	 * 设置tag 使用于android 和 IOS
	 *
	 * @return
	 */
	public static PushPayload buildPushObject_android_and_ios() {
		List<String> tagValues = new ArrayList<String>();
		tagValues.add("huangyuan");
		tagValues.add("davis");
		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.tag("huangyuan"))
				.setNotification(Notification.newBuilder().setAlert(MSG_CONTENT).addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE).build())
						.addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtra("extra_key", "extra_value").build()).build())
				.build();
	}

	/**
	 * 根据 tag 发送通知,适用于android
	 *
	 * @return
	 */
	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
		List<String> tagValues = new ArrayList<String>();
		tagValues.add("155275097841");
		tagValues.add("15527509784");
		return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.tag(tagValues)).setNotification(Notification.android(ALERT, TITLE, null)).build();
	}

	// 根据别名发送通知
	public static PushPayload buildPushObject_all_alias_alert() {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias("huangyuan")).setNotification(Notification.alert(ALERT)).build();
	}

	// 向所有的平台发送通知
	public static PushPayload buildPushObject_all_all_alert() {
		return PushPayload.alertAll(ALERT);
	}
}
