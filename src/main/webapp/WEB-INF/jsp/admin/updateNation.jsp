<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL 라이브러리 사용 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- jquery --%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - 국가 정보 수정</title>
    <link rel="stylesheet" href="/css/adminList.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<!--
    <header class="main-header">
        <div class="header-left logo-link">
        	<img src="/images/logo.png" alt="Logo">
        </div>
        
        <nav class="header-center">
            <a href="/adminNation" class="nav-item active">국가 목록</a>
            <a href="#" class="nav-item">관리자 목록</a>
        </nav>
        
        <div class="header-right"><div class="profile-circle"></div></div>
    </header>
    -->
    <main class="content-container">
        <section class="content-title-area">
            <h2 class="content-title">국가 정보 수정</h2>
        </section>

        <div class="form-wrapper">
        	<form method="post" enctype="multipart/form-data">
        		<input type="hidden" id="nationId" name="nationId" value="${nation.nationId}"/>
                <div class="form-group country-code-area">
                    <label>국가 코드 <span class="required">*</span></label>
                    <div class="input-with-action">
                        <input type="text" id="nationCode" name="nationCode" class="input-code" placeholder="예: KR" maxlength="2" value="${nation.nationCode}">
                        <button type="button" class="btn-check">중복 확인</button>
                        <span class="input-tip">ISO 3166-1 alpha-2 기준 (2자리 영문 대문자)</span>
                    </div>
                </div>
                
                <hr class="form-divider">
                
                <div class="form-row">
                    <div class="form-group">
                        <label>국가명</label>
                        <input type="text" id="nationNameKo" name="nationNameKo" name="nationNameKo" placeholder="예: 대한민국" value="${nation.nationNameKo}">
                    </div>
                    <div class="form-group">
                        <label>영문 국가명</label>
                        <input type="text" id="nationNameEn" name="nationNameEn" placeholder="예: South Korea" value="${nation.nationNameEn}">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>수도명</label>
                        <input type="text" id="capitarKo" name="capitarKo" placeholder="예: 서울" value="${nation.capitarKo}">
                    </div>
                    <div class="form-group">
                        <label>영문 수도명</label>
                        <input type="text" id="capitarEn" name="capitarEn" placeholder="예: Seoul" value="${nation.capitarEn}">
                    </div>
                </div>

                <div class="form-group">
                    <label>대륙</label>
                    <c:set var="chk_continent" value="${nation.continent}" />
                    <div class="radio-group">
                        <label class="radio-item"><input type="radio" value="아시아" name="continent" ${chk_continent == '아시아'? 'checked' : ''}/>아시아</label>
                        <label class="radio-item"><input type="radio" value="아프리카" name="continent" ${chk_continent == '아프리카'? 'checked' : ''}/>아프리카</label>
                        <label class="radio-item"><input type="radio" value="유럽" name="continent" ${chk_continent == '유럽'? 'checked' : ''}/>유럽</label>
                        <label class="radio-item"><input type="radio" value="북아메리카" name="continent" ${chk_continent == '북아메리카'? 'checked' : ''}/>북아메리카</label>
                        <label class="radio-item"><input type="radio" value="남아메리카" name="continent" ${chk_continent == '남아메리카'? 'checked' : ''}/>남아메리카</label>
                        <label class="radio-item"><input type="radio" value="오세아니아" name="continent" ${chk_continent == '오세아니아'? 'checked' : ''}/>오세아니아</label>
                    </div>
                </div>
                
                <hr class="form-divider">	
                
                <div class="form-group">
                    <div class="label-with-btn">
                        <label>국기 및 국가 이미지</label>
                        <label for="file-upload" class="btn-outline-sm">
	        				<span>+ 이미지 등록</span>
					    </label>
                        <input type="file" id="file-upload" name="uploadFile" accept="image/*" multiple style="display: none;" onchange="handleImageUpload(this)">
                    </div>
                    <div id="imagePreview" class="image-preview-grid">
                    	<c:forEach items="${imageGroup}" var="img" varStatus="status">
                    		<div id="container_${img.imageId}">
							<c:set var="stringId">${img.imageId}</c:set>
							<c:set var="chk_imageType" value="${img.typeList[stringId].imageType}"/>
                    			<div id="type_${img.imageId}" class="image-type">
                    				<label class="radio-item">
                    					<input type="radio" name="typeList[${stringId}].imageType" value="ORIGIN" ${chk_imageType eq 'ORIGIN'? 'checked' : ''}/> 원본
                    				</label>
                    				<label class="radio-item">
                    					<input type="radio" name="typeList[${stringId}].imageType" value="CIRCLE" ${chk_imageType eq 'CIRCLE'? 'checked' : ''}/> 원형
                    				</label>
                    				<label class="radio-item">
                    					<input type="radio" name="typeList[${stringId}].imageType" value="SQUARE" ${chk_imageType eq 'SQUARE'? 'checked' : ''}/> 정사각형
                    				</label>
                    			</div>
		                    	<div class="img-card" id ="file_${img.imageId}">
		                    		<div class="img-box">
		                    			<img src="${img.imgUrl}">
		                    			<input type="hidden" name = "imageId" value="${img.imageId}"/>
		                    		</div>
		                    		<div class="img-info">
		                    			<span class="file-name">${img.viewFileName}</span>
		                    			<button type="button" class="btn-icon-del" onclick="deleteFileImg(${img.imageId})">
		                    				<span class="material-symbols-outlined">delete</span>
		                    			</button>
		                    		</div>
		                    	</div>
		                    </div>
	                    </c:forEach>
                    </div>
                </div>

                <div class="form-footer">
                    <button type="button" class="btn-secondary" onclick="location.href='/adminNation'">목록</button>
                    <button type="button" onclick="submitForm()" class="btn-primary">수정</button>
                </div>
            </form>
        </div>
    </main>
    
	<script>
		function deleteFileImg(imageId){
			
			if(confirm('파일을 삭제하시겠습니까?')){
				$.ajax({
					url : '/nationImgDel?imageId=' + imageId,
					type : 'POST',
					success : function(response){
						alert('파일 삭제 성공');
						document.getElementById('container_'+imageId).remove();
					}
				
				})
			}		
		}
		
		// 서버로 보낼 파일들을 담을 배열
		let selectedFiles = [];
		
		// imageGroup은 서버에서 넘어온 기존 이미지 리스트
    	let imageIndex = ${fn:length(imageGroup)};
    	
    	let i = 0;

		/* 이미지 미리 보기*/
		function handleImageUpload(input) {
		    
		    const $previewCon = $('#imagePreview'); 
		    
		    const typeOption = [
			    { text: '원본', value: 'ORIGIN' },
			    { text: '원형', value: 'CIRCLE' },
			    { text: '정사각형', value: 'SQUARE' }
			];
		
		    Array.from(input.files).forEach(file => {
		    
		    	//실제 전송용 파일 추가
		    	selectedFiles.push(file);
		    	let newIdx = 'new_' + String(i).padStart(2, '0');
		    	++i;
		    	
		        const reader = new FileReader(); 
		
		        // 파일 읽기가 완료되었을 때 실행될 로직(이벤트 핸들러)
		        reader.onload = function(e) {
		            
		            // 1. jQuery 문법으로 카드 생성 ($('<div/>'))
		            const $container = $('<div/>');
		            const $div = $('<div/>', {class: 'image-type'});
		            const $card = $('<div/>', { class: 'img-card' });
		            
		            typeOption.forEach((opt, index) => {
		        		
		        		const $label = $('<label/>', {class: 'radio-item' });
		        		
		        		const $radio = $('<input/>', {
		        			type: 'radio',
		        			name: 'typeList[' + newIdx + '].imageType',		        			
		        			value: opt.value,
		        			checked: index === 0 
		        		});
		        		
		        		
		        		$label.append($radio, ' ' + opt.text + ' ');
		        		$div.append($label);
		        		
		        	});
		
		            // 2. 이미지 영역 생성
		            const $imgBox = $('<div/>', { class: 'img-box' }).append(
		                $('<img/>', { src: e.target.result }) //readAsDataURL로 얻은 결과값
		            );
		
		            // 3. 파일명과 삭제 버튼 생성
		            const $imgInfo = $('<div/>', { class: 'img-info' }).append(
		                $('<span/>', { class: 'file-name', text: file.name }),
		                $('<button/>', { 
		                    type: 'button', 
		                    class: 'btn-icon-del',
		                    html: '<span class="material-symbols-outlined">delete</span>',
		                    click: function() { 
		                    	selectedFiles = selectedFiles.filter(f => f !== file);
		                    	updateInputFiles();
		                        $container.fadeOut(200, function() { $(this).remove(); }); 
		                    }
		                })
		            );
		
		            // 4. 생성한 요소 추가
		            $card.append($imgBox, $imgInfo);
		            $container.append($div, $card);
		            $previewCon.append($container);
		        };
		
		        // 실제로 파일을 읽기 시작
		        reader.readAsDataURL(file);
		    });
		
		    // 같은 파일을 연달아 등록 가능하도록 버튼 초기화
		    $(input).val("");
		}
		
		function updateInputFiles() {
		    const dataTransfer = new DataTransfer();
		    selectedFiles.forEach(file => {
		        dataTransfer.items.add(file);
		    });
		    
		    document.querySelector('#file-upload').files = dataTransfer.files;
		}
		
		function submitForm() {
		    const formData = new FormData();
		    
		    const continentValue = $('input[name="continent"]:checked').val();
		    
		    formData.append("nationId", $('#nationId').val());
		    formData.append("nationCode", $('#nationCode').val());
		    formData.append("nationNameKo", $('#nationNameKo').val()); 
		    formData.append("nationNameEn", $('#nationNameEn').val());
		    formData.append("capitarKo", $('#capitarKo').val());
		    formData.append("capitarEn", $('#capitarEn').val());
		    formData.append("continent", continentValue);  
		    
		
		    // 배열에 담긴 파일들을 formData에 추가
		    selectedFiles.forEach(file => {
		        formData.append("uploadFile", file); // 서버 @RequestParam("uploadFile")과 일치
		    });
		    
		    $('input[name$=".imageType"]:checked').each(function() {
				formData.append($(this).attr('name'), $(this).val());
			});
		
		    $.ajax({
		        url: '/updateNation',
		        type: 'POST',
		        data: formData,
		        processData: false, // 필수: 데이터를 쿼리 문자열로 변환하지 않음
		        contentType: false, // 필수: 브라우저가 boundary를 포함한 multipart/form-data를 자동 설정
		        success: function(response) {
		            alert("수정되었습니다.");
		            location.href = "adminNation";
		        },
		        error: function(xhr) {
		            alert("에러 발생: " + xhr.responseText);
		        }
		    });
		}
		
		
	</script>
</body>
</html>