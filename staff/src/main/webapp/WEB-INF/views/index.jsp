<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	
	$(function (){
		
		fnList();
		
		$('#sno').on('blur', function () {
		
		  var chk = /^[0-9]{5}/;
				
		  if (chk.test($(this).val())==false) {
			  alert("사원번호는 5자리 숫자입니다." );
		  }		  
		});
		
		$('#dept').on('blur', function (){
			var chk = /^[가-힣]{3,5}/;
			if(chk.test($(this).val()) == false) {
				alert("부서는 3~5자리 한글입니다.");
			} 
		})
		
	})
		
	function fnList() {
		$.ajax({
			type: 'get',
			url: '${contextPath}/list.json',
			
			dataType: 'json',
			success: function(resData) { // resData = [{}, {}, {}]
				$('#staffList').empty();	
				$.each(resData, function(i, staff){
					let str = '<tr>';
					str += '<td>' + staff.sno;
					str += '<td>' + staff.name;
					str += '<td>' + staff.dept;
					str += '<td>' + staff.salary;
					$('#staffList').append(str);
				})
			}
		})
	}
	
	function fnAdd() {
		$.ajax({
			type: 'post',
			url: '${contextPath}/add.do',
			data: $('#frm_add').serialize(),
			dataType: 'text',
			success: function(resData) { // resData : 사원 등록이 성공했습니다.
				alert(resData);
				fnList();
				$('#sno').val('');
				$('#name').val('');
				$('#dept').val('');
			},
			error: function(jqXHR) { // jqXHR.responseText : 사원 등록이 실패했습니다.
				alert(jqXHR.responseText);
			}
			
		})
	}
	
	
	function fnSearch() {
		$.ajax({
			type: 'get',
			url: '${contextPath}/query.json',
			data: 'sno=' + $('#query').val(),
			dataType: 'json',
			success: function(resData) {
				
				let str = '<tr><td>' + resData.sno;
				   str += '<td>' + resData.name; 
				   str += '<td>' + resData.dept; 
				   str += '<td>' + resData.salary; 
				   $('#staffList').html(str);
			},
			error: function(jqXHR) {
				alert('해당 사원 번호를 가진 사원은 없습니다.');
			} 
			
			
		})
	}

	
	
	
	
	
</script>
</head>
<body>

	<div>
		<h3>사원등록</h3>
		<form id="frm_add">
			<input name="sno" id="sno" placeholder="사원번호">
			<input name="name" id="name" placeholder="사원명">
			<input name="dept" id="dept" placeholder="부서명">
			<input type="button" value="등록" onclick="fnAdd()">
		</form>
	</div>
	
	<hr>
	
	<div>
		<h3>사원조회</h3>
		<form id="frm_search">
			<input name="qeury" id="query" placeholder="사원번호입력">
			<input type="button" value="조회" onclick="fnSearch()">
			<input type="button" value="전체" onclick="fnList()">
		</form>
	</div>
	
	<hr>
	
	<div>
		<h3>사원목록</h3>
		<table border=1>
			<thead>
				<tr>
					<td>사원번호</td>
					<td>사원명</td>
					<td>부서명</td>
					<td>연봉</td>
				</tr>
			</thead>
			<tbody id="staffList"></tbody>
		</table>
	</div>
	
</body>
</html>