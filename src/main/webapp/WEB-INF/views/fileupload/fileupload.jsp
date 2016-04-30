<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${ctx}/static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery/fileupload.js"></script>
    <link rel="stylesheet" href="${ctx}/static/jquery/fileupload.css" type="text/css">
    <script type="text/javascript">
        $(document).ready(function () {
            $("#selectfile").fileupload({
                fileId: 'attachment',
                url: '${ctx}/outLink/upload',
                fileMaxSize : '5120',
                startBtn : 'startfile',
                autoupload :true,
                success: function (data) {
                    console.log(data)
                }
            });
        });
    </script>
</head>
<body>
<input value="选择文件" type="button" id="selectfile" name="selectfile"/>
<input value="上传文件" type="button" id="startfile" name="startfile"/>
<input type="file" name="attachment" style="display: none" id="attachment">
</body>
</html>
