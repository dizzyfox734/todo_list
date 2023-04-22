# docker_empty
springboot 도커 깡통 세팅


## 프로젝트 소개
git actions로 코드를 도커에 올려 자동배포할 수 있도록 함


## 개발환경
* /docker/docker-compose.yaml
* /web/build.gradle

참고

## 세팅 방법

### 프로젝트 설정
/web/settings.gradle의 rootProject.name 값 세팅

/web/src/main/java/org/dizzyfox734의 rootProject.name과 동일하게 projectname 폴더 이름 변경

/web/src/main/java/org/dizzyfox734/{projectname}/Application.java의 package 수정

/docker/env의 PROJECT_NAME 변수 설정 -> .env 생성


### 도커 포트 설정
/docker/docker-compose.yaml에서 변경


### git actions 설정
.github/workflow/deploy.yml를 참고하여 git actions에 변수 설정