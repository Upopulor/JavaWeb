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
	    //1 --null
		String  ss = (String) pageContext.getAttribute("ss");
		out.print(ss);
		//2 --sss
		out.print(request.getAttribute("ss"));
	%>
	<%--//3 session --%>
	<%=session.getAttribute("pp") %>
	<%--//4 application --%>
	<%=application.getAttribute("ss") %>
</body>
</html>