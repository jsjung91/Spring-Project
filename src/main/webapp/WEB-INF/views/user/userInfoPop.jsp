<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-resource.jsp" %>
<meta charset="UTF-8">
<title>사용자 정보</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn_close").on("click", function(){
			self.close();
		});
		
		$("#btn_unLink").on("click", function(){
			var type = "${ user.getMember_type() }";
		})
	})
</script>
</head>
<body>
<%@ include file="/WEB-INF/include/include-body.jsp" %>
<div>
	<div class="authManagerAdd" align="center">
		<c:if test="${ user.getMember_role() ne 'ROLE_ADMIN' }">
			<button type="button" id="btn_unLink" name="btn_unLink" class="infoBtn">탈퇴</button>
		</c:if>
		<button type="button" id="btn_close" name="btn_close" class="infoBtn">닫기</button>
		
		<form id="formCodeList" name="formCodeList">
		<table class="tb_authDetailPopList" id="tb_authDetailPopList">
			<colgroup>
				<col width="20%"/>
				<col width="*"/>
			    <col width="20%"/>
			    <col width="*"/>
			</colgroup>
			<tbody>
				<tr>
					<th scope="col">아이디</th>
					<td scope="col">${user.getMember_id() }</td>
					<th scope="col">이름</th>
					<td scope="col">${user.getMember_name() }</td>
				</tr>
				<tr>
					<th scope="col">권한</th>
					<td scope="col">${user.getMember_role() }</td>
					<th scope="col">이메일</th>
					<td scope="col">${user.getMember_mail() }</td>
				</tr>				
			</tbody>
		</table>
		</form>
		<br/>
	</div>
</div>
</body>
</html>