-------------------------------Oracle Exception---------------------------------
--PL/SQL 예외
--프로그램 실행 중 발생할 수 있는 오류를 잡아서 프로그램이 비정상적으로 종료되지 않도록 처리하는 기능
--오라클에서 미리 정의된 예외 사용 예시
DECLARE
   v_ename   emp.ename%TYPE;   -- 사용자 입력 및 결과 저장
BEGIN
   -- 사용자 입력값: 이름에 포함될 알파벳 또는 문자열
   v_ename := '&이름에_포함된_문자';

   -- 해당 문자열이 포함된 사원 이름 검색
   SELECT ename
   INTO v_ename
   FROM emp
   WHERE ename LIKE '%' || v_ename || '%';

   DBMS_OUTPUT.PUT_LINE('사원이름: ' || v_ename);

EXCEPTION
   WHEN NO_DATA_FOUND THEN --없는 데이터
      DBMS_OUTPUT.PUT_LINE('해당 문자를 포함한 사원이 존재하지 않습니다.');

   WHEN TOO_MANY_ROWS THEN --2개 이상의 값
      DBMS_OUTPUT.PUT_LINE('여러 명이 해당 문자를 포함하고 있습니다. 조건을 더 구체화하세요.');

   WHEN OTHERS THEN --모든 예외처리
      DBMS_OUTPUT.PUT_LINE('기타 오류 발생: ' || SQLERRM);
END;
/

--프라그마(PRAGMA) : 컴파일러에게 특정 동작을 지시하는 컴파일 타임 지시문
--컴파일러에게 특별한 지시를 내려주는 예약어로, 실행되는 로직에는 영향이 없지만 성능이나 예외 처리 방식 등에 영향
--EXCEPTION_INIT : 사용자 정의 예외를 특정 Oracle 에러 번호와 연결
--Oracle에서 발생하는 오류 번호를 사용자 정의 예외와 연결해서 WHEN 절에서 사용할 수 있게 함
DECLARE
   e_dup_val EXCEPTION;  
   -- 사용자가 직접 선언한 예외 객체 (e_dup_val이라는 이름의 예외를 만든다)

   PRAGMA EXCEPTION_INIT(e_dup_val, -00001);  
   -- 이 사용자 정의 예외를 Oracle 에러 번호 -00001 (고유 제약 조건 위반)에 연결함
   -- 즉, ORA-00001이 발생하면 e_dup_val 예외로 간주하라는 지시문

BEGIN
   -- 예외 발생 가능성이 있는 코드 작성 시작

   INSERT INTO emp(empno, ename) VALUES (7369, 'DUPLICATE');
   -- emp 테이블에 empno가 7369인 데이터를 삽입
   -- 만약 empno 7369가 이미 존재한다면 PRIMARY KEY 중복이 되어 에러 발생 (ORA-00001)

EXCEPTION
   WHEN e_dup_val THEN
   -- 만약 위에서 -00001 에러가 발생하면 e_dup_val 예외로 처리

      DBMS_OUTPUT.PUT_LINE('중복된 값 오류 발생');
      -- 예외 처리: 사용자에게 오류 메시지를 출력

END;
-- 블록 끝


--AUTONOMOUS_TRANSACTION : 독립적인 트랜잭션으로 실행되게 설정
--현재 트랜잭션과 독립적으로 작동하는 트랜잭션을 만들 수 있음
--로그 저장, 감사 처리, 에러 기록용 프로시저 등에 자주 사용
CREATE OR REPLACE PROCEDURE log_error(p_msg VARCHAR2) IS
-- log_error라는 이름의 프로시저를 만든다.
-- p_msg는 사용자가 보내주는 오류 메시지 (문자열 하나를 입력받는다).

   PRAGMA AUTONOMOUS_TRANSACTION;
   -- 이 프로시저는 따로 동작하는 트랜잭션이다.
   -- 원래 프로그램이 실패하더라도, 이 안에서 한 일은 따로 저장된다 (ROLLBACK 안 됨).
   -- 예를 들어, 오류가 났을 때 이 프로시저로 "기록"만 남겨두고 싶을 때 쓰는 기능이다.

BEGIN
   INSERT INTO error_log (log_time, message)
   VALUES (SYSDATE, p_msg);
   -- error_log 테이블에 현재 시간과 메시지를 저장한다.
   -- SYSDATE: 지금 시간 / p_msg: 입력받은 메시지

   COMMIT;
   -- 저장 완료! 꼭 직접 COMMIT 해줘야 저장이 확정된다.
   -- COMMIT 안 하면 이 기록은 사라진다.

END;
-- 프로시저 끝



