<%@page import="com.cako.platform.user.entity.User"%>
<%@page import="com.orm.commons.utils.MessageObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%
	Object object = session.getAttribute("user");
	String id = "1";
	if(object != null) {
		User u = (User)object;
		id = u.getId();
	}
%>
<html>
<%@ taglib prefix="hy" uri="https://www.hy.include" %>
<hy:extends name="title">滴落的露珠</hy:extends>
<c:set value="<%=id %>" var="uId"></c:set>
<hy:extends name="css">
<style type="text/css">
    .Validform_checktip {
        line-height: 22px;
    }
    .lanmubox .bd ul li a {
	    font-size: 11px;
	}
	
	a:HOVER {
		text-decoration: none;
	}
</style>
</hy:extends>
<hy:extends name="javascript">

<script type="text/javascript" src="${ctx}/static/bbs/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#login").on('click', function () {
            $("#show").html('<div style="width: 100%;padding:5px 0 20px 65px;">'
                    + '<img src="${ctx}/static/bbs/images/loading.gif">'
                    + '</div><div style="text-align:center">正在登录请稍等...</div>').css({
                'text-align': 'center'
            });
            showdiv();
            var ajaxCallUrl = "${ctx}/outLink/login.html";
            $.ajax({
            	type: "POST",
                url:ajaxCallUrl,
                data:$('#loginForm').serialize(),// 你的formid
                async: false,
                error: function(request) {
                	hidediv();
                	$("#msg-tip").html('用户名或者密码错误');
              		return false;
                },
                success: function(data) {
                	hidediv();
                  	data = JSON.parse(data);
                  	if (data.resposecode == '<%=MessageObject.ResponseCode.code_200%>'){
                  		window.location.href = "";
                  	} else {
                  		$("#msg-tip").html('用户名或者密码错误');
                  		return false;
                  	}
                }
            });
        });
    });
	function isClick() {
		var cUId = "${uId}";
		if(cUId == 1){
			alert('请先登陆');
			return false;
		}
		return true;
	}	
</script>
</hy:extends>
<hy:extends name="body">
<!--header start-->
<div id="header">
    <h1>滴落的露珠</h1>
    <p>青春是打开了,就合不上的书。人生是踏上了，就回不了头的路，爱情是扔出了，就收不回的赌注。</p>
</div>
<!--header end-->
<!--nav-->
<div id="nav">
    <ul>
        <li><a href="${ctx}/outLink/outerPage.html">首页</a></li>
        <li><a href="shuo.html">碎言碎语</a></li>
        <li><a href="riji.html" onclick="return isClick(this);">个人日记</a></li>
        <li><a href="xc.html" onclick="return isClick(this);">相册展示</a></li>
        <li><a href="learn.html" onclick="return isClick(this);">学无止境</a></li>
        <li><a href="guestbook.html" onclick="return isClick(this);">留言板</a></li>
        <li><a href="about.html">关于我</a></li>
        <div class="clear"></div>
    </ul>
