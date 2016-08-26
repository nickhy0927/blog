<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://www.hy.include" prefix="hy" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<hy:extends name="title">博客管理系统</hy:extends>
<hy:extends name="css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/main/css/admin.css">
    <style type="text/css">
        .Validform_checktip {
            line-height: 30px;
        }
    </style>
</hy:extends>

<hy:extends name="javascript">
    <script type="text/javascript">
        if("ontouchend" in document) document.write("<script src='${ctx}/static/admin/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
    </script>
    <script src="${ctx}/static/admin/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/admin/js/typeahead-bs2.min.js"></script>

    <!-- page specific plugin scripts -->

    <!--[if lte IE 8]>
    <script src="${ctx}/static/admin/js/excanvas.min.js"></script>
    <![endif]-->
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
            <li class="active">角色新增</li>
        </ul><!-- .breadcrumb -->
    </div>

    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <form class="form-horizontal" action="${ctx}/admin/platform/role/save.html" id="createForm" role="form" method="post">
                   	<div class="form-group">
                        <label for="code" class="col-sm-3 control-label no-padding-right">角色编号</label>
                        <div class="col-sm-9">
                            <input type="text" name="code" id="code"
                                   readonly="readonly"
                                   value="${code}"class="col-xs-10 col-sm-5" />
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name">角色名称</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="id" name="id" value="${role.id}"/>
                            <input type="text" id="name" name="name"
                                   datatype="s2-18" value="${role.name}"
                                   nullmsg="请输入角色名称！" errormsg="角色名称至少2个字符,最多18个字符！"
                                   placeholder="请输入角色名称" class="col-xs-10 col-sm-5" />
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-md-offset-3 col-md-9">
                            <button class="btn btn-info" type="submit">
                                <i class="icon-ok bigger-110"></i>
                               	 提交
                            </button>
                            &nbsp; &nbsp; &nbsp;
                            <button class="btn btn-danger" type="reset">
                                <i class="icon-undo bigger-110"></i>
                                重置
                            </button>
                            &nbsp; &nbsp; &nbsp;
                            <button class="btn" type="button" onclick="history.go(-1)">
                                <i class="icon-backward bigger-110"></i>
                                返回
                            </button>
                        </div>
                    </div>
                </form>
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.page-content -->
    <script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/Validform/js/Validform_v5.3.2.js"></script>
    <script src="${ctx}/static/admin/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="${ctx}/static/admin/js/chosen.jquery.min.js"></script>
    <script src="${ctx}/static/admin/js/date-time/bootstrap-datepicker.min.js"></script>

    <!-- ace scripts -->

    <script src="${ctx}/static/admin/js/ace-elements.min.js"></script>
    <script src="${ctx}/static/admin/js/ace.min.js"></script>

    <!-- inline scripts related to this page -->

    <script type="text/javascript">
        jQuery(function($) {
            $(".chosen-select").chosen();
            $('#brithday').datepicker({
                format: 'yyyy-mm-dd',
                todayBtn: true
            });//createForm

            $.extend($.Datatype,{
                "z2-18" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,18}$/,
            });
            $("#createForm").Validform({
                tiptype:3,
                showAllError:true,
                ajaxPost:true,
                beforeSubmit:function(curform){
                   showdiv();
                },
                callback:function(data){
                    //返回数据data是json对象，{"info":"demo info","status":"y"}
                    //info: 输出提示信息;
                    //status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;
                    //你也可以在ajax_post.php文件返回更多信息在这里获取，进行相应操作；
                    //ajax遇到服务端错误时也会执行回调，这时的data是{ status:**, statusText:**, readyState:**, responseText:** }；

                    //这里执行回调操作;
                    //注意：如果不是ajax方式提交表单，传入callback，这时data参数是当前表单对象，回调函数会在表单验证全部通过后执行，然后判断是否提交表单，如果callback里明确return false，则表单不会提交，如果return true或没有return，则会提交表单。
                    hidediv();
                    if (data.status == 'y') {
                        alert(data.info);
                        window.location.href="${ctx}/admin/platform/role/list.html";
                    } else {
                        alert(data.info);
                        return false;
                    }

                }
            });
        });
    </script>
</hy:extends>
<jsp:include page="/base/admin.jsp"/>
