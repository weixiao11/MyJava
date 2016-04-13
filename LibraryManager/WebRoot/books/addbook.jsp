<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加图书</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript">
function CheckName(){
   
   var f = document.myform;
   var name = f.name.value;
   if(name == ""){
   
      alert("图书名不能为空！");
      f.name.focus();
      return false;
   }
   return true;
}
function CheckAuther(){
   
   var f = document.myform;
   var auther = f.auther.value;
   if(auther == ""){
   
      alert("作者不能为空！");
      f.auther.focus();
      return false;
   }
   return true;
}
function CheckPrice(){
   
   var f = document.myform;
   var price = f.price.value;
   if(price == ""){
   
      alert("图书价格不能为空！");
      f.price.focus();
      return false;
   }
   return true;
}

function Check(){
   
   if(!(CheckName()&&CheckAuther()&&CheckPrice())){
      
      alert("含有不合法输入，请重试！");
      return false;
   }else{
      
      return true;
   }
}
</script>
</head>

<body>

	<!--/sidebar-->
	<div class="main-wrap">
		<div class="crumb-wrap" align="center">
			<h2>添加图书信息</h2>
		</div>
		<div class="search-wrap">
			<div class="search-content"></div>
		</div>
		<div class="result-wrap">
			<form name="myform" id="myform" method="post" action="../BooksServlet?action=add" onsubmit="return Check()">
				<div class="result-title">
					<table width="100%">
						<tr>
							<td width="48%" align="right">图片：</td>
							<td width="16%" height="80px"><img src="#" height="120px"
								width="80px" />
							</td>
							<td width="36%"></td>
						</tr>
						<tr>
							<td align="right">名称：</td>
							<td><input type="text" name="name" onblur="CheckName()">
							</td>
							<td></td>
						</tr>
						<tr>
							<td align="right">简介：</td>
							<td><input type="text" name="introduce" height="80ps" width="200ps">
							</td>
							<td></td>
						</tr>
						<tr>
							<td align="right">作者：</td>
							<td><input type="text" name="auther" onblur="CheckAuther()">
							</td>
							<td></td>
						</tr>
						<tr>
							<td align="right">价格：</td>
							<td><input type="text" name="price" onblur="CheckPrice()">
							</td>
							<td></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="right"><input type="submit" value="提交">
							</td>
							<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重置">
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
