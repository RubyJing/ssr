<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/commom.jsp"%>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <div class="layui-container">
        <div class="layui-row">
            <div class="layui-col-xs4 layui-col-sm7 layui-col-md8">
                移动：4/12 | 平板：7/12 | 桌面：8/12
                <c:out value="${path}"></c:out>
            </div>
        </div>
    </div>
</body>
</html>
