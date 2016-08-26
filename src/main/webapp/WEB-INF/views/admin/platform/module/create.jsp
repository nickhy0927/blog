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
        .treeColumn {
            position:absolute;
            z-index:9999;
            display: none;
            width:auto;
            margin-top: 20px;
            padding-right:0px;
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
    <link rel="stylesheet" href="${ctx}/static/ztree/css/demo.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
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
            <li class="active">菜单新增</li>
        </ul><!-- .breadcrumb -->
    </div>

    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <form class="form-horizontal" action="${ctx}/admin/platform/menu/save.html" id="createForm" role="form" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name">菜单名称</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="id" name="id" value="${menu.id}"/>
                            <input type="text" id="name" name="name"
                                   placeholder="请输入菜单名称"
                                   datatype="*2-20" nullmsg="请输入菜单名称！"
                                   errormsg="菜单名称至少2个字符,最多20个字符！"
                                   value="${menu.name}"class="col-xs-10 col-sm-5" />
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="href">链接地址</label>
                        <div class="col-sm-9">
                            <input type="text" id="href" name="href"
                                   value="${menu.href}"
                                   placeholder="请输入链接地址"
                                   datatype="*" nullmsg="请输入链接地址！" errormsg="链接地址不能为空"
                                   class="col-xs-10 col-sm-5" />
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="authority">菜单别名</label>
                        <div class="col-sm-9">
                            <input type="text" id="authority" name="authority"
                                   value="${menu.authority}"
                                   datatype="s2-20" nullmsg="请输入菜单别名！"
                                   errormsg="别名范围在2~20位之间！"
                                   placeholder="请输入菜单别名" class="col-xs-10 col-sm-5" />
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="parentName">上级菜单</label>
                        <div class="col-sm-9 ">
                            <input type="hidden" id="parentId" name="parentId">
                            <input type="text" name="parentName" class="col-xs-10 col-sm-5" id="parentName" placeholder="请输入上级菜单" />
                            <div class="zTreeDemoBackground left treeColumn">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
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
            $("#createForm").Validform({
                tiptype:3,
                showAllError:true,
                ajaxPost:true,
                datatype:{
                    "*3-20": /^[^\s]{3,20}$/,
                    "z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/
                },
                beforeSubmit:function(curform){
                   showdiv();
                },
                callback:function(data){
                    hidediv();
                    if (data.status == 'y') {
                        alert(data.info);
                        window.location.href="${ctx}/admin/platform/menu/list.html";
                    } else {
                        alert(data.info);
                        return false;
                    }

                }
            });
        });
        $(document).ready(function(){
            $("#parentName").click(function() {
                $(".treeColumn").slideDown(500);
            });
            $(".treeColumn").mouseleave(function() {
                $(".treeColumn").hide();
            });

            $('#parentName').keyup(function() {
                if($('#parentName').val() == ''){
                    $('#parentId').val('');
                }
            });
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });

        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: onClick
            }
        };

        var zNodes = ${menuTrees};
        function onClick(event,treeId, treeNode) {
            $("#parentId").val(treeNode.id);
            $("#parentName").val(treeNode.name);
            $(".treeColumn").hide();
            $(".treeColumn").slideUp(500);
        }
    </script>
</hy:extends>
<jsp:include page="/base/admin.jsp"/>
