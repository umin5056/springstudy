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
<script src="${contextPath}/resources/summernote/summernote-lite.min.js"></script>
<script src="${contextPath}/resources/summernote/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
</head>

<body>
	<h1>게시글 작성</h1>
	<form method="POST" action="${contextPath}/board/insert.do">
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
			<textarea id="content" name="content" cols="30" rows="5"></textarea>
		</div>
		<div>
			<button>작성완료</button>
			<input type="button" value="목록" onclick="location.href='${contextPath}/board/list.do'">
		</div>
	</form>
</body>
</html>