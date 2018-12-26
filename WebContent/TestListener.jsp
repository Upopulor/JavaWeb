<%@page import="cn.listener.User2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		/*request.setAttribute("name", "tom");
		request.setAttribute("name", "catter");
		request.removeAttribute("name");*/
		
		session.setAttribute("u", new User2());
	%>

</body>
</html>