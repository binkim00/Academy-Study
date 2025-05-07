-------------------------------오라클 서브프로그램---------------------------------
--FUNCTION 함수
--오라클의 **함수(Function)**는 반드시 하나의 값을 반환하는 서브프로그램
--입력 매개변수를 받아 연산을 수행하고 그 결과값을 반환
--SQL문이나 PL/SQL 블록에서 호출하여 사용 가능
--반드시 RETURN 문을 통해 단 하나의 값을 반환
--프로시저와 달리 함수를 호출할 때는 항상 반환값을 받을 변수가 필요
--예시 1 숫자 데이터 리턴하는 함수 만들기
CREATE OR REPLACE FUNCTION max_sal 
( 
    v_deptno    emp.deptno%TYPE      -- 부서번호 입력값
)
RETURN NUMBER                         
IS
    max_sal emp.sal%TYPE;            -- sal과 동일한 타입의 변수
BEGIN
    SELECT MAX(sal)
    INTO max_sal
    FROM emp
    WHERE deptno = v_deptno;         -- 파라미터와 컬럼 구분 명확히

    RETURN max_sal;
END;
/


--예시 2 날짜 데이터 리턴하는 함수 만들기
-- ✅ 특정 부서에서 근속 기간이 가장 짧은 (즉, 입사일이 가장 늦은) 직원의 이름과 입사일을
--    '이름 - YYYY-MM-DD HH24:MI:SS' 형식으로 문자열로 반환하는 함수
CREATE OR REPLACE FUNCTION max_hiredate (
    v_deptno emp.deptno%TYPE         -- 💡 파라미터: emp 테이블의 deptno 컬럼과 동일한 타입 사용
) RETURN VARCHAR2                    -- 💡 반환 타입: 이름과 입사일을 연결한 문자열이므로 VARCHAR2 사용
IS
    v_name    emp.ename%TYPE;        -- 💡 변수: 사원의 이름을 저장 (ename 컬럼과 같은 타입)
    v_hire    emp.hiredate%TYPE;     -- 💡 변수: 입사일을 저장 (hiredate 컬럼과 같은 타입)
    v_result  VARCHAR2(100);         -- 💡 변수: 최종 결과 문자열 저장용
BEGIN

    -- ✅ 서브쿼리를 통해 해당 부서에서 입사일이 가장 최근인(즉, 근속이 가장 짧은) 사원을 조회
    SELECT ename, hiredate           -- 💡 이름과 입사일을 조회
    INTO v_name, v_hire              -- 💡 조회 결과를 각각 v_name과 v_hire에 저장
    FROM (
        SELECT ename, hiredate
        FROM emp
        WHERE deptno = v_deptno      -- 💡 주어진 부서번호(v_deptno)와 일치하는 사원만 필터링
        ORDER BY hiredate DESC       -- 💡 입사일을 내림차순 정렬 → 가장 최근 입사자가 위로 옴
    )
    WHERE ROWNUM = 1;                -- 💡 상위 1명만 선택 (입사일이 가장 최근인 1명)

    -- ✅ 이름과 입사일을 문자열로 연결, 날짜는 시:분:초까지 포함된 형식으로 변환
    v_result := v_name || ' - ' || TO_CHAR(v_hire, 'YYYY-MM-DD HH24:MI:SS');

    -- ✅ 최종 결과 문자열 반환
    RETURN v_result;
END;
/

--날짜 데이터 함수 확인
SELECT deptno, max_hiredate(deptno)    -- 💡 부서번호별로 함수 실행 결과(이름 - 입사일) 조회
FROM emp
GROUP BY deptno                        -- 💡 부서번호별로 그룹핑 (중복 제거)
ORDER BY 1;                            -- 💡 첫 번째 컬럼(deptno) 기준으로 정렬


--문자 데이터 리턴하는 함수 만들기
-- ✅ 교수번호를 입력 받아 해당 교수의 부서명을 반환하는 함수 (서브쿼리 사용)
CREATE OR REPLACE FUNCTION get_dname (
    v_profno professor.profno%TYPE           -- 💡 입력값: 교수번호 (professor 테이블에서 참조)
) RETURN VARCHAR2
IS
    v_dname department.dname%TYPE;           -- 💡 부서명을 저장할 변수
BEGIN
    -- ✅ 서브쿼리로 먼저 교수의 부서번호를 찾고, 해당 부서의 부서명을 조회
    SELECT dname
    INTO v_dname
    FROM department
    WHERE deptno = (
        SELECT deptno
        FROM professor
        WHERE profno = v_profno              -- 💡 입력받은 교수번호에 해당하는 부서번호 조회
    );

    RETURN v_dname;                          -- 💡 최종 결과로 부서명 반환
END;
/

--문자 데이터 함수 확인
SELECT name, get_dname(profno) AS dname
FROM professor;