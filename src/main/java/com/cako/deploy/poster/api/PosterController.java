package com.cako.deploy.poster.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cako.deploy.poster.service.impl.PosterService;
import com.cako.platform.attachment.entity.Attachment;
import com.cako.platform.attachment.service.AttachmentService;
import com.orm.commons.exception.ServiceException;
import com.orm.commons.utils.ObjectTools;
import com.orm.commons.utils.Pager;
import com.orm.commons.utils.WebUtils;

@Controller
public class PosterController {

	@Autowired
	private PosterService posterService;

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/poster/list.html", method = { RequestMethod.POST, RequestMethod.GET })
	public String list(HttpServletRequest request, Model model) {
		Map<String, Object> paramMap = WebUtils.getParamsToMap(request);
		String currentPage = request.getParameter("currentPage");
		// paramMap.put("status_eq", SysContants.RecordType.VALID);
		try {
			ObjectTools<Attachment> tools = attachmentService.queryPageByMap(paramMap, currentPage, new Sort(Sort.Direction.DESC, "createTime"));
			model.addAttribute("posters", tools.getEntities());
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("pager", tools.getEntities().size() > 0 ? tools.getPager() : new Pager(0, "10", null));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "poster/list";
	}

	/**
	 * 进入新增海报的页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/poster/into.html", method = RequestMethod.GET)
	public String into() {

		return "poster/into";
	}
}
