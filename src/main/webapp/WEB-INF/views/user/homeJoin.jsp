<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/include-resource.jsp" %>
<title>회원가입</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#join_btn").on("click", function(){
			if(fieldNullCheck('member_join') == true && $("#member_pwd").val() != "비밀번호" && $("#member_id").val() != "아이디" && $("#member_name").val() != "이름" && $("#member_mail").val() != "이메일" && $("#member_phon").val() != "핸드폰"){
				var sendData = JSON.stringify({member_id:$("#member_id").val(), member_pwd : $("#member_pwd").val(), member_name : $("#member_name").val(), member_mail : $("#member_mail").val(), member_phon : $("#member_phon").val() });
				alert("sendData : " + sendData);
				$.ajax({
					type:"POST",
					url:"<c:url value='/join/memberInsert.do'/>",
					data:sendData,
					dataType: "text",
					contentType:"application/json;charset=UTF-8",
					success : function(data, status, xhr){
						if(data == 1){
							alert("회원 가입이 완료 되었습니다.");
							self.close();							
						}						
					},
					error : function(jqXHR, textStatus, errorThrown){						
						alert("실패 : " + jqXHR.responseText);
						/* window.location.href = "<c:url value='/common/503error.do'/>"; */
						self.close();
					}
				});				
			}else{
				alert("빈칸을 입력하세요");
			}
		});
		
		$("#cancle_btn").on("click", function(){
			self.close();
		});
		
		$("#member_id").on("focus", function(){
			if($(this).val() != "아이디"){
				$(this).removeClass("input_pre");
			}else{
				$(this).val("").removeClass("input_pre");
			}
		}).on("blur", function(){
			if($(this).val() == "" || $(this).val() == null){
				$(this).val("아이디");
				$(this).addClass("input_pre");
			}
		});
		$("#member_name").on("focus", function(){ 
			if($(this).val() != "이름"){ 
				$(this).removeClass("input_pre"); 
			}else{ 
				$(this).val("").removeClass("input_pre"); 
			}   
		}).on("blur", function() { 
			if($(this).val() == "" || $(this).val() == null ){ 
				$(this).val("이름"); $(this).addClass("input_pre"); 
			} 
		});
		$("#member_pwd").on("focus", function(){ $(this).prop("type","password"); if($(this).val() != "비밀번호"){ $(this).removeClass("input_pre"); }else{ $(this).val("").removeClass("input_pre"); } }).on("blur", function() { if($(this).val() == "" || $(this).val() == null ){ $(this).addClass("input_pre").prop("type","text"); $(this).val("비밀번호"); } });
		$("#member_mail").on("focus", function(){ 
			if($(this).val() != "이메일"){ 
				$(this).removeClass("input_pre"); 
			}else{ 
				$(this).val("").removeClass("input_pre"); 
			}   
		}).on("blur", function() { 
			if($(this).val() == "" || $(this).val() == null ){ 
				$(this).val("이메일"); $(this).addClass("input_pre"); 
			} 
		});
		$("#member_phon").on("focus", function(){ 
			if($(this).val() != "핸드폰"){ 
				$(this).removeClass("input_pre"); 
			}else{ 
				$(this).val("").removeClass("input_pre"); 
			}   
		}).on("blur", function() { 
			if($(this).val() == "" || $(this).val() == null ){ 
				$(this).val("핸드폰"); $(this).addClass("input_pre"); 
			} 
		});
		$(document).keydown(function(e) {

	 		  // Set self as the current item in focus
	 		  var self = $(':focus'),
	 		      // Set the form by the current item in focus
	 		      form = self.parents('form:eq(0)'),
	 		      focusable;

	 		  // Array of Indexable/Tab-able items
	 		  focusable = form.find('input,a,select,button,textarea,div[contenteditable=true]').filter(':visible');

	 		  function enterKey(){
	 		    if (e.which === 13 && !self.is('textarea,div[contenteditable=true]') && !self.is('a')) { // [Enter] key

	 		      // If not a regular hyperlink/button/textarea
	 		      if ($.inArray(self, focusable) && (!self.is('a,button'))){
	 		        // Then prevent the default [Enter] key behaviour from submitting the form
	 		        e.preventDefault();
	 		      } // Otherwise follow the link/button as by design, or put new line in textarea

	 		      // Focus on the next item (either previous or next depending on shift)
	 		      focusable.eq(focusable.index(self) + (e.shiftKey ? -1 : 1)).focus();

	 		      return false;
	 		    }
	 		  }
	 		  // We need to capture the [Shift] key and check the [Enter] key either way.
	 		  if (e.shiftKey) { enterKey() } else { enterKey() }
	 		});
	});
</script>
</head>
<body>
<%@ include file="/WEB-INF/include/include-body.jsp" %>
<form id="member_join" name="member_join" action="" method="post">
	<div class="join_main" align="center">
		<div class="login_con">
			<input type="text" id="member_id" required="required" class="login_txt input_pre" maxlength="30" value="아이디" />
		</div>
		<div class="login_con">
			<input type="text" id="member_name" name="member_name" required="required" class="login_txt input_pre" maxlength="30" value="이름" />
		</div>
		<div class="login_con">
			<input type="text" id="member_pwd" name="member_pwd" required="required" class="login_txt input_pre" maxlength="30" value="비밀번호" />
		</div>
		<div class="login_con">
			<input type="text" id="member_mail" name="member_mail" required="required" class="login_txt input_pre" maxlength="60" value="이메일" />
		</div>
		<div class="login_con">
			<input type="text" id="member_phon" name="member_phon" required="required" class="login_txt input_pre" maxlength="45" value="핸드폰" />
		</div>
	</div>
	
	<div align="center">
		<button type="button" id="join_btn" class="infoBtn">가입하기</button>
		<button type="button" id="cancle_btn" class="infoBtn">취소</button>
	</div>
</form>
</body>
</html>