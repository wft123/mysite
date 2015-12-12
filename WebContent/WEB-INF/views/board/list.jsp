<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById('header').onclick = function() {
			alert(this.value);
			header.onclick = null;
		}
	};
</script>
<style type="text/css">
.title-td {
	text-align:left;
}	
</style>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board" method="post">
					<input type="hidden" name="pg" value="${param.pg }">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					
					<c:forEach var="vo" items="${list }" varStatus="status">
					<tr>
						<td>${boardSize-(status.index+((param.pg-1)*pageSize)) }</td>
						<td class="title-td"><a href="/mysite/board?a=view&no=${vo.no }">
							<c:if test="${vo.order_no>1 }">
								<c:forEach begin="1" end="${vo.depth }">
									&nbsp;
								</c:forEach>
								<img src="/mysite/assets/images/ico-reply.gif">
							</c:if>
							${vo.title }
						</a></td>
						<td>${vo.member_name }</td>
						<td>${vo.view_cnt }</td>
						<td>${vo.reg_date }</td>
						<td>
						<c:if test="${vo.member_no==authUser.no }">
							<a href="/mysite/board?a=delete&no=${vo.no }" class="del">삭제</a>
						</c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
				<c:set var="pages" value="${boardSize/pageSize}" />
				<c:set var="prev" value="1"/>
				<div class="pager">
					<ul>
						<c:if test="${param.pg > 1 }"> <c:set var="prev" value="${param.pg-1 }"/>	</c:if>
						<li class="pg-prev"><a href="/mysite/board?pg=${prev }">◀ 이전</a></li>
						<c:forEach begin="1" end="${pages+(1-(pages%1))%1}" varStatus="status">
							<li><a href="/mysite/board?pg=${status.index }">${status.index }</a></li>
							<c:set var="next" value="${status.index }"/>
						</c:forEach>
						<c:if test="${param.pg < next }"> <c:set var="next" value="${param.pg+1 }"/>	</c:if>
						<li class="pg-next"><a href="/mysite/board?pg=${next }">다음 ▶</a></li>
					</ul>	
				</div>
				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a href="/mysite/board?a=writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>