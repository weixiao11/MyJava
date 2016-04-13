<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理员个人信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function CheckPwdO(pwd){
   
   var f = document.form;
   var pwdo = f.pwdo.value;
   if(pwdo == ""){
      
      alert("原密码不能为空！");
      f.pwdo.focus();
      return false;
   }
   if(pwd != pwdo){
      
      alert("原密码不正确，请核对后重新输入！");
      f.pwdo.focus();
      return false;
   }
   return true;
}

function CheckPwdN(){

   var f = document.form;
   var pwdn = f.pwdn.value;
   if(pwdn == ""){
      
      alert("输入不能为空！");
      f.pwdn.focus();
      return false;
   }
   return true;
}

function CheckPwdN2(){

   var f = document.form;
   var pwdn2 = f.pwdn2.value;
   var pwdn = f.pwdn.value;
   if(pwdn2 != pwdn){
      
      alert("两次密码输入不一致，请核对后重新输入！");
      f.pwdn2.focus();
      return false;
   }
   return true;
}
function Check(pwd){
   
   if(CheckPwdO(pwd)&&CheckPwdN()&&CheckPwdN2()){
      
      if(confirm("您确认对密码进行修改？")){
       
         return true; 
      }
   }else{
      
      alert("数据输入有误，请确认后重新输入！");   
   }
   return false;
}
</script>
</head>

<body>
	<form action="ManagerServlet?action=update" method="post" name="form"
		onsubmit="return Check(${sessionScope.m.pwd})">
		<table width="100%">
			<tr>
				<td height="89" colspan="2" align="center"><h2>修改管理员密码</h2></td>
			</tr>
			<tr>
				<td width="50%" height="50" align="right">原密码：</td>
				<td width="50%"><input type="password" name="pwdo"
					onblur="CheckPwdO(${sessionScope.m.pwd})" /></td>
			</tr>
			<tr>
				<td height="50" align="right">新密码：</td>
				<td><input type="password" name="pwdn" onblur="CheckPwdN()" />
				</td>
			</tr>
			<tr>
				<td height="50" align="right">确认密码：</td>
				<td><input type="password" name="pwdn2" onblur="CheckPwdN2()" />
				</td>
			</tr>
			<tr>
				<td height="50" align="right"><input type="submit" value="提交" />
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重置" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>