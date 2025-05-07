-- 1번
CREATE TABLE tcons (
    no      NUMBER(5)       CONSTRAINT tcons_no_pk PRIMARY KEY,
    name    VARCHAR2(20)    CONSTRAINT tcons_name_nn NOT NULL,
    jumin   VARCHAR2(13)    CONSTRAINT tcons_jumin_nn NOT NULL
                            CONSTRAINT tcons_jumin_uk UNIQUE,
    area    VARCHAR2(1)     CONSTRAINT tcons_area_ck CHECK (area IN ('1', '2', '3', '4')),
    deptno  VARCHAR2(6)     CONSTRAINT tcons_deptno_fk REFERENCES dept2(code)
);
-- 예시로 dept2 테이블을 만들어보자
CREATE TABLE dept2 (
    code VARCHAR2(6) PRIMARY KEY
);


-- 2번
-- 먼저 emp2.name에 UNIQUE 제약조건이 있어야 함
ALTER TABLE emp2
ADD CONSTRAINT emp2_name_uk UNIQUE (name);

-- 그런 다음 외래키 추가
ALTER TABLE tcons
ADD CONSTRAINT tcons_name_fk
FOREIGN KEY (name) REFERENCES emp2(name);


-- 3번
ALTER TABLE tcons
MODIFY CONSTRAINT tcons_jumin_uk
DISABLE NOVALIDATE;


-- 4번
-- (1) scott.exceptions 테이블이 없으면 먼저 생성
BEGIN
  EXECUTE IMMEDIATE 'CREATE TABLE scott.exceptions (
    row_id      ROWID,
    owner       VARCHAR2(30),
    table_name  VARCHAR2(30),
    constraint  VARCHAR2(30)
  )';
EXCEPTION
  WHEN OTHERS THEN NULL; -- 이미 존재하면 무시
END;
/

-- (2) jumin 제약조건 다시 활성화 + VALIDATE + 예외 저장
ALTER TABLE tcons
ENABLE VALIDATE CONSTRAINT tcons_jumin_uk
EXCEPTIONS INTO scott.exceptions;

-- 5번
SELECT col.table_name, col.column_name, col.constraint_name
FROM user_cons_columns col
JOIN user_constraints con
  ON col.constraint_name = con.constraint_name
WHERE col.table_name = 'EMP'
  AND con.constraint_name NOT LIKE 'SYS_%';

