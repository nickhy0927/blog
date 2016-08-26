<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://www.hy.include" prefix="hy" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<hy:extends name="title">博客管理系统</hy:extends>
<hy:extends name="css">
    <link rel="stylesheet" href="${ctx}/static/main/css/admin.css"/>
    <style type="text/css"></style>
</hy:extends>
<hy:extends name="javascript">
    <script type="text/javascript"></script>
</hy:extends>
<hy:extends name="body">
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">首页</a>
            </li>
            <li class="active">用户管理</li>
        </ul><!-- .breadcrumb -->
    </div>
    <div class="page-content">
        <!-- PAGE CONTENT BEGINS -->
        <div style="margin-top: 16%;text-align: center">
            <h2 style="color: #00B83F">正在建设中,敬请期待！！！！</h2>
        </div>
    </div>
    <!-- /.page-content -->
</hy:extends>
<jsp:include page="/base/admin.jsp"/>
