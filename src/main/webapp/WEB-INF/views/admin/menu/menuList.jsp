<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/include/include-menuStyle.jsp"%>
<c:url var = "saveURL" value="/menu/saveMenu.do"></c:url>
<c:url var = "deleteURL" value="/menu/deleteMenu.do"></c:url>
<c:url var = "updateURL" value="/menu/updateMenu.do"></c:url>
<c:url var = "getMenuListURL" value="/menu/getMenuList.do"></c:url>
<title>Menu List</title>
</head>
<script type="text/javascript">
	$(function(){
		fn_showList();
	});
	
	function fn_showList(){
		var paramData = {};
		
		$.ajax({
			url : "${getMenuListURL}"
			, type: "POST"
			, dataType : "json"
			, data : paramData
			, success : function(result){
				console.log(result);
				
				if(result.status == "OK"){
					if(result.menuList.length > 0) {
						var list = result.menuList;
						var htmls = "";
						result.menuList.forEach(function(e){
							htmls += '<tr>';
							htmls += '<td>' + e.m_id + '</td>';
							htmls += '<td>' + e.code + '</td>'; 
							htmls += '<td>' + e.codename + '</td>'; 
							htmls += '<td>' + e.sort_num + '</td>'; 
							htmls += '<td>' + e.comment + '</td>'; 
							htmls += '</tr>';
						});
					}
				} else {
					console.log("조회 실패");
				}
				$('#menuList').html(htmls);					
			}
		});
	}
	
	$(document).ready(function(){
		$('#btnSave').on("click", function(e){
			e.preventDefault();
			
			var url = "${saveURL}";
			
			var paramData = {
					"code" : $("#code").val()
					, "code_name" : $("#code_name").val()
					, "sort_num" : $("#sort_num").val()
					, "comment" : $("#comment").val()
			};
			
			$.ajax({
				url : url,
				type : "POST",
				dataType : "json",
				data : paramData,
				success : function(result){
					fn_showList();
				
					$("#btnInit").trigger("click");
				}
			});
		});
		
		$('#btnInit').on("click", function(e){
			document.getElementById("form").reset();
		});
	});
</script>
<body>
	<article>
		<div class="container">
			<!-- Menu form {s} -->
			<h4 class="mb-3">Menu Info</h4>
			<div>
				<form name="form" id="form" role="form" modelAttribute="Menu"
					method="post" action="/web/menu/saveMenu.do">
					<input type="hidden" path="m_id" id="m_id" />
					<div class="row">
						<div class="col-md-4 mb-3">
							<label for="code">Code</label>
							<input type="text" path="code" id="code" class="form-control"
								value="" required="" />
							<div class="invalid-feedback">Valid Code is required.</div>
						</div>
						<div class="col-md-5 mb-3">
							<label for="code_name">Code name</label>
							<input type="text" path="code_name" class="form-control" id="code_name"
								 value="" required="" />
							<div class="invalid-feedback">Valid Code name is required.
							</div>
						</div>
						<div class="col-md-3 mb-3">
							<label for="sort_num">Sort</label>
							<input type="text" path="sort_num" class="form-control" id="sort_num"
								 required="" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 mb-3">
							<label for="comment">Comment</label>
							<input type="text" path="comment" class="form-control" id="comment"
								value="" required="" />
						</div>
					</div>
				</form>
			</div>
			<!-- Menu form {e} -->
			<div>
				<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnInit">초기화</button>
			</div>
			<h4 class="mb-3" style="padding-top: 15px">Menu List</h4>
			<!-- List{s} -->
			<div class="table-responsive">
				<table class="table table-striped table-sm">
					<colgroup>
						<col style="width: 10%;" />
						<col style="width: 15%;" />
						<col style="width: 15%;" />
						<col style="width: 10%;" />
						<col style="width: auto;" />
					</colgroup>
					<thead>
						<tr>
							<th>menu id</th>
							<th>code</th>
							<th>codename</th>
							<th>sort</th>
							<th>comment</th>
						</tr>
					</thead>
					<tbody id="menuList">
						<c:forEach var="menu" items="${ result.menuList }">
						<tr>
							<th>${ menu.m_id }</th>
							<th>${ menu.code }</th>
							<th>${ menu.code_name }</th>
							<th>${ menu.sort_num }</th>
							<th>${ menu.comment }</th>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- List{e} -->
		</div>
	</article>

</body>
</html>