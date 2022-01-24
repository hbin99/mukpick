

/* 회원 테이블 */
CREATE table TB_USER (
                         user_id 				VARCHAR(20)													NOT NULL
    ,password				VARCHAR(128)													NOT NULL
    ,user_name				VARCHAR(100)													NOT NULL
    ,phone				    VARCHAR(13)												    NOT NULL
    ,profile_img			VARCHAR(200)		                 					      -- 기본 이미지 경로 지정
    ,email					VARCHAR(100)												    NOT NULL
    ,role_type				CHAR(1)	                    					                NOT NULL  -- (1:시스템 관리자, 2:일반 유저, 3:탈퇴 유저, 4:정지 유저)
    ,gender					CHAR(1)															NOT NULL
    ,age					INTEGER													        NOT NULL
    ,register_date		    DATE						DEFAULT CURRENT_DATE				NOT NULL
);

ALTER TABLE TB_USER ADD CONSTRAINT PK_USER_USER_ID PRIMARY KEY (user_id);
ALTER TABLE TB_USER ADD CONSTRAINT UNI_USER_PHONE UNIQUE (phone);
ALTER TABLE TB_USER ADD CONSTRAINT UNI_USER_EMAIL UNIQUE (email);

/* 시퀀스 생성 */
CREATE SEQUENCE SEQ_CALENDAR_NO;

/* 캘린더 테이블 */
CREATE TABLE TB_CALENDAR (
     calendar_no			INTEGER		DEFAULT NEXTVAL('SEQ_CALENDAR_NO')		NOT NULL
    ,user_id			VARCHAR(20)															NOT NULL
    ,weather_cd	        INTEGER
    ,calendar_date		DATE																NOT NULL
    ,feeling_cd			INTEGER
    ,memo				VARCHAR(100)
    ,address			VARCHAR(100)
    ,register_date		DATE						DEFAULT CURRENT_DATE					NOT NULL
);

ALTER TABLE TB_CALENDAR ADD CONSTRAINT PK_CALENDAR_CALENDAR_NO PRIMARY KEY (calendar_no);
ALTER TABLE TB_CALENDAR	ADD CONSTRAINT FK_CALENDAR_USER_ID FOREIGN KEY (user_id) REFERENCES TB_USER(user_id) ON DELETE CASCADE;




/* 시퀀스 생성*/
CREATE SEQUENCE SEQ_SEARCH_NO;


/* 검색 관리 테이블 */
CREATE TABLE TB_SEARCH_MNT (
    search_no INTEGER PRIMARY KEY DEFAULT NEXTVAL('SEQ_SEARCH_NO') NOT NULL
    ,search_text VARCHAR(512) NOT NULL
    ,search_cnt INTEGER DEFAULT 1 NOT NULL
    ,valid_date DATE NOT NULL
    ,register_date DATE DEFAULT CURRENT_DATE NOT NULL
);


/* 시퀀스 생성*/
CREATE SEQUENCE SEQ_TASTE_NO;

/* 개인 취향 테이블 */
CREATE TABLE TB_PERSONAL_TASTE (
    taste_no	INTEGER NOT NULL DEFAULT NEXTVAL('SEQ_TASTE_NO')  PRIMARY KEY
    ,user_id VARCHAR(20) NOT NULL
    ,weather_cd INTEGER
    ,feeling_cd INTEGER
    ,food_cd INTEGER NOT NULL
    ,register_date DATE default CURRENT_DATE NOT NULL
    ,count INTEGER DEFAULT 1
    ,CONSTRAINT FK_PERSONAL_TASTE_USER_ID FOREIGN KEY(user_id)
        REFERENCES TB_USER(user_id) ON DELETE CASCADE
);

/* 시퀀스 생성*/
CREATE SEQUENCE SEQ_code_no;

/* 코드 관리 */
CREATE TABLE TB_CODE(
     code_no NUMERIC(6) DEFAULT NEXTVAL('SEQ_CODE_NO') not null
    ,code_name VARCHAR(50) not null
    ,group_type VARCHAR(50) not null
    ,constraint pk_code primary key(code_no, code_name)
);

/* 식당 시퀀스 */
CREATE SEQUENCE SEQ_restaurant_no;

/* 식당 */
CREATE TABLE TB_RESTAURANT(
   restaurant_no INTEGER primary key DEFAULT NEXTVAL('SEQ_RESTAURANT_NO') not null
    ,restaurant_name VARCHAR(100) not null
    ,category VARCHAR(256) not null
    ,telephone VARCHAR(13) not null
    ,address VARCHAR(256) not null
    ,mapx VARCHAR(50) not null
    ,mapy VARCHAR(50) not null
    ,score NUMERIC(2,1) not null
    ,register_date date default CURRENT_DATE not null
);

