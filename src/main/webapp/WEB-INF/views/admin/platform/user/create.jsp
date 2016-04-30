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
            <li class="active">用户新增</li>
        </ul><!-- .breadcrumb -->
    </div>

    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <input id="btnshow" type="button" value="Show" onclick="showdiv();"/>
                <!-- PAGE CONTENT BEGINS -->
                <form class="form-horizontal" action="${ctx}/admin/platform/user/save.html" id="createForm" role="form" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="nickName">用户昵称</label>
                        <div class="col-sm-9">
                            <input type="text" id="nickName" name="nickName"
                                   datatype="z2-18"
                                   nullmsg="请输入昵称！" errormsg="昵称至少2个字符,最多18个字符！"
                                   placeholder="请输入用户昵称" class="col-xs-10 col-sm-5" />
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="loginName">登录账号</label>
                        <div class="col-sm-9">
                            <input type="text" id="loginName"
                                   ajaxurl="${ctx}/admin/platform/user/validLoginName.json"
                                   datatype="*6-20,loginName" nullmsg="请输入登录名称！"
                                   errormsg="登录名称范围在6~20位之间数字、下划线、字母组合"
                                   name="loginName" placeholder="请输入登录账号" class="col-xs-10 col-sm-5" />
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="password">登录密码</label>
                        <div class="col-sm-9">
                            <input type="password" id="password"
                                   name="password"
                                   datatype="*6-20" nullmsg="请设置密码！"
                                   errormsg="密码范围在6~20位之间！"
                                   placeholder="请输入登录密码" class="col-xs-10 col-sm-5" />
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="spassword">确认密码</label>
                        <div class="col-sm-9">
                            <input type="password" id="spassword"
                                   datatype="spassword,*"
                                   nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！"
                                   placeholder="请输入确认密码" class="col-xs-10 col-sm-5" />
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label no-padding-right">用户邮箱</label>
                        <div class="col-sm-9">
                            <input type="text" name="email"
                                   id="email" placeholder="请输入用户邮箱"
                                   datatype="email" nullmsg="请输入邮箱地址"
                                   errormsg="邮箱地址不正确" class="col-xs-10 col-sm-5" >
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="brithday">出生日期</label>
                        <div class="col-sm-9 ">
                            <input type="text" id="brithday"
                                   datatype="brithday" nullmsg="请输入出生日期"
                                   name="brithday" placeholder="请输入出生日期" class="col-xs-10 col-sm-5" />
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="brithday">用户类型</label>
                        <div class="col-sm-9 ">
                            <select name="userTypes" datatype="*" nullmsg="请选择用户类型" class="width-40 chosen-select" data-placeholder="Choose a Country...">
                                <option value="">&nbsp;</option>
                                <c:forEach items="${userTypeList}" var="type">
                                    <option value="${type.key}">${type.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-md-offset-3 col-md-9">
                            <button class="btn btn-info" type="submit">
                                <i class="icon-ok bigger-110"></i>
                                Submit
                            </button>
                            &nbsp; &nbsp; &nbsp;
                            <button class="btn" type="reset">
                                <i class="icon-undo bigger-110"></i>
                                Reset
                            </button>
                        </div>
                    </div>
                </form>
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.page-content -->
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
                datatype: {
                    "spassword":function(gets){
                        if(gets!=$("#password").val()){
                            return "两次输入的密码不一致！";
                        }
                    },
                    "z2-18" : function (value,obj) {
                        var length = value.length;
                        if (length < 2 || length > 18) {
                            return "昵称至少2个字符,最多18个字符！";
                        }
                        return /[\u4E00-\u9FA5\uF900-\uFA2D]/.test(value) ? true : "请输入中文！"
                    },
                    "loginName":function(value){
                        var usern = /^[a-zA-Z0-9_]{1,}$/;
                        if(!usern.test(value)){
                            return "登录名称必须由数字、下划线、字母组成！";
                        }
                    },
                    "email":function(value){
                        var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
                        if(!reg.test(value)){
                            return "用户邮箱地址格式不正确";
                        }
                    },
                    "brithday":function(value){
                        if(value == ''){
                            return "请输入出生日期!";
                        }
                        var a = /^(\d{4})-(\d{2})-(\d{2})$/
                        if (!a.test(value)) {
                            return "日期格式不正确!";
                        }
                    }
                    
                },
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
                        window.location.href="${ctx}/admin/platform/user/list.html";
                    } else {
                        alert(data.info);
                        return false;
                    }

                }
            });
        });
    </script>
</hy:extends>
<jsp:include page="/base/manager.jsp"/>
