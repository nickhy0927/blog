<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/static/project/js/tree.js"></script>
<title>这是首页</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#spanId").tree({
			treeJson:'${listTree}'
		});
	});
	
	
</script>
</head>
<body>
	${listTree}
	<span id="spanId"></span>
</body>
</html>