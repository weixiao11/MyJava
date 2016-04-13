<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>	

<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员登录</title>
<script type="application/x-javascript"> 
addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
function hideURLbar()
{ 
window.scrollTo(0,1);
 } 
 
 function dosubmit()
	{
		var th=document.f1;
		if(th.id.value=="")
		{
			alert("账号不能为空！");
			th.id.focus();
			return false;
			}
		if(th.pwd.value=="")
		{
			alert("密码不能为空！");
			th.pwd.focus();
			return false;
			}
			th.submit();
		return true;
		}
 </script>
<link href="css/style.css" rel='stylesheet' type='text/css' />
<style type="text/css">
#d1{
	background-color: rgba(7,111,187,0.79);
	}
#d2{
	background-color: rgba(0,0,0,0.62);
	}
h1{
	color: rgba(0,68,255,1.00);
	font-family: "楷体", "华文行楷", "汉仪清韵体简", "華康竹風體", "Wide Latin";
	font-style: normal;
	font-weight: normal;
	font-size: 44px;
	}
</style>

</head>
<body>
<script>$(document).ready(function(c) {
	$('.close').on('click', function(c){
		$('.login-form').fadeOut('slow', function(c){
	  		$('.login-form').remove();
		});
	});	  
});
</script>
 <!--SIGN UP-->
 <h1>图书管理系统</h1>
<div class="login-form" id="d1">
  <div id="d2" class="head-info">
            <label class="lbl-1"> </label>
			<label class="lbl-2"> </label>
			<label class="lbl-3"> </label>
	</div>
			<div id="d2" class="clear"> </div>
	<div class="avtar">
		<img src="images/avtar.png" />
	</div>
			<form name="f1" action="ManagerServlet?action=login" method="post" onSubmit="return dosubmit()">
					<input type="text" name="id" >
						<div class="key">
					<input type="password" name="pwd" >
						</div>
			</form>
	<div class="signin">
		<input type="submit" value="登录"  onClick="dosubmit()">
	</div>
  </div>				
</body>
</html>
