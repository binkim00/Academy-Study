----------------------------------시퀀스-----------------------------------------
--시퀀스는 자동으로 일련번호를 생성해주는 객체
--주로 기본 키(primary key) 값으로 사용할 고유한 숫자를 만들 때 사용
CREATE SEQUENCE jno_seq
INCREMENT BY 1
START WITH 100
MAXVALUE 110
MINVALUE 90
CYCLE
CACHE 2;

CREATE TABLE s_order
(ord_no NUMBER(4),
ord_name VARCHAR2(10),
p_name VARCHAR2(20),
p_qty NUMBER );

-- s_order 테이블에 한 건의 주문 데이터를 삽입한다.
INSERT INTO s_order 
VALUES (jno_seq.NEXTVAL,   -- ① ord_no: 시퀀스 jno_seq에서 다음 값을 가져와 자동으로 주문 번호 설정
        'James',           -- ② ord_name: 주문자 이름으로 'James' 입력
        'apple',           -- ③ p_name: 주문한 상품 이름으로 'apple' 입력
        5                  -- ④ p_qty: 주문 수량으로 5개 입력
);
INSERT INTO s_order
VALUES(jno_seq.nextval, 'Ford', 'berry',3);

SELECT * from s_order;


CREATE SEQUENCE  "SYSTEM"."jno_seq_rev"
MINVALUE 0
MAXVALUE 20
INCREMENT BY -2
START WITH 10;

CREATE TABLE s_rev1 (no NUMBER);

insert into s_rev1 VALUES(SYSTEM.jno_seq_rev.nextval);

drop SEQUENCE "SYSTEM"."jno_seq_rev";

--------------------------------- 시노님 ----------------------------------------
--긴 이름이나 다른 스키마(SCHEMA)의 객체를 짧게 또는 편하게 사용하기 위한 별칭
--스키마는 데이터베이스 안에서 "사용자 계정이 소유한 객체들의 집합"
--주로 다른 사용자의 테이블, 시퀀스, 뷰 등에 접근할 때 사용
--공용(PUBLIC) 또는 **개인(PRIVATE)**으로 생성 가능

-- 개인용 시노님 (현재 사용자만 사용 가능)
CREATE SYNONYM 시노님이름 FOR 실제객체이름;
--
-- 공용 시노님 (모든 사용자 가능)
CREATE PUBLIC SYNONYM 시노님이름 FOR 사용자명.객체이름;

-- 예시 1: 자신의 시퀀스에 시노님 만들기
-- 시퀀스 이름이 길 때
CREATE SYNONYM jrev FOR SYSTEM.jno_seq_rev;
-- 사용 시: SYSTEM.jno_seq_rev 대신 jrev만 써도 됨
SELECT jrev.NEXTVAL FROM dual;

-- 예시 2: 다른 사용자의 테이블에 시노님 만들기
-- SYSTEM 사용자의 테이블을 접근하고 싶을 때
CREATE SYNONYM emp_sys FOR SYSTEM.emp;
-- 사용 시
SELECT * FROM emp_sys;
-- 단, 해당 사용자에게 권한이 있어야 가능

-- 시노님 조회 방법 --
-- 자신의 시노님 확인
SELECT * FROM USER_SYNONYMS;
-- 전체 시노님 확인 (권한 필요)
SELECT * FROM ALL_SYNONYMS;
-- 공용 시노님 확인
SELECT * FROM DBA_SYNONYMS;
