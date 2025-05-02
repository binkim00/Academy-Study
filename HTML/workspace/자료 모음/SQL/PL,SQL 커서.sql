------------------------------PL/SQL 커서----------------------------------------
--SET SERVEROUTPUT ON; 설정

--SELECT 문은 여러 행(row)을 반환할 수 있는데,
--PL/SQL 블록에서는 기본적으로 한 번에 하나의 행만 다룰 수 있음
--"커서"라는 포인터를 만들어서, 여러 행을 차례대로 가져올 수 있게 함
--커서는 열린다 → 데이터를 가져온다(FETCH) → 닫는다 이런 순서로 사용

--PL/SQL 프로그램은 기본적으로 한 번에 한 줄씩만 처리할 수 있음
--SELECT한 여러 줄을 프로그램 안에서 하나씩 읽고 싶을 때
--커서는 "SELECT 결과를 하나하나 다루기 위한 도구"

--커서 종류
--명시적 커서(Explicit Cursor): 개발자가 직접 선언하고 열고(fetch)하고 닫는 커서
--암시적 커서(Implicit Cursor): SELECT INTO, INSERT, UPDATE, DELETE 같은 DML문을
--                            실행하면 자동으로 오라클이 내부에서 커서를 열고 닫아줌
--커서 FOR LOOP:	커서를 열고, fetch하고, 닫는 작업을 자동으로 처리해주는 간편한 반복문


--명시적 커서 예시1
DECLARE
   -- ① 커서를 선언한다.
   -- 이 커서는 부서번호가 10번인 사원들의 사번(empno), 이름(ename), 급여(sal)을 가져온다.
   CURSOR emp_cursor IS
      SELECT empno, ename, sal
      FROM emp
      WHERE deptno = 10;

   -- ② FETCH할 데이터를 저장할 변수를 각각 선언한다.
   v_empno emp.empno%TYPE;   -- 사원번호 저장할 변수
   v_ename emp.ename%TYPE;   -- 사원이름 저장할 변수
   v_sal   emp.sal%TYPE;     -- 급여 저장할 변수
BEGIN
   -- ③ 커서를 연다. (SELECT문이 실행되어 결과가 준비됨)
   OPEN emp_cursor;
   
   -- ④ 결과를 한 줄씩 읽어오면서 처리하는 루프 시작
   LOOP
      -- 커서에서 한 행(row)을 읽어와서 각각 변수에 저장한다.
      FETCH emp_cursor INTO v_empno, v_ename, v_sal;

      -- 더 이상 가져올 데이터가 없으면 루프를 종료한다.
      EXIT WHEN emp_cursor%NOTFOUND;

      -- 가져온 데이터를 화면에 출력한다.
      DBMS_OUTPUT.PUT_LINE('사원번호: ' || v_empno || ', 이름: ' || v_ename || ', 급여: ' || v_sal);
   END LOOP;
   
   -- ⑤ 커서를 닫는다. (메모리 해제)
   CLOSE emp_cursor;
END;
/


--명시적 커서 예시2
DECLARE
    -- 변수 선언 (커서에서 가져올 값을 저장할 변수들)
    v_empno NUMBER(5);      -- 사원번호를 저장할 변수
    v_ename VARCHAR2(30);   -- 사원이름을 저장할 변수
    v_sal   NUMBER(6);      -- 급여를 저장할 변수

    -- 명시적 커서 선언
    CURSOR cur1 IS
        SELECT empno, ename, sal
        FROM emp
        WHERE deptno = 10;
BEGIN
    -- 커서 열기 (SELECT문 실행 준비)
    OPEN cur1;

    -- 커서로부터 데이터 하나씩 읽어오는 루프 시작
    LOOP
        -- 한 행을 FETCH해서 변수에 저장
        FETCH cur1 INTO v_empno, v_ename, v_sal;

        -- 더 이상 가져올 행이 없으면 루프 종료
        EXIT WHEN cur1%NOTFOUND;

        -- 가져온 데이터 출력
        DBMS_OUTPUT.PUT_LINE('사원번호: ' || v_empno || ', 이름: ' || v_ename || ', 급여: ' || v_sal);
    END LOOP;

    -- 커서 닫기 (메모리 해제)
    CLOSE cur1;
END;
/


--커서 FOR LOOP 사용 예시
DECLARE
   -- ① 커서를 선언한다.
   -- 부서번호가 10번인 사원들의 정보를 가져오는 커서
   CURSOR emp_cursor IS
      SELECT empno, ename, sal
      FROM emp
      WHERE deptno = 10;
