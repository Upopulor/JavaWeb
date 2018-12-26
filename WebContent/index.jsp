<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
  </head>
  
  <body>
  	<%
  		String msg = (String)request.getAttribute("msg");
  		if(msg!=null){
  			out.print(msg);
  		}
  	 %>
  	<form action="/Servlet1/Demo6.jsp" method="post">
		用户名：<input type="text" name="userName"/><br>
		密码：<input type="password" name="pwd"/><br>
		验证码：<input type="text" name="code"/>
		<img src="/Servlet1/CodeSession" onclick="changeCode()"/><a href="javascript:changeCode()" >看不清换一张</a><br>
		<input type="submit" value="登录"/><br>
	</form>
  </body>
</html>
