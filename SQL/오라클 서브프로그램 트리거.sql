-------------------------------오라클 서브프로그램---------------------------------
--트리거 : 데이터베이스의 특정 이벤트가 발생했을 때 자동으로 실행되는 PL/SQL 블록
--DML 이벤트(INSERT, UPDATE, DELETE)에 반응하도록 설계
--데이터의 무결성 유지, 로그 기록, 자동 처리 등에 활용

--BEFORE 트리거 : 트리거 이벤트 전에 실행됨 (주로 유효성 검사)
--AFTER 트리거 : 트리거 이벤트 후에 실행됨 (감사 로그 작성 등)
--INSTEAD OF 트리거 : 뷰(View)에서 DML 대신 실행되는 트리거
--FOR EACH ROW : 행 단위(Row-Level) 트리거로, 변경된 각 행마다 실행
--WHEN 조건 : 특정 조건일 때만 트리거 작동

--트리거 예제
CREATE TABLE tab_order (
    ord_no   NUMBER,
    ord_name  VARCHAR2(50),
    ord_date DATE  
);

-- 주문이 INSERT 되기 전에 작동하는 트리거 생성
CREATE OR REPLACE TRIGGER tri_order
BEFORE INSERT ON tab_order       -- tab_order 테이블에 INSERT가 일어나기 직전에 작동
BEGIN
    -- 현재 시각이 17:40 ~ 17:50 사이가 아니라면 예외 발생
    IF TO_CHAR(SYSDATE,'HH24:MI') NOT BETWEEN '17:40' AND '17:50' THEN
        -- 사용자 정의 에러 발생시키기: 에러 코드 -20100, 메시지 'Time Error!'
        RAISE_APPLICATION_ERROR(-20100,'Time Error!');
    END IF;
END;
/

-- 현재 서버 시각을 확인하는 쿼리 (테스트용)
SELECT SYSDATE FROM DUAL;

-- tab_order 테이블에 한 건의 주문 데이터 삽입 시도
INSERT INTO tab_order VALUES (1, 'apple', SYSDATE);
--결과
--ORA-20100: Time Error!
--ORA-06512: at "TRI_ORDER", line 3

--트리거의 검사 조건을 WHEN절에서 더 구체저그로 지정
-- '체리'라는 주문자가 18:30~18:40 사이가 아니면 예외 발생
CREATE OR REPLACE TRIGGER tri_order3
BEFORE INSERT ON tab_order
FOR EACH ROW       -- 행 단위 트리거
WHEN (NEW.ord_name = '체리')  -- 입력되는 값이 '체리'일 때만 트리거 작동
BEGIN
    -- 현재 시각이 18:30 ~ 18:40 사이가 아니면 오류 발생
    IF TO_CHAR(SYSDATE,'HH24:MI') NOT BETWEEN '18:30' AND '18:40' THEN
        RAISE_APPLICATION_ERROR(-20100, 'Cherry 주문은 18:30~18:40 사이에만 가능합니다!');
    END IF;
END;
/


--기존 테이블에 데이터가 업데이트될 때 기존 내용을 백업 테이블에 옮겨 놓는 트리거를 생성. 
--삭제되는 특정 행이 트리거의 대상이므로 행 레벨 트리거를 사용
-- 원본 테이블
CREATE TABLE tab_test1 (
    no    NUMBER,           -- 번호
    name  VARCHAR2(50)      -- 이름
);

-- 백업용 테이블
CREATE TABLE tab_test2 (
    no    NUMBER,           -- 삭제된 번호
    name  VARCHAR2(50)      -- 삭제된 이름
);

-- 테스트 데이터 삽입
INSERT INTO tab_test1 VALUES (1, 'apple');
INSERT INTO tab_test1 VALUES (2, 'banana');
commit;

-- 행이 삭제될 때 삭제된 값을 백업 테이블에 저장
CREATE OR REPLACE TRIGGER move_data
BEFORE DELETE ON tab_test1
FOR EACH ROW
BEGIN
    -- OLD는 삭제되는 데이터를 참조
    INSERT INTO tab_test2 (no, name)
    VALUES (:OLD.no, :OLD.name);
END;
/

-- 데이터 삭제
DELETE FROM tab_test1 WHERE no = 1;
DELETE FROM tab_test1 WHERE no = 2;

-- 백업 테이블 확인
SELECT * FROM tab_test2;


--기존 테이블의 데이터가 delete될 때, 기존 내용을 백업 테이블로 이동시키며
--이때 백업 테이블에 삭제한 시간, 삭제 전 데이터, 삭제한 사용자 이름 모두 기록하는 트리거를 생성.
--가장 자주 사용할만한 쿼리 예시(실무에서 적합?)
-- 원본 테이블
CREATE TABLE tab_test3 (
    no    NUMBER,            -- 고유 번호
    name  VARCHAR2(50)       -- 이름
);

-- 백업 테이블
CREATE TABLE tab_test4 (
    no          NUMBER,           -- 삭제된 번호
    name        VARCHAR2(50),     -- 삭제된 이름
    del_time    DATE,             -- 삭제된 시각
    deleted_by  VARCHAR2(30)      -- 삭제한 사용자 이름
);

--트리거 생성
CREATE OR REPLACE TRIGGER delete_trigger
BEFORE DELETE ON tab_test3
FOR EACH ROW
BEGIN
    INSERT INTO tab_test4 (
        no, name, del_time, deleted_by
    )
    VALUES (
        :OLD.no,
        :OLD.name,
        SYSDATE,                   -- 삭제 시각
        USER                       -- 삭제한 사용자 이름 (Oracle 내장 사용자)
    );
END;
/

-- 테스트용 데이터 입력
INSERT INTO tab_test3 VALUES (1, 'apple');
INSERT INTO tab_test3 VALUES (2, 'banana');
commit;

-- 특정 행 삭제
DELETE FROM tab_test3 WHERE no = 2;

-- 백업 결과 확인
SELECT * FROM tab_test4;

--------------------------------------------------------------------------------
--선생님 언급
--트리거의 조건 분기를 설계하는 기본 구조를 연습
--다음을 익히기 위한 교육용 트리거
--DML 이벤트 구분 방법 (INSERTING, UPDATING, DELETING)
--트리거에서의 조건 분기 문법 (IF, ELSIF)
--예외 발생 및 메시지 반환 연습 (RAISE_APPLICATION_ERROR)
CREATE OR REPLACE TRIGGER trg_test1
BEFORE INSERT OR UPDATE OR DELETE ON emp
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        -- INSERT 시 예외 발생 (메시지 'I')
        RAISE_APPLICATION_ERROR(-20000, 'I');
    
    ELSIF UPDATING THEN
        -- UPDATE 시 예외 발생 (메시지 'U')
        RAISE_APPLICATION_ERROR(-20000, 'U');
    
    ELSIF DELETING THEN
        -- DELETE 시 예외 발생 (메시지 'D')
        RAISE_APPLICATION_ERROR(-20000, 'D');
    END IF;
END;
/
