-------------------------------오라클 서브프로그램---------------------------------
--프로시저 : 값을 반환하지 않고 어떤 작업을 수행할 때 사용
--오라클에서 프로시저는 특정 작업을 실행하는 PL/SQL 코드 블록
--데이터를 수정하거나 로그를 남기는 작업 등 복잡한 로직을 하나의 명령으로 실행할 수 있음
-- 프로시저 생성 예시1
CREATE OR REPLACE PROCEDURE update_30
IS
BEGIN
   -- 부서번호가 30번인 직원들의 직무를 'MANAGER'로 업데이트
   UPDATE emp
   SET job = 'MANAGER'
   WHERE deptno = 30;

END;
/


-- 프로시저 생성 예시2
CREATE OR REPLACE PROCEDURE new_sal
    (vempno emp.empno%TYPE)    -- ① 매개변수 선언 (emp 테이블의 empno와 같은 타입)
IS
BEGIN
   UPDATE emp                 -- ② emp 테이블을 수정
   SET sal = 2000            -- ③ 급여(sal)를 2000으로 고정 설정
   WHERE empno = vempno;     -- ④ 조건: 전달받은 사번(empno)에 해당하는 사원만
END;
/


--프로시저 생성 예시3
--기존에 같은 이름의 프로시저가 있으면 덮어쓰고 새로 생성
--값을 반환하지 않음 → 다른 코드에서 재사용 불가
--단순히 사람 눈으로 결과만 확인할 때 사용
--일회성 출력용 (보고용)
CREATE OR REPLACE PROCEDURE ename_sal (
    
    v_empno IN emp.empno%TYPE       -- [1] 입력 매개변수 v_empno 선언
                                    -- emp 테이블의 empno 컬럼과 같은 타입으로 지정 (정수형)
                                    -- IN 모드는 호출 시 값을 전달만 받는 역할

)
IS
    -- [2] 이름과 급여를 저장할 변수 선언
    -- 이 변수들도 각각 emp 테이블의 ename과 sal 컬럼 타입을 따라감
    v_ename emp.ename%TYPE;         -- 사원 이름을 담을 변수
    v_sal   emp.sal%TYPE;           -- 사원 급여를 담을 변수

BEGIN
    -- [3] SELECT문으로 해당 사원의 이름과 급여를 변수에 저장
    -- INTO 구문을 사용해 SELECT 결과를 PL/SQL 변수에 할당
    SELECT ename, sal
    INTO v_ename, v_sal             -- SELECT 결과를 각각 변수에 넣음
    FROM emp
    WHERE empno = v_empno;          -- 매개변수로 받은 사번 조건으로 검색

    -- [4] 출력 (콘솔에 메시지를 출력함)
    -- DBMS_OUTPUT.PUT_LINE은 PL/SQL에서 텍스트를 출력할 때 사용하는 내장 프로시저
    DBMS_OUTPUT.PUT_LINE('사원 이름: ' || v_ename);        -- 이름 출력
    DBMS_OUTPUT.PUT_LINE('사원 급여: $' || TO_CHAR(v_sal, '999,999'));  
    -- 급여 출력 (TO_CHAR로 천단위 쉼표와 함께 문자열 변환)

END;
/
-- [5] 슬래시는 SQL Developer에서 PL/SQL 블록 실행을 마무리하는 명령


--프로시저 생성 예시4
--값을 프로시저 밖으로 "돌려줌" (OUT 모드)
--다른 코드에서 변수로 받아서 활용 가능
--Java, C#, Python 같은 언어에서 값을 가져올 때 반드시 사용하는 방식
--재사용 가능성이 있음 → 응용 프로그램과 연동하기 좋음
CREATE OR REPLACE PROCEDURE info_emp (
    v_empno   IN  emp.empno%TYPE,     -- [1] IN: 사번을 입력으로 받음
    v_ename   OUT emp.ename%TYPE,     -- [2] OUT: 이름을 프로시저 밖으로 반환
    v_sal     OUT emp.sal%TYPE        -- [3] OUT: 급여를 프로시저 밖으로 반환
)
IS
BEGIN
    -- [4] SELECT문으로 해당 사원의 이름과 급여를 OUT 변수에 저장
    SELECT ename, sal
    INTO v_ename, v_sal
    FROM emp
    WHERE empno = v_empno;
END;
/

--OUT 파라미터 위치 기준 호출방식
DECLARE
    name_var emp.ename%TYPE;
    sal_var  emp.sal%TYPE;
BEGIN
    info_emp(7839, name_var, sal_var);  -- 순서 중요
    DBMS_OUTPUT.PUT_LINE('이름: ' || name_var);
    DBMS_OUTPUT.PUT_LINE('급여: $' || TO_CHAR(sal_var, '999,999'));
END;
/

--OUT 파라미터 이름 지정 방식
DECLARE
    name_var emp.ename%TYPE;
    sal_var  emp.sal%TYPE;
BEGIN
    -- 매개변수 이름을 지정해서 전달 (순서 상관없음)
    info_emp(
        v_sal   => sal_var,
        v_ename => name_var,
        v_empno => 7839
    );

    DBMS_OUTPUT.PUT_LINE('이름: ' || name_var);
    DBMS_OUTPUT.PUT_LINE('급여: $' || TO_CHAR(sal_var, '999,999'));
END;
/

--IN : 값을 프로시저에 전달하는 용도, 외부 → 내부, 반환 없음
--OUT : 프로시저 내부에서 설정한 값을 되돌려줌, 내부 → 외부, 반환 있음
--IN OUT : 값을 전달도 받고, 수정해서 다시 반환함, 외부 → 내부 → 외부, 반환 있음
--IN = “입력만 받는 계산기”
--OUT	= “결과만 보여주는 기계”
--IN OUT = “입력한 값 수정해서 되돌려주는 기계”


--프로시저 내용 확인
SELECT text
FROM user_source
WHERE name = '프로시저이름'
  AND type = 'PROCEDURE'
ORDER BY line;