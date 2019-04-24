<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/include/include-resource.jsp" %>
<title>게시글 목록</title>

</head>
<body>
<%@ include file="/WEB-INF/include/include-header.jsp" %>
<div class="container">
	<div align="center">
		<c:forEach var="rowin" items="${ menuVo }" varStatus="status">
			<c:if test="${ rowin.getMenu_id() eq cur_menu_id }">
				<c:set var="read_grant" value="${ rowin.getRead_grant() }"></c:set>
			</c:if>
		</c:forEach>
<p> 총 ${ map.count } 개의 게시물이 있습니다.</p>
<table width="700" align="center">
		<tr>
			<td>
				<table width="690" height="50" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td><img src="../resources/img/title_04.gif"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="690" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<table width="690" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="50" class="td_a">No.</td>
									<td width="2" class="td_b"><img src="../resources/img/td_bg_01.gif"></td>
									<td class="td_b" width="300">제목</td>
									<td width="2" class="td_b"><img src="../resources/img/td_bg_01.gif"></td>
									<td width="90" class="td_b">작성자</td>
									<td width="2" class="td_b"><img src="../resources/img/td_bg_01.gif"></td>
									<td width="90" class="td_b">작성일</td>
									<td width="2" class="td_b"><img src="../resources/img/td_bg_01.gif"></td>
									<td width="60" class="td_c">조회수</td>
								</tr>
								<!-- 데이터가 있을 경우 -->
								<!--  for(BoardVo vo : list)  -->
								<c:forEach var="vo" items="${ map.list }">
									<tr>
										<td align="center" class="td_a_1">${ vo.bno }</td>
										<td class="td_b_1"><img src="../resources/img/td_bg_02.gif"></td>
										<td class="td_b_1 left">											
											<%-- href="/board/${ vo.idx }&page=${ (param.page==null || param.page=='' ) ? 1 : param.page }&search=${ param.search}&search_text=${ param.search_text}" --%>
											<a href="#this" id="subject" style="text-decoration: none; color: red;">${ vo.subject }</a>
											<%-- <a href="javascript:fn_boardDetail('${ vo.bno }', '${ map.paging.curPage }')" id="subject" style="text-decoration: none; color: red;">${ vo.subject }</a> --%>
											<input type="hidden" id="bno" value="${ vo.bno }">
										</td>
										<td class="td_b_1"><img src="../resources/img/td_bg_02.gif"></td>
										<td align="center" class="td_b_1">${ vo.writer }</td>
										<td class="td_b_1"><img src="../resources/img/td_bg_02.gif"></td>
										<td align="center" class="td_b_1">
										<fmt:formatDate value="${ vo.reg_date }" pattern="yyyy.MM.dd" /></td>
										<td class="td_b_1"><img src="../resources/img/td_bg_02.gif"></td>
										<td align="center" class="td_c_1">${ vo.hit }</td>
									</tr>
										
								</c:forEach>
										
								<!-- 게시물이 없는경우 -->
								<c:if test="${ empty map.list }">
									<tr>
										<td align="center" colspan="11" width="100%" height="50"
											style="border: 1 solid #efefef">현재 등록된 글이 없습니다.</td>
									</tr>
								</c:if>
							</table>
						</td>
					</tr>
					<tr>
						<td height="8"></td>
					</tr>
					<tr>
						<td>
							<table width="690" border="0" cellpadding="0" cellspacing="0" bgcolor="#F1F5F4">
								<tr>
									<td width="7"><img src="../resources/img/search_bg_01.gif"></td>
									<td class="f11" align="center">
										<!-- 검색메뉴 -->
										<div>
											<select id="select_searchType" name="searchType">
											  <option value="all" selected="selected">::::전체보기::::</option>
												<option value="subject">제목</option>
												<option value="writer">작성자</option>	
																							
											</select> 
											<input type="text" id="searchText" name="searchText" value="${ map.searchText }">
											<a href="javascript:fn_search()" class="btn">검색</a>
										</div>										 
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
									<c:if test="${ map.paging.curPage > 1}">
										<a href="javascript:fn_list('1')">[처음]</a>
									</c:if>
									<!-- 이전 페이지 블록으로 이동 : 현재 페이지 블록이 1보다 크면 [이전]을 출력 -->
									<c:if test="${ map.paing.curBlock > 1 }">
										<a href="javascript:fn_list('${ map.paging.prevPage }')">[이전]</a>
									</c:if>
									<!-- 하나의 블럭에서 반복문 수행 시작페이지부터 끝페이지까지 -->
									<c:forEach var="num" begin="${ map.paging.blockBegin }" end="${ map.paging.blockEnd }">
									<!-- 현재 페이지이면 하이퍼링크 제거 -->
									<c:choose>
										<c:when test="${ num == map.paging.curPage }">
										 <span style="color: red">${ num }</span>&nbsp;
										</c:when>
										<c:otherwise>
											<a href="javascript:fn_list('${ num }')">${ num }</a>&nbsp;
										</c:otherwise>
									</c:choose>
									</c:forEach>
									<!-- 다음 페이지 블록으로 이동 : 현재 페이지 블록이 전체 페이지 블록보다 작거나 같으면 [다음] 출력  -->
									<c:if test="${ map.paging.curBlock <= map.paging.totalBlock }">
										<a href="javascript:fn_list('${map.paging.nextPage }')">[다음]</a>
									</c:if>
									<!-- 끝 페이지로 이동 : 현재 피이지가 전체 페이지보다 작으면[끝] 출력 -->
									<c:if test="${ map.paging.curPage < map.paging.totalPage }">
										<a href="javascript:fn_list('${ map.paging.totalPage }')">[끝]</a>
									</c:if>
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>
					<tr><!-- onClick="location.href='/web/board/boardInsert.do'" -->
						<td>
							<!-- 등록 버튼 이미지 --> 
							<img src="../resources/img/btn_reg.gif" id="write" onClick="location.href='/web/board/boardInsertForm.do'" style="cursor:pointer">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</div>
</div>
	<%@ include file="/WEB-INF/include/include-body.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$("#write").on("click", function(e){
		e.preventDefault();
		fn_boardWrite();
	});
		
	 $("a[id='subject']").on("click", function(e){
		e.preventDefault();
		fn_boardDetail($(this));
	}); 
});
	
	function fn_boardWrite(){		
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("<c:url value='/board/boardInsertForm.do'/>");
		comSubmit.submit();
	}
	
	function fn_boardDetail(val){
		var comSubmit = new ComSubmit();		
		comSubmit.setUrl("<c:url value='/board/boardDetail.do'/>");
		comSubmit.addParam("bno",val.parent().find("#bno").val());
		comSubmit.submit();
	} 
	
/* 	function fn_PageSearch(pageNo){
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("<c:url value='/board/boardList.do'/>");
		comSubmit.addParam("currentPageNo", pageNo);
		comSubmit.submit();
	} */
	
	/* function fn_boardDetail(bno, curPage) {
		window.location.href="/web/board/boardDetail.do?bno="+bno+"&curPage="+curPage;
	} */
	
	function fn_list(page) {
		 window.location.href = "/web/board/boardList.do?curPage=" + page;
	}
	
	function fn_search(){
		var searchType = document.getElementById("select_searchType").value;
		var searchText = document.getElementById("searchText").value;
		
		window.location.href="/web/board/boardList.do?curPage=1&searchType=" + searchType + "&searchText=" + encodeURIComponent(searchText, "UTF-8");
	}
	
</script>
</body>
</html>
    