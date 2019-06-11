<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인 페이지</title>
<%@ include file="/WEB-INF/include/include-resource.jsp" %>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#loginid").focus();
		$("#login_btn").on("click", function(){
			var f = document.login_member;
			if(f.loginid.value == ''){
				alert("아이디를 입력하세요.");
				f.loginid.focus();
				return false;
			}
			if(f.loginpwd.value == ''){
				alert("비밀번호를 입력하세요.");
				f.loginpwd.focus();
				return false;
			}
			form_submit();
		});
		
		$("#kakoAuth").on("click", function(){
			kakaoAuth("join");
		});
		
		$("#kakaoMember").on("click", function(){
			kakaoAuth("login");
		});
		
		// 로그아웃
		$("#kakaoLogout").on("click",function(){ Kakao.Auth.logout(function(){ }); });
		
		// 연결상태
		$("#kakaoStatus").on("click",function(){ Kakao.Auth.getStatus(function(statusObj){ });});

		// 앱연결 해제
		$("#kakaoDrop").on("click",function(){ 
			Kakao.Auth.login({ persistAccessToken: true, persistRefreshToken: true, success: function(authObj) { kakaoDrop(authObj.access_token); }, fail: function(err) { console.log(err); } }); 
			
		});
		
	});
	
	function kakaoAuth(state){
		var url = "https://kauth.kakao.com/oauth/authorize?client_id=${kakaoRestApi}&redirect_uri=${kakaoRdUrl }&response_type=code&state="+state+"&encode_state=true";
		var popOption = "width=460px, height=608px, resizable=yes, scrollbars=no, location=no, top=300px, left=300px;"
		window.open(url, "카카오톡", popOption);
	}
	
	function kakaoLogin(member) {
		$("#loginid").prop("type", "password").val(member.id);
		$("#loginpwd").prop("type", "password").val(member.id);
		
		form_submit();
	}
	
	function form_submit(){
		$("#login_member").submit();
	}
	
	function fn_register(val){
		if(val == 'join'){			
			displaySet("join_div", "block");
		}
	}
	
	function homeJoin(){
		var url = "<c:url value='/join/memberRegister.do'/>";
		var popOption = "width=460px, height=300px, resizable=yes, scrollbars=no, location=no, top=300px, left=300px;"
		window.open(url, "회원가입", popOption);
	}
	
	
</script>
<body>

<div class="modal-dialog">
	<!-- Modal content-->
	<div class="modal-content">
		<div class="modal-header" style="padding: 35px 50px;">
			<h4>
				<span class="glyphicon glyphicon-lock"></span> 로그인
			</h4>
				<label>정상적인 서비스 사용을 위해서 로그인을 해야 합니다.</label>
		</div>
		<div class="modal-body" style="padding: 40px 50px;">
			<form role="form" id="login_member" name="login_member" method="post" action="<c:url value='/j_spring_security_check' />">
				<div class="form-group">
					<label for="id"> ID :</label>
					 <input type="text" class="form-control" id="loginid" name="loginid" placeholder="Enter ID">
				</div>
				<div class="form-group">
					<label for="pw"> Password :</label> 
					<input type="password" class="form-control" id="loginpwd" name="loginpwd" placeholder="Enter Password">
				</div>
			</form>
				<button id="login_btn" class="btn btn-default btn-block">로그인</button>
				<a href="javascript:;" id="kakoMember" class="login_btn"><img src="<c:url value='/resources/img/kakao_account_login_btn.png'/>" alt="카카오톡으로 시작하기"/></a>
				<button id="register_btn" onclick="fn_register('join');" class="btn btn-default btn-block">회원가입</button>						
		</div>
	</div>
</div>
			<div align="center">
				<div id="join_div" class="wrap_join displayNone" align="center">
					<div class="wrap_join_inner">
						<ul>
							<li id="homeJoin"><a href="javascript:homeJoin();" id="homeJoin">회원 가입</a>
						</ul>
					</div>
				</div>
			</div>
		<div class="modal-footer"></div>		
		<c:if test="${ not empty ERRORMSGS }">
			<p style="color:red; font-weight: bolder;"> ${ERRORMSGS} 때문에 로그인에 실패하셨습니다.</p>
		</c:if>
</body>
</html>