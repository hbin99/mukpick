/* 회원 테이블 */
CREATE TABLE TB_USER (
     user_id 				VARCHAR2(20)													NOT NULL
    ,password				VARCHAR2(128)													NOT NULL
    ,user_name				VARCHAR2(100)													NOT NULL
    ,phone				    VARCHAR2(13)												    NOT NULL
    ,profile_img			VARCHAR2(200)		        DEFAULT('')           					      -- 기본 이미지 경로 지정
    ,email					VARCHAR2(100)												    NOT NULL
    ,role_type				CHAR(1)	                    					                NOT NULL  -- (1:시스템 관리자, 2:일반 유저, 3:탈퇴 유저, 4:정지 유저)
    ,gender					CHAR(1)															NOT NULL
    ,age					NUMBER													        NOT NULL
    ,register_date		    DATE						DEFAULT SYSDATE				        NOT NULL
);

ALTER TABLE TB_USER ADD CONSTRAINT PK_USER_USER_ID PRIMARY KEY (user_id);
ALTER TABLE TB_USER ADD CONSTRAINT UNI_USER_PHONE UNIQUE (phone);
ALTER TABLE TB_USER ADD CONSTRAINT UNI_USER_EMAIL UNIQUE (email);

/* 캘린더 테이블 */
CREATE TABLE TB_CALENDAR (
    calendar_no			NUMBER																NOT NULL
    ,user_id					VARCHAR2(20)													NOT NULL
    ,weather_cd	        NUMBER
    ,calendar_date		DATE																	NOT NULL
    ,feeling_cd			NUMBER
    ,memo						VARCHAR2(100)
    ,address					VARCHAR2(100)
    ,register_date		DATE						DEFAULT SYSDATE				NOT NULL
);

ALTER TABLE TB_CALENDAR ADD CONSTRAINT PK_CALENDAR_CALENDAR_NO PRIMARY KEY (calendar_no);
ALTER TABLE TB_CALENDAR	ADD CONSTRAINT FK_CALENDAR_USER_ID FOREIGN KEY (user_id) REFERENCES TB_USER(user_id) ON DELETE CASCADE;

CREATE SEQUENCE SEQ_CALENDAR_NO START WITH 1 INCREMENT BY 1 MAXVALUE 999999 CYCLE NOCACHE;

/* 검색 관리 테이블 */
CREATE TABLE TB_SEARCH_MNT (
	 search_no NUMBER PRIMARY KEY NOT NULL
	,search_text varchar2(512) NOT NULL
	,search_cnt number DEFAULT 1 NOT NULL
	,valid_date DATE NOT NULL
	,register_date DATE DEFAULT SYSDATE NOT NULL
);


/* 시퀀스 생성*/
CREATE SEQUENCE SEQ_SEARCH_NO START WITH 1 INCREMENT BY 1 MAXVALUE 999999 CYCLE NOCACHE;

/* 개인 취향 테이블 */
CREATE TABLE TB_PERSONAL_TASTE (
    taste_no	NUMBER NOT NULL PRIMARY KEY
    ,user_id VARCHAR2(20) NOT NULL
    ,weather_cd NUMBER
    ,feeling_cd NUMBER
    ,food_cd NUMBER NOT NULL
    ,register_date DATE default SYSDATE NOT NULL
    ,count NUMBER DEFAULT 1
    ,CONSTRAINT FK_PERSONAL_TASTE_USER_ID FOREIGN KEY(user_id)
        REFERENCES TB_USER(user_id) ON DELETE CASCADE
);

/* 시퀀스 생성*/
CREATE SEQUENCE SEQ_TASTE_NO START WITH 1 INCREMENT BY 1 MAXVALUE 999999 CYCLE NOCACHE;

CREATE SEQUENCE seq_code_no INCREMENT BY 1 START WITH 1 MAXVALUE 999999 CYCLE NOCACHE;
CREATE SEQUENCE seq_bookmark_no INCREMENT BY 1 START WITH 1 MAXVALUE 999999 CYCLE NOCACHE;
CREATE SEQUENCE seq_restaurant_no INCREMENT BY 1 START WITH 1 MAXVALUE 999999 CYCLE NOCACHE;

/* 코드 관리 */
CREATE TABLE TB_CODE(
    code_no number(6) not null
    ,code_name varchar2(50) not null
    ,gruop_type varchar2(50) not null
    ,constraint pk_code primary key(code_no, code_name)
);

