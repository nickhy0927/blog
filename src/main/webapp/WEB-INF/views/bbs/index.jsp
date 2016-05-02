<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<html>
<%@ taglib prefix="hy" uri="https://www.hy.include" %>
<hy:extends name="title">滴落的露珠</hy:extends>
<hy:extends name="css">

<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" href="${ctx}/static/font-awesome/css/font-awesome.css"/>
<link rel="stylesheet" href="${ctx}/static/font-awesome/css/font-awesome-ie7.css.css"/>
<link rel="stylesheet" href="${ctx}/static/bbs/css/index.css"/>
<link rel="stylesheet" href="${ctx}/static/bbs/css/style.css"/>

<style type="text/css"></style>
</hy:extends>
<hy:extends name="javascript">
<script type="text/javascript" src="${ctx}/static/bbs/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript"></script>
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
        <li><a href="index.html">首页</a></li>
        <li><a href="about.html">关于我</a></li>
        <li><a href="shuo.html">碎言碎语</a></li>
        <li><a href="riji.html">个人日记</a></li>
        <li><a href="xc.html">相册展示</a></li>
        <li><a href="learn.html">学无止境</a></li>
        <li><a href="guestbook.html">留言板</a></li>
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
            <div class="login">
                <div class="login-title">用户登录</div>
                <div class="login-content">
                    <form action="" method="post" >
                        <div class="input-group">
                            <div class="input-group-addon" style="width: 40px;">
                                <i class="icon-user"></i>
                            </div>
                            <input type="text" class="form-control" id="loginName" name="loginName" placeholder="登录名称">
                        </div>
                        <div class="input-group" style="margin-top: 10px;">
                            <div class="input-group-addon" style="width: 40px;">
                                <i class="icon-lock"></i>
                            </div>
                            <input type="password" class="form-control" id="password" name="password" placeholder="登录密码">
                        </div>
                        <div class="input-group" style="margin-top: 10px;width: 100%" >
                            <a href="#" class="btn btn-success btn-sm">登录</a>
                            <span style="width: 60px;">&nbsp;&nbsp;&nbsp;</span>
                            <a href="#" class="btn btn-info btn-sm">注册</a>
                            <span style="margin-right: 20px;float: right;margin-top: 5px">
                                <a> <i class="icon-question"></i>&nbsp;忘记了密码</a>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
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
<!--footer end-->
<script type="text/javascript">jQuery(".lanmubox").slide({easing: "easeOutBounce", delayTime: 400});</script>
<script type="text/javascript" src="${ctx}/static/bbs/js/nav.js"></script>
</hy:extends>
<jsp:include page="/base/bbs.jsp"/>
