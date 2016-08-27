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
        .chosen-container {
        	width: 408px !important;
        }
    </style>
</hy:extends>

<hy:extends name="javascript">
    <script type="text/javascript">
        if("ontouchend" in document) document.write("<script src='${ctx}/static/admin/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
    </script>
    <script src="${ctx}/static/admin/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/admin/js/typeahead-bs2.min.js"></script>
	
    <script src="${ctx}/static/ckeditor/ckeditor.js"></script>
    <script src="${ctx}/static/ckeditor/config.js"></script>
    <script src="${ctx}/static/ckeditor/sample.js"></script>
    <!-- page specific plugin scripts -->

    <!--[if lte IE 8]>
    <script src="${ctx}/static/admin/js/excanvas.min.js"></script>

    <![endif]-->
    <link rel="stylesheet" href="${ctx}/static/ztree/css/demo.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/static/ckeditor/skins/moono/editor.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/static/ckeditor/samples.css" type="text/css">
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
            <li class="active">栏目帖子</li>
        </ul><!-- .breadcrumb -->
    </div>

    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <form class="form-horizontal" action="${ctx}/tribune/user/note/save.html" id="createForm" role="form" method="post">
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right">编号：</label>
                        <div class="col-sm-11">
                            ${note.noteNumber}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right">栏目：</label>
                        <div class="col-sm-11 ">
                            ${note.category.name}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right">标题：</label>
                        <div class="col-sm-11">
                           ${note.noteTitle}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right">内容：</label>
                        <div class="col-sm-11">
                            ${note.noteContent}
                        </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-md-offset-3 col-md-9">
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
		initSample();
        jQuery(function($) {
        	$('.chosen-container').css({
        		"width" : "408px"
        	});
            $(".chosen-select").chosen();
            $("#createForm").Validform({
                tiptype:3,
                showAllError:true,
                ajaxPost:true,
                datatype:{
                    "*1-50": /^[^\s]{1,50}$/,
                    "z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/
                },
                beforeSubmit:function(curform){
               		var stem = CKEDITOR.instances.editor.getData();
               		if(stem != undefined && stem != null) {
               			showdiv();
               			console.log(stem);
               			$("#noteContent").val(stem);
               		} else {
               			return false;
               		}
                },
                callback:function(data){
                    hidediv();
                    if (data.status == 'y') {
                        alert(data.info);
                        window.location.href="${ctx}/admin/platform/category/categoryList.html";
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
