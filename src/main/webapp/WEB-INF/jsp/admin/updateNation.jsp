<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL 라이브러리 사용 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- jquery --%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
  
    <form action="/updateNation?nationId=${nation.nationId}" method="post" enctype="multipart/form-data">
		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<td bgcolor="orange" width="70"> 국가코드</td>
				<td><input name="nationCode" type = "text" value="${nation.nationCode}"/></td>
			</tr> 
			<tr>
				<td bgcolor="orange" width="70"> 국가명(한국어)</td>
				<td><input name="nationNameKo" type = "text" value="${nation.nationNameKo}"/></td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 국가명(영어)</td>
				<td><input name="nationNameEn" type = "text" value="${nation.nationNameEn}"/></td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 수도</td>
				<td><input name="capitar" type = "text" value="${nation.capitarKo}"/></td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 수도</td>
				<td><input name="capitar" type = "text" value="${nation.capitarEn}"/></td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 대륙</td>
				<c:set var="chk_continent" value="${nation.continent}" /> 
				<td>
				    <input type="radio" value="아시아" name="continent" ${chk_continent == '아시아'? 'checked' : ''} />아시아
				    <input type="radio" value="아프리카" name="continent"${chk_continent == '아프리카'? 'checked' : ''} />아프리카
				    <input type="radio" value="유럽" name="continent" ${chk_continent == '유럽'? 'checked' : ''}/>유럽
				    <input type="radio" value="북아메리카" name="continent" ${chk_continent == '북아메리카'? 'checked' : ''}/>북아메리카
				    <input type="radio" value="남아메리카" name="continent" ${chk_continent == '남아메리카'? 'checked' : ''}/>남아메리카
				</td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 국기 업로드</td>
				<td>
					<c:forEach items="${imageGroup}" var="img">
						<div id ="file_${img.imageId}">
							<img src="${img.imgUrl}" style="max-width: 400px; height: auto;">
							<input type="hidden" name = "imageId" value="${img.imageId}"/>
							<button type ="button" onclick="deleteFileImg(${img.imageId})">삭제</button>
						</div>
					</c:forEach>
					<input type="file" name="uploadFile"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="등록"/>
				</td>
			</tr>
		</table>
	</form>
	
	<script>
		function deleteFileImg(imageId){
			
			if(confirm('파일을 삭제하시겠습니까?')){
				$.ajax({
					url : '/nationImgDel?imageId=' + imageId,
					type : 'POST',
					success : function(response){
						alert('파일 삭제 성공');
						document.getElementById('file_'+imageId).remove();
					}
				
				})
			}
		
		}
	</script>
</body>
</html>