<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL 라이브러리 사용 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--다국어 처리 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>
	
	
	<table border="1" cellpadding="0" cellspacing="0" width="700">
		<tr>
			<th bgcolor="orange" width="100">번호</th>
			<th bgcolor="orange" width="200">국가명(한국어)</th>
			<th bgcolor="orange" width="150">국가명(영어)</th>
			<th bgcolor="orange" width="150">수도(한국어)</th>
			<th bgcolor="orange" width="150">수도(영어)</th>
			<th bgcolor="orange" width="100">대륙</th>
			<th bgcolor="orange" width="100">국기</th>
			<th bgcolor="orange" width="100">수정/삭제</th>
		</tr>
		<c:forEach items="${nationList}" var="nation">
		<tr>
			<td>${nation.nationCode}</td>
			<td>
				<a href="updateNation?nationId=${nation.nationId}">
					${nation.nationNameKo}
				</a>
			</td>
			<td>${nation.nationNameEn}</td>
			<td>${nation.capitarKo}</td>
			<td>${nation.capitarEn}</td>
			<td>${nation.continent}</td>
			<td><img src="${nation.imgUrl}" style="max-width: 100px; height: auto;"></td>
			<td>
				<a href="updateNation?nationId=${nation.nationId}">수정</a>
				<a href="deleteNation?nationId=${nation.nationId}">삭제</a>
			</td>
		</tr> 
		</c:forEach>
	</table>
	
	<a href="insertNation">새 글 등록</a>
</body>
</html>