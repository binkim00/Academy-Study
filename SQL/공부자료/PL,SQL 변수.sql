-------------------------------PL/SQL 변수--------------------------------------
--스칼라 변수: 하나의 값만 저장할 수 있는 변수
--NUMBER: 숫자 (정수, 실수 모두 가능)
--VARCHAR2(n): 문자열 (최대 n글자), 실제 입력된 길이만큼만 공간을 차지함, 실무에서 주로 사용
--CHAR(n): 고정 길이 문자열, 실제 입력된 값보다 짧으면, 공백으로 채워짐
--DATE: 날짜와 시간 저장
--BOOLEAN: 참/거짓

-- 참조 변수: 테이블 컬럼의 데이터 타입을 참조해서 변수 선언하는 방식
--특정 컬럼 몇 개만 쓸 때: %TYPE
--테이블 전체 컬럼을 다 쓸 때: %ROWTYPE
SET SERVEROUTPUT ON;

--%TYPE 예시
--테이블의 컬럼 타입이 바뀌어도 변수 타입도 같이 따라감 → 유지보수가 쉬움
--예: VARCHAR2(30) → VARCHAR2(100)으로 바뀌어도 코드 수정 필요 없음
DECLARE
   v_deptno dept.deptno%TYPE;   -- 부서번호 참조 변수
   v_dname  dept.dname%TYPE;    -- 부서명 참조 변수
   v_loc    dept.loc%TYPE;      -- 지역 참조 변수
BEGIN
   SELECT deptno, dname, loc
   INTO   v_deptno, v_dname, v_loc   -- 변수 이름 정확히 일치시킴
   FROM   dept
   WHERE  deptno = 10;

   DBMS_OUTPUT.PUT_LINE('부서번호: ' || v_deptno);
   DBMS_OUTPUT.PUT_LINE('부서명: ' || v_dname);
   DBMS_OUTPUT.PUT_LINE('지역: ' || v_loc);
END;

--%ROWTYPE 예시
--컬럼이 많을 때 유용
--테이블 구조가 바뀌어도 자동 반영
--단, SELECT * INTO만 사용할 수 있음 (컬럼을 지정할 수 없음)
DECLARE
   v_dept dept%ROWTYPE;   -- dept 테이블의 한 행 전체를 저장하는 참조 변수
BEGIN
   -- deptno가 10인 행 전체를 v_dept에 저장
   SELECT * INTO v_dept
   FROM dept
   WHERE deptno = 10;

   -- 저장된 각 컬럼 값을 출력
   DBMS_OUTPUT.PUT_LINE('부서번호: ' || v_dept.deptno);
   DBMS_OUTPUT.PUT_LINE('부서명: '   || v_dept.dname);
   DBMS_OUTPUT.PUT_LINE('지역: '     || v_dept.loc);
END;

--------------------두 수를 입력 받아 합계 출력 하는 문제---------------------------
DECLARE
   v_num1 NUMBER := &num1;
   v_num2 NUMBER := &num2;
   v_sum  NUMBER;
BEGIN
   v_sum := v_num1 + v_num2;

   -- 한 줄에 모두 출력
   DBMS_OUTPUT.PUT_LINE(
      '입력한 숫자: ' || v_num1 || ' + ' || v_num2 || ' = ' || v_sum
   );
END;

----------------%ROWTYPE 실습용 테이블을 만들고 데이터를 넣은 상태-------------------
CREATE TABLE t_rowtype1
( no        NUMBER,
  name      VARCHAR2(10),
  hiredate  DATE);
  
CREATE TABLE t_rowtype2
AS SELECT * FROM t_rowtype1;
  
insert into t_rowtype1 values(10,'APPLE',SYSDATE);
insert into t_rowtype1 values(20,'BANANA',SYSDATE);
insert into t_rowtype1 values(30,'BERRT',SYSDATE);

select * from t_rowtype1;

--------------------------------- 단행을 복사
DECLARE
   -- t_rowtype1 테이블의 구조(컬럼 전체)를 참조해서 v_row 변수 선언
   -- 즉, v_row는 no, name, hiredate 세 개의 값을 담을 수 있음
   v_row t_rowtype1%ROWTYPE;
BEGIN
   -- t_rowtype1 테이블에서 no 값이 10인 행을 조회하여
   -- 그 결과 전체를 v_row 변수에 저장
   SELECT * INTO v_row
   FROM t_rowtype1
   WHERE no = 10;

   -- v_row에 저장된 값을 그대로 t_rowtype2 테이블에 삽입
   -- t_rowtype2 테이블의 컬럼 구조가 t_rowtype1과 동일해야 함
   INSERT INTO t_rowtype2 VALUES v_row;

   -- 성공 메시지 출력
   DBMS_OUTPUT.PUT_LINE('복사 완료');
END;

---------------------------------- 모든 행 복사
DECLARE
   CURSOR c_row IS
      SELECT * FROM t_rowtype1;   -- 모든 행을 가져올 커서

   v_row t_rowtype1%ROWTYPE;      -- 한 행씩 담을 %ROWTYPE 변수
BEGIN
   OPEN c_row;

   LOOP
      FETCH c_row INTO v_row;     -- 한 행씩 읽어와서 v_row에 저장
      EXIT WHEN c_row%NOTFOUND;  -- 더 이상 행이 없으면 루프 종료

      INSERT INTO t_rowtype2 VALUES v_row;  -- t_rowtype2에 복사
   END LOOP;

   CLOSE c_row;

   DBMS_OUTPUT.PUT_LINE('모든 행 복사 완료');
END;

DELETE FROM t_rowtype2
WHERE no = 10;


SELECT * FROM t_rowtype2;

---------------------------------- 로우타입을 활용한 데이터 변경
DECLARE
   v_row t_rowtype1%ROWTYPE;  -- 행 전체를 담을 변수
BEGIN
   -- 사용자로부터 no 입력받아서 해당 행 조회
   SELECT * INTO v_row
   FROM t_rowtype1
   WHERE no = 10;

   -- name만 변경
   v_row.name := 'ORANG';

   -- 변경된 name을 테이블에 반영
   UPDATE t_rowtype2
   SET name = v_row.name
   WHERE no = v_row.no;

   DBMS_OUTPUT.PUT_LINE('이름이 ORANG으로 변경되었습니다. (no = ' || v_row.no || ')');
END;

------------------------------로우타입과 타입의 차이-------------------------------
--항목     | %ROWTYPE                          | %TYPE
--선언 방식 | 한 변수로 전체 컬럼 담음 (v_row)     | 컬럼마다 개별 변수 선언
--사용 방식 | v_row.no, v_row.name 등           | v_no, v_name, v_hiredate 등
--간결함    | 코드 짧고 단순                     | 명확하고 컬럼별 제어 가능
--가독성    | 행 단위로 보기 좋음                 | 컬럼별 로직 짜기 좋음


