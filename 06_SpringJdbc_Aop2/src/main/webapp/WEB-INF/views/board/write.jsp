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
	<h1>게시글 작성</h1>
	<form method="post" action="${contextPath}/board/add.do">
		<div>
			<label for="title">제목</label>
			<input id="title" name="title">
		</div>
		
		<div>
			<label for="writer">작성자</label>
			<input id="writer" name="writer">
		</div>
		
		<div>
			<p>내용</p>
			<textarea cols="30" rows="5"></textarea>
		</div>
		<div>
			<button>작성완료</button>
			<input type="button" value="목록으로" onclick="location.href='${contextPath}/board/list.do'">
		</div>
	</form>
</body>
</html>