/* 시퀀스 */
CREATE SEQUENCE SEQ_bookmark_no;
/* 북마크 */
CREATE TABLE TB_BOOKMARK(
     bookmark_no INTEGER primary key DEFAULT NEXTVAL('SEQ_bookmark_no') not null
    ,user_id VARCHAR(20) not null
    ,restaurant_no INTEGER not null
    ,register_date date default CURRENT_DATE not null
    ,constraint fk_user_id FOREIGN KEY(user_id) REFERENCES TB_USER(user_id) ON DELETE CASCADE
    ,constraint fk_restaurant_no FOREIGN KEY(restaurant_no) REFERENCES TB_RESTAURANT(restaurant_no) ON DELETE CASCADE
);


/* 후기 시퀀스 */
CREATE SEQUENCE SEQ_REVIEW_NO;


/* 리뷰 테이블 */
CREATE TABLE TB_REVIEW(
     REVIEW_NO INTEGER NOT NULL DEFAULT NEXTVAL('SEQ_REVIEW_NO') PRIMARY KEY
    ,CONTENT VARCHAR(100) NOT NULL
    ,REGISTER_DATE DATE DEFAULT CURRENT_DATE NOT NULL
    ,MODIFIED_DATE DATE
    ,USER_ID VARCHAR(20) NOT NULL
    ,SCORE INTEGER NOT NULL
    ,RESTAURANT_NO INTEGER NOT NULL
    ,IMG_PATH VARCHAR(100) NOT NULL
    ,CONSTRAINT FK_REVIEW_USER_ID FOREIGN KEY(USER_ID)
        REFERENCES TB_USER(USER_ID) ON DELETE CASCADE
    ,CONSTRAINT FK_REVIEW_TO_RESTAURANT_ID FOREIGN KEY(RESTAURANT_NO)
        REFERENCES TB_RESTAURANT(RESTAURANT_NO) ON DELETE CASCADE
);


/* 공지 시퀀스 */
CREATE SEQUENCE SEQ_NOTICE_NO;


/* 공지 */
CREATE TABLE TB_NOTICE(
      NOTICE_NO INTEGER NOT NULL DEFAULT NEXTVAL('SEQ_NOTICE_NO') PRIMARY KEY
    ,TITLE VARCHAR(100) NOT NULL
    ,CONTENT VARCHAR(3000) NOT NULL
    ,REGISTER_DATE DATE NOT NULL
    ,MODIFIED_DATE DATE
    ,IS_POPUP CHAR(1) DEFAULT 'N' NOT NULL
    ,START_DATE DATE
    ,END_DATE DATE
    ,USER_ID VARCHAR(20) NOT NULL
    ,VIEW_CNT INTEGER  DEFAULT 0 NOT NULL
    ,CONSTRAINT FK_NOTICE_USER_ID FOREIGN KEY(USER_ID)
        REFERENCES TB_USER(USER_ID) ON DELETE CASCADE
);

/* 시퀀스 생성*/
CREATE SEQUENCE SEQ_FOOD_NO;

/* 음식 테이블 */
CREATE TABLE TB_FOOD(
     food_no INTEGER NOT NULL DEFAULT NEXTVAL('SEQ_FOOD_NO') PRIMARY KEY
    ,food_name VARCHAR(100) NOT NULL
    ,is_show CHAR(1) DEFAULT 'Y' NOT NULL
    ,is_default CHAR(1) DEFAULT 'N' NOT NULL
    ,register_date DATE DEFAULT CURRENT_DATE NOT NULL
);



/* 시퀀스 생성*/
CREATE SEQUENCE SEQ_TAG_HISTORY;

/* 개인 취향 테이블 */
CREATE TABLE TB_TAG_HISTORY (
    history_no	INTEGER NOT NULL DEFAULT NEXTVAL('SEQ_TAG_HISTORY') PRIMARY KEY
    ,user_id VARCHAR(20) NOT NULL
    ,weather_cd INTEGER
    ,feeling_cd INTEGER
    ,food_cd INTEGER NOT NULL
    ,register_date DATE default CURRENT_DATE NOT NULL
    ,CONSTRAINT FK_TAG_HISTORY_USER_ID FOREIGN KEY(user_id)
        REFERENCES TB_USER(user_id) ON DELETE CASCADE
);
