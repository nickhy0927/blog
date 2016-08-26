<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://www.hy.include" prefix="hy" %>
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
    </style>
</hy:extends>

<hy:extends name="javascript">
    <script type="text/javascript">
        $(function () {

        });
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
                <form>
                    <table class="table table-bordered table-responsive">
                        <caption class="bg-success">搜索条件</caption>
                        <tr>
                            <td>用户名</td>
                            <td>
                                <input name="username" id="username" type="text">
                            </td>
                            <td></td>
                            <td>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" class="">
                                <button type="button" class="btn btn-info btn-sm icon-search">搜索</button>
                            </td>
                        </tr>
                    </table>
                    <table class="table table-bordered table-responsive table-hover" id="tableList">
                        <caption class="bg-success" style="height: 50px;line-height: 30px;">
                            用户列表
                            <div style="float: right">
                                <button type="button" id="create" class="btn btn-primary btn-sm icon-plus">新增</button>
                                <button type="button" id="edit" class="btn btn-success btn-sm icon-edit">修改</button>
                                <button type="button" id="remove" class="btn btn-danger btn-sm icon-remove">删除</button>
                            </div>
                        </caption>
                        <tr>
                            <td></td>
                            <td>序号</td>
                            <td>用户昵称</td>
                            <td>登录名称</td>
                            <td>电子邮箱</td>
                            <td>用户标识</td>
                            <td>出生日期</td>
                            <td>用户状态</td>
                            <td class="operate">操作</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>序号</td>
                            <td>用户昵称</td>
                            <td>登录名称</td>
                            <td>电子邮箱</td>
                            <td>用户标识</td>
                            <td>出生日期</td>
                            <td>用户状态</td>
                            <td class="operate">操作</td>
                        </tr>

                    </table>
                </form>
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.page-content -->
</hy:extends>
<jsp:include page="/base/manager.jsp"/>
