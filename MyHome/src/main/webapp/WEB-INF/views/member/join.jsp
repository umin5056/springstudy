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
</head>
<body>
	
	<h1>회원 가입</h1>
	<form method="post" action="${contextPath}/member/join.do">
		<div>
			<input id="id" name="id" placeholder="아이디">
		</div>
		<div>
			<input id="pw" name="pw" placeholder="비밀번호">
		</div>
		<div>
			<input id="name" name="name" placeholder="이름">
		</div>
		<div>
			<input id="email" name="email" placeholder="이메일">
		</div>
		<button>회원가입</button>
	</form>
		
</body>
</html>