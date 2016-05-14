<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib uri="https://www.hy.include" prefix="hy"%>
<hy:extends name="titie">联系我</hy:extends>
<hy:extends name="css">
	<style>
	</style>
</hy:extends>
<hy:extends name="javascript">
	<script type="text/javascript"></script>
</hy:extends>
<hy:extends name="body">
	你好，欢迎使用这个博客系统...我们正在努力完善
</hy:extends>
<<jsp:include page="/base/bbs.jsp" />

