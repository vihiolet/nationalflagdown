</br>

# 국기 이미지 다운로드 웹 서비스(PICKFLAG)
>
<img width="200" alt="Image" src="https://github.com/user-attachments/assets/e9f772e0-be78-4e83-b919-c87b9001e195" />


> 기존 해외 서비스의 비용 부담과 낮은 접근성을 해결하기 위한 국기 이미지 다운로드 웹 서비스입니다.  
> 누구나 쉽고 빠르게 고화질 국기 이미지 파일을 다운로드할 수 있습니다.

</br>

## Tech Stack
* **Language**: Java 17
* **Framework**: Spring Boot
* **ORM**: JPA (Hibernate)
* **Database**: MySQL
* **Note**: 본 프로젝트는 가독성과 명확한 제어를 위해 **Lombok을 사용하지 않고** 개발되었습니다.

</br>

## 프로젝트 구조
```
PICKFLAG
├── src/main/java
│   └── com
│       ├── admin                             
│       │   ├── config          
│       │   │   ├── AdmSecurityConfige.java
│       │   │   └── AdmWebConfige.java
│       │   ├── controller
│       │   │   ├── AdmMemberController.java
│       │   │   └── AdmNationController.java
│       │   ├── domain                       
│       │   │   ├── AdmImageVO.java
│       │   │   ├── AdmMemberVO.java
│       │   │   └── AdmNationVO.java
│       │   ├── dto                           
│       │   │   ├── AdmLoginRequestDto.java
│       │   │   ├── AdmNationImgsDto.java
│       │   │   ├── AdmNationListDto.java
│       │   │   └── AdmSearchCond.java
│       │   ├── interceptor
│       │   │   └── LoginCheckInterceptor.java
│       │   ├── repository
│       │   │   ├── AdmImageRepository.java
│       │   │   ├── AdmMemberRepository.java
│       │   │   ├── AdmNationRepository.java
│       │   │   ├── AdmNationRepositoryImpl.java
│       │   │   └── AdmRepositoryCustom.java
│       │   └── service
│       │       ├── AdmMemberService.java
│       │       ├── AdmMemberServiceImpl.java
│       │       ├── AdmNationServie.java
│       │       └── AdmNatonServiceImpl.java
│       └── client          
│           ├── config
│           │   ├── MessageConfige.java
│           │   └── WebConfige.java
│           ├── controller
│           │   └── NationController.java
│           ├── domain
│           │   ├── AdmNaionVO.java
│           │   └── AdmNationVO.java
│           ├── dto
│           │   ├── NationDto.java
│           │   ├── NationImgsDto.java
│           │   ├── NationSearchCond.java
│           │   └── NationViewDto.java
│           ├── repository
│           │   ├── ImageRepository.java
│           │   ├── NationRepository.java
│           │   ├── NationRepositoryCustom.java
│           │   └── NationRepositoryImpl.java
│           ├── service
│           │   ├── NationServie.java
│           │   └── NatonServiceImpl.java
│           └── common
│               └── GlobalExceptionHandler.java
├── src/main/resources
│   ├── message
│   │   ├── messages_en.properties
│   │   └── messages_ko.properties 
│   ├── static
│   │   └── css
│   │       ├── adminList.css
│   │       ├── clientList.css
│   │       └── clientView.css
│   └── application.properties
└── src
    └── main
        └── webapp
            └── WEB-INF
                └── jsp
                    ├── admin
                    │   ├── inertNation.jsp
                    │   ├── listNation.jsp 
                    │   ├── login.jsp
                    │   └── updateNation.jsp
                    ├── client
                    │   ├── listNation.jsp
                    │   └── viewNation.jsp
                    └── error
                        └── error.jsp
```

</br>

## 개발 기간 및 개발 인원
- **개발 가간**: 2025.12 ~ 고도화 진행중
- **개발 인원**: 1인

</br>

## 주요 기능
### 사용자
**목록 조회**: 조건별 검색(국가 or 수도) 및 필터링(대륙) 기능
<img width="650" alt="Image" src="https://github.com/user-attachments/assets/b4b3e600-487d-4b82-927a-86aeb556a680" />
</br>
<figcaption>▲ 사용자 목록 화면</figcaption>

- 사용자 초기화면으로 국가 또는 수도로 찾고자 하는 국가를 검색할 수 있습니다.
- 대륙을 탭으로 나누어 대륙별로 국가를 찾을 수 있습니다.
- 우측 상단에서 다국어 기능을 이용할 수 있습니다. 기본은 한국어이며 영어를 클릭할 경우 영문 데이터를 제공합니다.
</br>

**상세 보기**: 국가 상세 정보 노출 및 국기 리소스 다운로드  

<img width="650" alt="Image" src="https://github.com/user-attachments/assets/24776b05-344f-4759-8cb2-e3e34dad8cac" />  
</br>
<figcaption>▲ 사용자 상세 보기 화면</figcaption>

- 페이지의 상단에서 국가의 기본 정보를 확인할 수 있습니다.
- 현재 해당 국가의 조회수와 국기 리소스 다운로드수를 확인할 수 있습니다.
- 업로드되어 있는 국기 리소스의 상태를 확인하고 다운로드 할 수 있습니다.

</br>

### 관리자
**콘텐츠 관리**: 목록 조회 및 데이터 삭제 기능  

<img width="650" alt="Image" src="https://github.com/user-attachments/assets/a31754bc-55a5-4689-a314-71d236bcf194" />
</br>
<figcaption>▲ 관리자 목록 화면</figcaption>

- 현재 등록된 국가 목록을 조회할 수 있습니다.
- 각 그리드의 가장 우측 아이콘으로 국가의 정보를 수정하거나 수정할 수 있습니다.
</br>

**데이터 관리**: 신규 등록 및 기존 정보 수정
<table>
    <tr>
        <td>
            <img width="650" alt="Image" src="https://github.com/user-attachments/assets/22e830d3-c705-4ce5-b94d-4eb4b9dfcafc" />
        </td>
        <td>
            <img width="650" alt="Image" src="https://github.com/user-attachments/assets/2481bb91-c1ac-4e16-b497-87d1b819fc35" />
        </td>
    </tr>
</table>
<figcaption>▲ 관리자 등록 화면</figcaption>

- 등록 시 국기 리소스를 업로드하면 이미지 파일의 미리보기를 제공합니다.  
<table>
    <tr>
        <td>
            <img width="650" alt="Image" src="https://github.com/user-attachments/assets/19bbd95d-8483-4766-8c7f-c690d9fd2d3e" />
        </td>
        <td>
            <img width="650" alt="Image" src="https://github.com/user-attachments/assets/a9cd79c8-8bac-4e9c-969b-8991eed0bae3" />
        </td>
    </tr>
</table>
<figcaption>▲ 관리자 수정 화면</figcaption>

- 수정 시 이미 등록된 국기 리소스를 삭제할 수 있습니다.
</br>

## Architecture
- JPA를 활용한 도메인 모델링 및 DTO 패턴 적용
>
<img width="1370" alt="Image" src="https://github.com/user-attachments/assets/e8530fbd-68b1-4b40-b266-487e11aeb79c" />
