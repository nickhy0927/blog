<%@page import="com.orm.commons.utils.MessageObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://curtainContain/tag" prefix="curtain"%>
<%@ taglib uri="http://caokopage.com.page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.hy.include" prefix="hy"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>

<hy:extends name="title">用户信息列表</hy:extends>
<hy:extends name="css">
	<link type="text/css" rel="stylesheet" href="${ctx}/static/project/css/index.css">
	<style type="text/css"></style>
</hy:extends>
<hy:extends name="javascript">
	<script type="text/javascript">
		function deleteInfo(id,departId){
			//${ctx}/platform/depart/delete
			if(confirm("你确定删除？")){
				$.get("${ctx}/platform/user/userDelete/"+id,function(data){
					data = jQuery.parseJSON(data);
					if(data.resposecode == '<%=MessageObject.ResponseCode.code_200%>'){
						alert(data.message);
						window.location.href = '${ctx}/platform/user/userList';
					} else {
						alert(data.message);
						return ;
					}
						
				});
			}
		}
	</script>
</hy:extends>
<hy:extends name="body">
	<div class="container-fluid">
		<form action="" method="get" name="queryForm" id="queryForm">
			<div class="row">
				<table class="search table table-striped table-bordered table-hover table-condensed">
					<tr>
						<td colspan="4" class="search-td">搜索条件</td>
					</tr>
					<tr>
						<td>用户名称</td>
						<td><input type="text" class="query-input"></td>
					</tr>
				</table>
				<table class="table table-striped table-bordered table-hover table-condensed">
					<tr>
						<td class="">
							<div class="operate-toolbar">
				                <a class="btn btn-info btn-sm" href="javascript:void(0)">查询</a>
				                <a class="btn btn-primary btn-sm" href="${ctx}/platform/user/userCreate">添加</a>
				            </div>
						</td>
					</tr>
				</table>
			</div>
			<div class="row">
				<table class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<td colspan="7" class="search-td">用户信息列表</td>
						</tr>
						<tr>
							<td>用户昵称</td>
							<td>登录名称</td>
							<td>用户邮箱</td>
							<td>出生日期</td>
							<td>用户状态</td>
							<td>用户类型</td>
							<td style="text-align: center;">操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${list}">
							<tr>
								<td>${user.nickName}</td>
								<td>${user.loginName}</td>
								<td>${user.email}</td>
								<td>
									${user.brithday}
								</td>
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
									<a href="${ctx}/platform/user/userEdit/${user.id}">修改</a>
									<a href="javascript:void(0)" onclick="deleteInfo('${entity.id}','${depart.id}')">删除</a>
									<a href="${ctx}/platform/user/userAddRoles/${user.id}">添加角色</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7" style="text-align: right;">
								<page:pageInfo pageInfo="${pager}" formId="queryForm" currentPage="${currentPage}"/>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</form>
	</div>
</hy:extends>
<jsp:include page="/base/manager.jsp"></jsp:include>
