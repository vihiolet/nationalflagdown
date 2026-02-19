# 국기 이미지 다운로드 웹 서비스(PICKFLAG)
>
<img width="200" alt="Image" src="https://github.com/user-attachments/assets/e9f772e0-be78-4e83-b919-c87b9001e195" />


> 기존 해외 서비스의 비용 부담과 낮은 접근성을 해결하기 위한 국기 이미지 다운로드 웹 서비스입니다.  
> 누구나 쉽고 빠르게 고화질 국기 이미지 파일을 다운로드할 수 있습니다.
>
## Tech Stack
* **Language**: Java 17
* **Framework**: Spring Boot
* **ORM**: JPA (Hibernate)
* **Database**: MySQL
* **Note**: 본 프로젝트는 가독성과 명확한 제어를 위해 **Lombok을 사용하지 않고** 개발되었습니다.

## 주요 기능
### 사용자
- **목록 조회**: 조건별 검색 및 필터링 기능
- **상세 보기**: 데이터 상세 정보 노출 및 리소스 다운로드

### 관리자
- **콘텐츠 관리**: 목록 조회 및 데이터 삭제 기능
- **데이터 관리**: 신규 등록 및 기존 정보 수정

## Architecture
- JPA를 활용한 도메인 모델링 및 DTO 패턴 적용
>
<img width="1370" alt="Image" src="https://github.com/user-attachments/assets/e8530fbd-68b1-4b40-b266-487e11aeb79c" />
