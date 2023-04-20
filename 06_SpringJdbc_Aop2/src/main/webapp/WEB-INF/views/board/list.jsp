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
<script src="${contexPath}/resources/js/lib/jqeury-3.6.4.min.js"></script>
<style>
	tbody tr:hover{
		background-color: skyblue;
	}
</style>

</head>
<body>
	<h1>게시판</h1>
	<table border=1>
		<thead>
			<tr>
				<td>게시번호</td>
				<td>최종수정일</td>
				<td>제목</td>
				<td>작성자</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty boardList}">
				<tr>
					<td colspan="3">게시물이 없어용가리 탕탕~~ 빵야빵야~~ (찡긋)</td>
				</tr>
			</c:if>
			<c:if test="${not empty boardList}">
				<c:forEach items="${boardList}" var="b" varStatus="vs">
					<tr onclick="location.href='${contextPath}/board/detail.do?board_no=${b.board_no}'">
						<td>${boardList.size() + 1 - vs.count}</td>
						<td>${b.modified_at}</td>
						<td>${b.title}</td>
						<td>${b.writer}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<input type="button" value="새게시글" onclick="location.href='${contextPath}/board/write.do'">
</body>
</html>