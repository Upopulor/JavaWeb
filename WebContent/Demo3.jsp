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
	    //1  --null
		pageContext.setAttribute("ss", "sss");
		//等于上一句pageContext.setAttribute("ss", "sss",PageContext.PAGE_SCOPE);
		//request.getRequestDispatcher("/Demo4.jsp").forward(request, response);
		//2  --sss 
		//pageContext.setAttribute("ss", "sss",PageContext.REQUEST_SCOPE);
		//等于上一句，直接使用request，相当于设置scope了 request.setAttribute("ss","sss");
		request.setAttribute("ss","request");
		//request.getRequestDispatcher("Demo4.jsp").forward(request, response);
		//response.sendRedirect(request.getContextPath()+"/Demo4.jsp"); //如果使用了重定向，那么将取不到
		//3  --session
		//pageContext.setAttribute("ss", "sss",PageContext.SESSION_SCOPE);
		session.setAttribute("ss", "session");
		//request.getRequestDispatcher("Demo4.jsp").forward(request, response);
		//response.sendRedirect(request.getContextPath()+"/Demo4.jsp"); //如果使用了重定向，能取到
		//4
		application.setAttribute("ss", "application");
		//request.getRequestDispatcher("Demo4.jsp").forward(request, response);
		//response.sendRedirect(request.getContextPath()+"/Demo4.jsp"); //如果使用了重定向，能取到
		//如果不设置转发或者重定向，那么只有session和application可以取到值
	%>
	${ss}
</body>
</html>