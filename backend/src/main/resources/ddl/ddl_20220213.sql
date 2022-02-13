CREATE TABLE tb_terms_cond_mnt (
      terms_no int4 NOT NULL DEFAULT nextval('seq_food_no'::regclass),
      title varchar(100) NOT NULL,
      "content" varchar(3000) NOT NULL,
      register_date timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
      modified_date timestamptz NULL DEFAULT CURRENT_TIMESTAMP,
      user_id varchar(20) NOT NULL,
      CONSTRAINT tb_terms_cond_mnt_pk PRIMARY KEY (terms_no),
      CONSTRAINT tb_terms_cond_mnt_fk FOREIGN KEY (user_id) REFERENCES tb_user(user_id)
);
COMMENT ON TABLE tb_terms_cond_mnt IS '이용약관 관리 페이지';
