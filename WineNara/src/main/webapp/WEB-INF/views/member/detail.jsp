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
	function fnIndex() {
		location.href="${contextPath}/";
	}
	
	function fnDelete() {
		location.href="${contextPath}/member/delete.do?memberNo=${member.memberNo}";
	}
	
	$(function() {
		if('${updateResult}' == '0') {
			alert('회원정보 수정이 실패했습니다.');
		}
		
		let deleteResult = '${deleteResult}';
		if(deleteResult == '0') {
			alert('회원탈퇴에 실패했습니다.');
		}
	})

</script>
</head>
<body>
	<h1>${member.name}님 회원정보</h1>
	<form method="post" action="${contextPath}/member/update.do">
		<div>
		    <label for="id">아이디:</label>
		    <input id="id" name="id" value="${member.id}" readonly>
		</div>
		<div>
		    <label for="pw">비밀번호:</label>
		    <input id="pw" name="pw" value="${member.pw}">
		</div>
		<div>
		    <label for="name">이름:</label>
		    <input id="name" name="name" value="${member.name}">
		</div>
		<div>
		    <label for="email">이메일:</label>
		    <input id="email" name="email" value="${member.email}">
		</div>
		<div>
		    <label for="phoneNumber">전화번호:</label>
		    <input id="phoneNumber" name="phoneNumber" value="${member.phoneNumber}">
		</div>
		<div>
		    <label for="address">주소:</label>
		    <input id="address" name="address" value="${member.address}">
		</div>
		<input type="hidden" name="memberNo" value="${member.memberNo}">
		<button>수정하기</button>
	</form>
	
	<input type="button" value="메인으로" onclick="fnIndex()">
	<input type="button" value="회원탈퇴" onclick="fnDelete()">
	
</body>
</html>