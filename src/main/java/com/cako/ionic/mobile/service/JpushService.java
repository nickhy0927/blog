package com.cako.ionic.mobile.service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.TimeUnit;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.WinphoneNotification;
import cn.jpush.api.report.ReceivedsResult;
import cn.jpush.api.report.ReceivedsResult.Received;
import cn.jpush.api.report.UsersResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JpushService {
    protected static final Logger LOG = LoggerFactory.getLogger(JpushService.class);
    private static final String APP_KEY = "31d952c639c6c31299ff64a8";
    private static final String MASTER_SECRET = "9c14f114dcf9bb98256d0860";

    /**
     * 推送通知接口
     *
     * @param alias   别名
     * @param tags    tag数组
     * @param title   推送标题
     * @param btype   推送类型
     * @param content 推送内容
     */
    public void sendPushNotice(String alias, String[] tags, String title, String btype, String content,
                                      ClientConfig config) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, config);
        PushPayload payload = null;
        // 生成推送的内容，这里我们先测试全部推送
        // 通知提示信息
        if (content != null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("btype", btype);
            // 根据别名推送
            if (alias != null && tags == null) {
                payload = buldPushObject_all_all_alias(alias, title, content, map);
            } else if (null == alias && null != tags) { // 根据tag[]推送
                payload = buldPushObject_all_all_tag(tags, title, content, map);
            } else if (null != alias && tags != null) { // 别名和tags[] 推送通知
                payload = buldPushObject_all_all_aliasAndTag(alias, tags, title, content, map);
            } else if (null == alias && tags == null) {
                payload = buldPushObject_all_all(title, content, map);
            }
        } else {
            LOG.info("No notification - " + content);
        }
        try {
            System.out.println(payload.toString());
            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result.msg_id + "................................");
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }

    /**
     * 推送自定义消息接口.根据别名修改标签（tag）
     *
     * @param alias   别名
     * @param content 推送内容
     */
    public static void sendPushMessage(String alias, String content, ClientConfig config) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, config);
        PushPayload payload = null;
        // For push, all you need do is to build PushPayload object.
        // PushPayload payload = buildPushObject_all_all_alert();
        // 判断用户别名和tag不为空的情况下才推送修改标签（tag）
        if (content != null && alias != null) {
            payload = PushPayload.newBuilder().setAudience(Audience.alias(alias)).setPlatform(Platform.all())
                    .setMessage(Message.content(content)).build();
        } else {
            LOG.info("No notification - " + content);
        }
        try {
            System.out.println(payload.toString());
            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result + "................................");
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
//            LOG.error("Error response from JPush server. Should review and fix it. ", e);
//            LOG.info("HTTP Status: " + e.getStatus());
//            LOG.info("Error Code: " + e.getErrorCode());
//            LOG.info("Error Message: " + e.getErrorMessage());
//            LOG.info("Msg ID: " + e.getMsgId());
        }
    }

    /**
     * 查询记录推送成功条数
     *
     * @param mid
     */
    public static void countPush(String mid, ClientConfig config) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, config);
        try {
            ReceivedsResult result = jpushClient.getReportReceiveds(mid);
            Received received = result.received_list.get(0);
            System.out.println("android_received:" + received.android_received + "\nios:" + received.ios_apns_sent);
            LOG.debug("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
        }
    }

    /**
     * 统计用户数据。需要vip用户才能访问
     */
    public static void getReportUser(ClientConfig config) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, config);
        try {
            UsersResult result = jpushClient.getReportUsers(TimeUnit.DAY, "2015-04-28", 8);
            LOG.debug("Got result - " + result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
            LOG.error("Connection error, should retry later", e);
        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    /**
     * 根据别名通知推送
     *
     * @param alias   别名
     * @param content 推送内容
     * @return
     */
    public static PushPayload buldPushObject_all_all_alias(String alias, String title, String content,
                                                           Map<String, String> map) {
        return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder().setAlert(content).addExtras(map).build())
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).setTitle(title)
                                .addExtras(map).build())
                        .addPlatformNotification(
                                WinphoneNotification.newBuilder().setAlert(content).addExtras(map).build())
                        .build())
                .build();
    }

    /**
     * 根据tag通知推送
     *
     * @param content 推送内容
     * @return
     */
    public static PushPayload buldPushObject_all_all_tag(String[] tags, String title, String content,
                                                         Map<String, String> map) {
        return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.tag(tags))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder().setAlert(content).addExtras(map).build())
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).setTitle(title)
                                .addExtras(map).build())
                        .addPlatformNotification(
                                WinphoneNotification.newBuilder().setAlert(content).addExtras(map).build())
                        .build())
                .build();
    }

    /**
     * 根据tag通知推送
     *
     * @param alias   别名
     * @param content 推送内容
     * @return
     */
    public static PushPayload buldPushObject_all_all_aliasAndTag(String alias, String[] tags, String title,
                                                                 String content, Map<String, String> map) {
        return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias))
                .setAudience(Audience.tag(tags))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder().setAlert(content).addExtras(map).build())
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).setTitle(title)
                                .addExtras(map).build())
                        .addPlatformNotification(
                                WinphoneNotification.newBuilder().setAlert(content).addExtras(map).build())
                        .build())
                .build();
    }

    /**
     * 根据通知推送
     * title 标题
     * <p>
     * content 推送内容
     *
     * @return
     */
    public static PushPayload buldPushObject_all_all(String title, String content, Map<String, String> map) {
        return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder().setAlert(content).addExtras(map).build())
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).setTitle(title)
                                .addExtras(map).build())
                        .addPlatformNotification(
                                WinphoneNotification.newBuilder().setAlert(content).addExtras(map).build())
                        .build())
                .build();
    }

}
