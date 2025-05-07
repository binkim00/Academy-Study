-------------------------------계층형 쿼리---------------------------------------
SELECT LPAD(ename, LEVEL * 4, ' ') AS "ENAME"  -- 사원 이름(ename)을 왼쪽에서부터 LEVEL * 4 길이만큼 ' '로 채워서 출력
-- 오라클 계층형 쿼리에서 LEVEL은 현재 행이 트리 구조에서 몇 번째 단계(깊이)에 있는지 알려주는 특별한 가상 컬럼
FROM emp                                       -- 사원 정보가 들어있는 emp 테이블에서
START WITH empno = 7839;                       -- 루트 노드는 사번이 7839인 사원(KING)부터 시작
CONNECT BY PRIOR empno = mgr                   -- 부모(empno) → 자식(mgr) 방향으로 계층 구성 (상사 → 부하)
--반드시 START WITH가 먼저, 그 다음에 CONNECT BY를 써야 함.
--PRIOR는 부모-자식 관계의 방향을 결정하는 핵심!
--PRIOR empno = mgr → 부모 → 자식 방향
--empno = PRIOR mgr → 자식 → 부모 방향
--WHERE 조건은 계층 구조 전체를 만든 다음 필터링

-- 계층형 쿼리 기본 구조
SELECT empno, ename, job, mgr
     , PRIOR ename AS mgr_name                                  -- 현재 행의 상사 이름 (PRIOR은 부모 노드에서 가져온 값)
     , LEVEL                                           
     , LPAD(' ', (LEVEL - 1) * 2, ' ') || ename AS depth_ename  -- 계층 레벨에 따라 들여쓰기 후 이름 출력 (트리 구조 시각화)
     , SYS_CONNECT_BY_PATH(ename, '-') AS ename_list            -- 루트부터 현재 행까지의 경로를 문자열로 표시
FROM emp
START WITH mgr IS NULL                                          -- 루트 노드: 상사가 없는 사원부터 시작 (ex: KING)
CONNECT BY PRIOR empno = mgr                                    -- 계층 전개: 부모(empno) → 자식(mgr)
ORDER SIBLINGS BY empno;                                        -- 같은 레벨의 형제 노드끼리는 사번(empno) 기준 정렬

SELECT empno, ename, job, mgr
     , PRIOR ename AS mgr_name
     , LEVEL
     , LPAD(' ', (LEVEL - 1) * 2, ' ') || ename AS depth_ename
     , SYS_CONNECT_BY_PATH(ename, '-') AS ename_list
FROM emp                            
START WITH mgr IS NULL
CONNECT BY PRIOR empno = mgr
            AND ENAME <> 'JONES' -- JONES를 루트로 갖는 하위 계층도 제거
ORDER SIBLINGS BY empno;

SELECT empno, ename, job, mgr
     , PRIOR ename AS mgr_name
     , LEVEL
     , LPAD(' ', (LEVEL - 1) * 2, ' ') || ename AS depth_ename
     , SYS_CONNECT_BY_PATH(ename, '-') AS ename_list
FROM emp
WHERE ENAME <> 'JONES'            -- JONES만 제외 (자식은 그대로 출력됨)
START WITH mgr IS NULL
CONNECT BY PRIOR empno = mgr
ORDER SIBLINGS BY empno;

----------------------------CONNECT_BY_ISLEAF 함수------------------------------
--계층형 쿼리에서 현재 행이 말단 노드(leaf node)인지 여부를 알려주는 함수
--트리 구조에서 "최종 담당자"만 뽑고 싶을 때 유용함.
SELECT LPAD(ename, LEVEL*5, ' ') AS ENAME
      ,SYS_CONNECT_BY_PATH(ename,'->') "ORDER(LOW -> HIGH)" -- 자식(empno)이 부모(mgr)를 가리키는 구조 → 위로 거슬러 올라감
FROM emp
START WITH empno = 7369
CONNECT BY empno = PRIOR mgr ;

SELECT LPAD(ename, LEVEL*5, ' ') AS ENAME
      ,SYS_CONNECT_BY_PATH(ename,'->') "ORDER(LOW -> HIGH)"
