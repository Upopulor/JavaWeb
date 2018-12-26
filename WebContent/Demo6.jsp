<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<!-- 注意，这里是parameter，不是Attribute，所以不能直接使用userName取 -->
	欢迎你：${param.userName } <!-- ==request.getParameter("userName"); -->
	${pramValues.hobby[0]}<br/><!--   取数组-->
	${header["User-agent"] }<br/>
	${cookie.JSESSIONID.value}
</body>
</html>