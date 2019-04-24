<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-resource.jsp" %>
<meta charset="UTF-8">
<title>관리자 메인페이지</title>
</head>
<body>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<div class="container">
			<div class="adminMain">
				<div class="adminList">
					<ul>
					<c:choose>
						<c:when test="${fn:length(admin_menuvo) > 0 }">
							<c:forEach var="row" items="${admin_menuvo }" varStatus="var">
								<c:choose>
<%-- 									<c:when test="${row.getLeaf() == 0 && row.getDepth_1() == 0 }" > --%>
									<c:when test="${row.getDepth_1() == 0 }" >
										<li>
<%-- 											<a href="#">${row.getMenu_name()}</a> --%>
												<a href="<c:url value='${row.getMenu_url()}'/>" target="adminMainFrame">${row.getMenu_name()}</a>
											<c:forEach var="rowin" items="${admin_menuvo }" varStatus="status">
												<c:if test ="${status.first}">
													<ul>
												</c:if>	
												<c:if test ="${row.getDepth_2() eq rowin.getDepth_1() }">
													<li>
														<a href="<c:url value='${rowin.getMenu_url()}'/>" target="adminMainFrame">${rowin.getMenu_name()}</a>
													</li>
												</c:if>
												<c:if test ="${status.last}">
													</ul>
												</c:if>	
											</c:forEach>
										</li>
									</c:when>
				                  	<c:when test="${row.getLeaf() == 1 && row.getDepth_1() == 0}" >
				                  		<li>
				                  			<a href="<c:url value='${row.getMenu_url()}'/>" target="adminMainFrame" >${row.getMenu_name()}</a>
				                  		</li>
				                  	</c:when>
								</c:choose>
							</c:forEach>
						</c:when>  
					 </c:choose>
					 </ul>
				</div>
				<iframe id ="adminMainFrame" name ="adminMainFrame" class="adminMainFrame">
				</iframe>
				<div class="admin_clear">
				</div>
		</div>  
    </div>
  
<%@ include file="/WEB-INF/include/include-footer.jsp" %>

</body>
</html>