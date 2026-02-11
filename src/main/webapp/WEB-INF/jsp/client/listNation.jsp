<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- JSTL 라이브러리 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--다국어 처리 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- 페이징 블록의 정수 연산-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>국기 검색 서비스</title>
    <link rel="stylesheet" href="/css/clientList.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>

<header>
    <div class="header-container">
    	<c:set var="currentLang" value="${empty param.lang ? 'ko' : param.lang}" />
        
        <a href="nation?lang=${currentLang}" class="logo-link">
        	<!--<img src="/images/logo.png" alt="Logo">-->
        </a>
        <div class="lang-selector">
            <a href ="nation?lang=ko" class="lang-btn ${empty param.lang || param.lang eq 'ko' ? 'active' : ''}">
            	<spring:message code="message.language.ko"/>
            </a>
            <span class="divider">|</span>
            <a href ="nation?lang=en" class="lang-btn ${param.lang eq 'en' ? 'active' : ''}">
				<spring:message code="message.language.en"/>
			</a>
        </div>
    </div>
</header>

<main>
    <section class="search-section">
        <h2><spring:message code="message.list.mainTitle"/></h2>
        <p class="description"><spring:message code="message.list.subText"/></p>
        
            <form action="nation" method = "get" onsubmit="removeEmptyField(this)" id="searchForm" class="search-bar">
		        <input name="search" type = "text" value="${cond.search}" placeholder="<spring:message code="message.list.search.placeholder"/>" class="search"/>
		        <input name= "lang" type = "hidden" value="${currentLang}"/>
		        <input name="continent" type="hidden" value="${current}">
		        <input type="submit" value="검색" class="button"/>
            </form>
    </section>

    <nav class="continent-tabs">
        <button class="tab-btn ${empty cond.continent || cond.continent eq 'All' ? 'active' : ''}" onclick="loadDataByContinent('All')">
        	<spring:message code="message.list.continent.all"/>
        </button>
        <button class="tab-btn ${cond.continent eq '아시아' ? 'active' : ''}" onclick="loadDataByContinent('Asia')">
        	<spring:message code="message.list.continent.asia"/>
        </button>
        <button class="tab-btn ${cond.continent eq '아프리카' ? 'active' : ''}" onclick="loadDataByContinent('Africa')">
			<spring:message code="message.list.continent.africa"/>
		</button>
        <button class="tab-btn ${cond.continent eq '유럽' ? 'active' : ''}" onclick="loadDataByContinent('Europe')">
        	<spring:message code="message.list.continent.europe"/>
        </button>
        <button class="tab-btn ${cond.continent eq '북아메리카' ? 'active' : ''}" onclick="loadDataByContinent('North America')">
        	<spring:message code="message.list.continent.northAmerica"/>
        </button>
        <button class="tab-btn ${cond.continent eq '남아메리카' ? 'active' : ''}" onclick="loadDataByContinent('South America')">
        	<spring:message code="message.list.continent.southAmerica"/>
        </button>
        <button class="tab-btn ${cond.continent eq '오세아니아' ? 'active' : ''}" onclick="loadDataByContinent('Oceania')">
        	<spring:message code="message.list.continent.oceania"/>
        </button>
    </nav>
   
    <div class="country-grid" id="countryGrid">
       <c:forEach items="${nationList.content}" var="nation">
            <div class="country-card">
                <div class="flag-img">
                	<a href="viewNation?nationId=${nation.nationId}&lang=${currentLang}"><img src="${nation.imgUrl}"</a>
                </div>
                <div class="info">
                    <a href="viewNation?nationId=${nation.nationId}&lang=${currentLang}">${nation.nationName}</a>
                    <span class="capital">${nation.capitar}</span>
                </div>
            </div>
        </c:forEach>
    </div>
    
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
</main>

<footer>
    <div class="footer-content">
        <h2 class="logo">LOGO</h2>
        <p>Copyright © 2025 Country Search. All rights reserved.</p>
    </div>
</footer>

<script>
	
	function removeEmptyField(form){
		const searchInput = form.querySelector('input[name="search"]');
		const currentContinent = form.querySelector('input[name="continent"]');
	    if (!searchInput.value.trim()) { //trim()은 양쪽 공백 제거한 문자열을 반환 즉 반환되는 문자열이 없을 경우
	        searchInput.disabled = true; // 비어있으면 전송하지 않음
	    }
	    if(!currentContinent.value.trim()){
	    	currentContinent.disabled = true;
	    }
	}
		
    function loadDataByContinent(continent) {
    
    	document.querySelector('input[name="search"]').value = '';
 		document.querySelector('input[name="continent"]').value = continent; 
       
        $('#searchForm').submit(); 
    }
   
</script>

</body>
</html>