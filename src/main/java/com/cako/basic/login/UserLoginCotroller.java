package com.cako.basic.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orm.login.SingleLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.druid.support.json.JSONParser;
import com.cako.platform.user.entity.User;
import com.cako.platform.user.service.UserService;
import com.orm.commons.utils.MessageObject;
import com.orm.commons.utils.WebUtils;

@Controller
@Scope("prototype")
@RequestMapping(value = "/outLink")
public class UserLoginCotroller {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/mobile/login", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> paramsMap = WebUtils.getParamsToMap(request);
            StringBuilder stringBuilder = new StringBuilder();
            MessageObject message = new MessageObject();
            User user = null;
            String callbackFunName = request.getParameter("callback");
            if (paramsMap != null && !paramsMap.isEmpty()) {
                Object data = paramsMap.get("data");
                JSONParser parser = new JSONParser(data.toString());
                Map<String, Object> parseMap = parser.parseMap();
                Object name = parseMap.get("username");
                Object pass = parseMap.get("password");
                String username = name != null ? (String) name : "";
                String password = pass != null ? (String) pass : "";
                user = userService.findUserByLoginNameAndPassword(username, password);
            }
            if (user != null) {
                message.setInforMessage("登陆成功");
                message.setObject(user);
            } else {
                message.setErrorMessage("用户名或密码错误");
            }
            if (!"".equals(callbackFunName) && callbackFunName != null) {
                stringBuilder.append(callbackFunName);
            }

            // 这句话的意思，是让浏览器用utf8来解析返回的数据
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            stringBuilder.append("(");
            stringBuilder.append(message.getJsonMapper(message));
            stringBuilder.append(")");
            out.print(stringBuilder.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/mobile/getUserInfo")
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            MessageObject message = new MessageObject();
            String callbackFunName = request.getParameter("callback");
            if (!"".equals(callbackFunName) && callbackFunName != null) {
                stringBuilder.append(callbackFunName);
            }
            try {
                String inforMessage = "获取数据成功";
                List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
                for (int i = 1; i <= 20; i++) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", i);
                    map.put("username", "張三-" + i);
                    maps.add(map);
                }
                message.setObject(maps);
                message.setInforMessage(inforMessage);
            } catch (Exception e) {
                message.setErrorMessage("数据加载异常.");
            }
            // 这句话的意思，是让浏览器用utf8来解析返回的数据
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            stringBuilder.append("(");
            stringBuilder.append(message.getJsonMapper(message));
            stringBuilder.append(")");
            out.print(stringBuilder.toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/admin/adminLogin.html", method = RequestMethod.POST)
    public String adminLogin(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> parseMap = WebUtils.getParamsToMap(request);
        Object name = parseMap.get("username");
        Object pass = parseMap.get("password");
        String username = name != null ? (String) name : "";
        String password = pass != null ? (String) pass : "";
        User user = userService.findUserByLoginNameAndPassword(username, password);
        if (user != null) {
            SingleLogin.setUser(request, user);
            return "redirect:/admin/adminIndex.html";
        } else
            return "redirect:/admin/intoLogin.html";
    }
}
