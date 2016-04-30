<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://www.hy.include" prefix="hy" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<!DOCTYPE html>
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
	<!-- 填充css代码 -->
	<hy:block name="css"></hy:block>
	
	<script type="text/javascript" src="${ctx}/static/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/Validform/js/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<!-- 填充javascript的代码 -->
	<hy:block name="javascript"></hy:block>
</head>
<body>
	<hy:block name="body"></hy:block>
</body>
</html>