BEGIN
   -- ② FOR LOOP를 사용해 커서를 순회한다.
   -- emp_rec이라는 레코드(record)형 변수가 자동으로 만들어진다. (empno, ename, sal 필드를 가짐)
   FOR emp_rec IN emp_cursor LOOP
      -- emp_rec.empno, emp_rec.ename, emp_rec.sal로 데이터에 접근할 수 있다.
      -- 매 행마다 DBMS_OUTPUT으로 출력
      DBMS_OUTPUT.PUT_LINE('사원번호: ' || emp_rec.empno || ', 이름: ' || emp_rec.ename || ', 급여: ' || emp_rec.sal);
   END LOOP;
   -- (OPEN, FETCH, CLOSE를 별도로 안 해도 FOR LOOP가 알아서 다 해준다)
END;
/


--암시적 커서 예시
DECLARE
   -- ① 사원이름을 저장할 변수를 선언한다.
   v_ename emp.ename%TYPE;
BEGIN
   -- ② 특정 사번(7788번)의 사원이름을 하나만 가져온다.
   -- SELECT INTO 구문을 사용하면 오라클이 내부적으로 커서를 자동 관리해준다. (암시적 커서)
   SELECT ename INTO v_ename
   FROM emp
   WHERE empno = 7788;

   -- ③ 가져온 사원이름을 출력한다.
   DBMS_OUTPUT.PUT_LINE('이름: ' || v_ename);
END;
/


--암시적 커서 예시2
DECLARE
BEGIN
    -- ① 안내 문구를 출력
    DBMS_OUTPUT.PUT_LINE(' Information about department name and location');
    DBMS_OUTPUT.PUT_LINE('-----------------------------------------------------');

    -- ② FOR LOOP를 이용해 department 테이블을 조회
    --    build 컬럼이 NULL이 아닌 데이터만 조회하고,
    --    부서명(dname)을 기준으로 오름차순 정렬 (ORDER BY 1 : 첫 번째 컬럼 dname 기준)
    FOR dept IN (
        SELECT dname, build
        FROM department
        WHERE build IS NOT NULL
        ORDER BY 1
    )
    LOOP
        -- ③ 각 부서명과 건물(build) 정보를 출력
        DBMS_OUTPUT.PUT_LINE(dept.dname || ' ---> ' || dept.build);
    END LOOP;

    -- ④ LOOP가 끝나면 오라클이 자동으로 커서를 닫는다.
END;
/


--파라미터 Explicit Cursor :SELECT할 때 어떤 값을 "입력받고" 싶을 때 사용
--실행할 때 다른 값을 넘겨줘서 다양한 조회를 할 수 있다.
--값을 넘겨줄 수 있는 명시적 커서
--예시1
DECLARE
    -- 변수 선언
    v_empno emp.empno%TYPE;
    v_ename emp.ename%TYPE;
    v_sal   emp.sal%TYPE;

    -- 파라미터 커서 선언 (부서번호를 매개변수로 받는다)
    CURSOR cur1(p_deptno NUMBER) IS
        SELECT empno, ename, sal
        FROM emp
        WHERE deptno = p_deptno;
BEGIN
    -- 커서 열기 (10번 부서 사원들 조회)
    OPEN cur1(10);

    -- 커서 읽기
    LOOP
        FETCH cur1 INTO v_empno, v_ename, v_sal;
        EXIT WHEN cur1%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('사원번호: ' || v_empno || ', 이름: ' || v_ename || ', 급여: ' || v_sal);
    END LOOP;

    -- 커서 닫기
    CLOSE cur1;
END;
/


--예시2
DECLARE
    -- 파라미터 커서 선언 (부서번호를 매개변수로 받아 해당 부서 교수 조회)
    CURSOR prof_cur(v_deptno IN NUMBER) 
    IS 
        SELECT * 
        FROM professor 
        WHERE deptno = v_deptno;
    
    -- 커서로 가져온 한 줄 데이터를 담을 변수
    v_prof professor%ROWTYPE;
