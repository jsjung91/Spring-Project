<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="<c:url value='/js/jquery-1.7.2.min.js'/>" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var msgg = "${ msg }";
		var url = "${ url }";
		alert(msgg);
		window.location.href = url;
	});
</script>
</body>
</html>