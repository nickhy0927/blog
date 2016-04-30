<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.hy.include" prefix="hy"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<hy:extends name="title">博客管理系统</hy:extends>
<hy:extends name="css">
	<style type="text/css"></style>
</hy:extends>

<hy:extends name="javascript">
	<script type="text/javascript">
		
	</script>
</hy:extends>
<hy:extends name="body">
	博客管理系统
</hy:extends>
<jsp:include page="/base/manager.jsp" />
