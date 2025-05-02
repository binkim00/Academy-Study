---------------------------단일 행-----------------------------------------------
SELECT s.name AS student_name,          -- ① 학생 이름 출력
       d.dname AS department_name       -- ② 전공 이름 출력
FROM student s                          -- ③ student 테이블을 s라는 별칭으로 사용
JOIN department d                       -- ④ department 테이블을 d라는 별칭으로 사용
    ON s.deptno1 = d.deptno             -- ⑤ 학생의 전공 번호와 학과 번호를 연결
WHERE s.deptno1 = (                     -- ⑥ 아래 서브쿼리와 같은 전공을 가진 학생만 조회
    SELECT deptno1                      -- ⑦ Anthony Hopkins의 전공 번호를 가져옴
    FROM student
    WHERE name = 'Anthony Hopkins'      -- ⑧ 기준 학생 이름
);

SELECT p.name AS prof_name,             -- ① 교수 이름 출력
       p.hiredate AS hiredate,          -- ② 입사일 출력
       d.dname AS department_name       -- ③ 교수의 학과 이름 출력
FROM professor p                        -- ④ professor 테이블을 p라는 별칭으로 사용
JOIN department d ON p.deptno = d.deptno-- ⑤ 학과 번호로 조인
WHERE p.hiredate > (                    -- ⑥ 아래 서브쿼리보다 입사일이 늦은 교수만 조회
    SELECT hiredate                     -- ⑦ Megg Ran 교수의 입사일을 가져옴
    FROM professor
    WHERE name = 'Megg Ran'             -- ⑧ 기준 교수 이름
);

SELECT s.name AS name,          -- ① 학생 이름 출력
       s.weight AS weight       -- ② 몸무게 출력
FROM student s
WHERE s.weight > (              -- ③ 아래 서브쿼리에서 구한 평균보다 몸무게가 많은 경우만
    SELECT AVG(weight)          -- ④ 평균 몸무게 구하기
    FROM student
    WHERE deptno1 = 201         -- ⑤ 1전공이 201번인 학생들만 대상으로
);


-----------------------------다중 행---------------------------------------------
SELECT name AS name,                         -- ① 이름
       position AS position,                 -- ② 직급
       TO_CHAR(pay, '$999,999,999') AS salary    -- ③ 연봉 출력 형식 지정 ($, 콤마 포함)
FROM emp2 e
WHERE pay > (
    SELECT MIN(pay)
    FROM emp2
    WHERE position = 'Section head'            -- ④ 'Section Head' 직급 중 최소 연봉
);

SELECT name,                             -- ① 학생 이름
       weight,                           -- ② 몸무게
       grade                             -- ③ 학년
FROM student
WHERE weight < (
    SELECT MIN(weight)                   -- ④ 2학년 중 가장 가벼운 학생의 몸무게
    FROM student
    WHERE grade = 2
);

SELECT d.dname AS dept_name,                             -- ① 부서명
       e.name AS emp_name,                               -- ② 직원 이름
       TO_CHAR(e.pay, '$999,999,999') AS salary          -- ③ 연봉 출력 형식 ($, 천 단위 구분)
FROM emp2 e
JOIN dept2 d ON e.deptno = d.dcode                       -- ④ emp2와 dept2를 부서번호로 조인
WHERE e.pay < (                                          -- ⑤ 아래 평균보다 연봉이 적은 사람만 조회
    SELECT MIN(avg_pay)                                  -- ⑥ 부서별 평균 연봉 중 가장 낮은 값 찾기
    FROM (
        SELECT deptno, AVG(pay) AS avg_pay               -- ⑦ 부서별 평균 연봉 구하기
        FROM emp2
        GROUP BY deptno
    )
);


--------------------------다중 컬럼----------------------------------------------
SELECT p.profno AS prof_no,                 -- ① 교수 번호 출력
       p.name AS prof_name,                 -- ② 교수 이름 출력
       d.dname AS department_name,          -- ③ 학과 이름 출력
       p.hiredate AS hire_date              -- ④ 교수 입사일 출력
FROM professor p, department d              -- ⑤ professor, department 테이블 함께 조회
WHERE p.deptno = d.deptno                   -- ⑥ 교수의 부서번호와 학과 부서번호 일치시켜 조인
  AND (p.deptno, p.hiredate) IN (           -- ⑦ 아래 조건을 만족하는 교수만 추려냄
    SELECT deptno, MIN(hiredate)            -- ⑧ 각 부서별로 가장 오래된 입사일(최소값)을 찾음
    FROM professor
    GROUP BY deptno                         -- ⑨ 학과별로 묶어서 처리
  )
