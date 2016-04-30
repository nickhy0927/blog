package com.orm.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.cako.platform.user.entity.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Curtain on 2015/9/26.
 */
public class SingleLogin implements HttpSessionListener {

    // 保存sessionID和username的映射
    private static Map<String,Object> hUserName = new HashMap<String,Object>();

    /**
     * @描述：获得一个用户对象
     * @author HUANGYUAN
     * @TIME:2015年9月28日 下午12:24:10
     * @param session
     * @return
     */
    public static User getUser(HttpSession session){
    	return (User) session.getAttribute(session.getId());
    }
    
    /**
     * @描述：存入一个用户对象
     * @author HUANGYUAN
     * @TIME:2015年9月28日 下午12:24:29
     * @param request
     * @param loginName
     */
    public static void setUser(HttpServletRequest request,User user){
    	HttpSession session = request.getSession();
    	session.setAttribute(session.getId(), user);
    }
    
    /** 以下是实现HttpSessionListener中的方法* */
    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        hUserName.remove(se.getSession().getId());
    }

    /**
     * isAlreadyEnter-用于判断用户是否已经登录以及相应的处理方法
     *
     * @param sUserName
     *            String-登录的用户名称
     * @return boolean-该用户是否已经登录过的标志
     */
    public static boolean isAlreadyEnter(HttpSession session, String sUserName) {
        boolean flag = false;
        // 如果该用户已经登录过，则使上次登录的用户掉线(依据使用户名是否在hUserName中)
        if (hUserName.containsValue(sUserName)) {
            flag = true;
            // 遍历原来的hUserName，删除原用户名对应的sessionID(即删除原来的sessionID和username)
            Iterator<Entry<String, Object>> iter = hUserName.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String,Object> entry = iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                if (sUserName.equals(val)) {
                    hUserName.remove(key);
                }
            }
            // 添加现在的sessionID和username
            hUserName.put(session.getId(), sUserName);
            System.out.println("hUserName = " + hUserName);
        } else {// 如果该用户没登录过，直接添加现在的sessionID和username
            flag = false;
            hUserName.put(session.getId(), sUserName);
            System.out.println("hUserName = " + hUserName);
        }
        return flag;
    }


    /**
     * isOnline-用于判断用户是否在线
     *
     * @param session
     *            HttpSession-登录的用户名称
     * @return boolean-该用户是否在线的标志
     */
    public static boolean isOnline(HttpSession session) {
        boolean flag = true;
        if (hUserName.containsKey(session.getId())) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}
