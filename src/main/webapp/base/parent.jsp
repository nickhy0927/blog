<%@page import="com.cako.platform.utils.GlobalVariable"%>
<%@page import="com.cako.platform.user.entity.User"%>
<%@page import="org.springframework.data.domain.Sort"%>
<%@page import="com.orm.enums.SysEnum"%>
<%@page import="com.orm.commons.exception.ServiceException"%>
<%@page import="java.util.*"%>
<%@page import="com.cako.basic.topic.column.entity.Column"%>
<%@page import="com.orm.commons.spring.SpringContextHolder"%>
<%@page import="com.cako.basic.topic.column.service.ColumnService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.hy.include" prefix="hy" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<!DOCTYPE html>

<%
	ColumnService columnService = SpringContextHolder.getBean(ColumnService.class);
	Map<String,Object> paramsMap = new HashMap<String, Object>();
	try {
		paramsMap.put("displayStatus_eq", SysEnum.Display.DISPLAY);
		paramsMap.put("deleteStatus_eq", SysEnum.DeleteStatus.NO);
		paramsMap.put("parent_eq", 1);
		List<Column> columns =columnService.queryByMap(paramsMap,new Sort(Sort.Direction.DESC,"createTime"));
		Map<String,Object> childernMap = new HashMap<String, Object>();
		for (Column column : columns){
			paramsMap.put("parent_eq", 2);
			paramsMap.put("column.id_eq",column.getId());
			List<Column> childerns =columnService.queryByMap(paramsMap);
			childernMap.put(column.getId(),childerns);
		}
		request.setAttribute("childernMap", childernMap);
		request.setAttribute("columns", columns);
	} catch (ServiceException e) {
		e.printStackTrace();
	}
 %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- 填充标题 -->
	<title>
		<hy:block name="title"></hy:block>
	</title>
	<!-- 结束 -->
	<link rel="stylesheet" type="text/css" href="${ctx}/static/Validform/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/Validform/css/demo.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap-theme.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.css.map">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css">
	<style type="text/css">
        .menu {
            float: left;
            margin: 5px 0px 5px 10px;
        }

        .logincss {
            float: right;
            color: #ddd;
            margin: 5px 5px 5px 0;
        }

        .menu a:HOVER, .logincss a:HOVER {
            text-decoration: none;
            color: white;
        }

        a:HOVER a:LINK, .logincss a:LINK {
            text-decoration: none;
            color: white;
        }

        a {
            margin-left: 5px;
            color: white;
        }

        #sddm {
            margin: 0 auto;
            padding: 0;
            z-index: 30;
            width: auto;
            height: 23px;
        }

        #sddm li {
            margin: 0;
		    padding: 0;
		    list-style: none;
		    float: left;
		    font: bold 14px arial;
		    vertical-align: middle;
		    height: 20px;
		    line-height: 25px;
        }

        #sddm li a {
            display: block;
            margin: 0 1px 0 0;
            padding: 4px 10px;
            text-align: center;
            text-decoration: none
        }

        #sddm li a:hover {
            background: #49A3FF
        }

        #sddm div {
            position: absolute;
            visibility: hidden;
            margin: 0;
            padding: 0;
        }

        #sddm div a {
            position: relative;
            display: block;
            margin: 0;
            padding: 5px 10px;
            width: auto;
            white-space: nowrap;
            text-align: left;
            text-decoration: none;
            background: #EAEBD8;
            color: #2875DE;
            font: 12px arial
        }

        #sddm div a:hover {
            background: #49A3FF;
            color: #FFF
        }
        .modal.fade.in {
		 	top: 40%;
		}
    </style>
	<!-- 填充css代码 -->
	<hy:block name="css"></hy:block>
	
	<script type="text/javascript" src="${ctx}/static/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript">
		var timeout = 500;
        var closetimer = 0;
        var ddmenuitem = 0;
        // open hidden layer
        function mopen(id) {
            // cancel close timer
            mcancelclosetime();
            // close old layer
            if (ddmenuitem)
                ddmenuitem.style.visibility = 'hidden';
            // get new layer and show it
            ddmenuitem = document.getElementById(id);
            ddmenuitem.style.visibility = 'visible';
        }
        // close showed layer
        function mclose() {
            if (ddmenuitem)
                ddmenuitem.style.visibility = 'hidden';
        }
        // go close timer
        function mclosetime() {
            closetimer = window.setTimeout(mclose, timeout);
        }

        // cancel close timer
        function mcancelclosetime() {
            if (closetimer) {
                window.clearTimeout(closetimer);
                closetimer = null;
            }
        }
        // close layer when click-out
        document.onclick = mclose;
        
        $(function(){
        	var heigth = window.innerHeight;
        	$("#myModal").css({
        		"top":heigth / 2 -160
        	});
        });
	</script>
	<!-- 填充javascript的代码 -->
	<hy:block name="javascript"></hy:block>
