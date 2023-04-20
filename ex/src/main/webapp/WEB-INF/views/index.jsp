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
</head>
<body>

	<!-- /detail.do로 boardNo = 1, title = 제목 파라미터로 보내기 -->
	

	<!-- 1. <a> -->
	<div>
		<a href="${contextPath}/detail.do?boardNo=1&title=제목">보내기</a>
		<a href="${contextPath}/detail.do">보내기(노파라)</a>
	</div>
	
	<!-- 2.	<form> -->
	<div>
		<form method="post" action="${contextPath}/detail.do">
			<input type="hidden" name="boardNo" value="1">
			<input type="hidden" name="title" value="제목">
			<button>보내기</button>
		</form>
		<form method="post" action="${contextPath}/detail.do">
		
			<button>보내기(노파라)</button>
		</form>
	</div>
	
	<!-- 3. location -->
	<div>
		<button	onclick="location.href='${contextPath}/detail.do?boardNo=1&title=제목'">보내기</button>
	</div>
	
	<hr>
	
	<div>
		<a href="${contextPath}/board/detail1.do?boardNo=10">상세요청1	</a>
		<a href="${contextPath}/board/detail2.do?boardNo=10">상세요청2	</a>
		<a href="${contextPath}/board/detail3.do?boardNo=10">상세요청3</a>
		<a href="${contextPath}/board/list.do">목록보기</a>
	</div>
	
	
	
	
	
	
	
</body>
</html>