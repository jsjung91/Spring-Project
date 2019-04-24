<%@ page pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!-- <!DOCTYPE html> -->
<!-- <meta charset="utf-8"> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>프로젝트</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/ui.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common.css'/>" />
<%@ page import="javax.servlet.http.*" %>
<c:set var="contextPath" value="<%= request.getContextPath()%>"></c:set>

<script src="<c:url value='/resources/js/common.js'/>" charset="utf-8"></script>
<script src="<c:url value='/resources/js/jquery-1.7.2.min.js'/>" charset="utf-8"></script>