/* 식당 */
CREATE TABLE TB_RESTAURANT(
    restaurant_no number primary key not null
    ,restaurant_name varchar2(100) not null
    ,category varchar2(256) not null
    ,telephone varchar2(13) not null
    ,address varchar2(256) not null
    ,mapx varchar2(50) not null
    ,mapy varchar2(50) not null
    ,score number(2,1) not null
    ,register_date date default sysdate not null
);

/* 북마크 */
CREATE TABLE TB_BOOKMARK(
    bookmark_no number primary key not null
    ,user_id varchar2(20) not null
    ,restaurant_no number not null
    ,register_date date default sysdate not null
    ,constraint fk_user_id FOREIGN KEY(user_id) REFERENCES TB_USER(user_id) ON DELETE CASCADE
    ,constraint fk_restaurant_no FOREIGN KEY(restaurant_no) REFERENCES TB_RESTAURANT(restaurant_no) ON DELETE CASCADE
);

/* 리뷰 테이블 */
CREATE TABLE TB_REVIEW(
     REVIEW_NO NUMBER NOT NULL PRIMARY KEY
    ,CONTENT VARCHAR2(100) NOT NULL
    ,REGISTER_DATE DATE DEFAULT SYSDATE NOT NULL
    ,MODIFIED_DATE DATE
    ,USER_ID VARCHAR2(20) NOT NULL
    ,SCORE NUMBER NOT NULL
    ,RESTAURANT_NO NUMBER NOT NULL
    ,IMG_PATH VARCHAR2(100) NOT NULL
    ,CONSTRAINT FK_REVIEW_USER_ID FOREIGN KEY(USER_ID)
        REFERENCES TB_USER(USER_ID) ON DELETE CASCADE
    ,CONSTRAINT FK_REVIEW_TO_RESTAURANT_ID FOREIGN KEY(RESTAURANT_NO)
        REFERENCES TB_RESTAURANT(RESTAURANT_NO) ON DELETE CASCADE
);

/* 후기 시퀀스 */
CREATE SEQUENCE SEQ_REVIEW_NO START WITH 1
    INCREMENT BY 1  MAXVALUE 999999 CYCLE NOCACHE;

/* 공지 */
CREATE TABLE TB_NOTICE(
    NOTICE_NO NUMBER NOT NULL PRIMARY KEY
    ,TITLE VARCHAR2(100) NOT NULL
    ,CONTENT VARCHAR2(3000) NOT NULL
    ,REGISTER_DATE DATE NOT NULL
    ,MODIFIED_DATE DATE
    ,IS_POPUP CHAR(1) DEFAULT 'N' NOT NULL
    ,START_DATE DATE
    ,END_DATE DATE
    ,USER_ID VARCHAR2(20) NOT NULL
    ,VIEW_CNT NUMBER  DEFAULT 0 NOT NULL
    ,CONSTRAINT FK_NOTICE_USER_ID FOREIGN KEY(USER_ID)
        REFERENCES TB_USER(USER_ID) ON DELETE CASCADE
);
/* 공지 시퀀스 */
CREATE SEQUENCE SEQ_NOTICE_NO START WITH 1
    INCREMENT BY 1 MAXVALUE 999999 CYCLE NOCACHE;


/* 음식 테이블 */
CREATE TABLE TB_FOOD(
food_no NUMBER NOT NULL PRIMARY KEY
,food_name VARCHAR2(100) NOT NULL
,is_show CHAR(1) DEFAULT 'Y' NOT NULL
,is_default CHAR(1) DEFAULT 'N' NOT NULL
,register_date DATE DEFAULT SYSDATE NOT NULL
);

CREATE SEQUENCE SEQ_FOOD_NO START WITH 1 INCREMENT BY 1
MAXVALUE 999999 CYCLE NOCACHE;


/* 개인 취향 테이블 */
CREATE TABLE TB_TAG_HISTORY (
    history_no	NUMBER NOT NULL PRIMARY KEY
    ,user_id VARCHAR2(20) NOT NULL
    ,weather_cd NUMBER
    ,feeling_cd NUMBER
    ,food_cd NUMBER NOT NULL
    ,register_date DATE default SYSDATE NOT NULL
    ,CONSTRAINT FK_TAG_HISTORY_USER_ID FOREIGN KEY(user_id)
        REFERENCES TB_USER(user_id) ON DELETE CASCADE
);

/* 시퀀스 생성*/
CREATE SEQUENCE SEQ_TAG_HISTORY START WITH 1 INCREMENT BY 1 MAXVALUE 999999 CYCLE NOCACHE;