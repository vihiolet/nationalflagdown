<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL 라이브러리 사용 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--다국어 처리 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- 페이징 블록의 정수 연산-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>

	<section class="search-section">
            <form action="adminNation" method = "get" onsubmit="removeEmptyField(this)" id="searchForm" class="search-bar">
		        <input name="search" type = "text" value="${cond.search}" placeholder="검색" class="search"/>
		        <input type="submit" value="검색" class="button"/>
            </form>
    </section>
	
	
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
		<c:forEach items="${nationList.content}" var="nation">
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
	 <div class="pagination-container">
        <!--페이징 블록 세팅-->
		<c:set var="blockLimit" value="5" /> <!-- 한 블록에 보여줄 번호 개수 -->
		<c:set var="nowPage" value="${nationList.number + 1}" /> <!-- 현재 페이지 (1부터 시작) -->
		
		<!-- 시작 번호 계산: ((현재페이지-1) / 블록개수) * 블록개수 + 1 -->
		<fmt:parseNumber var="startPage" value="${((nowPage - 1) / blockLimit)}" integerOnly="true" />
		<c:set var="startPage" value="${startPage * blockLimit + 1}" />
		
		<!-- 끝 번호 계산: 시작번호 + 블록개수 - 1 (단, 전체 페이지수를 넘을 수 없음) -->
		<c:set var="endPage" value="${startPage + blockLimit - 1}" />
		<c:if test="${endPage > nationList.totalPages}">
            <c:set var="endPage" value="${nationList.totalPages}" />
        </c:if>
        
        <div class="pagination" id="pagination">
            <c:if test="${startPage > 1}">        <!--.concat() 여러 텍스트를 하나로 연결-->
                <a href="?page=${startPage - 1}${not empty cond.search ? '&search='.concat(cond.search) : ''}" class="prev">이전</a>
            </c:if>

            <c:forEach var="i" begin ="${startPage}" end = "${endPage}">
                <c:choose>
                    <c:when test ="${i == nowPage}">
                        <strong class="page-num active">${i}</strong>
                    </c:when>
                    <c:otherwise>
                        <c:url var="pageUrl" value="">
                            <c:param name="page" value="${i}" />
                            <c:if test = "${not empty cond.search}">
                                <c:param name="search" value="${cond.search}" />
                            </c:if>
                        </c:url>
                        <a href= "${pageUrl}" class="page-num">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test = "${endPage < nationList.totalPages}">
                <a href="?page=${endPage + 1}${not empty cond.search ? '&search='.concat(cond.search) : ''}">다음</a>
            </c:if>
        </div>
    </div>
	
	<a href="insertNation">새 글 등록</a>
	
	<script>
	
	function removeEmptyField(form){
		const searchInput = form.querySelector('input[name="search"]');
		
	    if (!searchInput.value.trim()) { //trim()은 양쪽 공백 제거한 문자열을 반환 즉 반환되는 문자열이 없을 경우
	        searchInput.disabled = true; // 비어있으면 전송하지 않음
	    }
	}
	
	</script>
</body>
</html>