BEGIN
    -- 출력 포맷 꾸미기
    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE('교수 정보 조회 결과');
    DBMS_OUTPUT.PUT_LINE('--------------------------------');

    -- [1] 101번 부서 교수 조회
    OPEN prof_cur(101);  -- 부서번호 101번으로 커서 열기

    LOOP
        FETCH prof_cur INTO v_prof;   -- 교수 한 명씩 읽어오기
        EXIT WHEN prof_cur%NOTFOUND;  -- 더 이상 데이터가 없으면 종료

        -- 101번 부서 교수 정보 출력
        DBMS_OUTPUT.PUT_LINE('[101번 부서] 교수번호: ' || v_prof.profno || ', 이름: ' || v_prof.name || ', 부서번호: ' || v_prof.deptno);
    END LOOP;

    CLOSE prof_cur; -- 101번 부서 커서 닫기

    DBMS_OUTPUT.PUT_LINE('--------------------------------');

    -- [2] 102번 부서 교수 조회
    OPEN prof_cur(102);  -- 부서번호 102번으로 커서 열기

    LOOP
        FETCH prof_cur INTO v_prof;   -- 교수 한 명씩 읽어오기
        EXIT WHEN prof_cur%NOTFOUND;  -- 더 이상 데이터가 없으면 종료

        -- 102번 부서 교수 정보 출력
        DBMS_OUTPUT.PUT_LINE('[102번 부서] 교수번호: ' || v_prof.profno || ', 이름: ' || v_prof.name || ', 부서번호: ' || v_prof.deptno);
    END LOOP;

    CLOSE prof_cur; -- 102번 부서 커서 닫기

    -- 출력 포맷 마무리
    DBMS_OUTPUT.PUT_LINE('================================');
END;
/


--SELECT FOR UPDATE: 조회(SELECT) 하면서, 그 행(row)을 다른 사람이 수정 못 하게 "잠금(LOCK)"을 걸어버리는 것
--여러 사용자가 동시에 DB를 사용할 때 누군가가 특정 데이터를 수정하려고 읽었는데,
--다른 사람이 먼저 수정하거나 삭제해버리면 문제가 생김
--읽는 순간 그 행을 잠그고, 커밋(COMMIT)이나 롤백(ROLLBACK)할 때까지
--다른 사람은 수정, 삭제 못 하게 막는다.
--예시1
DECLARE
    -- 부서번호 20번 사원들을 조회하는 커서 선언 (읽으면서 행을 잠금)
    CURSOR emp_cur IS
        SELECT empno, ename, sal
        FROM emp
        WHERE deptno = 20
        FOR UPDATE;  -- 읽을 때 해당 행을 다른 사람이 수정 못하게 LOCK

BEGIN
    -- 커서를 열고 FOR LOOP를 돌면서 FETCH 자동으로 처리
    FOR emp IN emp_cur LOOP
        -- emp는 emp_cur 커서에서 가져온 현재 행을 의미
        -- emp.empno, emp.ename, emp.sal 로 컬럼 접근 가능

        -- 사원번호, 이름, 급여를 출력
        DBMS_OUTPUT.PUT_LINE('사원번호: ' || emp.empno || ', 이름: ' || emp.ename || ', 급여: ' || emp.sal);

        -- (필요하면 여기서 WHERE CURRENT OF emp_cur로 UPDATE나 DELETE도 할 수 있음)
    END LOOP;
    
    -- (FOR문이 끝나면 오라클이 커서를 자동으로 CLOSE 해준다)
END;
/

--다른 창에서 아래 쿼리를 실행하면 LOCK이 되기 때문에
--END LOOP와 END사이에 커밋이나 롤백 명령어가 필요
update emp
set sal =900
where ename = 'SMITH';


--참조 커서: 커서를 변수처럼 만들어서, 그 안에 다양한 SELECT 결과를 동적으로 저장할 수 있는 커서
--커서 = SELECT 결과를 담는 그릇이라면 참조 커서는 그 그릇을 변수처럼 자유롭게 다루는 방법
--일반 커서는 항상 같은 SELECT만 할 수 있음
--실무에선 상황에 따라 SELECT가 다를 수도 있고, SELECT 결과가 달라질 수도 있음
--참조 커서를 쓰면 하나의 커서에 다른 SELECT 결과를 담을 수 있음
--예시
-- SQL*Plus 설정
set echo off          -- 실행된 명령어는 화면에 표시하지 않는다
set serveroutput on   -- DBMS_OUTPUT.PUT_LINE 결과를 화면에 표시
set verify off        -- &변수 사용 시 변수명 비교 표시를 끈다
set define '&'        -- 변수 치환을 위해 & 사용

-- 사용자에게 출력할 번호를 입력받는다
prompt
prompt '출력을 원하는 번호를 선택하세요'
accept num prompt '1) Student , 2) Professor , 3) Department :'

prompt

DECLARE
    -- 참조 커서 타입을 선언한다 (Weak Ref Cursor)
    TYPE weak_ref_cur1 IS REF CURSOR;
    -- 참조 커서 변수를 선언한다
    w_ref weak_ref_cur1;

    -- FETCH 결과를 담을 RECORD 타입을 선언한다 (컬럼: no, name)
    TYPE weak_type_1 IS RECORD (
        no NUMBER,                -- 번호 컬럼 (studno, profno, deptno)
        name VARCHAR2(40)          -- 이름 컬럼 (name, dname)
    );
    -- RECORD 타입 변수를 선언한다
    v_weak_1 weak_type_1;

    -- 사용자가 입력한 값을 저장할 변수 선언
    choice CHAR(1) := &num;
