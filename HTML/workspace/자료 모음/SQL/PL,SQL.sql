------------------------------PL/SQL--------------------------------------------
--SQL Developer를 사용 중이라면, 출력을 보이게 하려면 다음 명령을 실행
SET SERVEROUTPUT ON;

-- [익명 PL/SQL 블록 시작]
DECLARE
   -- [변수 선언 구간]
   v_empno  emp.empno%TYPE;   -- 사번을 저장할 변수 (emp 테이블의 empno 컬럼 타입과 동일)
   v_ename  emp.ename%TYPE;   -- 사원 이름을 저장할 변수 (emp 테이블의 ename 컬럼 타입과 동일)

BEGIN
   -- [실행 블록 시작]

   -- 사용자로부터 입력 받은 사번(&empno)에 해당하는 사원의 사번과 이름을 조회하여
   -- 각각 v_empno와 v_ename 변수에 저장
   SELECT empno, ename INTO v_empno, v_ename
   FROM emp
   WHERE empno = &empno;
--  &empno는 Substitution Variable (치환 변수)
--  &변수	입력창이 뜨고 사용자가 값을 입력
--  &&변수	처음 한 번만 입력받고, 이후엔 재사용
--  DEFINE 변수 = 값	아예 고정된 값으로 미리 정의해서 입력 생략

   -- 사원의 사번과 이름을 출력
   DBMS_OUTPUT.PUT_LINE(v_empno || ' - 이 사원의 이름은 ' || v_ename || '입니다.');

EXCEPTION
   -- [예외 처리 블록 시작]

   WHEN NO_DATA_FOUND THEN
      -- 조건에 맞는 사원이 없는 경우 출력
      DBMS_OUTPUT.PUT_LINE('해당 사원이 없습니다.');

   WHEN TOO_MANY_ROWS THEN
      -- 조회 결과가 2건 이상일 경우 출력 (SELECT INTO는 한 건만 가능)
      DBMS_OUTPUT.PUT_LINE('해당 사원이 두 명입니다.');

   WHEN OTHERS THEN
      -- 그 외의 모든 예외에 대한 처리
      DBMS_OUTPUT.PUT_LINE('알 수 없는 오류 발생');

END;
-- [익명 블록 종료]

