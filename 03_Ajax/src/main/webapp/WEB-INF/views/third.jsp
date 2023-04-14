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
	function fn1() {
		
		$.ajax({
			// 요청
			type: 'post', // 서버로 보낼 데이트를 요청 본문(request body)에 저장해서 보낸다.
			url: '${contextPath}/third/ajax1',
			data: JSON.stringify({
			// 자바스크립트 객체를 문자열 형태로 바꿔준다. (라이브러리 필요 x)
			// 문자열 형식의 JSON 데이터를 서버로 보낸다.
			// 파라미터 이름이 없으므로, 서버에서 파라미터로 받을 수 없다. (파라미터가 없으므로 get 방식은 불가)
				'name': $('#name').val(),
				'tel': $('#tel').val()
			}), 
			// 예시 - data: '{"name":"kim", "tel":"010-1234-5665"}'
			contentType: 'application/json', // 서버로 보내는 data의 타입을 서버에 알려준다.
			
			// 응답
			dataType: 'json',
			success: function(resData) { // resData = {"name":"조우민", "tel":"010-2131-2313"}
				let str = '<ul>';
				str += '<li>' + resData.name;
				str += '<li>' + resData.tel;
				
				// append는 추가해주는 메소드라 empty()로 초기화해줘야함.
				// html은 내용을 새로 바꿔주는 메소드라 초기화 필요 없음.				
				$('#result').html(str);
			},
			error: function(jqXHR){
				if(jqXHR.status == 400) {
					alert('이름과 전화번호는 필수입니다.');
				}
			}
			
		})
		
	}
	function fn2() {
		
	}
</script>
</head>
<body>
	
	<div>
		<form id="frm">
			<div>
				<label for="name">이름</label>
				<input id="name" name="name">
			</div>
			<div>
				<label for="tel">전화번호</label>
				<input id="tel" name="tel">
			</div>
			<div>
				<input type="button" value="전송1" onclick="fn1()">
				<input type="button" value="전송2	" onclick="fn2()">
			</div>
		</form>
	</div>
	
	<hr>
	
	<div id="result">	
	</div>
	
</body>
</html>