<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>

	<input type="button" value="작성하기" onclick="location.href='${contextPath}/board/write.do'">
	<input type="button" value="작성하기" onclick="location.href='${contextPath}/WEB-INF/views/board/write.jsp'">
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일자</td>
				</tr>
			</thead>	
			<tbody>
				<c:if test="${empty boardList}">
					<tr>
						<td colspan="3">등록된 게시물이 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${not empty boardList}">
					<c:forEach items="${boardList}" var="b">
						<tr>
							<td>${b.title}</td>
							<td>${b.writer}</td>
							<td>${b.created_at}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>	
		</table>
	</div>
</body>
</html>