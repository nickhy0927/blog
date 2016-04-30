<%@page import="com.orm.commons.utils.MessageObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://curtainContain/tag" prefix="curtain"%>
<%@ taglib uri="http://caokopage.com.page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.hy.include" prefix="hy"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<hy:extends name="title">选择角色</hy:extends>
<hy:extends name="css">
	<link type="text/css" rel="stylesheet" href="${ctx}/static/project/css/index.css">
	<style type="text/css"></style>
</hy:extends>
<hy:extends name="javascript">
	<script type="text/javascript">
		$(document).ready(function() {
			$("#chkAll").click(function () {//反选  
				var chkOnes = document.getElementsByName('chkOne');
				for(var i = 0; i < chkOnes.length ; i ++){
					if(chkOnes[i].checked){
						chkOnes[i].removeAttribute("checked")
					}else {
						chkOnes[i].setAttribute("checked",true)
					}
				}
	        });  
			
			$("#getValue").click(function(){ 
			    var valArr = new Array; 
			    $("#queryForm :checkbox[checked]").each(function(i){ 
			        valArr[i] = $(this).val(); 
			    }); 
			    var vals = valArr.join(',');//转换为逗号隔开的字符串 
			   	$("#roleIds").val(vals);
				$.ajax({
					type:'POST',
					dataType:'json',
					url:'${ctx}/platform/user/addRole/${user.id}',
					cache:false,
					data:$("#addForm").serialize(),
					success: function (data) {
						console.log(data);
						if(data.resposecode == '<%=MessageObject.ResponseCode.code_200%>'){
							alert(data.message);
	                        window.location.href = "${ctx}/platform/user/userList"
						} else {
							alert(data.message);
							return;
						}
					}
				})
			}); 
		});
	</script>
</hy:extends>
<hy:extends name="body">
	<div class="container-fluid">
		<form action="${ctx}/platform/role/roleList/${user.id}" method="get" name="queryForm" id="queryForm">
			<div class="row">
				<table class="search table table-striped table-bordered table-hover table-condensed">
					<tr>
						<td colspan="4" class="search-td">
							<div class="query-toolbar">
				                <span>当前用户：</span><span>${user.nickName}</span>
								<input type="hidden" value="" name="roleIds" id="roleIds"/>
				            </div
						</td>
					</tr>
				</table>
				<table class="table table-striped table-bordered table-hover table-condensed">
					<tr>
						<td class="">
							<div class="operate-toolbar">
				                 <a class="btn btn-primary btn-sm" href="javascript:void(0)" id="getValue">确定</a>
				            </div>
						</td>
					</tr>
				</table>
			</div>
			<div class="row">
				<table class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<td colspan="7" class="search-td">角色信息列表</td>
						</tr>
						<tr>
							<td style="width: 30px;vertical-align: middle;text-align: center;">
								<input type="checkbox" name="chkAll" id="chkAll"/>
							</td>
							<td style="width:200px;">角色编号</td>
							<td>角色名称</td>
							<td>是否显示</td>
							<td style="text-align: center;">操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${rolesList}" var="roles">
							<tr>
								<td style="width: 30px;vertical-align: middle;text-align: center;">
									<input type="checkbox" name="chkOne" id="chkOne" value="${roles.id}"/>
								</td>
								<td>${roles.code}</td>
								<td>${roles.name}</td>
								<td>
									<c:choose>
										<c:when test="${roles.displayStatus == 'DISPLAY'}">显示</c:when>
										<c:when test="${roles.displayStatus == 'NONE'}">不显示</c:when>
									</c:choose>
								</td>
								<td style="text-align: center;width:100px;">
									<a href="${ctx}/platform/role/roleEdit/${roles.id}">修改</a>
									<a href="javascript:void(0)" onclick="deleteInfo('${roles.id}')">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6" style="text-align: right;">
								<page:pageInfo pageInfo="${tools.pager}" formId="queryForm" currentPage="${currentPage}"/>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</form>
	</div>
</hy:extends>
<jsp:include page="/base/manager.jsp"></jsp:include>
