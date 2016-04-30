package com.cako.basic.topic.column.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.cako.basic.topic.column.entity.Column;
import com.cako.basic.topic.column.service.ColumnService;
import com.cako.basic.topic.column.tree.ColumnTree;
import com.cako.platform.utils.BaseController;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.JsonMapper;
import com.orm.commons.utils.ObjectTools;
import com.orm.commons.utils.Pager;
import com.orm.enums.SysEnum;
import com.orm.enums.SysEnum.DeleteStatus;

@Controller
@RequestMapping("/basic/topic")
public class ColumnController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ColumnController.class);

    @Autowired
    private ColumnService columnService;

    @RequestMapping(value = "/column/editDisplay/{id}/{display}", method = RequestMethod.GET)
    public void editDisplay(@PathVariable("id") String id, @PathVariable("display") String display, HttpServletResponse response) {
        if (StringUtils.isNotEmpty(display) && StringUtils.isNotEmpty(id)) {
            try {
                Column column = columnService.get(id);
                if (column != null) {
                    String success = "启用成功";
                    if (StringUtils.equals(display, "NONE")) {
                        success = "停用成功";
                        column.setDisplayStatus(SysEnum.Display.NONE);
                    } else
                        column.setDisplayStatus(SysEnum.Display.DISPLAY);
                    columnService.save(column);
                    message.setInforMessage(success);
                } else {
                    String errorInfo = "启用失败";
                    if (StringUtils.equals(display, "NONE")) {
                        errorInfo = "停用失败";
                    }
                    message.setErrorMessage(errorInfo);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
                String errorInfo = "启用失败";
                if (StringUtils.equals(display, "NONE")) {
                    errorInfo = "停用失败";
                }
                message.setErrorMessage(errorInfo);
            } finally {
                try {
                    response.getWriter().write(message.getJsonMapper(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/column/columnCreate", method = RequestMethod.GET)
    public String columnCreate(HttpServletRequest request, Model model) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("deleteStatus_eq", DeleteStatus.NO);
            List<Column> list = columnService.queryByMap(paramMap);
            List<ColumnTree> columnTrees = new ArrayList<ColumnTree>();
            for (Column column : list) {
                columnTrees.add(new ColumnTree(column));
            }
            model.addAttribute("columnTrees", new JsonMapper().toJson(columnTrees));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            model.addAttribute("number", "TC" + sdf.format(new Date()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "basic/topic/column/columnCreate";
    }

    @RequestMapping(value = "/column/columnDelete/{id}", method = RequestMethod.GET)
    public void columnDelete(@PathVariable("id") String id, HttpServletResponse response) {
        try {
            if (StringUtils.isNotEmpty(id)) {
                Column column = columnService.get(id);
                DeleteStatus deleteStatus = DeleteStatus.YES;
                column.setDeleteStatus(deleteStatus);
                columnService.saveColumn(column);
                message.setInforMessage("栏目删除成功");
            } else {
                message.setErrorMessage("栏目删除失败");
            }
        } catch (ServiceException e) {
            message.setErrorMessage("栏目删除失败");
            e.printStackTrace();
        } finally {
            try {
                response.getWriter().write(message.getJsonMapper(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/column/columnEdit/{id}", method = RequestMethod.GET)
    public String columnEdit(@PathVariable("id") String id, Model model) {
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("deleteStatus_eq", DeleteStatus.NO);
            List<Column> list = columnService.queryByMap(paramMap);
            List<ColumnTree> columnTrees = new ArrayList<ColumnTree>();
            for (Column column : list) {
                columnTrees.add(new ColumnTree(column));
            }
            model.addAttribute("columnTrees", new JsonMapper().toJson(columnTrees));
            if (StringUtils.isNotEmpty(id)) {
                Column column = columnService.get(id);
                model.addAttribute("column", column);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "basic/topic/column/columnUpdate";
    }

    @RequestMapping(value = "/column/columnList", method = RequestMethod.GET)
    public String columnList(HttpServletRequest request, Model model) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String currentPage = request.getParameter("currentPage");
        paramMap.put("deleteStatus_eq", SysEnum.DeleteStatus.NO);
        try {
            ObjectTools<Column> columns = columnService.queryPageByMap(paramMap, currentPage, new Sort(Sort.Direction.DESC, "createTime"));
            List<ColumnTree> columnTrees = new ArrayList<ColumnTree>();
            for (Column column : columns.getEntities()) {
                columnTrees.add(new ColumnTree(column));
            }
            model.addAttribute("list", columnTrees);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("pager", columns.getEntities().size() > 0 ? columns.getPager() : new Pager(0, "10"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "basic/topic/column/columnList";
    }

    @RequestMapping(value = "/column/columnSave", method = RequestMethod.GET)
    public String columnSave(HttpServletRequest request, Model model, Column column, HttpServletResponse response) {
        try {
            String parentColumnId = request.getParameter("parentColumnId");
            String columnType = request.getParameter("columnType");
            column.setColumnType(SysEnum.getColumnType(columnType));
            if (StringUtils.isNotEmpty(parentColumnId)) {
                Column column2 = columnService.get(parentColumnId);
                column.setColumn(column2);
            }else {
                column.setParent(1);
            }

            column = columnService.save(column);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:columnList";
    }
}
