<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib uri="http://file-upload.upload" prefix="file"%>
<%@ taglib uri="http://curtainContain/tag" prefix="curtain"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <curtain:include href="base/bootstap.jsp" />
    <title>附件添加</title>
    <script type="text/javascript">
        $(document).ready(function(){

        });
        
    </script>
</head>
<body>
	<div class="container-fluid">
        <form class="form-horizontal registerform" method="post" action="${ctx}/platform/user/userSave">

        </form>
        <file:upload/>
    </div>
</body>
</html>