-----------------------------Record Type 변수-----------------------------------
--복합 변수: 여러 값을 하나의 변수처럼 묶어서 다루는 것
--여러 개의 값을 하나의 변수처럼 묶어 다룰 수 있는 PL/SQL 변수
--사원의 이름, 급여, 입사일을 각각 따로따로 저장하는 대신
--이 세 가지를 한 번에 묶어서 처리하고 싶을 때
-- 항상 하고 시작하기 SET SERVEROUTPUT ON;

------- 사용자에게 profno 입력받고 해당 교수의 정보 출력, position이 NULL이면 '사원'으로 표시
DECLARE
   -- 사용자 정의 RECORD 타입 선언 (교수 정보를 위한 구조)
   TYPE prof_rec IS RECORD (
      profno    NUMBER,
      name      VARCHAR2(50),
      position  VARCHAR2(30),
      hiredate  DATE,
      pay       NUMBER
   );

   v_prof    prof_rec;  -- 복합 변수: 교수 한 명의 정보를 담음
   v_profno  professor.profno%TYPE := &profno;  -- 사용자로부터 받은 교수번호 저장
BEGIN
   -- 교수번호를 기준으로 교수 정보 조회 → v_prof에 저장
   SELECT profno, name, position, hiredate, pay
   INTO v_prof
   FROM professor
   WHERE profno = v_profno;

   -- 직급이 없으면 '사원'으로 바꿔서 저장
   IF v_prof.position IS NULL THEN
      v_prof.position := '사원';
   END IF;

   -- 결과 출력
   DBMS_OUTPUT.PUT_LINE('교수번호 : ' || v_prof.profno);
   DBMS_OUTPUT.PUT_LINE('이름     : ' || v_prof.name);
   DBMS_OUTPUT.PUT_LINE('직급     : ' || v_prof.position);
   DBMS_OUTPUT.PUT_LINE('입사일   : ' || TO_CHAR(v_prof.hiredate, 'YYYY-MM-DD'));
   DBMS_OUTPUT.PUT_LINE('급여     : $' || TO_CHAR(v_prof.pay, '9,999'));

EXCEPTION
   WHEN NO_DATA_FOUND THEN
      DBMS_OUTPUT.PUT_LINE('해당 교수번호의 정보가 없습니다.');
END;


------------------------------Table Type 변수-----------------------------------
-- 하나의 필드를 여러 개 저장할 수 있는 변수, 즉 배열처럼 쓰는 변수

-- 테이블 타입 변수를 사용해서 교수번호가 2001인 교수 이름을 조회하고 변수에 저장 후 출력
DECLARE
   -- 교수 이름을 임시로 저장할 일반 변수
   v_name VARCHAR2(20);

   -- 교수 이름을 여러 개 저장할 테이블 타입 정의
   TYPE TBL_PROF IS TABLE OF professor.name%TYPE
      INDEX BY BINARY_INTEGER;

   -- 테이블 타입 변수 선언 (인덱스 → 이름 형태로 저장)
   profname TBL_PROF;
BEGIN
   -- 교수번호 2001인 교수의 이름을 조회해서 v_name에 저장
   SELECT name INTO v_name
   FROM professor
   WHERE profno = 2001;

   -- 테이블 타입 변수에 가공된 이름 저장
   profname(0) := v_name || '_0';
   profname(1) := v_name || '_1';

   -- 저장한 이름 출력
   DBMS_OUTPUT.PUT_LINE(profname(0));
   DBMS_OUTPUT.PUT_LINE(profname(1));
END;
/

-- 테이블 타입 변수에 로우타입을 활용하는 방법
DECLARE
   -- professor 테이블 한 행 전체를 담을 수 있는 테이블 타입 정의 (INDEX BY 사용)
   TYPE prof_table_type IS TABLE OF professor%ROWTYPE
      INDEX BY BINARY_INTEGER;

   -- 테이블 타입 변수 선언 (v_prof_row는 여러 행을 인덱스로 저장 가능)
   v_prof_row prof_table_type;
BEGIN
   -- v_prof_row의 1번째 인덱스에 교수 정보 직접 할당
   v_prof_row(0).profno   := 9000;         -- 교수번호
   v_prof_row(0).name     := 'imsy';       -- 이름
   v_prof_row(0).id       := 'imsy_id';    -- 아이디
   v_prof_row(0).position := 'imsy';       -- 직급
   v_prof_row(0).pay      := 0;            -- 급여
   v_prof_row(0).hiredate := SYSDATE;      -- 입사일

   -- 할당된 데이터를 한 줄로 출력 (공백으로 구분)
   DBMS_OUTPUT.PUT_LINE(
      v_prof_row(0).profno || ' ' ||
      v_prof_row(0).name || ' ' ||
      v_prof_row(0).id || ' ' ||
      v_prof_row(0).position || ' ' ||
      v_prof_row(0).pay || ' ' ||
      v_prof_row(0).hiredate
   );
END;
/

