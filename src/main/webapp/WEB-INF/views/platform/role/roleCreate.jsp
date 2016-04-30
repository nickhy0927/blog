<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib uri="http://curtainContain/tag" prefix="curtain" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <curtain:include href="base/bootstap.jsp"/>
    <script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/static/project/css/index.css">
    <title>菜单信息添加</title>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".registerform").Validform({
                tiptype: 2,
                datatype: {
                    "*3-20": /^[^\s]{3,20}$/,
                    "z2-4": /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/
                }
            });
        });
    </script>
</head>
<body>
<div class="container-fluid">
    <form class="form-horizontal registerform" method="post" action="${ctx}/platform/role/roleSave">
        <div class="form-group">
            <label for="code" class="col-sm-2 control-label">角色编号</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="code" id="code" placeholder="请输入菜单名称"
                       value="${code}" readonly="readonly">
            </div>
            <div class="Validform_checktip"></div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">角色名称</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="name" id="name" placeholder="请输入菜单名称"
                       datatype="*1-20" nullmsg="请输入角色名称！" errormsg="角色名称至少1个字符,最多20个字符！">
            </div>
            <div class="Validform_checktip"></div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-6">
                <button type="submit" class="btn btn-primary">添加</button>
                <button type="button" onclick="javascript:history.go(-1)" class="btn btn-default">返回</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>