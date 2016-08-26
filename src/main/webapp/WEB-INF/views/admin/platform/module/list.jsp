<%@page import="com.orm.commons.utils.MessageObject" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://www.hy.include" prefix="hy" %>
<%@ taglib prefix="page" uri="https://caokopage.com.page" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<hy:extends name="title">博客管理系统</hy:extends>
<hy:extends name="css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/main/css/admin.css">
</hy:extends>
<hy:extends name="javascript">
    <script type="text/javascript">
        $(function () {
            $("#create").click(function () {
                window.location.href = "${ctx}/admin/platform/menu/create.html"
            });
        });
        function deleteInfo(id) {
            if (confirm('确定删除该用户吗？')) {
                showdiv();
                $.get("${ctx}/admin/platform/user/delete/" + id + ".html", function (data) {
                    hidediv();
                    data = JSON.parse(data);
                    if (data.resposecode == '<%=MessageObject.ResponseCode.code_200%>') {
                        alert(data.message);
                        window.location.href = "${ctx}/admin/platform/menu/list.html";
                    } else {
                        alert(data.message);
                        return false;
                    }
                });
            }
        }
        function selectAll() {
            var checklist = document.getElementsByName("checkbox");
            if (document.getElementById("chkAll").checked) {
                for (var i = 0; i < checklist.length; i++) {
                    checklist[i].checked = 1;
                }
            } else {
                for (var j = 0; j < checklist.length; j++) {
                    checklist[j].checked = 0;
                }
            }
        }
    </script>
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
            <li class="active">菜单管理</li>
        </ul><!-- .breadcrumb -->
    </div>
    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <form id="queryForm" name="queryForm" action="${ctx}/admin/platform/menu/list.html" method="post">
                    <table id="search-table" class="table table-bordered table-responsive search-table">
                        <caption class="bg-success">搜索条件</caption>
                        <tr>
                            <td>菜单名称</td>
                            <td>
                                <input name="name_li" id="name" type="text">
                            </td>
                            <td>菜单路径</td>
                            <td>
                                <input name="href_eq" id="href" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" style="text-align: right">
                                <button type="submit" class="btn btn-info btn-sm icon-search">搜索</button>
                            </td>
                        </tr>
                    </table>
                    <div class="title-bar">
                        <div style="float: left">菜单列表</div>
                        <div style="float: right;margin-right: 10px;">
                            <button type="button" id="create" class="btn btn-success btn-sm icon-plus">新增</button>
                            <button type="button" id="addRole" class="btn btn-success btn-sm icon-plus">赋权限</button>
                        </div>
                    </div>
                    <table class="table table-bordered table-responsive table-hover" id="tableList">
                        <tr>
                            <td class="operate">
                                <input onclick="selectAll()" type="checkbox" name="checkbox" id="chkAll">
                            </td>
                            <td class="operate">序号</td>
                            <td style="">菜单名称</td>
                            <td>菜单地址</td>
                            <td>菜单别名</td>
                            <td>上级菜单</td>
                            <td>是否显示</td>
                            <td class="operate">操作</td>
                        </tr>
                        <c:forEach items="${menuTrees}" var="menu" varStatus="u">
                            <tr>
                                <td class="operate">
                                    <input type="checkbox" name="checkbox">
                                </td>
                                <td class="operate">
                                    ${u.index + 1}
                                </td>
                                <td>${menu.name}</td>
                                <td>${menu.href}</td>
                                <td>${menu.authority}</td>
                                <td>${menu.menu.name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${menu.displayStatus == 'DISPLAY'}">显示</c:when>
                                        <c:when test="${menu.displayStatus == 'NONE'}">不显示</c:when>
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
                                        <i class="icon-plus-sign-alt icon-large green"></i>
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
    </div>
    <!-- /.page-content -->
</hy:extends>
<jsp:include page="/base/admin.jsp"/>
