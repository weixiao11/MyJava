<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>无标题文档</title>
<style type="text/css">
#bg{
	background-image:url("images/right.jpg");
    position:absolute;
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
<div id="bg"></div>
</body>
</html>
