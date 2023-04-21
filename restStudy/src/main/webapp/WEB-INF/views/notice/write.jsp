<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	function fnList() {
		location.href = '${contextPath}/notice/list.do';
	}
</script>
</head>
<body>

	<div>
		<h1>공지 작성하기</h1>
		<form method="post" action="${contextPath}/notice/add.do">
			<div>
				<label for="gubun">구분</label>
				<select name="gubun">
					<option value="1">긴급</option>
					<option value="2">일반</option>
				</select>
			</div>
			<div>
				<label for="title">제목</label>
				<input id="title" name="title">
			</div>
			<div>
				내용
				<div>
					<textarea cols="30" rows="6" name="content">안녕</textarea>
				</div>
			</div>
			<div>
				<button>작성완료</button>
				<input type="button" value="목록" onclick="fnList()">
			</div>
		</form>
	</div>
	
</body>
</html>