--SERIALLY_REUSABLE : 메모리 재사용을 위한 지시문 (패키지 관련)
--패키지의 전역 변수 사용 시 메모리 효율을 높이기 위해 사용
--서브 프로그램이 끝나면 패키지 상태가 초기화됨
CREATE OR REPLACE PACKAGE my_pkg IS
   PRAGMA SERIALLY_REUSABLE;
   -- 이 패키지는 호출이 끝나면 메모리(상태)를 초기화하라는 지시문
   -- 서버 자원을 아끼기 위해 상태를 재사용하지 않겠다는 의미

   g_count NUMBER := 0;
   -- 전역 변수 g_count를 0으로 초기화 (패키지 내에서 공유)

   PROCEDURE show_count;
   -- g_count 값을 증가시키고 출력하는 프로시저 선언
END;

CREATE OR REPLACE PACKAGE BODY my_pkg IS
   PRAGMA SERIALLY_REUSABLE;
   -- 패키지 본문에도 반드시 동일한 PRAGMA 선언이 필요함

   PROCEDURE show_count IS
   BEGIN
      g_count := g_count + 1;
      -- 전역 변수 g_count를 1 증가시킴

      DBMS_OUTPUT.PUT_LINE('Count: ' || g_count);
      -- 현재 값을 출력
   END;
END;


--RAISE를 사용하여 예외 상황 처리하기
--예외를 고의로 발생시켜서 EXCEPTION 블록으로 흐름을 넘기는 것
DECLARE
   no_empno EXCEPTION;                    -- ❗ 내가 만든 사용자 정의 예외 선언
BEGIN
   -- 🔧 emp 테이블에서 empno가 일치하는 사원의 급여를 6000으로 변경 시도
   UPDATE emp
   SET sal = 6000
   WHERE empno = &empno;

   -- 🔍 아무 행도 바뀐 게 없다면 (즉, 해당 사원번호가 없다는 뜻)
   IF SQL%NOTFOUND THEN
      RAISE no_empno;                     -- ❗ 직접 예외를 발생시킴
   END IF;

   -- ✅ 정상적으로 수정된 경우 메시지 출력
   DBMS_OUTPUT.PUT_LINE('급여가 6000으로 변경되었습니다.');

EXCEPTION
   -- 🚨 위에서 만든 예외가 발생하면 이 부분으로 흐름이 넘어옴
   WHEN no_empno THEN
      DBMS_OUTPUT.PUT_LINE('해당 사원 번호는 존재하지 않습니다.');
END;


--RAISE_APPLICATION_ERROR : 오류 번호와 오류 메시지를 직접 정해서 예외를 발생시키는 명령어
--직접 예외를 발생시키고 메시지도 함께 출력함
--Oracle에서 허용하는 사용자 정의 예외 번호 범위 (-20001 ~ -20999)
DECLARE
   v_empno emp.empno%TYPE := &empno;  -- 사용자로부터 사원번호를 입력받음 (예: 7788)
BEGIN
   -- emp 테이블에서 해당 사원의 급여를 6000으로 변경
   UPDATE emp
   SET sal = 6000
   WHERE empno = v_empno;

   -- 만약 사원번호가 없어서 아무 행도 변경되지 않았다면
   IF SQL%NOTFOUND THEN
      -- 직접 오류 번호와 메시지를 만들어 예외 발생 (오류 코드: -20003)
      RAISE_APPLICATION_ERROR(-20003, '해당 사원 번호는 존재하지 않습니다.');
   END IF;

   -- 정상적으로 급여가 수정되었을 때 출력되는 메시지
   DBMS_OUTPUT.PUT_LINE('급여가 6000으로 변경되었습니다.');
END;


--SQLCODE와 SQLERRM 사용하기
--디버깅할 때 정확한 예외 정보를 저장하거나 로그로 남기기 위해
--OTHERS는 범용 예외라서 구체적인 내용이 없어 → SQLCODE, SQLERRM으로 보완함
--실무에선 EXCEPTION WHEN OTHERS + SQLERRM 조합이 거의 필수
DECLARE
    name emp.ename%TYPE;       -- emp 테이블에서 ename(사원이름)을 저장할 변수
    v_code NUMBER;             -- 오류 번호를 저장할 변수
    v_errm VARCHAR2(64);       -- 오류 메시지를 저장할 변수
BEGIN
    -- empno를 입력받아 사원이름을 조회
    SELECT ename 
    INTO name 
    FROM emp 
    WHERE empno = &eno;

    -- 정상적으로 조회되었을 경우 출력
    DBMS_OUTPUT.PUT_LINE('사원이름: ' || name);

EXCEPTION
    WHEN OTHERS THEN
        -- 어떤 예외든 발생하면 실행됨 (가장 포괄적인 예외 처리)
        v_code := SQLCODE;          -- 발생한 예외의 오류 번호 저장
        v_errm := SQLERRM;          -- 발생한 예외의 메시지 저장

        -- 오류 코드와 메시지를 화면에 출력
        DBMS_OUTPUT.PUT_LINE('오류 코드: ' || v_code);
        DBMS_OUTPUT.PUT_LINE('오류 메시지: ' || v_errm);
END;

