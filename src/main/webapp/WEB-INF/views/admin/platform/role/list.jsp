<%@page import="com.orm.commons.utils.MessageObject" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://www.hy.include" prefix="hy" %>
<%@ taglib prefix="page" uri="https://caokopage.com.page" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<hy:extends name="title">博客管理系统</hy:extends>
<hy:extends name="css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/main/css/admin.css">
    <style type="text/css">
    </style>
</hy:extends>

<hy:extends name="javascript">
    <script type="text/javascript">
        $(function () {
            $("#create").click(function () {
                window.location.href = "${ctx}/admin/platform/role/create.html"
            });

            $('#addMenu').click(function () {
                d = dialog({
                    title: '请选择菜单',
                    fixed: true,
                    okValue: '确 定',
                    ok: function () {
                        return false;
                    },
                    cancelValue: '取消',
                    cancel: function () {
                        d.close().remove();
                    }
                });
                var menuList = $('#menuList');
                d.content(menuList);
                d.showModal();
            });
        });
        function deleteInfo(id) {
            if (confirm('确定删除该角色吗？')) {
                showdiv();
                $.get("${ctx}/admin/platform/role/delete/" + id + ".html", function (data) {
                    hidediv();
                    data = JSON.parse(data);
                    if (data.resposecode == '<%=MessageObject.ResponseCode.code_200%>') {
                        alert(data.message);
                        window.location.href = "${ctx}/admin/platform/role/list.html";
                    } else {
                        alert(data.message);
                        return false;
                    }
                });
            }
        }
        function addPremiss(id) {
            if (confirm('确定添加权限吗？')) {
                alert('添加权限成功');
            }
        }
    </script>
    <link rel="stylesheet" href="${ctx}/static/ztree/css/demo.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/static/main/css/admin.css">
    <style type="text/css">
        body {
            color: #393939;
            font-family: "Open Sans";
            font-size: 13px;
            line-height: 1.5;
        }
    </style>
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
            <li class="active">角色管理</li>
        </ul><!-- .breadcrumb -->
    </div>
    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <form id="queryForm" name="queryForm" action="${ctx}/admin/platform/role/list.html" method="post">
                    <table id="search-table" class="table table-bordered table-responsive search-table">
                        <caption class="bg-success" style="height: 40px;line-height: 40px;">搜索条件</caption>
                        <tr>
                            <td>角色名称</td>
                            <td>
                                <input name="name_li" id="name" type="text">
                            </td>
                            <td>角色编号</td>
                            <td>
                                <input name="code_li" id="code" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" style="text-align: right">
                                <button type="submit" class="btn btn-info btn-sm icon-search">搜索</button>
                            </td>
                        </tr>
                    </table>
                    <div class="title-bar">
                        <div style="float: left">角色列表</div>
                        <div style="float: right;margin-right: 10px;">
                            <button type="button" id="create" class="btn btn-success btn-sm icon-plus">新增</button>
                        </div>
                    </div>
                    <table class="table table-bordered table-responsive table-hover" id="tableList">
                        <tr>
                            <td class="operate">
                                <input onclick="selectAll()" type="checkbox" name="checkbox" id="chkAll">
                            </td>
                            <td class="operate">序号</td>
                            <td>角色编号</td>
                            <td>角色名称</td>
                            <td class="operate">操作</td>
                        </tr>
                        <c:forEach items="${rolesList}" var="role" varStatus="u">
                            <tr>
                                <td class="operate">
                                    <input type="checkbox" name="checkbox">
                                </td>
                                <td class="operate">
                                        ${u.index + 1}
                                </td>
                                <td>${role.code}</td>
                                <td>${role.name}</td>
                                <td class="operate">
                                    <a title="修改" href="${ctx}/admin/platform/role/edit/${role.id}.html">
                                        <i class="icon-edit-sign icon-large blue"></i>
                                    </a>
                                    <span title="删除" style="cursor: pointer" href="javascript:void(0)"
                                          onclick="deleteInfo('${role.id}')">
                                        <i class="icon-minus-sign icon-large red"></i>
                                    </span>
                                    <span title="添加权限" style="cursor: pointer" href="javascript:void(0)" id="addMenu">
                                        <i class="icon-plus-sign-alt icon-large green"></i>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="5" style="text-align: right">
                                <page:pageInfo pageInfo="${pager}" formId="queryForm" currentPage="${currentPage}"/>
                            </td>
                        </tr>
                    </table>
                </form>
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
    <div style="display: none" id="menuList">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
    <SCRIPT type="text/javascript">
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
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: onCheck
            }
        };
        function onCheck(e, treeId, treeNode) {
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = treeObj.getCheckedNodes(true),
                    v = "";
            for (var i = 0; i < nodes.length; i++) {
                v += nodes[i].name + ",";
                alert(nodes[i].id); //获取选中节点的值
            }
        }
        var zNodes = [
            {id: 1, pId: 0, name: "随意勾选 1", open: true},
            {id: 11, pId: 1, name: "随意勾选 1-1", open: true},
            {id: 111, pId: 11, name: "随意勾选 1-1-1"},
            {id: 112, pId: 11, name: "随意勾选 1-1-2"},
            {id: 12, pId: 1, name: "随意勾选 1-2", open: true},
            {id: 121, pId: 12, name: "随意勾选 1-2-1"},
            {id: 122, pId: 12, name: "随意勾选 1-2-2"},
            {id: 2, pId: 0, name: "随意勾选 2", checked: true, open: true},
            {id: 21, pId: 2, name: "随意勾选 2-1"},
            {id: 22, pId: 2, name: "随意勾选 2-2", open: true},
            {id: 221, pId: 22, name: "随意勾选 2-2-1", checked: true},
            {id: 222, pId: 22, name: "随意勾选 2-2-2"},
            {id: 23, pId: 2, name: "随意勾选 2-3"}
        ];

        $(document).ready(function () {
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
    </SCRIPT>
</hy:extends>
<jsp:include page="/base/admin.jsp"/>
