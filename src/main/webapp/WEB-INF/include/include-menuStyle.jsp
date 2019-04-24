<%@ page pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!-- <!DOCTYPE html> -->
<!-- <meta charset="utf-8"> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<%@ page import="javax.servlet.http.*" %>
<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>

<script src="<c:url value='/resources/js/jquery-1.7.2.min.js'/>" charset="utf-8"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

