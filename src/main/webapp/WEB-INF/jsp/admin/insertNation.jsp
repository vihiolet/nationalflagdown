<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - 국가 정보 등록</title>
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
            <h2 class="content-title">국가 정보 등록</h2>
        </section>

        <div class="form-wrapper">
            <form method="post" enctype="multipart/form-data">            
                <div class="form-group country-code-area">
                    <label>국가 코드 <span class="required">*</span></label>
                    <div class="input-with-action">
                        <input type="text" id="nationCode" name="nationCode" class="input-code" placeholder="예: KR" maxlength="2">
                        <button type="button" id="btnCheckCode" class="btn-check">중복 확인</button>
                        <span class="input-tip">ISO 3166-1 alpha-2 기준 (2자리 영문 대문자)</span>
                        <span id="msgArea"></span>
                    </div>
                </div>
                
                <hr class="form-divider">
                
                <div class="form-row">
                    <div class="form-group">
                        <label>국가명 <span class="required">*</span></label>
                        <input type="text" id="nationNameKo" class="check" name="nationNameKo" placeholder="예: 대한민국">
                    </div>
                    <div class="form-group">
                        <label>영문 국가명</label>
                        <input type="text" id="nationNameEn" class="check" name="nationNameEn" placeholder="예: South Korea">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>수도명 <span class="required">*</span></label>
                        <input type="text" id="capitarKo" class="check" name="capitarKo" placeholder="예: 서울">
                    </div>
                    <div class="form-group">
                        <label>영문 수도명 <span class="required">*</span></label>
                        <input type="text" id="capitarEn" class="check" name="capitarEn" placeholder="예: Seoul">
                    </div>
                </div>

                <div class="form-group">
                    <label>대륙 <span class="required">*</span></label>
                    <div class="radio-group">
                        <label class="radio-item"><input type="radio" value="아시아" name="continent" checked/>아시아</label>
                        <label class="radio-item"><input type="radio" value="아프리카" name="continent"/>아프리카</label>
                        <label class="radio-item"><input type="radio" value="유럽" name="continent"/>유럽</label>
                        <label class="radio-item"><input type="radio" value="북아메리카" name="continent"/>북아메리카</label>
                        <label class="radio-item"><input type="radio" value="남아메리카" name="continent"/>남아메리카</label>
                        <label class="radio-item"><input type="radio" value="오세아니아" name="continent"/>오세아니아</label>
                    </div>
                </div>
                
                <hr class="form-divider">

                <div class="form-group">
                    <div class="label-with-btn">
                        <label>국기 이미지</label>
                        <label for="file-upload" class="btn-outline-sm">
	        				<span>+ 이미지 등록</span>
					    </label>
                        <input type="file" id="file-upload" name="uploadFile" accept="image/*" multiple style="display: none;" onchange="handleImageUpload(this)">
                    </div>
                    
		            <div id="imagePreview" class="image-preview-grid"></div>
                    
                </div>

                <div class="form-footer">
                    <button type="button" class="btn-secondary" onclick="location.href='/adminNation'">목록</button>
                    <button type="button" id="btnSubmit" class="btn-primary" onclick="submitForm()" disabled>등록</button>
                </div>
            </form>
        </div>
    </main>
    <script>
    	let selectedFiles = [];
    	let i = 0;
    	
    	/* 입력 값 검증 후 버튼 활성화 */
    	$(document).ready(function(){
    		$(document).on('input change', '.check', function(){
				checkFormValidity();		
			});	
			$(document).on('input change', '#nationCode', function(){
				$('#msgArea').empty();
				checkFormValidity();		
			});	
    	});
		
    	/* 이미지 미리 보기*/
		function handleImageUpload(input) {
		
			const $container = $('#img-container');
			
		    const $previewCon = $('#imagePreview'); 
		    
		    const typeOption = [
			    { text: '원본', value: 'ORIGIN' },
			    { text: '원형', value: 'CIRCLE' },
			    { text: '정사각형', value: 'SQUARE' }
			];
		
		    Array.from(input.files).forEach(file => {
		    
		    	selectedFiles.push(file);
		    	
		        const reader = new FileReader(); 
		        reader.onload = function(e) {
		        
		        	const $container = $('<div/>');
		        	
		            const $div = $('<div/>', {class: 'image-type'});
		            const $card = $('<div/>', { class: 'image-card' });
		            
		            typeOption.forEach((opt, index) => {
		        		
		        		const $label = $('<label/>', {class: 'radio-item' });
		        		
		        		const $radio = $('<input/>', {
		        			type: 'radio',
		        			name: 'typeList[' + i + '].imageType',
		        			value: opt.value,
		        			checked: index === 0 
		        		});
		        		
		        		$label.append($radio, ' ' + opt.text + ' ');
		        		$div.append($label);
		        		
		        	});
		        	i++;
		            
		            const $imgBox = $('<div/>', { class: 'img-box' }).append(
		                $('<img/>', { src: e.target.result }) //readAsDataURL로 얻은 결과값
		            );
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
		            
		            $card.append($imgBox, $imgInfo);
		            $container.append($div, $card);
		            $previewCon.append($container);
		        };
		        reader.readAsDataURL(file);
		    });
		    $(input).val("");
		    checkFormValidity();
		}
		
		function updateInputFiles() {
		    const dataTransfer = new DataTransfer();
		    selectedFiles.forEach(file => {
		        dataTransfer.items.add(file);
		    });
		    
		    document.querySelector('#file-upload').files = dataTransfer.files;
		}
		
		function checkFormValidity(){
    		let isAllTextFilled  = true;
    		
    		$('.check').each(function(){
				if($(this).val().trim() === ""){
					isAllTextFilled = false;
					return false;
				}
			});
			
			const isSelectedFiles = selectedFiles.length > 0;
			
			const isSpanText = $('#msgArea').text().trim().length > 0;
			
			if(isAllTextFilled && isSelectedFiles && isSpanText){
				$('#btnSubmit').prop('disabled', false);
			}else{
				$('#btnSubmit').prop('disabled', true);
			}
			
    	}
		
		$('#btnCheckCode').click(function() {
			const nationCode = $('#nationCode').val().trim();
			const $msgArea = $('#msgArea');
			
			if(!nationCode){
				alert("국가 코드를 입력해주세요.");
				$('#nationCode').focus();
				return;
			}
			
			$.ajax({
				type: "GET",
				url: "/checkNation",
				data: {"nationCode": nationCode},
				success: function(response){
					$msgArea.text(response);
					$msgArea.css({"color": "#7148fc", "font-size": "12px"});
					checkFormValidity();
				},
				error: function(xhr){
				 	$msgArea.text(xhr.responseText);
				 	$msgArea.css({"color": "red", "font-size": "12px"});
				 	$('#nationCode').val("");
				 	$('#btnSubmit').prop('disabled', true);
				 	$('#nationCode').focus();
				}
			});
		});
		
		function submitForm(){
			const formData = new FormData();
			
			const continenValue = $('input[name="continent"]:checked').val();
			formData.append("nationCode", $('#nationCode').val());
			formData.append("nationNameKo", $('#nationNameKo').val());
			formData.append("nationNameEn", $('#nationNameEn').val());
			formData.append("capitarKo", $('#capitarKo').val());
			formData.append("capitarEn", $('#capitarEn').val());
			formData.append("continent", continenValue);
			
			selectedFiles.forEach(file => {
				formData.append("uploadFile", file);
			});
			
			$('input[name$=".imageType"]:checked').each(function() {
				formData.append($(this).attr('name'), $(this).val());
			});
			
			$.ajax({
				url: '/insertNation',
				type: 'POST',
				data: formData,
				processData: false,
				contentType: false,
				success: function(response){
					alert("등록되었습니다.");
					location.href = "adminNation";
				},
				error: function(xhr){
					alert(xhr.responseText);
				}
			});
		}
    	
    </script>

</body>
</html>