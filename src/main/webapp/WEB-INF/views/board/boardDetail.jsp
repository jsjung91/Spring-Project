<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/include-resource.jsp" %>
<title>게시판 상세</title>
</head>
<body>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<form id="frm" name="frm">
	<table class="board_view">
		<colgroup>
			<col width="15%">
			<col width="35%">
			<col width="15%">
			<col width="35%">
			<col width="15%">
		</colgroup>
		<caption>게시판 상세</caption>
		<tbody>
            <tr>
                <th>제목</th>
                <td>${map.subject}</td>
                <th>조회수</th>
                <td>${map.hit }</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${map.writer }</td>
                <th>작성시간</th>
                <td><fmt:formatDate value="${ map.reg_date }" pattern="yyyy.MM.dd" /></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="4" class="view_text">
                
                <c:out value="${row.fileext }" />
              	  <c:forEach var="row" items="${list }">
                	<c:if test ="${row.fileext == 'jpg' or row.fileext == 'png' or row.fileext == 'jpeg' or row.fileext == 'bmp' or
                				   row.fileext == 'JPG' or row.fileext == 'PNG' or row.fileext == 'JPEG' or row.fileext == 'BMP'}">
                		<img src ="<c:url value ="/uploadFiles/${row.storedfilename}" />" alt="첨부파일_<c:out value="${row.originalfilename }" />" width="500px" height="auto" class="att_img" />
                	</c:if>
                	<br/> 
             	  </c:forEach>
             	  
              	  ${map.content }
              	  
                </td>
            </tr>
            <c:if test="${ fn:length(list) > 0}">
            <tr>
            	<th scope="row">첨부파일</th>
            	<td colspan="3">
            		<div class="fileDiv" id="fileDiv">
            		<c:forEach items="${ list }" var="row">
            			<p>
            				<input type="hidden" id="f_idx" value="${ row.f_idx }"/>
            				<a href="#this" id="file">${ row.ori_file_name }</a>
            				<span class="fileSize">(${ row.file_size }Byte)</span>
            			</p>
            		</c:forEach>
            		</div>
            	</td>
            </tr>
            </c:if>
        </tbody>
	</table>
	<a href="javascript:fn_boardList()" class="btn" id="list">목록으로</a>
	<a href="#" class="btn" id="update">수정하기</a>
</form>
<%@ include file="/WEB-INF/include/include-body.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#list").on("click",function(e){
			e.preventDefault();
			fn_boardList();
		});
		
		$("#update").on("click",function(e){
			e.preventDefault();
			fn_boardUpdate();
		});
		
		$("a[id='file']").on("click", function(e){
			e.preventDefault();
			fn_fileDownload($(this));
		})
	});
	
	function fn_boardList(){		
		var fmSubmit = new FmSubmit();
		fmSubmit.setUrl("<c:url value='/board/boardList.do'/>");
		fmSubmit.submit();
	}
	
	function fn_boardUpdate(){
		var idx = "${ map.bno }";
		$("#frm")[0].reset();
		var fmSubmit = new FmSubmit("frm");
		fmSubmit.setUrl("<c:url value='/board/boardUpdateForm.do'/>");
		fmSubmit.addParam("bno", idx);
		fmSubmit.submit();
	}
	
	function fn_fileDownload(obj){
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("<c:url value='/common/downloadFile.do'/>");
		comSubmit.addParam("f_idx",obj.parent().find("#f_idx").val());
		comSubmit.submit();	
		comSubmit.delParam();
	}
</script>
</body>
</html>