--TB_USER ROLE_TYPE 컬럼타입 변경, UPDATE_DATE 컬럼 추가
ALTER TABLE public.tb_user ALTER COLUMN role_type TYPE int USING role_type::int;
ALTER TABLE public.tb_user ADD update_date timestamp NULL;
