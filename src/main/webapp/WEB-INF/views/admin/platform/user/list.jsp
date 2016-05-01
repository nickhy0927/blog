<%@page import="com.orm.commons.utils.MessageObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://www.hy.include" prefix="hy" %>
<%@ taglib prefix="page" uri="https://caokopage.com.page" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<hy:extends name="title">博客管理系统</hy:extends>
<hy:extends name="css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/main/css/admin.css">
    <style type="text/css">
        .bg-success {
            padding-left: 8px;
            font-size: 15px;
        }
        .operate {
            text-align: center;
        }
        .search-table input {
        	width: 80%;
		    border: 1px solid #dedede;
		    -moz-border-radius: 5px;      /* Gecko browsers */
		    -webkit-border-radius: 5px;   /* Webkit browsers */
		    border-radius:10px;            /* W3C syntax */
        }
    </style>
</hy:extends>

<hy:extends name="javascript">
    <script type="text/javascript">
        $(function () {
            $("#create").click(function () {
                window.location.href = "${ctx}/admin/platform/user/create.html"
            });
        });
        function deleteInfo(id){
        	if(confirm('确定删除该用户吗？')) {
        		showdiv();
	        	$.get("${ctx}/admin/platform/user/delete/"+id+".html",function (data) {
	        		hidediv();
	        		data = JSON.parse(data);
	        		if(data.resposecode == '<%=MessageObject.ResponseCode.code_200%>') {
	        			alert(data.message);
	        			window.location.href="${ctx}/admin/platform/user/list.html";
	        		} else {
	        			alert(data.message);
						return false;						
					}
	        	});
        	}
        }
    </script>
</hy:extends>
<hy:extends name="body">
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
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
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <form id="queryForm" name="queryForm" action="${ctx}/admin/platform/user/list.html" method="post">
                    <table id="search-table" class="table table-bordered table-responsive search-table">
                        <caption class="bg-success">搜索条件</caption>
                        <tr>
                            <td>用户昵称</td>
                            <td>
                                <input name="nickName" id="nickName" type="text">
                            </td>
                            <td>用户名</td>
                            <td>
                                <input name="loginName" id="loginName" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" style="text-align: right">
                                <button type="button" class="btn btn-info btn-sm icon-search">搜索</button>
                            </td>
                        </tr>
                    </table>
                    <table class="table table-bordered table-responsive table-hover" id="tableList">
                        <caption class="bg-success" style="height: 55px;line-height: 36px;">
                                                                    用户列表
                            <div style="float: right;margin-right: 10px;">
                                <button type="button" id="create" class="btn btn-primary btn-sm icon-plus">新增</button>
                                <button type="button" id="addRole" class="btn btn-primary btn-sm icon-plus">赋权限</button>
                            </div>
                        </caption>
                        <tr>
                            <td class="operate"></td>
                            <td>序号</td>
                            <td>用户昵称</td>
                            <td>登录名称</td>
                            <td>电子邮箱</td>
                            <td>出生日期</td>
                            <td>用户类型</td>
                            <td>用户状态</td>
                            <td class="operate">操作</td>
                        </tr>
                        <c:forEach items="${list}" var="user" varStatus="u">
                            <tr>
                                <td class="operate">
                                    <input type="checkbox" name="chkOne">
                                </td>
                                <td>${(u.index + 1)}</td>
                                <td>${user.nickName}</td>
                                <td>${user.loginName}</td>
                                <td>${user.email}</td>
                                <td>${user.brithday}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.userType=='ADMIN'}">管理员</c:when>
                                        <c:when test="${user.userType=='GENERAL'}">普通用户</c:when>
                                        <c:when test="${user.userType=='LEAGUER_MEMBER'}">超级会员</c:when>
                                        <c:when test="${user.userType=='MEMBER'}">普通会员</c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.userStatus == 'INIT'}">初始状态</c:when>
                                        <c:when test="${user.userStatus == 'LOCKED'}">账户锁定</c:when>
                                        <c:when test="${user.userStatus == 'NORMAL'}">账户可用</c:when>
                                    </c:choose>
                                </td>
                                <td style="text-align: center;width:140px;">
                                    <a title="修改" href="${ctx}/admin/platform/user/edit/${user.id}.html">
                                    	<i class="icon-edit icon-large"></i>
                                    </a>
                                    <a title="删除" href="javascript:void(0)" onclick="deleteInfo('${user.id}')">
										<i class="icon-minus-sign icon-large red"></i>                                    
                                    </a>
                                    <a href="${ctx}/platform/user/userAddRoles/${user.id}" title="添加角色">
                                    	<i class="icon-plus-sign-alt icon-large"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tfoot>
                            <tr>
                                <td colspan="9" style="text-align: right">
                                    <page:pageInfo pageInfo="${pager}" formId="queryForm" currentPage="${currentPage}"/>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </form>
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.page-content -->
</hy:extends>
<jsp:include page="/base/manager.jsp"/>