</div>
<!--nav end-->
<!--content start-->
<div id="content">
    <!--left-->
    <div class="left" id="c_left">
        <div class="s_tuijian">
            <h2>文章<span>推荐</span></h2>
        </div>
        <div class="content_text">
            <!--wz-->
            <div class="wz">
                <h3><a href="#" title="浅谈：html5和html的区别">浅谈：html5和html的区别</a></h3>
                <dl>
                    <dt><img src="${ctx}/static/bbs/images/s.jpg" width="200" height="123" alt=""></dt>
                    <dd>
                        <p class="dd_text_1">最近看群里聊天聊得最火热的莫过于手机网站和html5这两个词。可能有人会问，这两者有什么关系呢？随着这移动互联
                            网快速发展的时代，尤其是4G时代已经来临的时刻，加上微软对"XP系统"不提供更新补丁、维护的情况下。
                            html5+css3也逐渐的成为新一代web前端技术.....</p>
                        <p class="dd_text_2">
                            <span class="left author">段亮</span><span class="left sj">时间:2014-8-9</span>
                            <span class="left fl">
                                分类:
                                <a href="#" title="学无止境">学无止境</a>
                            </span>
                            <span class="left yd">
                                <a href="#" title="阅读全文">阅读全文</a>
                            </span>
                        <div class="clear"></div>
                        </p>
                    </dd>
                    <div class="clear"></div>
                </dl>
            </div>
            <!--wz end-->
        </div>
    </div>
    <!--left end-->
    <!--right-->
    <div class="right" id="c_right">
        <div class="s_about">
        	<%
        		if (object == null){
        	%>
            <div class="login">
                <div class="login-title">用户登录</div>
                <div class="login-content">
                    <form action="" method="post" name="loginForm" id="loginForm">
                        <div class="input-group">
                            <div class="input-group-addon" style="width: 40px;">
                                <i class="icon-user"></i>
                            </div>
                            <input type="text" class="form-control" name="loginName" placeholder="登录名称">
                        </div>
                        <div class="input-group" style="margin-top: 10px;">
                            <div class="input-group-addon" style="width: 40px;">
                                <i class="icon-lock"></i>
                            </div>
                            <input type="password" class="form-control" name="password" placeholder="登录密码">
                        </div>
                        <div id="msg-tip"></div>
                        <div class="input-group" style="margin-top: 10px;width: 100%">
                            <a id="login" href="#" class="btn btn-success btn-sm">登录</a>
                            <span style="width: 60px;">&nbsp;&nbsp;&nbsp;</span>
                            <a href="#" data-toggle="modal"
                               data-target="#myModal"
                               class="btn btn-info btn-sm">注册</a>
                            <span style="margin-right: 20px;float: right;margin-top: 5px">
                                <a> <i class="icon-question"></i>&nbsp;忘记了密码</a>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
            <%
            	} else {
            		User user = (User)object;
       		%>	
       			<%-- 登录成功：<%=user.getNickName()%> --%>
       			<h2>关于博主</h2>
           		<img src="${ctx}/static/bbs/images/my.jpg" width="230" height="230" alt="博主"/>
           		<p>博主：<%=user.getNickName()%></p>
           		<p>职业：web前端、视觉设计</p>
           		<p>简介：</p>
           		<p>
           			<a href="#" title="联系博主">
           				<span class="left b_1"></span>
           			</a>
           			<a href="#" title="加入QQ群，一起学习！">
           				<span class="left b_2"></span>
           			</a>
           			<div class="clear"></div>
          		</p>	
     		<%
     			}
     		%>
        </div>
        <!--栏目分类-->
        <div class="lanmubox">
            <div class="hd">
                <ul>
                    <li>精心推荐</li>
                    <li>最新文章</li>
                    <li class="hd_3">随机文章</li>
                </ul>
            </div>
            <div class="bd">
                <ul>
                    <li><a href="#" title="网站项目实战开发（-）">网站项目实战开发（-）</a></li>
                    <li><a href="#" title="关于响应式布局">关于响应式布局</a></li>
                    <li><a href="#" title="如何创建个人博客网站">如何创建个人博客网站</a></li>
                    <li><a href="#" title="网站项目实战开发（二）">网站项目实战开发（二）</a></li>
                    <li><a href="#" title="为什么新站前期排名老是浮动？(转)">为什么新站前期排名老是浮动？(转)</a></li>
                </ul>
                <ul>
                    <li><a href="#" title="网站项目实战开发（-）">网站项目实战开发（-）</a></li>
                    <li><a href="#" title="关于响应式布局">关于响应式布局</a></li>
                    <li><a href="#" title="如何创建个人博客网站">如何创建个人博客网站</a></li>
                    <li><a href="#" title="网站项目实战开发（二）">网站项目实战开发（二）</a></li>
                    <li><a href="#" title="为什么新站前期排名老是浮动？(转)">为什么新站前期排名老是浮动？(转)</a></li>
                </ul>
                <ul>
                    <li><a href="#" title="网站项目实战开发（-）">网站项目实战开发（-）</a></li>
                    <li><a href="#" title="关于响应式布局">关于响应式布局</a></li>
                    <li><a href="#" title="如何创建个人博客网站">如何创建个人博客网站</a></li>
                    <li><a href="#" title="网站项目实战开发（二）">网站项目实战开发（二）</a></li>
                    <li><a href="#" title="为什么新站前期排名老是浮动？(转)">为什么新站前期排名老是浮动？(转)</a></li>
                </ul>


            </div>
        </div>
        <!--end-->
        <div class="link">
            <h2>友情链接</h2>
            <p><a href="http://www.duanliang920.com">段亮个人博客</a></p>
        </div>
    </div>
    <!--right end-->
    <div class="clear"></div>
