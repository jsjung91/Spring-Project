<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<%@ include file="/WEB-INF/include/include-resource.jsp" %>
<body>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
	 <form id="frm" enctype="multipart/form-data">
          <table class="board_view">
          	<colgroup>
          		<col width="15%">
          		<col width="*">
          	</colgroup>
     	    <caption>게시글 작성</caption>
     	    <tbody>
     	    	<tr>
     	    		<th scope="row">제목</th>
     	    		<td><input type="text" id="subject" name="subject" class="wdp_90"></input></td>
     	    	</tr>
     	    	<tr>
     	    		<th scope="row">작성자</th>
     	    		<td><input type="text" id="writer" name="writer" class="wdp_90"></input></td>
     	    	</tr>  
          		<tr>
          			<td colspan="2" class="view_text">
          				<textarea rows="20" cols="100" title="내용" id="content" name="content"></textarea>
          			</td>
          		</tr>   	      
	            <tr>
	            <th scope="row">첨부파일</th>
	              <td colspan="3">
	              	<div class="fileDiv" id="fileDiv">
	              		<p>
        					<input type="file" name="file_0"/>
        					<a href="#this" name="delete" class="btn">삭제하기</a>
        				</p>
        			</div>
	              </td>
	            </tr>
          	</tbody>
        </table> 
       
        
        <a href="#this" id="file_add" class="btn">파일 추가</a>
        <a href="#this" class="btn" id="write">작성하기</a>
		<a href="#this" class="btn" id="list">목록으로</a>
     </form>
    
<%@ include file="/WEB-INF/include/include-body.jsp" %>
	<script type="text/javascript">
		var g_count = 1;
		$(document).ready(function(){
			$("#list").on("click", function(e){
				e.preventDefault();
				fn_boardList();
			});
			
			$("#write").on("click", function(e){
				e.preventDefault();
				fn_boardInsert();
			});
			
			$("a[name='delete']").on("click", function(e){
				e.preventDefault();
				fn_fileDelete($(this));
			});
			
			$("#file_add").on("click", function(e){
				e.preventDefault();
				fn_fileAdd();
			});
		});
		
		function fn_boardList(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/board/boardList.do'/>");
			comSubmit.submit();
		}
		
		function fn_boardInsert(){
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/board/boardInsert.do'/>");
			comSubmit.submit();
		}
		
		function fn_fileDelete(obj){
			obj.parent().remove();
		}
		
		function fn_fileAdd(){
			var str = "<p><input type='file' name='file_"+(g_count++)+"'/><a href='#this' name='delete' class='btn'>삭제하기</a></p> ";
			$("#fileDiv").append(str);
			
			$("a[name='delete']").on("click", function(e){
				e.preventDefault();
				fn_fileDelete($(this));
			});
		}
	</script>
</body>
</html>