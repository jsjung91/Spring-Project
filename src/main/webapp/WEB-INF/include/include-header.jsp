<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
<%@ include file="/WEB-INF/include/include-resource.jsp" %>
<div class="nav_wrapper"> 
	<div class="spinner-master">
		<input type="checkbox" id="spinner-form" />
		<label for="spinner-form" class="spinner-spin">
			<div class="spinner diagonal part-1"></div>
			<div class="spinner horizontal"></div>
			<div class="spinner diagonal part-2"></div>
		</label>
	</div>
	
	<div class="w_info">
		<span>			
			${user.getMember_name() }님 환영합니다.			
		</span>
		<button type="button" class="headerBtn" id="info_Btn">내정보</button>
		<button type="button" class="headerBtn" id="logout_Btn">Logout</button>
	</div>
	
	<nav id="menu" class="menu">
		<ul class="dropdown">
		<%-- <c:choose> --%>
			<%-- <c:when test="${ fn:length(menu) > 0"> --%>
			<li>
				<a href="/web/main.do">Home</a>
			</li>
		<c:forEach var="row" items="${ menu }" varStatus="var">			 
			<li>
				<a href="<c:url value='${ row.getMenu_url() }'/>">${ row.getCode() }</a>
			</li>
		</c:forEach>
			<c:if test="${ user.getMember_role() == 'ROLE_ADMIN' }">
				<li>
					<a href="/web/menu/getMenuList.do">관리자</a>
				</li>
				<li>
					<a href="/web/admin/member/memberInfo.do">사용자 관리</a>
				</li>
			</c:if>
			<%-- <c:when test="${fn:length(menuvo) > 0 }">
				<c:forEach var="row" items="${menuvo }" varStatus="var">
			
				</c:forEach>
			</c:when> --%>  
		 	<!-- <li>
		 		<a href="#" >사용할 수 있는 메뉴가 없습니다. <b style="font-size: 9px; color: red;">관리자에게 문의하세요</b></a>
		 	</li> -->
		
			
		
		 </ul>
	</nav>
</div>       
<form id="logoutForm" name="logoutForm"></form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#logout_Btn").on("click", function(){
			var fmSubmit = new FmSubmit("logoutForm");
			fmSubmit.setUrl("<c:out value = '${contextPath}/j_spring_security_logout'/>");
			fmSubmit.submit();
		});
		
		$("#info_Btn").on("click", function(){
			var popUrl = "<c:url value='/main/userInfoPop.do'/>";
			var popOption = "width=420px, height=295px, resizable=no, location=no, top=300px, left=300px;"
			window.open(popUrl, "정보확인", popOption);
		});
	});
	
	function logout() {
		$("#logout_Btn").trigger("click");
	}
</script>
<script src="<c:url value='/resources/js/common/menu.js'/>" charset="utf-8"></script>
</header> 