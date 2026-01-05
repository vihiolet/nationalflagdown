<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL 라이브러리 사용 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/clientView.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>a
  
    <form>
		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<td bgcolor="blue" width="70"> 국가코드</td>
				<td>${nation.nationCode}</td>
			</tr> 
			<tr>
				<td bgcolor="orange" width="70"> 국가명</td>
				<td>${nation.nationName}</td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 수도</td>
				<td>${nation.capitar}</td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 대륙</td>
				<td>${nation.continent}</td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 국기</td>
				<td>
					<c:forEach items="${imageGroup}" var="img">
							<img src="${img.imgUrl}" style="max-width: 400px; height: auto;">
							<a href="/viewNationDown/${img.nationId}/${img.imageId}" onclick="increaseCountDisplay()" download="국기_이미지.png">이미지 다운로드</a>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70">조회수</td>
				<td>${nation.viewCnt}</td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70">다운로드수</td>
				<td><span id="downCnt">${nation.downCnt}</span></td>
			</tr>
		</table>
	</form>
	<script>
		function increaseCountDisplay() {
		
		    const countElement = document.getElementById('downCnt');
		    
		    let currentCount = parseInt(countElement.innerText) || 0;
		        
		    countElement.innerText = currentCount + 1;
		    
		}
	</script>
</body>
</html>