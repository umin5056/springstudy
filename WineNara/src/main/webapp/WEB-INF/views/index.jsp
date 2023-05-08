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

	
	
	function fnHidden() {
		$('#welcome').addClass('hidden');
		$('#shop').removeClass('hidden');
	}
	
	function fnAlert() {
		alert('만 19세 미만은 입장하실 수 없습니다.');
	}

	function fnLogout() {
		location.href="${contextPath}/member/logout.do";
	}
	
	function fnDetail() {
		location.href="${contextPath}/member/detail.page";
	}
	
	function fnSignUp() {
		location.href="${contextPath}/member/signup.page";		
	}
	
	$(function() {
		
		let updateResult = '${updateResult}';
		if(updateResult == '1') {
			alert('회원정보가 수정되었습니다. 다시 로그인해주세요.');
		}	
		
		let deleteResult = '${deleteResult}';
		if(deleteResult == '1') {
			fnLogout();
			alert('회원탈퇴가 완료되었습니다.');
		}
		
		if(${member != null}) {
			$('#shop').removeClass('hidden');
		}
		
	})
	
	(function getItems() {
		location.href='${contextPath}/wine/getAllItems.do';
	})();
		
</script>

<style>
	.hidden {
		display: none;
	}
	#itemList {
		list-style: none;
	}
</style>
</head>
<body>
	
	<c:if test="${member == null}">
		<div id="welcome">
			<h1>만 19세 이상이십니까?</h1>
			<input type="button" value="예" onclick="fnHidden()">
			<input type="button" value="아니요" onclick="fnAlert()">
		</div>
	</c:if>
	<div id="shop" class="hidden">
		<c:if test="${member == null }">
			<div>
				<a href="${contextPath}/member/login.page">로그인하러 가기</a>
			</div>
			<div>
				<input type="button" value="회원가입" onclick="fnSignUp()">
			</div>
		</c:if>
		
		<c:if test="${member != null}">
			<div>
				${member.name}님 환영합니다.
			</div>
		
			<input type="button" value="로그아웃" onclick="fnLogout()">	
			<input type="button" value="회원정보" onclick="fnDetail()">
		</c:if>
	
		<div>
			<h1>OUR PICK</h1>
			<p>와인나라팀에서 특별히 선정한 상품들을 만나보세요.</p>
			<ul id="itemList">
				<c:forEach items="wineList" var='w'>
					<li>
						<div class='wineBox whiteWine'></div>
						
					</li>
				</c:forEach>
			</ul>
		</div>
		
	</div>
	
</body>
</html>