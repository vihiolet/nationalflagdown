<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - 국가 정보 수정</title>
    <link rel="stylesheet" href="/css/adminList.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    <header class="main-header">
        <div class="header-left"><h1 class="logo">Logo</h1></div>
        <nav class="header-center">
            <a href="#" class="nav-item active">국가목록</a>
            <a href="#" class="nav-item">관리자 목록</a>
        </nav>
        <div class="header-right"><div class="profile-circle"></div></div>
    </header>

    <main class="content-container">
        <section class="content-title-area">
            <h2 class="content-title">국가 정보 등록</h2>
        </section>

        <div class="form-wrapper">
            <form action="insertNation" method="post" enctype="multipart/form-data">            
                <div class="form-group country-code-area">
                    <label>국가 코드 <span class="required">*</span></label>
                    <div class="input-with-action">
                        <input type="text" name="nationCode" class="input-code" placeholder="예: KR" maxlength="2">
                        <button type="button" class="btn-check">중복 확인</button>
                        <span class="input-tip">ISO 3166-1 alpha-2 기준 (2자리 영문 대문자)</span>
                    </div>
                </div>
                
                <hr class="form-divider">
                
                <div class="form-row">
                    <div class="form-group">
                        <label>국가명</label>
                        <input type="text" name="nationNameKo" placeholder="예: 대한민국">
                    </div>
                    <div class="form-group">
                        <label>영문 국가명</label>
                        <input type="text" name="nationNameEn" placeholder="예: South Korea">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>수도명</label>
                        <input type="text" name="capitarKo" placeholder="예: 서울">
                    </div>
                    <div class="form-group">
                        <label>영문 수도명</label>
                        <input type="text" name="capitarEn" placeholder="예: Seoul">
                    </div>
                </div>

                <div class="form-group">
                    <label>대륙</label>
                    <div class="radio-group">
                        <label class="radio-item"><input type="radio" value="아시아" name="continent"/>아시아</label>
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
                        <label>국기 및 국가 이미지</label>
                        <label for="file-upload" class="btn-outline-sm">
	        				<span>+ 이미지 등록</span>
					    </label>
                        <input type="file" id="file-upload" name="uploadFile" accept="image/*" multiple style="display: none;" onchange="handleImageUpload(this)">
                    </div>
                    <div class="image-preview-grid">
                    </div>
                </div>

                <div class="form-footer">
                    <button type="button" class="btn-secondary" onclick="history.back()">목록으로</button>
                    <button type="submit" class="btn-primary">등록</button>
                </div>
            </form>
        </div>
    </main>

</body>
</html>