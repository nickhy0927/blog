<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/static/Validform/js/jquery-1.9.1.min.js"></script>
</head>
<body>
 <script type="text/javascript">
 	$(document).ready(function(){
 		window.location.href = "${ctx}/outLink/outerPage?SESSIONID="+(Math.random() * 100000000);
 	});
 </script>
</body>
</html>