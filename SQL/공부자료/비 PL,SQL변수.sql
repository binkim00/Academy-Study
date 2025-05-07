-----------------------------비 PL/SQL 변수--------------------------------------
--비 PL/SQL 변수: SELECT문에서 값을 직접 입력받고 싶을 때 잠깐 쓰는 변수
--SELECT문 테스트할 때 사용
--사용자 입력을 받아서 SQL을 실행하고 싶을 때 사용
--PL/SQL 선언부 없이 간단히 필터링 하고 싶을 때 사용

--예제 1: 교수번호를 입력받아 해당 교수의 이름 보기
SELECT name
FROM professor
WHERE profno = &교수번호;

--예제 2: 이름을 입력받아 찾기
SELECT *
FROM professor
WHERE name = '&이름';

--바인드 변수
-- :변수명 형식으로 사용하는 변수로, SQL 실행 시 미리 값을 준비해두고 연결해서 실행하는 변수
-- SQL문을 **한 번만 분석(파싱)**하고, 여러 번 실행할 때 값만 바꿔서 빠르게 실행할 수 있도록 도와주는 변수
-- 예제
-- ▶ 바인드 변수 선언: 이름을 저장할 변수 b_name을 만든다
VARIABLE b_name VARCHAR2(20);

-- ▶ 바인드 변수에 값을 넣는 PL/SQL 블록
BEGIN
   :b_name := 'Winona Ryder';  -- b_name에 'Winona Ryder'를 저장함 (앞에 : 꼭 붙임)
END;
/

-- ▶ 저장된 바인드 변수를 사용해서 SELECT 실행
SELECT * 
FROM professor
WHERE name = :b_name;           -- 여기서 :b_name은 'Winona Ryder'로 해석됨
--장점
--빠르고 효율적: 쿼리를 한 번만 분석하고 계속 쓸 수 있음
--보안에 강함: 해커가 변수값을 조작할 수 없음
--SQL문이 깔끔: 변수로 관리하니까 보기 좋고 수정도 쉬움


--항목     | 비 PL/SQL 변수 (&)          | PL/SQL 변수
--위치     | SQL문 안                    | DECLARE 블록 안
--입력 방식 | 실행 시 사용자 입력          | 코드 내에서 값 지정 또는 SELECT INTO
--타입 지정 | 불가능 (입력값 그대로 사용)   | 가능 (VARCHAR2, NUMBER 등)
--사용 대상 | 단순 테스트, 필터링          | 복잡한 로직 처리, 프로시저 등

