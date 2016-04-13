<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="../WEB-INF/MyPage.tld" prefix="pf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/libs/modernizr.min.js">
	
</script>
<script type="text/javascript">
	/*按用户的id来删除*/
	function DeleteUser(id) {

		if (confirm("您确定要删除该图书信息吗？")) {

			location.href = "BooksServlet?action=delete&id=" + id;
		}
	}
</script>
</head>
<body>

	<!--/sidebar-->
	<div class="main-wrap">
		<div class="crumb-wrap" align="center">
		<h2>图书列表</h2>
		</div>
		<div class="search-wrap">
			<div class="search-content">
			</div>
		</div>
		<div class="result-wrap">
			<form name="myform" id="myform" method="post">
				<div class="result-title">
					<div class="result-list">
					</div>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th align="center">图片</th>
							<th>名称</th>
							<th>编号</th>
							<th>介绍</th>
							<th>作者</th>
							<th>价格</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${requestScope.booklist}" var="book">
							<tr>
								<th><img src="images/bookimage/${book.id}.jpg" width="80px"
									height="120px">
								</th>
								<th>${book.name}</th>
								<th>${book.id}</th>
								<th>${book.introduce}</th>
								<th>${book.auther}</th>
								<th>${book.price}元</th>
								<td><a class="link-update" href="BooksServlet?action=select&id=${book.id}">修改信息</a> &nbsp;&nbsp; <a
									class="link-del" href="javascript:DeleteUser(${book.id});">删除</a>
								</td>
							</tr>
						</c:forEach>
					</table>
					<div class="list-page">
						<pf:page pageIndex="${requestScope.page}"
							url="BooksServlet?action=list&" pageMax="${requestScope.maxpage}" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>