ORDER BY p.hiredate;                        -- ⑩ 입사일이 오래된 순으로 정렬 (오름차순)

SELECT e.name AS emp_name,                             -- ① 직원 이름
       e.position AS position,                         -- ② 직급
       TO_CHAR(e.pay, '$999,999,999') AS salary        -- ③ 연봉 포맷
FROM emp2 e                                            -- ④ emp2 테이블 사용
WHERE (e.position, e.pay) IN (                         -- ⑤ 아래 서브쿼리 결과와 같은 행만 출력
    SELECT position, MAX(pay)                          -- ⑥ 직급별 최대 연봉
    FROM emp2
    GROUP BY position                                  -- ⑦ 직급별 그룹핑
)
ORDER BY e.pay;                                        -- ⑧ 연봉 오름차순 정렬


-----------------------------상호 연관-------------------------------------------
-- emp2 테이블에서 같은 직급 평균보다 연봉이 높은 직원 조회
SELECT e.name,                                  -- ① 직원 이름
       e.position,                              -- ② 직급
       TO_CHAR(e.pay, '$999,999,999') AS salary -- ③ 연봉 (천단위, $포맷)
FROM emp2 e
WHERE e.pay > (
    SELECT AVG(e2.pay)                          -- ④ 같은 직급의 평균 연봉 계산
    FROM emp2 e2
    WHERE e2.position = e.position              -- ⑤ 바깥 쿼리의 직급과 비교 → 상호 연관!
);

--------상호 연관 조인 방식-------------
-- emp2 테이블에서 같은 직급 평균보다 연봉이 높은 직원 조회
SELECT e.name,                                  -- ① 직원 이름
       e.position,                              -- ② 직급
       TO_CHAR(e.pay, '$999,999,999') AS salary -- ③ 연봉 포맷 ($ + 천단위)
FROM emp2 e,                                    -- ④ 메인 테이블 emp2
     (SELECT position, AVG(pay) AS avg_pay      -- ⑤ 서브쿼리로 직급별 평균 연봉 계산
      FROM emp2
      GROUP BY position) avg_table              -- ⑥ 서브쿼리를 avg_table이라는 별칭으로 사용
WHERE e.position = avg_table.position           -- ⑦ 조인 조건 (직급 기준)
  AND e.pay > avg_table.avg_pay;                -- ⑧ 평균보다 높은 사람만 출력


----------------------------스칼라 서브 쿼리--------------------------------------
-- 에러 예시
SELECT name,                                -- ① 직원 이름
       (SELECT pay                          -- ② 스칼라 서브쿼리
        FROM emp2
        WHERE position = 'Manager') AS manager_pay  -- ③ 'Manager'인 사람의 pay를 가져오려 함
FROM emp2;

-- 해결 방법 1: MAX, MIN 등 집계 함수로 하나만 선택
SELECT name,                                 -- ① 직원 이름
       (SELECT MAX(pay)                      -- ② 여러 명 중 최고 연봉 하나만 가져오기
        FROM emp2
        WHERE position = 'Manager') AS max_manager_pay
FROM emp2;

-- 해결 방법 2: WHERE 조건 더 줘서 결과가 하나만 나오게 만들기
SELECT name,
       (SELECT pay
        FROM emp2
        WHERE position = 'Manager'
          AND name = '지민') AS jimin_pay     -- ① 특정 이름 조건 추가
FROM emp2;

--상황                        | 해결 방법                     | 예시
--결과 2개 이상 스칼라 사용     | ❌ 에러 발생                   | SELECT (SELECT pay FROM ...)
--해결 방법 ①                 | 집계함수 사용                  | SELECT MAX(pay)
--해결 방법 ②                 | WHERE 절로 1명만 걸리게        | WHERE name = '지민'



-----------------------------WITH 절을 활용한 서브쿼리 ---------------------------
--문제 상황                             | WITH절이 필요한 이유
--복잡한 서브쿼리를 여러 번 쓰거나         | 👉 중복 없이 깔끔하게 정리할 수 있어
--서브쿼리를 나눠서 이해하고 싶을 때       | 👉 가독성이 좋아지고 유지보수도 쉬움
--큰 쿼리를 단계적으로 나누고 싶을 때      | 👉 마치 프로그래밍처럼 단계별 처리 가능

