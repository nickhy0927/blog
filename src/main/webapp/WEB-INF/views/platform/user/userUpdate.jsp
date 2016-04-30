<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib uri="http://curtainContain/tag" prefix="curtain"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <curtain:include href="base/bootstap.jsp" />
    <script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/static/project/css/index.css">
    <title>用户信息添加</title>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".registerform").Validform({
                tiptype:2,
                datatype:{
                    "*3-20": /^[^\s]{3,20}$/,
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
</head>
<body>
	<div class="container-fluid">
        <form class="form-horizontal registerform" method="post" action="${ctx}/platform/user/userSave">
            <input type="hidden" value="${user.id}" name="id" id="id">
            <div class="form-group">
                <label for="nickName" class="col-sm-2 control-label">用户昵称</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" value="${user.nickName}" name="nickName" id="nickName" placeholder="请输入用户昵称"
                           datatype="z2-20" nullmsg="请输入昵称！" errormsg="昵称至少2个字符,最多20个字符！">
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <label for="loginName" class="col-sm-2 control-label">登录名称</label>
                <div class="col-sm-6">
                    <input type="text" name="loginName" value="${user.loginName }" class="form-control" id="loginName" placeholder="请输入登录名称"
                           datatype="*6-20,loginName" nullmsg="请输入登录名称！" errormsg="登录名称范围在6~16位之间" >
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <label for="brithday" class="col-sm-2 control-label">出生日期</label>
                <div class="col-sm-6">
                    <input type="text" name="brithday" value="${user.brithday}" class="form-control" id="brithday" placeholder="请输入出生日期"
                           datatype="brithday" nullmsg="请输入出生日期" onfocus="WdatePicker()">
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">用户邮箱</label>
                <div class="col-sm-6">
                    <input type="text" name="email" value="${user.email}" class="form-control" id="email" placeholder="请输入用户邮箱"
                            datatype="email" nullmsg="请输入邮箱地址" errormsg="邮箱地址不正确">
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="submit" class="btn btn-primary">修改</button>
                    <a href="javascript:history.go(-1)" class="btn btn-default">返回</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>