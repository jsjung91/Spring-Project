<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/include-resource.jsp" %>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="modal-dialog">

			<div>
				<h1 style="font-size: x-large;">
					Spring Security<small>v.인메모리</small>
				</h1>
			</div>

		<!-- Modal content-->
		<div class="modal-content">

			<div class="modal-header" style="padding: 35px 50px;">
				<h4>
					<span class="glyphicon glyphicon-lock"></span> 로그인
				</h4>
				<label>로그인에 실패했습니다.</label>
			</div>
			떠야 <c:if test="${ not empty errormsg }">
			<p style="color:red; font-weight: bolder;"> ${errormsg} 때문에 로그인에 실패하셨습니다.</p>
			</c:if>
			<div class="modal-body" style="padding: 40px 50px;">
					<div class="form-group">
					</div>
					<a href="/web/join/login.do" class="btn btn-default btn-block">Re Login</a>
			</div>
			<div class="modal-footer"></div>
		</div>

	</div>
</body>
</html>