-- 예제 1: 단일 가상 테이블 직급별 평균 연봉 구한 뒤, 평균보다 많이 받는 직원 조회
WITH avg_table AS (
    SELECT position, AVG(pay) AS avg_pay           -- ① 직급별 평균 연봉 계산
    FROM emp2
    GROUP BY position
)
SELECT e.name,                                      -- ② 직원 이름
       e.position,                                  -- ③ 직급
       TO_CHAR(e.pay, '$999,999,999') AS salary     -- ④ 연봉 출력 형식
FROM emp2 e
JOIN avg_table a ON e.position = a.position         -- ⑤ 직급 기준 조인
WHERE e.pay > a.avg_pay;                            -- ⑥ 평균보다 높은 사람만 출력

-- 예제 2: 다중 가상 테이블
WITH avg_table AS (                             -- ① 첫 번째 가상 테이블 생성
    SELECT position,                            --    직급별로
           AVG(pay) AS avg_pay                  --    평균 연봉을 계산
    FROM emp2
    GROUP BY position
),
high_earner AS (                                -- ② 두 번째 가상 테이블 생성
    SELECT e.name,                              --    직원 이름
           e.position,                          --    직급
           e.pay                                 --    연봉
    FROM emp2 e
    JOIN avg_table a                            --    앞에서 만든 avg_table과 조인
         ON e.position = a.position             --    직급 기준으로 연결
    WHERE e.pay > a.avg_pay                     --    평균보다 연봉이 높은 사람만 추림
)
SELECT name, position, TO_CHAR(pay, '$999,999,999') AS salary  -- ③ 최종 결과 출력
FROM high_earner                                 --    위에서 만든 high_earner 테이블 사용
ORDER BY pay;

----------------------------------연습문제---------------------------------------

-- 연습문제1 : 스칼라 서브쿼리로 emp, dept 테이블을 사용해서 ename, sal, deptno, 부서명을 출력하시오.
SELECT e.ename,                               -- ① 직원 이름
       TO_CHAR(e.sal, '$999,999') AS sal,     -- ② 급여 (스칼라 서브쿼리와 무관, 포맷만 적용)
       e.deptno,                               -- ③ 부서번호
       (SELECT d.dname                         -- ④ 스칼라 서브쿼리: 부서명
        FROM dept d
        WHERE d.deptno = e.deptno) AS dname    -- ⑤ 바깥 쿼리의 deptno에 해당하는 부서명
FROM emp e;

-- 연습문제2 : 스칼라 서브쿼리로 emp, dept 테이블을 사용해서 deptno, dname, 부서별 직원 수를 출력하시오.
SELECT d.deptno,                                      -- ① 부서번호
       d.dname,                                       -- ② 부서명
       (SELECT COUNT(*)                               -- ③ 스칼라 서브쿼리 시작: 해당 부서 직원 수
        FROM emp e
        WHERE e.deptno = d.deptno) AS emp_count       -- ④ 바깥 쿼리의 deptno와 비교하여 인원 수 계산
FROM dept d;

-- 연습문제2-1 :  JOIN + GROUP BY 방식
SELECT d.deptno,                                  -- ① 부서번호
       d.dname,                                   -- ② 부서명
       COUNT(e.empno) AS emp_count                -- ③ 부서별 직원 수 (조인된 emp에서 집계)
FROM dept d
LEFT JOIN emp e ON d.deptno = e.deptno            -- ④ dept 기준으로 emp를 LEFT JOIN
GROUP BY d.deptno, d.dname;                       -- ⑤ 부서별로 그룹핑 (dept 기준)

-- 연습문제3 : 부서인원 3명 이상인 부서 조회
SELECT d.deptno,                                     -- ① 부서번호
       d.dname,                                      -- ② 부서명 (→ 반드시 GROUP BY에도 포함돼야 함)
       COUNT(e.empno) AS emp_count                   -- ③ 부서별 인원 수
FROM dept d, emp e                                   -- ④ 옛날 스타일 FROM 절
WHERE d.deptno = e.deptno                            -- ⑤ 조인 조건
GROUP BY d.deptno, d.dname                           -- ✅ 부서명도 포함시켜야 에러 안 남
HAVING COUNT(e.empno) >= 3;                          -- ⑥ 인원 수가 3명 이상인 부서만 출력







