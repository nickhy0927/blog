<%@page import="com.orm.commons.utils.MessageObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://curtainContain/tag" prefix="curtain"%>
<%@ taglib uri="http://caokopage.com.page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<curtain:include href="base/bootstap.jsp" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/project/css/index.css">
<script type="text/javascript">
	function deleteInfo(id,departId){
		//${ctx}/platform/depart/delete
		if(confirm("你确定删除？")){
			$.get("${ctx}/basic/topic/column/columnDelete/"+id,function(data){
				data = jQuery.parseJSON(data);
				if(data.resposecode == '<%=MessageObject.ResponseCode.code_200%>'){
					alert(data.message);
					window.location.href = '${ctx}/basic/topic/column/columnList';
				} else {
					alert(data.message);
					return ;
				}
			});
		}
	}

    function editDisplay(id,display){
        var msg = "你确定启用？"
        if(display == 'NONE'){
            msg = "你确定停用？"
        }
        if(confirm(msg)){
            $.get("${ctx}/basic/topic/column/editDisplay/"+id+"/" + display,function(data){
                data = jQuery.parseJSON(data);
                if(data.resposecode == '<%=MessageObject.ResponseCode.code_200%>'){
                    alert(data.message);
                    window.location.href = '${ctx}/basic/topic/column/columnList';
                } else {
                    alert(data.message);
                    return ;
                }
            });
        }
    }
</script>
<title>栏目列表</title>
</head>
<body>
	<div class="container-fluid">
        <form action="" method="get" name="queryForm" id="queryForm">
            <div class="row btn-operate" style="">
                <div class="form-group query-toolbar">
                    <span>栏目名称</span>
                    <span>
                        <input type="text" class="query-input">
                    </span>
                </div>
                <div class="operate-toolbar">
                    <a class="btn btn-info btn-sm" href="javascript:void(0)">查询</a>
                    <a class="btn btn-primary btn-sm" href="${ctx}/basic/topic/column/columnCreate">添加</a>
                </div>
            </div>
            <div class="row">
                <table class="table table-striped table-bordered table-hover table-condensed">
                    <thead>
                        <tr>
                            <td>栏目编号</td>
                            <td>栏目名称</td>
                            <td>上级栏目编号</td>
                            <td>上级栏目名称</td>
                            <td>栏目状态</td>
                            <td>是否显示</td>
                            <td style="text-align: center;">操作</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="column" items="${list}">
                            <tr>
                                <td>${column.number}</td>
                                <td>${column.name}</td>
                                <td>
                                    ${column.column.number}
                                </td>
                                <td>
                                    ${column.column.name}
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${column.columnType=='CMS'}">CMS模块</c:when>
                                        <c:when test="${column.columnType=='NOTE'}">BBS模块</c:when>
                                        <c:when test="${column.columnType=='COMMON'}">公共模块</c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${column.displayStatus == 'DISPLAY'}">显示</c:when>
                                        <c:when test="${column.displayStatus == 'NONE'}">不显示</c:when>
                                    </c:choose>
                                </td>
                                <td style="text-align: center;width:130px;">
                                    <a href="${ctx}/basic/topic/column/columnEdit/${column.id}">修改</a>
                                    <a href="javascript:void(0)" onclick="deleteInfo('${column.id}')">删除</a>
                                    <c:if test="${column.displayStatus == 'NONE'}">
                                        <a href="javascript:void(0)" onclick="editDisplay('${column.id}','DISPLAY')">启用</a>
                                    </c:if>
                                    <c:if test="${column.displayStatus == 'DISPLAY'}">
                                        <a href="javascript:void(0)" onclick="editDisplay('${column.id}','NONE')">停用</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="7" style="text-align: right;">
                                <page:pageInfo pageInfo="${pager}" formId="queryForm" currentPage="${currentPage}"/>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </form>
	</div>
</body>
</html>