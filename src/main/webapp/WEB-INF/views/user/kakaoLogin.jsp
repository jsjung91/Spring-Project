<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/include/include-resource.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		var childWindow = window.parent;
		var parentWindow = childWindow.opener;
		var member = new Objcet();
		
		member["id"] = "${ membervo.getMember_id() }";
		member["pwd"] = "${ membervo.getMember_pwd() }";
		
		childWindow.opener.kakaoLogin(member);
		childWindow.close();
		
	});
</script>