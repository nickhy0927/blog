<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib uri="https://www.hy.include" prefix="hy"%>
<%@ taglib uri="http://blog.grid" prefix="grid"%>
<hy:extends name="titie">联系我</hy:extends>
<hy:extends name="css">
	<style type="text/css">
	</style>
</hy:extends>
<hy:extends name="javascript">
	<script type="text/javascript"></script>
</hy:extends>
<hy:extends name="body">
	<div class="container-fluid">
		<grid:header id="nava"/>
		<div style="margin-left: 20px;">
			你好，欢迎使用，如有任何问题请联系管理员
		</div>
	</div>
</hy:extends>
<jsp:include page="/base/bbs.jsp" />

