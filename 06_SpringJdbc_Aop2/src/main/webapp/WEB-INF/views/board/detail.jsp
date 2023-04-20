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
</head>
<body>
	<h1>${board.board_no}번 게시글</h1>
	<form method="post" action="${contextPath}/board/modify.do">
		<div>
			<label for="title">제목 </label>
			<input id="title" name="title" value="${board.title}">
		</div>
		<div>
			최종수정일 : ${board.modified_at}
		</div>
		<div>
			<label for="writer">작성자 </label>
			<input id="writer" name="writer" value="${board.writer}">
		</div>
		<div>
			<p>내용</p>
			<textarea rows="5" cols="30">${board.content}</textarea>
		</div>
		<input type="hidden" value="${board.board_no}" name="board_no">
		<button>수정완료</button>
	</form>
	<input type="button" value="목록으로" onclick="location.href='${contextPath}/board/list.do'">
</body>
</html>