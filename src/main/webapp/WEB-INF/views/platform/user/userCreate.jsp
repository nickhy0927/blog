<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.hy.include" prefix="hy" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<hy:extends name="title">用户添加</hy:extends>
<hy:extends name="css">
	<style type="text/css">
		.header {
			height: 40px;
			padding-left:15px;
			line-height: 40px;
			font-weight:bold;
			background: #BBFFEE;
			margin-bottom: 10px;
		}
		.form-horizontal .control-label {
			text-align:left;
		}
		.form-group {
			border-bottom: 1px solid #CCDDFF;
			height: 50px;
			line-height: 50px;
	    	margin-top: -10px;
		}
		.form-group label{
	    	margin-top: -10px;
		}
		.form-group input{
	    	margin-top: 5px;
		}
	</style>
</hy:extends>
<hy:extends name="javascript">
	<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
	        $(".registerform").Validform({
	            tiptype:2,
	            datatype:{
	                "*3-20": /^[^\s]{6,20}$/,
	                "z2-20" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,20}$/,
	                "loginName":function(value){
	                    var usern = /^[a-zA-Z0-9_]{1,}$/;
	                    if(!usern.test(value)){
	                        return "登录名称必须由数字、下划线、字母组成！";
	                    }
	                },
	                "spassword":function(gets){
	                    if(gets!=$("#password").val()){
	                        return "两次输入的密码不一致！";
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
	            }
	        });
	    });
	</script>
</hy:extends>
<hy:extends name="body">
	<div class="header">添加用户</div>
	<div class="container-fluid">
        <form class="form-horizontal registerform" method="post" action="${ctx}/platform/user/userSave">
            <div class="form-group">
                <label for="nickName" class="col-sm-2 control-label">用户昵称</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="nickName" id="nickName" placeholder="请输入用户昵称"
                           datatype="z2-20" nullmsg="请输入用户昵称！" errormsg="昵称至少3个字符的中文,最多20个字符！">
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <label for="loginName" class="col-sm-2 control-label">登录名称</label>
                <div class="col-sm-5">
                    <input type="text" name="loginName" class="form-control" id="loginName" placeholder="请输入登录名称"
                           datatype="*6-20,loginName" nullmsg="请输入登录名称！" errormsg="登录名称范围在6~20位之间数字、下划线、字母组合" >
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">登录密码</label>
                <div class="col-sm-5">
                    <input type="password" name="password" class="form-control" id="password" placeholder="请输入登录密码"
                           datatype="*6-16" nullmsg="请输入登录密码！" errormsg="密码范围在6~16位之间！" >
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <label for="spassword" class="col-sm-2 control-label">确认密码</label>
                <div class="col-sm-5">
                    <input type="password" name="spassword" class="form-control" id="spassword" placeholder="请输入确认密码"
                            datatype="spassword,*" nullmsg="请输入确认密码">
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <label for="brithday" class="col-sm-2 control-label">出生日期</label>
                <div class="col-sm-5">
                    <input type="text" name="brithday" class="form-control" id="brithday" placeholder="请输入出生日期"
                           datatype="brithday" nullmsg="请输入出生日期" onfocus="WdatePicker()">
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">用户邮箱</label>
                <div class="col-sm-5">
                    <input type="text" name="email" class="form-control" id="email" placeholder="请输入用户邮箱"
                            datatype="email" nullmsg="请输入邮箱地址" errormsg="邮箱地址不正确">
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="submit" class="btn btn-primary">注册</button>
                    <button type="reset" class="btn btn-default">返回</button>
                </div>
            </div>
        </form>
    </div>
</hy:extends>
<jsp:include page="/base/manager.jsp" />