<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
  
    <form action="insertNation" method="post" enctype="multipart/form-data">
		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<td bgcolor="orange" width="70"> 국가코드</td>
				<td><input name="nationCode" type = "text"/></td>
			</tr> 
			<tr>
				<td bgcolor="orange" width="70"> 국가명(한국어)</td>
				<td><input name="nationNameKo" type = "text"/></td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 국가명(영어)</td>
				<td><input name="nationNameEn" type = "text"/></td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 수도</td>
				<td><input name="capitarKo" type = "text"/></td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 수도</td>
				<td><input name="capitarEn" type = "text"/></td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 대륙</td>
				<td>
				    <input type="radio" value="아시아" name="continent"/>아시아
				    <input type="radio" value="아프리카" name="continent"/>아프리카
				    <input type="radio" value="유럽" name="continent"/>유럽
				    <input type="radio" value="북아메리카" name="continent"/>북아메리카
				    <input type="radio" value="남아메리카" name="continent"/>남아메리카
				    <input type="radio" value="오세아니아" name="continent"/>오세아니아
				</td>
			</tr>
			<tr>
				<td bgcolor="orange" width="70"> 국기 업로드</td>
				<td>
					<input type="file" name="uploadFile"/>
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
</body>
</html>