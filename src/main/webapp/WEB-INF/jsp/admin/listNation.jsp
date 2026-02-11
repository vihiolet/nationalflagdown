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
    <title>Admin - 국가 관리</title>
    <link rel="stylesheet" href="/css/adminList.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
	<!--
    <header class="main-header">
        <div class="header-left logo-link">
            <img src="/images/logo.png" alt="Logo">
        </div>
        
        <nav class="header-center">
            <a href="#" class="nav-item active">국가 목록</a>
            <a href="#" class="nav-item">관리자 목록</a>
        </nav>
        
        <div class="header-right">
            <div class="profile-circle"></div>
        </div>
    </header>
    -->
    <main class="content-container">
        <section class="content-title-area">
            <h2 class="content-title">국가목록</h2>
            <a href="insertNation" class="btn-primary">새 국가 등록</a>
        </section>

        <section class="filter-bar">
            <form action="adminNation" method = "get" onsubmit="removeEmptyField(this)" id="searchForm" class="search-form">
	            <div class="search-box">
	            	<span class="material-symbols-outlined">search</span>
			        <input name="search" type = "text" value="${cond.search}" placeholder="Searching..." class="search"/>
		       </div>
		       <input type="submit" value="검색" class="btn-search"/>
            </form>
            <div class="filter-options">
                <button type="button" id="sortOrderBtn" class="btn-filter" onclick="toggleSort()">
                    등록일순 <span class="arrow">↑</span>
                </button>
                
                <div class="select-wrapper">
                    <label for="continent-select">대륙</label>
                    <select id="continent-select" class="select-custom">
                        <option value="all">All</option>
                        <option value="asia">아시아</option>
                        <option value="europe">유럽</option>
                        <option value="america">아메리카</option>
                        <option value="africa">아프리카</option>
                        <option value="oceania">오세아니아</option>
                    </select>
                </div>
            </div>
        </section>

        <div class="info-banner">
            <div class="info-text">
                💡 새로운 국가 데이터를 등록하거나 기존 정보를 효율적으로 관리할 수 있습니다.
            </div>
            <!--<button class="btn-help"><span class="material-symbols-outlined">help_outline</span>도움말</button>-->
        </div>

        <div class="table-wrapper">
            <table class="data-table">
                <thead>
                    <tr>
                        <th>국가명</th>
                        <th>국가명(영어)</th>
                        <th>수도</th>
                        <th>수도(영어)</th>
                        <th>대륙</th>
                        <th>국기 썸네일</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${nationList.content}" var="nation">
	                    <tr>
	                        <td class="font-bold">
	                        	<a href="updateNation?nationId=${nation.nationId}">${nation.nationNameKo}</a>
							</td>
	                        <td class="font-bold">
	                        	<a href="updateNation?nationId=${nation.nationId}">${nation.nationNameEn}</a>
	                        </td>
	                        <td>
	                        	<a href="updateNation?nationId=${nation.nationId}">${nation.capitarKo}</a>
	                        </td>
	                        <td>
	                        	<a href="updateNation?nationId=${nation.nationId}">${nation.capitarEn}</a>
	                        </td>
	                        <td>
	                        	<a href="updateNation?nationId=${nation.nationId}">
	                        		<span class="badge badge-asia">${nation.continent}</span>
	                        	</a>
	                        </td>
	                        <td>
	                        	<a href="updateNation?nationId=${nation.nationId}">
	                        		<img src="${nation.imgUrl}" style="max-width: 100px; height: auto;">
	                        	</a>
	                        </td>
	                        <td>
	                            <a href="updateNation?nationId=${nation.nationId}" class="btn-icon edit">
	                            	<span class="material-symbols-outlined">edit</span>
	                            </a>
	                            <a href="deleteNation?nationId=${nation.nationId}" class="btn-icon delete">
	                            	<span class="material-symbols-outlined">delete</span>
	                            </a>
	                        </td>
	                    </tr>
	                </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
    
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