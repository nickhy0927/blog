<%@page import="com.orm.commons.utils.MessageObject" %>
<%@ page import="com.orm.commons.utils.SysContants" %>
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
                window.location.href = "${ctx}/tribune/user/note/create.html"
            });
        });
        function deleteInfo(id) {
            if (confirm('确定删除该帖子吗？')) {
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
            <li class="active">帖子管理</li>
        </ul><!-- .breadcrumb -->
    </div>
    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <form id="queryForm" name="queryForm" action="${ctx}/tribune/user/note/list.html" method="post">
                    <table id="search-table" class="table table-bordered table-responsive search-table">
                        <caption class="bg-success">搜索条件</caption>
                        <tr>
                            <td>帖子标题</td>
                            <td colspan="3">
                                <input name="noteTitle_li" id="noteTitle" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" style="text-align: right">
                                <button type="submit" class="btn btn-info btn-sm icon-search">搜索</button>
                            </td>
                        </tr>
                    </table>
                    <div class="title-bar">
                        <div style="float: left">帖子列表</div>
                        <div style="float: right;margin-right: 10px;">
                            <button type="button" id="create" class="btn btn-success btn-sm icon-plus">新增</button>
                        </div>
                    </div>
                    <table class="table table-bordered table-responsive table-hover" id="tableList">
                        <tr>
                            <td class="operate">序号</td>
                            <td>帖子编号</td>
                            <td>栏目名称</td>
                            <td>标题</td>
                            <td class="operate">操作</td>
                        </tr>
                        <c:forEach items="${notes}" var="note" varStatus="u">
                            <tr>
                                <td class="operate">
                                    ${u.index + 1}
                                </td>
                                <td>${note.noteNumber}</td>
                                <td>${note.category.name}</td>
                                <td>${note.noteTitle}</td>
                                <td>
                                    <c:if test="note.isShow == 1">
                                        <%=SysContants.IsDisplay.getIsDispalyName("1")%>
                                    </c:if>
                                    <c:if test="note.isShow == 0">
                                        <%=SysContants.IsDisplay.getIsDispalyName("0")%>
                                    </c:if>
                                </td>
                                <td style="text-align: center;width:140px;">
                                    <a title="查看" href="${ctx}/tribune/user/note/edit/${note.id}.html">
                                        <i class="icon-file icon-large"></i>
                                    </a>
                                    <a title="删除" href="javascript:void(0)" onclick="deleteInfo('${note.id}')">
                                        <i class="icon-minus-sign icon-large red"></i>
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
