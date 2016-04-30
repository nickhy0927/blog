package com.cako.basic.index;

import com.cako.basic.topic.column.entity.Column;
import com.cako.basic.topic.column.service.ColumnService;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.JsonMapper;
import com.orm.commons.utils.MessageObject;
import com.orm.enums.SysEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private ColumnService columnService;

    @RequestMapping(value = "/outLink/outerPage", method = RequestMethod.GET)
    public String outerPage(Model model) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        try {
            paramsMap.put("displayStatus_eq", SysEnum.Display.DISPLAY);
            paramsMap.put("deleteStatus_eq", SysEnum.DeleteStatus.NO);
            paramsMap.put("parent_eq", 1);
            List<Column> columns = columnService.queryByMap(paramsMap, new Sort(Sort.Direction.DESC, "createTime"));
            Map<String, Object> childernMap = new HashMap<String, Object>();
            for (Column column : columns) {
                paramsMap.put("parent_eq", 2);
                paramsMap.put("column.id_eq", column.getId());
                List<Column> childerns = columnService.queryByMap(paramsMap);
                childernMap.put(column.getId(), childerns);
            }
            model.addAttribute("childernMap", childernMap);
            model.addAttribute("columns", columns);
        } catch (ServiceException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        return "outerPage";
    }

    @RequestMapping(value = "/outLink/index", method = RequestMethod.GET)
    public String test() {

        return "inner/childern";
    }

    @RequestMapping(value = "/outLink/getColumnList.json", method = RequestMethod.GET)
    public void getColumnList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        List<Column> columns = new ArrayList<Column>();
        String callbackFunName = request.getParameter("callback");
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotEmpty(callbackFunName)) {
            stringBuilder.append(callbackFunName);
        }
        try {
            paramsMap.put("displayStatus_eq", SysEnum.Display.DISPLAY);
            paramsMap.put("deleteStatus_eq", SysEnum.DeleteStatus.NO);
            paramsMap.put("parent_eq", 1);
            columns = columnService.queryByMap(paramsMap, new Sort(Sort.Direction.DESC, "createTime"));
        } catch (ServiceException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                // 这句话的意思，是让浏览器用utf8来解析返回的数据
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                /* response.getWriter().write(new JsonMapper().toJson(columns)); */
                PrintWriter out = response.getWriter();
                stringBuilder.append("(");
                stringBuilder.append(new JsonMapper().toJson(columns));
                stringBuilder.append(")");
                out.print(stringBuilder.toString());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/outLink/getColumnById/{columnId}.json", method = RequestMethod.GET)
    public void getColumnById(@PathVariable("columnId") String columnId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        Map<String, Object> childernMap = new HashMap<String, Object>();
        String callbackFunName = request.getParameter("callback");
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotEmpty(callbackFunName)) {
            stringBuilder.append(callbackFunName);
        }
        try {
            paramsMap.put("displayStatus_eq", SysEnum.Display.DISPLAY);
            paramsMap.put("deleteStatus_eq", SysEnum.DeleteStatus.NO);
            paramsMap.put("parent_eq", 1);
            paramsMap.put("parent_eq", 2);
            paramsMap.put("column.id_eq", columnId);
            List<Column> childerns = columnService.queryByMap(paramsMap);
            childernMap.put(columnId, childerns);
        } catch (ServiceException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                // 这句话的意思，是让浏览器用utf8来解析返回的数据
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                /*response.getWriter().write(new JsonMapper().toJson(childernMap));*/
                PrintWriter out = response.getWriter();
                stringBuilder.append("(");
                stringBuilder.append(new JsonMapper().toJson(childernMap));
                stringBuilder.append(")");
                out.print(stringBuilder.toString());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/outLink/fileupload")
    public String fileUpload() {
        return "fileupload/fileupload";
    }

    @RequestMapping(value = "/outLink/upload", method = RequestMethod.POST)
    public void upload(@RequestParam MultipartFile[] attachment, MultipartHttpServletRequest request, HttpServletResponse response) {

        System.out.println(attachment.length);
        Iterator<String> iterator = request.getFileNames();
        MessageObject message = new MessageObject();
        while (iterator.hasNext()) {
            MultipartFile multipartFile = request.getFile((String) iterator.next());
            String type = multipartFile.getContentType();
            String name = multipartFile.getOriginalFilename();
            float size = (float) (multipartFile.getSize() / (1024));
            System.out.println("文件名称 ： " + name);
            message.setInforMessage("上传成功");
        }
        try {
            response.getWriter().write(message.getJsonMapper(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
