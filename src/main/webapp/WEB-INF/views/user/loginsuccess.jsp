<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/WEB-INF/include/include-resource.jsp" %>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>Welcome! ${pageContext.request.userPrincipal.name}! This is
			Login Success Page</h1>
		<a href="<c:url value='/j_spring_security_logout' />" style="color: red">
			${pageContext.request.userPrincipal.name} 로그아웃</a>
	</div>
</body>
</html>