</div>
<!--content end-->
<!--footer start-->
<div id="footer">
    <p>Design by:<a href="http://www.duanliang920.com" target="_blank">少年</a> 2014-8-9</p>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:800px;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    <!-- PAGE CONTENT BEGINS -->
                    用户注册
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="${ctx}/blog/register" id="createForm" role="form"
                      method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="nickName">用户昵称</label>
                        <div class="col-sm-9">
                            <input type="text" id="nickName" name="nickName"
                                   datatype="z2-18"
                                   nullmsg="请输入昵称！" errormsg="昵称至少2个字符,最多18个字符！"
                                   placeholder="请输入用户昵称" class="col-xs-10 col-sm-5"/>
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
                                   name="loginName" placeholder="请输入登录账号" class="col-xs-10 col-sm-5"/>
                        </div>
                        <span class="middle Validform_checktip"></span>
                    </div>
                    <div class="space-4"></div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="password">登录密码</label>
                        <div class="col-sm-9">
                            <input type="password" id="password"
                                   name="password"
                                   datatype="*6-20" nullmsg="请设置密码！"
                                   errormsg="密码范围在6~20位之间！"
                                   placeholder="请输入登录密码" class="col-xs-10 col-sm-5"/>
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="spassword">确认密码</label>
                        <div class="col-sm-9">
                            <input type="password" id="spassword"
                                   datatype="spassword,*"
                                   nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！"
                                   placeholder="请输入确认密码" class="col-xs-10 col-sm-5"/>
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-3 control-label no-padding-right">用户邮箱</label>
                        <div class="col-sm-9">
                            <input type="text" name="email"
                                   id="email" placeholder="请输入用户邮箱"
                                   datatype="email" nullmsg="请输入邮箱地址"
                                   errormsg="邮箱地址不正确" class="col-xs-10 col-sm-5">
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="brithday">出生日期</label>
                        <div class="col-sm-9 ">
                            <input type="text" id="brithday"
                                   onfocus="WdatePicker()"
                                   datatype="brithday" nullmsg="请输入出生日期"
                                   name="brithday" placeholder="请输入出生日期" class="col-xs-10 col-sm-5"/>
                            <span class="middle Validform_checktip"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <button type="submit" class="btn btn-primary">
                                提交
                            </button>
                        </div>
                    </div>
                </form>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!--footer end-->
<script type="text/javascript">jQuery(".lanmubox").slide({easing: "easeOutBounce", delayTime: 400});</script>
<script type="text/javascript" src="${ctx}/static/bbs/js/nav.js"></script>
<script src="${ctx}/static/admin/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${ctx}/static/admin/js/chosen.jquery.min.js"></script>
<script src="${ctx}/static/admin/js/date-time/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/static/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<!-- ace scripts -->

<script src="${ctx}/static/admin/js/ace-elements.min.js"></script>
<script src="${ctx}/static/admin/js/ace.min.js"></script>
<script type="text/javascript">
    $.extend($.Datatype, {
        "z2-18": /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,18}$/,
    });
    $("#createForm").Validform({
        tiptype: 3,
        showAllError: true,
        ajaxPost: true,
        datatype: {
            "spassword": function (gets) {
                if (gets != $("#password").val()) {
                    return "两次输入的密码不一致！";
                }
            },
            "z2-18": function (value, obj) {
                var len = value.length;
                if (len < 2 || len > 18) {
                    return "昵称至少2个字符,最多18个字符！";
                }
                return /[\u4E00-\u9FA5\uF900-\uFA2D]/.test(value) ? true : "请输入中文！"
            },
            "loginName": function (value) {
                var usern = /^[a-zA-Z0-9_]{1,}$/;
                if (!usern.test(value)) {
                    return "登录名称必须由数字、下划线、字母组成！";
                }
            },
            "email": function (value) {
                var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
                if (!reg.test(value)) {
                    return "用户邮箱地址格式不正确";
                }
            },
            "brithday": function (value) {
                if (value == '') {
                    return "请输入出生日期!";
                }
                var a = /^(\d{4})-(\d{2})-(\d{2})$/
                if (!a.test(value)) {
                    return "日期格式不正确!";
                }
            }

        },
        beforeSubmit: function (curform) {
            showdiv();
        },
        callback: function (data) {
            hidediv();
            if (data.status == 'y') {
                alert(data.info);
                window.onload();
            } else {
                alert(data.info);
                return false;
            }

        }
    });
</script>
</hy:extends>
<jsp:include page="/base/bbs.jsp"/>
