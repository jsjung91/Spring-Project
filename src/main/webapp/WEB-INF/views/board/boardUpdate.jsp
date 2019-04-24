<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<title>수정 게시판</title>
</head>
<body>
	<form id="frm" enctype="multipart/form-data">
		<table class="board_view">
			<caption>글 수정하기</caption>
			<colgroup>
				<col width="15%">
				<col width="*">
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">글 번호</th>
					<td>${ map.bno }</td>
					<th scope="row">조회수</th>
					<td>${ map.hit }</td>
				</tr>
				<tr>
					<th scope="row">작성자</th>
					<td>${ map.writer }</td>
					<th scope="row">작성시간</th>
					<td>${ map.reg_date }</td>
				</tr>
				<tr>
					<th scope="row">제목</th>
					<td colspan="3">
						<input type="text" id="subject" name="subject" class="wdp_90" value="${ map.subject }"/>
					</td>
				</tr>
				<tr>
					<th scope="row">내용</th>
					<td colspan="3" class="content">
						<textarea rows="20" cols="100" title="내용" id="content" name="content">${ map.content }</textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3">
						<div class="fileDiv" id="fileDiv">
							<c:forEach items="${ list }" varStatus="var" var="row">
								<p>
                                    <a href="#this" name="name_${row.f_idx }" id="name_${row.f_idx }">${row.ori_file_name }</a>
                                    <span class="filesize">(${row.file_size }kb)</span>
                                    <label id="lab_file_${ row.f_idx }" for="file_${ row.f_idx }">+</label>                                    
                                    <input type="file" id="file_${ row.f_idx }" name="file_${row.f_idx }">
                                    <a href="#this" class="btn" id="delete_${row.f_idx }" name="delete_${row.f_idx }">삭제하기</a>
                                </p>
                            </c:forEach>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" id="bno" name="bno" value="${ map.bno }"/>
	</form>
	<a href="#this" id="list" class="btn">목록으로</a>
	<a href="#this" id="addFile" class="btn">파일 추가</a>
	<a href="#this" id="update" class="btn">수정하기</a>
	<a href="#this" id="delete" class="btn">삭제하기</a>
	
<%@ include file="/WEB-INF/include/include-body.jsp" %>
<script type="text/javascript">
	var gfv_count = '${fn:length(list) +1}';
	
	$(document).ready(function(){
		$("#list").on("click", function(e){
			e.preventDefault();
			fn_boardList();
		});
		
		$("#update").on("click", function(e){
			e.preventDefault();
			fn_updateBoard();
		});
		
		$("#delete").on("click", function(e){
			e.preventDefault();
			fn_deleteBoard();
		});
		
		$("#addFile").on("click", function(e){
			e.preventDefault();
			fn_fileAdd();
		});
		
		$("a[name^='delete']").on("click", function(e){
			e.preventDefault();
			fn_deleteFile($(this));
		});
	});
	
	function fn_boardList(){
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("<c:url value='/board/boardList.do'/>");
		comSubmit.submit();
	}
	
	function fn_updateBoard(){
		var comSubmit = new ComSubmit("frm");
		comSubmit.setUrl("<c:url value='/board/boardUpdate.do'/>");
		comSubmit.submit();
	}
		
	function fn_deleteBoard(){
		var comSubmit = new ComSubmit("frm");
		if(confirm("정말 삭제하시겠습니까?")){
		comSubmit.setUrl("<c:url value='/board/boardDelete.do'/>");
		comSubmit.submit();
		}
	}
	
	function fn_fileAdd(){
		var str = "<p><label id='lab_file_"+(gfv_count)+"' for='file_${row.f_idx }'>+</label>" +
        "<input type='file' id='file_"+(gfv_count)+"' name='file_"+(gfv_count)+"' onchange='filenameView(this.id)'>"+
        "<a href='#this' class='btn' id='delete_"+(gfv_count)+"' name='delete_"+(gfv_count)+"'>삭제하기</a>" +
    	"</p>";
    	$("#fileDiv").append(str);
    	$("#file_"+(gfv_count)).trigger("click");
    	$("#delete_"+(gfv_count++)).on("click", function(e){
    		e.preventDefault();
    		fn_fileDelete($(this));
    	});
	}
	
	function fn_fileDelete(obj){
		obj.parent().remove();
	}
</script>
</body>
</html>