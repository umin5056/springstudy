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
	
	$('#frm').on('submit', function(){
		
		$('#aft').text("${contextPath}/papago?content=" + $('#bf'));
	})
</script>
</head>
<body>
	
	<style>
		.papago {
			display: flex;
			justify-content: space-between;
			width: 1380px;
			margin: 0 auto;
		}
		.source_area, .target_area {
			width: 640px;
		}
		.btn_area {
			width: 50px;
			line-height: 320px;
			text-align: center;
		}
		#text, #translatedText {
			width: 100%;
			height: 300px;
			border: 1px solid gray;
			outline: 0;
			font-size: 24px;
		}
		#text:focus, #translatedText:focus {
			border: 1px solid skyblue;
		}
	</style>
	<div class="papago">
		<div class="source_area">
			<div>
				<select id="source">
					<option value="ko">한국어</option>
					<option value="en">영어</option>
					<option value="ja">일본어</option>
				</select>
			</div>
			<div>
				<textarea id="text"></textarea>
			</div>
		</div>
		<div class="btn_area">
			<input type="button" id="btn_translate" value="번역">
		</div>
		<div class="target_area">
			<div>
				<select id="target">
					<option value="ko">한국어</option>
					<option value="en">영어</option>
					<option value="ja">일본어</option>
				</select>
			</div>
			<div>
				<textarea id="translatedText"></textarea>
			</div>
		</div>
	</div>
	
	<script>
		$('#btn_translate').on('click', function(){
			if($('#text').val() == ''){
				alert('번역할 텍스트를 입력하세요');
				$('#text').focus();
				return;
			}
			$.ajax({
				type: 'get',
				url: '${contextPath}/papago.do',
				data: 'source=' + $('#source').val() + '&target=' + $('#target').val() + '&text=' + $('#text').val(),
				dataType: 'json',
				success: (resData)=>{
					$('#translatedText').text(resData.message.result.translatedText);
				},
				error: function(jqXHR) {
					if(jqXHR.status == 503) {
						alert('입력 정보를 확인하시발라마.')
					}
				}
			})
		})
	</script>

	<hr>
	
	<h1>주말에 풀어보기</h1>
	<form>
		<div>
			<span>검색결과건수</span>
			
			<select>
				<option id="10" name="10">10</option>
				<option id="20" name="30">30</option>
				<option id="30" name="50">50</option>
				<option id="40" name="100">100</option>
			</select>
		</div>
		
		<div>
			<input type="radio" name="sort" id="sim">
			<label for="similar">유사도순</label>
			<input type="radio" name="sort" id="date">
			<label for="similar">날짜순</label>
			<input type="radio" name="sort" id="asc">
			<label for="similar">낮은가격순</label>
			<input type="radio" name="sort" id="dsc">
			<label for="similar">높은가격순</label>
		</div>
		<div>
			<label for="keyword">검색어 입력</label>
			<input id="keyword" name="keyword">
			<input type="submit" value="검색">
		</div>
	</form>
	<hr>
	
	<table border=1>
		<thead>
			<tr>
				<td>상품명</td>
				<td>썸네일</td>
				<td>최저가</td>
				<td>판매처</td>
			</tr>
		</thead>
		<tbody id="content">
		
		</tbody>
		
		
	</table>
	
	


</body>
</html>