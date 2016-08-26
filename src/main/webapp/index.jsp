<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="hy" uri="https://www.hy.include" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<hy:extends name="title">用户登录</hy:extends>
<hy:extends name="javascript">
    <script type="text/javascript">
        $(function () {
            window.location.href = "${ctx}/admin/intoLogin.html"
        });
    </script>
</hy:extends>
<jsp:include page="base/admin.jsp"/>