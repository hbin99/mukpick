# mukpick


## 👪프로젝트 멤버

[김서영](https://github.com/seoyyyy)

[김혜빈](https://github.com/hbin99)

[배소현](https://github.com/baesohyun)

## 🍍기능리스트

<img width="2146" alt="먹픽" src="https://user-images.githubusercontent.com/54252085/126858741-005dd698-885e-4e77-ae0f-1802c6dee681.png">


## 🥑개발 기술

- springboot
- java11
- gradle7.0.2
- react
- git
- tomcat10
- oracle sql

## 🥕기능 상세 정의서

[기능 상세 정의서](https://docs.google.com/spreadsheets/d/17x7B2sLc9nB7mzgeUx4PosnLQnQa96lVDvcq-8DyTg4/edit?usp=sharing)

## 🍒 테이블정의서

[테이블 정의서](https://docs.google.com/spreadsheets/d/1PX5RF2CDvRfBcd2lHBmYPKD5pnriD_d6PYupaWuXPmg/edit?usp=sharing)

# 🐨  커밋 규칙

1. 기능 단위로 branch 생성
2. main이 아닌 다른 branch에서 origin/main으로 직접 push 하지 않는다.
3. 두번째 주, 네번째 주 일요일에 한사람이 기능 통합 작업을 진행한다.
    - 기능 통합 branch인 dev_main에서 각 기능 branch들을 merge한다.
    - 로컬 main으로 checkout하여 각 기능들을 통합한 dev_main branch를 merge 한다.
    - 기능 테스트 후 이상이 없으면 origin/main으로 push 한다.
4. merge된 main의 tag를 v1.0, v2.0과 같이 생성한다.
    - 각 맡은 기능을 완료하기 전까지는 v1.1 v1.2와 같이 minor version을 올린다.
    - 맡은 기능이 완료 된 후 새로운 기능을 구현할 때에 major version 올린다.
5. 각 브랜치에서 업데이트된 origin/main을 pull 한다.
6. 예민한 정보가 담긴 파일들을 .gitignore 파일에 등록하여 commit에 올리지 않는다.
7. 커밋 메세지는 한글로 작성하며 [추가], [수정], [삭제], [버그] 와 같이 메세지 앞에 대괄호를 붙여주고 상세 메세지를 작성한다.
8. 커밋 메세지는 최대한 자세히 작성한다.

# 🥝 디렉터리 구조

1. 디렉터리 구조는 기능별로 구분한다.

디렉터리 구조 예시

- config
- 기능1
    - api
    - service
    - dao
    - mapper
    - domain
    - dto
- common
- exception
