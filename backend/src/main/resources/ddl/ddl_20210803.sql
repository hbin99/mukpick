-- 검색 유효기간 조회하는 펑션
CREATE FUNCTION FN_GET_SEARCH_VALID_DATE()
    RETURNS VARCHAR AS $$
DECLARE valid_date VARCHAR(10);
BEGIN
select CODE_NAME INTO VALID_DATE
FROM TB_CODE
WHERE GROUP_TYPE = 'VALID_DATE';

RETURN VALID_DATE;
END;
$$  LANGUAGE plpgsql;