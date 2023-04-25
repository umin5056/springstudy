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
	
	<h1>공지사항</h1>
	
	<table border=1>
		<thead>
			<tr>
				<td>공지번호</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${noticeList}" var="n">
				<tr>
					<td>${n.noticeNo}</td>
					<td>${n.title}</td>
					<td>${n.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3">
					<!-- 현재 페이지가 3보다 클 때만 이전블록을 표시 -->
					<c:if test="${page > pagePerBlock}">
						<a href="${contextPath}/notice/notice.do?page=${beginPage-1}">&lt;이전블록</a>
					</c:if>
					
					<!-- 1페이지가 아니면 이전을 표기 -->
					<c:if test="${page != 1}">
						<a href="${contextPath}/notice/notice.do?page=${page-1}">&lt;이전</a>
					</c:if>
					
					<c:forEach begin="${beginPage}" end="${endPage}" step="1" var="p">
						<!-- 현재 페이지와 같으면 링크 없음 -->
						<c:if test="${page == p}">
							${p}
						</c:if>
						
						<!-- 현재 페이지와 다르면 링크 -->
						<c:if test="${page != p}">
							<a href="${contextPath}/notice/notice.do?page=${p}">${p}</a>
						</c:if>
					</c:forEach>
					
					<!-- 마지막 페이지가 아니면 다음 페이지 버튼이 보임 -->
					<c:if test="${page != totalPageCnt}">
						<a href="${contextPath}/notice/notice.do?page=${page+1}">다음&gt;</a>
					</c:if>
					
					<c:if test="${endPage != totalPageCnt}">
						<a href="${contextPath}/notice/notice.do?page=${endPage+1}">다음블록&gt;</a>
					</c:if>
					
				</td>
			</tr>
		</tfoot>
	</table>
	
		
</body>
</html>