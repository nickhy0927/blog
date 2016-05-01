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
            -moz-border-radius: 5px; /* Gecko browsers */
            -webkit-border-radius: 5px; /* Webkit browsers */
            border-radius: 10px; /* W3C syntax */
        }
    </style>
</hy:extends>

<hy:extends name="javascript">
    <script type="text/javascript">
        $(function () {
            $("#create").click(function () {
                window.location.href = "${ctx}/admin/platform/role/create.html"
            });
            $('#myModal').on('show.bs.modal', function () {
                // 执行一些动作...
            });

            $('#myModal').on('shown.bs.modal', function () {
                // 执行一些动作...
            });
            $('#myModal').modal('hide');
            $('#myModal').on('hide.bs.modal', function () {
                //alert('嘿，我听说您喜欢模态框...');
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
                        <caption class="bg-success">搜索条件</caption>
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
                    <table class="table table-bordered table-responsive table-hover" id="tableList">
                        <caption class="bg-success" style="height: 55px;line-height: 36px;">
                            角色列表
                            <div style="float: right;margin-right: 10px;">
                                <button type="button" id="create" class="btn btn-primary btn-sm icon-plus">新增</button>
                            </div>
                        </caption>
                        <tr>
                            <td class="operate"></td>
                            <td>序号</td>
                            <td>角色编号</td>
                            <td>角色名称</td>
                            <td class="operate">操作</td>
                        </tr>
                        <c:forEach items="${rolesList}" var="role" varStatus="u">
                            <tr>
                                <td class="operate">
                                    <input type="checkbox" name="chkOne">
                                </td>
                                <td>${(u.index + 1)}</td>
                                <td>${role.code}</td>
                                <td>${role.name}</td>
                                <td style="text-align: center;width:140px;">
                                    <a title="修改" href="${ctx}/admin/platform/role/edit/${user.id}.html">
                                        <i class="icon-edit icon-large"></i>
                                    </a>
                                    <span title="删除" style="cursor: pointer" href="javascript:void(0)"
                                          onclick="deleteInfo('${user.id}')">
                                        <i class="icon-minus-sign icon-large red"></i>
                                    </span>
                                    <span title="添加权限" style="cursor: pointer"
                                          href="javascript:void(0)" data-toggle="modal"
                                          data-target="#myModal">
                                        <i class="icon-plus-sign-alt icon-large green"></i>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                        <tfoot>
                        <tr>
                            <td colspan="4" style="text-align: right">
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
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel">
                        允许访问权限
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="zTreeDemoBackground left">
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        关闭
                    </button>
                    <button type="button" class="btn btn-primary">
                        提交更改
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
        <SCRIPT type="text/javascript">
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
    </div>
    <!-- /.page-content -->

</hy:extends>
<jsp:include page="/base/manager.jsp"/>
