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

<title>My JSP 'addma.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
function CheckId(){
   
   var f = document.form;
   var id= f.id.value;
   if(id == ""){
      
      alert("账号不能为空！");
      f.id.focus();
      return false;
   }
   return true;
}

function CheckName(){
   
   var f = document.form;
   var name= f.name.value;
   if(name == ""){
      
      alert("姓名不能为空！");
      f.name.focus();
      return false;
   }
   return true;
}

function CheckPwd(){

   var f = document.form;
   var pwd = f.pwd.value;
   if(pwd == ""){
      
      alert("密码不能为空！");
      f.pwd.focus();
      return false;
   }
   return true;
}

function CheckPwd2(){

   var f = document.form;
   var pwda = f.pwda.value;
   var pwd = f.pwd.value;
   if(pwda != pwd){
      
      alert("两次密码输入不一致，请核对后重新输入！");
      f.pwda.focus();
      return false;
   }
   return true;
}
function Check(pwd){
   
   if(CheckId()&&CheckName()&&CheckPwd()&&CheckPwd2()){
      
      if(confirm("您确认要添加该管理员？")){
       
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
	<form action="ManagerServlet?action=add" method="post" name="form"
		onsubmit="return Check()">
		<table width="100%">
			<tr>
				<td height="89" colspan="2" align="center"><h2>添加管理员</h2></td>
			</tr>
			<tr>
				<td width="50%" height="50" align="right">账号：</td>
				<td width="50%"><input type="text" name="id" onblur="CheckId()" />
				</td>
			</tr>
			<tr>
				<td height="50" align="right">姓名：</td>
				<td><input type="text" name="name" onblur="CheckName()" /></td>
			</tr>
			<tr>
				<td height="50" align="right">密码：</td>
				<td><input type="password" name="pwd" onblur="CheckPwd()" /></td>
			</tr>
			<tr>
				<td height="50" align="right">确认密码：</td>
				<td><input type="password" name="pwda" onblur="CheckPwd2()" />
				</td>
			</tr>
			<tr>
				<td height="50" align="right">性别：</td>
				<td><input type="radio" value="男" checked="checked" name="sex" />男 <input
					type="radio" value="女" name="sex" />女</td>
			</tr>
			<tr>
				<td height="50" align="right"><input type="submit" value="提交" />
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
