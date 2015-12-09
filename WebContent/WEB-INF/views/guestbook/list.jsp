<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.hanains.mysite.vo.GuestBookVo"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		
		<div id="content">
			<div id="guestbook">
				<form action="/mysite/guestbook" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="message" cols=60 rows=5></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				
				<ul>
					<c:forEach var="vo"  items="${list }"  varStatus="status">
					<li>
						<table>
							<tr>
								<td>[ ${list.size()-status.count+1 } ]</td>
								<td>${vo.getName() }</td>
								<td>${vo.getReg_date() }</td>
								<td><a href="/mysite/guestbook?a=deleteform&no=${vo.getNo() }">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>
									${vo.getMessage() }
								</td>
							</tr>
						</table>
						<br>
					</li>
					</c:forEach>
				</ul>
				
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
</body>
</html>