FROM emp
WHERE CONNECT_BY_ISLEAF = 0
START WITH empno = 7369
CONNECT BY empno = PRIOR mgr ;

SELECT LPAD(ename, LEVEL*5, ' ') AS ENAME
      ,SYS_CONNECT_BY_PATH(ename,'->') "ORDER(LOW -> HIGH)"
FROM emp
WHERE CONNECT_BY_ISLEAF = 1
START WITH empno = 7369
CONNECT BY empno = PRIOR mgr ;


---------------------------CONNECT_BY_ROOT 함수---------------------------------
--조직도에서 소속 본부 또는 팀의 이름이 필요할 때
--카테고리 구조에서 루트 카테고리명을 같이 출력할 때
--여러 개의 루트가 있을 때 어느 루트에 속해있는지 구분할 때

SELECT empno, ename, CONNECT_BY_ROOT empno AS "Root EMPNO",
       SYS_CONNECT_BY_PATH(ename, '<-') AS "ROOT <- LEAF"
FROM emp
WHERE LEVEL > 1
AND empno = 7369        
CONNECT BY PRIOR empno = mgr;

SELECT empno, ename, CONNECT_BY_ROOT empno AS "Root EMPNO",
       SYS_CONNECT_BY_PATH(ename, '<-') AS "ROOT <- LEAF"
FROM emp
WHERE LEVEL = 1
AND empno = 7369        
CONNECT BY PRIOR empno = mgr;

SELECT empno, ename, CONNECT_BY_ROOT empno AS "Root EMPNO",
       SYS_CONNECT_BY_PATH(ename, '<-') AS "ROOT <- LEAF"
FROM emp
WHERE LEVEL = 2 --SMITH의 상사 (FORD)
AND empno = 7369        
CONNECT BY PRIOR empno = mgr;

SELECT empno, ename, CONNECT_BY_ROOT empno AS "Root EMPNO",
       SYS_CONNECT_BY_PATH(ename, '<-') AS "ROOT <- LEAF"
FROM emp
WHERE LEVEL = 3 --SMITH의 상사의 상사 (JONES)
AND empno = 7369        
CONNECT BY PRIOR empno = mgr;


----------------------------------연습문제---------------------------------------
--연습문제1
SELECT 
    LPAD(' ', (LEVEL - 1) * 4, ' ') ||                      -- 계층 깊이에 따라 들여쓰기 (LEVEL - 1) * 4칸 공백
    e.name || ' - ' || d.dname || ' - ' ||                  -- 사원명 - 부서명 출력
    NVL(e.position, 'Team-Worker') AS employee_line         -- 직급이 NULL이면 'Team-Worker'로 출력
FROM emp2 e, dept2 d                                        -- 옛날 방식의 조인: emp2와 dept2 테이블 사용
WHERE e.deptno = d.dcode                                    -- 조인 조건: 사원의 부서번호와 부서 테이블의 코드 연결
START WITH e.pempno IS NULL                                 -- 계층 시작 조건: 상사가 없는 사람부터 시작 (루트 노드)
CONNECT BY PRIOR e.empno = e.pempno;                         -- 계층 전개 조건: empno(상사)가 다음 행의 pempno(부하)와 연결

--연습문제1-1
SELECT 
    LPAD(' ', (LEVEL - 1) * 4, ' ') ||                      -- 계층 깊이에 따라 들여쓰기 (LEVEL - 1) * 4칸 공백
    e.name || ' - ' || d.dname || ' - ' ||                  -- 사원명 - 부서명 출력
    NVL(e.position, 'Team-Worker') AS employee_line         -- 직급이 NULL이면 'Team-Worker'로 출력
