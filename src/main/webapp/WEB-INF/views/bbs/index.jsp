<%@page import="com.cako.platform.user.entity.User"%>
<%@page import="com.orm.commons.utils.MessageObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib prefix="hy" uri="https://www.hy.include" %>
<hy:extends name="title">滴落的露珠</hy:extends>
<hy:extends name="css">
<style type="text/css">
</style>
</hy:extends>
<hy:extends name="javascript">
<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    	
</script>
</hy:extends>
<hy:extends name="body">
	<div class=""></div>
</hy:extends>
<jsp:include page="/base/bbs.jsp"/>
