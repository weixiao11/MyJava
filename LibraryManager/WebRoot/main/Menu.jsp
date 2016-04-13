<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理系统</title>
<link rel="stylesheet" type="text/css" href="../css/common.css" />
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
</head>
<body>
	<div class="container clearfix">
		<div class="sidebar-wrap">
			<div class="sidebar-title">
				<h1>菜单</h1>
			</div>
			<div class="sidebar-content">
				<ul class="sidebar-list">
					<li><a href="#"><i class="icon-font">&#xe003;</i>图书管理</a>
						<ul class="sub-menu">
							<li><a href="../BooksServlet?action=list" target="dmMain"><i
									class="icon-font">&#xe008;</i>查看图书</a>
							</li>
							<li><a href="../books/addbook.jsp" target="dmMain"><i
									class="icon-font">&#xe006;</i>添加图书</a>
							</li>
						</ul></li>
					<li><a href="#"><i class="icon-font">&#xe018;</i>管理管理员</a>
						<ul class="sub-menu">
							<li><a href="../manager/addma.jsp" target="dmMain"><i
									class="icon-font">&#xe017;</i>添加管理员</a>
							</li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
