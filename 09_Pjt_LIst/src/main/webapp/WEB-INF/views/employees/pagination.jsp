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
</head>
<body>

	<div>
		<a href="${contextPath}/employees/search.form">사원 조회 화면으로 이동</a>
	</div>
	
	<div>
		<h1>사원 목록</h1>
		<div>
			<select>
				<option value="10">10개</option>
				<option value="20">20개</option>
				<option value="30">30개</option>
			</select>
		</div>
		<hr>
		<table border=1>
			<thead>
				<tr>
					<td>순번</td>
					<td>사원번호</td>
					<td>사원명</td>
					<td>이메일</td>
					<td>전화번호</td>
					<td>입사일자</td>
					<td>직업</td>
					<td>연봉</td>
					<td>커미션</td>
					<td>매니저</td>
					<td>부서번호</td>
					<td>부서명</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${employees}" var="emp" varStatus="vs">
					<tr>
						<td>${vs.index}</td>
						<td>${emp.employeeId}</td>
						<td>${emp.firstName} ${emp.lastName}</td>
						<td>${emp.email}</td>
						<td>${emp.phoneNumber}</td>
						<td>${emp.hireDate}</td>
						<td>${emp.jobId}</td>
						<td>${emp.salary}</td>
						<td>${emp.commissionPct}</td>
						<td>${emp.managerId}</td>
						<td>${emp.dept.department}</td>
						<td>${emp.dept.departmentName}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="12">
						<div>${pagination}</div>
					</td>
				</tr>
			</tfoot>
		
		</table>

	</div>
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>