<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	$(function() {
		let insertResult= '${insertResult}';
		
		if(insertResult == '0') {
			alert('회원가입에 실패했습니다.');
		}
		
	})
</script>
</head>
<body>
	<h1>회원가입</h1>
	<form method="post" action="${contextPath}/member/signup.do">
		<div>
		    <label for="id">아이디:</label>
		    <input id="id" name="id">
		</div>
		<div>
		    <label for="pw">비밀번호:</label>
		    <input id="pw" name="pw">
		</div>
		<div>
		    <label for="name">이름:</label>
		    <input id="name" name="name">
		</div>
		<div>
		    <label for="email">이메일:</label>
		    <input id="email" name="email">
		</div>
		<div>
		    <label for="phoneNumber">전화번호:</label>
		    <input id="phoneNumber" name="phoneNumber">
		</div>
		<div>
		    <label for="address">주소:</label>
		    <input id="address" name="address">
		</div>
		<input type="hidden" name="memberNo">
		<button>회원가입</button>
	</form>
	
	<input type="button" value="메인으로" onclick="fnIndex()">
	
</body>
</html>