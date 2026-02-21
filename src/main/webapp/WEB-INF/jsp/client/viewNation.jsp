<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL 라이브러리 사용 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>국기 검색 서비스</title>
     <link rel="stylesheet" href="/css/clientView.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header>
	    <div class="header-container">
	    	<c:set var="currentLang" value="${empty param.lang ? 'ko' : param.lang}" />
	    	
	        <a href="nation?lang=${currentLang}" class="logo-link">
	        	<img src="/images/view_logo.png" alt="Logo">
	        </a>
	        <div class="lang-selector">
	            <a href ="viewNation?nationId=${param.nationId}&lang=ko" class="lang-btn ${empty param.lang || param.lang eq 'ko' ? 'active' : ''}">
	            	<spring:message code="message.language.ko"/>
	            </a>
	            <span class="divider">|</span>
	            <a href ="viewNation?nationId=${param.nationId}&lang=en" class="lang-btn ${param.lang eq 'en' ? 'active' : ''}">
					<spring:message code="message.language.en"/>
				</a>
	        </div>
	    </div>
	</header>
	
	<div class="container">
	    <div class="page-header">
	        <h1><spring:message code ="message.view.mainTitle"/></h1>
	    </div>
	    
	    <div class="info-grid">
	        <div class="info-card dark">
	            <span class="label"><spring:message code ="message.view.mainTtile.nation"/></span>
	            <span class="value">${nation.nationName}</span>
	        </div>
	        <div class="info-card">
	            <span class="label"><spring:message code ="message.view.mainTtile.capital"/></span>
	            <span class="value">${nation.capitar}</span>
	        </div>
	        <div class="info-card">
	            <span class="label"><spring:message code ="message.view.mainTtile.continent"/></span>
	            <span class="value">${nation.continent}</span>
	        </div>
	    </div>
	
	    <div class="section-title-row">
	        <h2 class="section-title"><spring:message code ="message.view.downloadTtile"/></h2>
	        <div class="stats-inline">
	            <span class="stat-text"><spring:message code ="message.view.downloadViewCnt"/> <strong>${nation.viewCnt}</strong></span>
	            <span class="stat-divider">|</span>
	            <span class="stat-text"><spring:message code ="message.view.downloadDownCnt"/> <strong id="downCnt">${nation.downCnt}</strong></span>
	        </div>
	    </div>
	
        <div class="display-section">
        	<c:forEach items="${imageGroup}" var="img">
		        <div class="image-row">
		            <div class="img-box">
		                <img src="${img.imgUrl}" alt="원본 국기">
		            </div>
		            <a href="/viewNationDown/${img.nationId}/${img.imageId}" class="download-box-btn" onclick="increaseCountDisplay()" download="국기_이미지.png">
		                <div class="icon-circle">📄</div>
		                <div class="btn-text-content">
		                    <span class="btn-main-title">${img.fileName}</span>
		                    <span class="btn-sub-info">${img.size} KB</span>
		                </div>
		            </a>
		        </div>
	        </c:forEach>
	    </div>
	
	    <div class="table-section">
	        <table class="file-table">
	            <thead>
	                <tr>
	                    <th><spring:message code ="message.view.downloadGrid.type"/></th>
	                    <th><spring:message code ="message.view.downloadGrid.fileName"/></th>
	                    <th><spring:message code ="message.view.downloadGrid.size"/></th>
	                    <th><spring:message code ="message.view.downloadGrid.dimensions"/></th>
	                    <th><spring:message code ="message.view.downloadGrid.download"/></th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:forEach items="${imageGroup}" var="img">
		                <tr>
		                	<c:set var="badgeClass" value="" />
							<c:choose>
							    <c:when test="${img.imageType eq 'ORIGIN' or img.imageType eq'원본'}"><c:set var="badgeClass" value="origin"/></c:when>
							    <c:when test="${img.imageType == 'CIRCLE' or img.imageType == '원형'}"><c:set var="badgeClass" value="circle" /></c:when>
							    <c:when test="${img.imageType == 'SQUARE' or img.imageType == '사각형'}"><c:set var="badgeClass" value="square" /></c:when>
							</c:choose>
		                    <td><span class="badge ${badgeClass}">${img.imageType}</span></td>
		                    <td class="file-name">${img.fileName}</td>
		                    <td>${img.size} KB</td>
		                    <td>${img.width} X ${img.hight}</td>
		                    <td>
		                    	<a href="/viewNationDown/${img.nationId}/${img.imageId}" class="btn-table-download" onclick="increaseCountDisplay()" download="국기_이미지.png">
		                    	다운로드
		                    	</a>
		                    </td>
		                </tr>
		            </c:forEach>
	            </tbody>
	        </table>
	    </div>
	</div>
	<footer>
	    <div class="footer-content">
	    	<a href="nation?lang=ko" class="logo-link">
	        	<img src="/images/footer_logo.png" alt="Logo">
	        </a>
	        <p>© 2026 Shin Yun Jin. All rights reserved.</p>
	    </div>
	</footer>

	<script>
		function increaseCountDisplay() {
		
		    const countElement = document.getElementById('downCnt');	    
		    let currentCount = parseInt(countElement.innerText) || 0;
		    countElement.innerText = currentCount + 1;
		    
		}
	</script>
</body>
</html>