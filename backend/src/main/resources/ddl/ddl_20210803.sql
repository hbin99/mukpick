-- 검색 유효기간 조회하는 펑션
CREATE OR REPLACE FUNCTION JUMPSTD.FN_GET_SEARCH_VALID_DATE
RETURN VARCHAR2 IS VALID_DATE VARCHAR2(10);
BEGIN SELECT CODE_NAME INTO VALID_DATE
      FROM TB_CODE
      WHERE GRUOP_TYPE = 'VALID_DATE';
RETURN VALID_DATE;
END;