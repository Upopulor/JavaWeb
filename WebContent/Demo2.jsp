<%@page import="cn.jsp.Student"%>
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
		Student ss = new Student();
		ss.setName("tom");
		out.print(ss.getName());
		
		//request.getRequestDispatcher("index2.jsp");
	%>
	<%--但是上面的太过于复杂，对于前端人员，故使用以下 --%>
	<jsp:useBean id="ss1" class="cn.jsp.Student"></jsp:useBean>
	<jsp:setProperty property="name" name="ss1" value="jerry"/>
	<jsp:getProperty property="name" name="ss1"/>
	
	<%--<jsp:forward page="index2.jsp"></jsp:forward> --%>
	<%--http://localhost:8080/Servlet1/Demo2.jsp?name=123 --%>
	<jsp:forward page="index2.jsp">
		<jsp:param value="123" name="name"/>
	</jsp:forward>
</body>
</html>