BEGIN
    -- 사용자가 1번(Student)을 선택한 경우
    IF choice = 1 THEN
        -- Student 테이블을 조회해서 커서를 연다
        OPEN w_ref FOR
            SELECT studno, name
            FROM student;
        -- 출력 안내 메시지
        DBMS_OUTPUT.PUT_LINE('Printing Student Information---------');

    -- 사용자가 2번(Professor)을 선택한 경우
    ELSIF choice = 2 THEN
        -- Professor 테이블을 조회해서 커서를 연다
        OPEN w_ref FOR
            SELECT profno, name
            FROM professor;
        -- 출력 안내 메시지
        DBMS_OUTPUT.PUT_LINE('Printing Professor Information---------');

    -- 사용자가 3번(Department)을 선택한 경우
    ELSIF choice = 3 THEN
        -- Department 테이블을 조회해서 커서를 연다
        OPEN w_ref FOR
            SELECT deptno, dname
            FROM department;
        -- 출력 안내 메시지
        DBMS_OUTPUT.PUT_LINE('Printing Department Information---------');
    END IF;

    -- 커서로부터 첫 번째 행을 FETCH해서 v_weak_1에 저장
    FETCH w_ref INTO v_weak_1;

    -- 커서에 데이터가 존재하는 동안 반복
    WHILE w_ref%FOUND LOOP
        -- FETCH한 데이터를 출력
        DBMS_OUTPUT.PUT_LINE('no: ' || v_weak_1.no || ', name: ' || v_weak_1.name);

        -- 다음 행을 계속 FETCH
        FETCH w_ref INTO v_weak_1;
    END LOOP;

    -- 커서를 닫는다
    CLOSE w_ref;
END;
/
--전체흐름
--1. 사용자에게 1, 2, 3 중 하나 입력받음
--2. 입력값에 따라 SELECT문 다르게 OPEN (student, professor, department)
--3. 커서로 결과를 하나씩 FETCH해서 no, name 출력
--4. FETCH 끝나면 커서 CLOSE

--Strong Ref Cursor: 참조 커서인데, 반드시 SELECT 결과의 구조(컬럼 타입과 순서)를 고정해놓는 커서
--Weak Ref Cursor는 SELECT 결과가 자유로웠던 반면,
--Strong Ref Cursor는 어떤 SELECT를 열어도 지정된 타입과 똑같은 컬럼 구조를 따라야 함
--예시
DECLARE
    -- ① 커서가 반환할 데이터 형태를 저장할 record 타입을 선언
    TYPE emp_job_rec IS RECORD (
        empno NUMBER,              -- 사원번호
        ename VARCHAR2(10),         -- 사원이름
        job   VARCHAR2(10)          -- 직무
    );

    -- ② 위 record 타입을 반환하는 strong ref cursor 타입을 선언
    TYPE emp_job_refcur_TYPE IS REF CURSOR
        RETURN emp_job_rec;

    -- ③ strong ref cursor 타입의 변수 선언
    emp_refcur emp_job_refcur_TYPE;

    -- ④ 커서로 가져온 데이터를 저장할 실제 record 변수 선언
    emp_job emp_job_rec;

BEGIN
    -- ⑤ 커서를 열면서 쿼리 실행 (emp 테이블에서 사번, 이름, 직무 선택)
    OPEN emp_refcur FOR
        SELECT empno, ename, job
        FROM emp;

    -- ⑥ 커서에서 첫 번째 행을 읽어옴
    FETCH emp_refcur INTO emp_job;

    -- ⑦ 커서에 데이터가 남아있는 동안 반복
    WHILE emp_refcur%FOUND LOOP
        -- 사원번호, 이름, 직무를 출력
        DBMS_OUTPUT.PUT_LINE(emp_job.empno || ' ' || emp_job.ename || ' ' || emp_job.job);

        -- 다음 행을 읽음
        FETCH emp_refcur INTO emp_job;
    END LOOP;

END;
/
--TYPE emp_job_rec IS RECORD (...) : 커서가 가져올 "행" 하나의 구조를 정의.
--TYPE emp_job_refcur_TYPE IS REF CURSOR RETURN emp_job_rec : 이 구조를 반환하는 "강한 타입"의 Ref Cursor를 정의.
--OPEN ~ FOR SELECT ... : 커서를 열면서 SELECT 결과를 커서에 담음.
--FETCH ... INTO ... : 커서에서 한 행씩 읽어서 emp_job에 저장.
--WHILE ~ LOOP : 커서에 남은 데이터가 있는 동안 반복.