FROM emp2 e, dept2 d                                        -- 옛날 방식의 조인: emp2와 dept2 테이블 사용
WHERE e.deptno = d.dcode                                    -- 조인 조건: 사원의 부서번호와 부서 테이블의 코드 연결
START WITH e.pempno IS NULL                                 -- 계층 시작 조건: 상사가 없는 사람부터 시작 (루트 노드)
CONNECT BY PRIOR e.empno = e.pempno                         -- 계층 전개 조건: empno(상사)가 다음 행의 pempno(부하)와 연결
ORDER SIBLINGS BY e.name;                                   -- 같은 LEVEL 내 형제 노드들끼리는 이름으로 정렬

--연습문제2
SELECT 
    RPAD(' ', (LEVEL - 1) * 4, ' ') ||                      -- 계층 깊이에 따라 공백 들여쓰기 (LEVEL 1은 들여쓰기 없음)
    name || ' - ' || NVL(position, 'Team-Worker')           -- 사원 이름과 직급 출력, 직급이 없으면 'Team-Worker'로 대체
    AS employee_line
FROM emp2
START WITH name = 'Kevin Bacon'                             -- 'Kevin'이라는 이름의 사원을 루트로 설정
CONNECT BY PRIOR empno = pempno                             -- 상사(empno) → 부하(pempno) 방향으로 계층 전개
ORDER SIBLINGS BY name;                                     -- 같은 상사(형제 노드)끼리는 이름순 정렬


--연습문제3
SELECT 
    RPAD(' ', (LEVEL - 1) * 4, ' ') || 
    name || ' - ' || NVL(position, 'Team-Worker') AS boss_line
FROM emp2
START WITH name = 'Kevin Costner'                       -- 케빈 코스터에서 시작
CONNECT BY empno = PRIOR pempno                        -- 부하의 상사 추적 (거꾸로 올라감)
ORDER SIBLINGS BY name;

--연습문제4
SELECT 
    name AS employee_name,                                -- 현재 행: 사원의 이름
    PRIOR name AS manager_name                            -- PRIOR: 상사(부모 노드)의 이름
FROM emp2
START WITH pempno IS NULL                                 -- 상사가 없는 사람부터 시작 (루트 사원)
CONNECT BY PRIOR empno = pempno;                          -- 계층 전개 조건: 상사(empno) → 부하(pempno)

--연습문제5
SELECT 
    e.empno,                                                                                 -- 사원 번호
    e.name || ' - ' || d.dname || ' - ' || NVL(e.position, 'Team-Worker') AS emp_info,       -- 사원명 - 부서명 - 직급 (직급이 없으면 Team-Worker로 표시)
    (   SELECT COUNT(*) 
        FROM emp2 sub -- sub 별칭으로 사용
        START WITH sub.pempno = e.empno                                                      -- 현재 사원의 사번을 상사로 하는 사원들부터 시작
        CONNECT BY PRIOR sub.empno = sub.pempno                                              -- 상사(empno) → 부하(pempno) 구조로 아래로 내려가며 전체 부하 계산
    ) AS total_sub_count                                                                     -- 전체 부하 직원 수 (직속 + 간접 모두 포함)
FROM emp2 e, dept2 d                                                                          -- 사원(emp2)과 부서(dept2) 테이블을 함께 사용 (옛날 방식 조인)
WHERE e.deptno = d.dcode                                                                      -- 사원의 부서 번호와 부서 테이블의 코드 연결
ORDER BY total_sub_count DESC;                                                               -- 부하 직원 수가 많은 순서대로 출력

--연습문제6
SELECT 
    RPAD(' ', (LEVEL - 1) * 4, ' ') || e.name || ' - ' || d.dname AS emp_line,     -- 들여쓰기 + 이름 - 부서명
    SYS_CONNECT_BY_PATH(e.name, ' → ') AS full_path                               -- 루트부터 현재 사원까지의 이름 경로
FROM emp2 e
JOIN dept2 d
ON e.deptno = d.dcode                                                              -- 사원의 부서 번호와 부서 테이블 연결
START WITH e.name = 'Kevin Bacon'                                                 -- Kevin Bacon을 루트로 지정
CONNECT BY PRIOR e.empno = e.pempno                                               -- 상사(empno) → 부하(pempno) 방향 계층 전개
ORDER SIBLINGS BY e.name;                                                         -- 형제 노드는 이름순 정렬
