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
	<a href="${contextPath}/notice/notice.do">공지사항</a>
	<c:if test="${member == null}">
		<div>
			<form method="post" action="${contextPath}/member/login.do">
				<div>
					<input id="id" name="id" placeholder="아이디">
				</div>
				<div>
					<input id="pw" name="pw" placeholder="비밀번호">
				</div>
				<button>로그인</button>
			</form>
		</div>
		<div>
			<a href="${contextPath}/member/joinPage.do">회원가입</a>
		</div>
	</c:if>
	<c:if test="${member != null}">
		<div>
			${member.name}님 안녕하세요.
			<input type="button" value="로그아웃" onclick="location.href='${contextPath}/member/logout.do'">
		</div>
		
		<form method="post" action="${contextPath}/member/delete.do">
			<input type="hidden" value="${member.memberNo}" name="memberNo">
			<button>회원탈퇴</button>
		</form>
		
	</c:if>
	
</body>
</html>