</head>
<body>
	<div class="container">
	    <div class="row" style="background-color:#15a230">
	        <span class="menu">
	            <ul id="sddm">
	                <li>
	                    <a href="#" onmouseover="mopen('m1')" onmouseout="mclosetime()">首页</a>
	                </li>
	                <c:forEach items="${columns}" var="column" varStatus="vstatus">
	                    <li>
	                        <a href="#" onmouseover="mopen('m${vstatus.count}')" onmouseout="mclosetime()">${column.name}</a>
	                        <div id="m${vstatus.count}" onmouseover="mcancelclosetime()" onmouseout="mclosetime()">
	                            <c:set var="columnId" value="${column.id}"></c:set>
	                            <c:set var="childerns" value="${childernMap.get(columnId)}"></c:set>
	                            <c:forEach var="childern" items="${childerns}">
	                                <a href="#">${childern.name}</a>
	                            </c:forEach>
	                        </div>
	                    </li>
	                </c:forEach>
	            </ul>
	        </span>
	        <span class="logincss">
	        	当前访客身份：
	        	<%
	        		User user = GlobalVariable.getUser(request);
	        		if(user == null){
	        	%>
	        		游客
	        		<a <a href="#myModal" role="button" class="btn" data-toggle="modal">[ 登录</a>|&nbsp;&nbsp;
	        	<%	
	        		} else {
	        	%>
	        		<a href="javascript:void(0)">[ <%=user.getNickName() %></a>
	        	<%	
	        		}
	        	%>
	            <a href="javascript:void(0)">加入落幕 ]</a>
	        </span>
	    </div>
	    <div class="row">
			<div class="col-md-8">
				<%
	        		if(user != null){
	        	%>
	        	<%
	        		}
	        	%>
	        </div>
	        <div class="col-md-4">
	            <form id="queryForm" name="queryForm">
	                <div class="row" style="margin-top:5px;margin-bottom:5px;">
	                    <div class="form-group">
	                        <div class="col-sm-10">
	                            <input type="text" name="keyWord" value="${keyWord}" class="form-control" id="keyWord"  placeholder="请输入搜索内容">
	                        </div>
	                        <label class="col-sm-2 control-label" style="margin-left: -2px;">
	                            <button class="btn btn-primary" type="button" onclick=""
	                                    style="width:auto;margin-right:15px;">搜索
	                            </button>
	                        </label>
	                    </div>
	                </div>
	            </form>
	        </div>
	    </div>
	    <div class="row">
			<!-- 填充正文的内容 -->
			<hy:block name="body"></hy:block>
	    </div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">用户登录</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">登录名称</label>
							<div class="col-sm-10">
								<input type="text" name="username" class="form-control" id="username" placeholder="请输入登录名称">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">登录密码</label>
							<div class="col-sm-10">
								<input type="password" name="password" class="form-control" id="password" placeholder="请输入登录密码">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label> 
										<input type="checkbox"> 请记住我
									</label>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary">登录</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>