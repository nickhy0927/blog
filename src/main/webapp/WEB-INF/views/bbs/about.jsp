<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib uri="https://www.hy.include" prefix="hy"%>
<hy:extends name="titie">联系我</hy:extends>
<hy:extends name="css">
	<style type="text/css">
	</style>
</hy:extends>
<hy:extends name="javascript">
	<script type="text/javascript"></script>
</hy:extends>
<hy:extends name="body">
	<div class="container-fluid">
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
		        <li><a href="${ctx}/outLink/about.html">关于我</a></li>
		        <div class="clear"></div>
		    </ul>
		</div>
	</div>
</hy:extends>
<jsp:include page="/base/bbs.jsp" />

