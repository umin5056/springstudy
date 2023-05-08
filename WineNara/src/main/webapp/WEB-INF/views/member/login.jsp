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
		$('#id').focus();	
		
		let insertResult= '${insertResult}';
		if(insertResult == '1') {
			alert('회원가입 축하드립니다.');
		}
	})

</script>
</head>
<body>
	<h1>와인나라 로그인</h1>
	<form id="frm_login" method="post" action="${contextPath}/member/login.do">	
		<div>
			<label for="id">ID : </label>
			<c:if test="${userId != null }">
			 	<input id="id" name="id" placeholder="아이디" value="${userId}">
			</c:if>
			<c:if test="${userId == null }">
			 	<input id="id" name="id" placeholder="아이디">
			</c:if>	
		</div>
		<div>
			<label for="pw">PW : </label>
			<input type="password" id="pw" name="pw" placeholder="비밀번호">
		</div>
		<div>
			<c:if test="${userId != null }">
			<input type="checkbox" id="rememberId" name="rememberId" checked>
			</c:if>
			<c:if test="${userId == null }">
			<input type="checkbox" id="rememberId" name="rememberId">
			</c:if>	
			<label for="rememberId">아이디 저장</label>
		</div>
		<div>
			<input type="checkbox" id="autoLogin" name="autoLogin">
			<label for="autoLogin">로그인 상태 유지</label>
		</div>
		<button>로그인</button>
	</form>


</body>
</html>