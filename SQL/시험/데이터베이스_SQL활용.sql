--데이터베이스 SQL 활용
--1번
INSERT INTO emp VALUES (1011, '이순신', '104', '부장', 500, NULL);

--2번
UPDATE dept
SET area = '서울'
WHERE dname = '영업부';

UPDATE dept
SET area = '부산'
WHERE dname != '영업부';

--3번
DELETE FROM emp
WHERE deptno = (
    SELECT deptno
    FROM dept
    WHERE dname = '홍보부'
);

--4번
--case문
SELECT 
    name AS "이름",
    pay AS "급여",
    CASE 
        WHEN pay BETWEEN 0 AND 200 THEN pay * 0.05
        WHEN pay BETWEEN 201 AND 300 THEN pay * 0.10
        WHEN pay BETWEEN 301 AND 400 THEN pay * 0.15
        ELSE pay * 0.20
    END AS "세금"
FROM emp;

--decode문
SELECT 
    name AS "이름",
    pay AS "급여",
    DECODE(
        SIGN(pay - 200),
        -1, pay * 0.05,
         0, pay * 0.05,
        DECODE(SIGN(pay - 300),
            -1, pay * 0.10,
             0, pay * 0.10,
            DECODE(SIGN(pay - 400),
                -1, pay * 0.15,
                 0, pay * 0.15,
                pay * 0.20
            )
        )
    ) AS "세금"
FROM emp;

--5번
SELECT 
    e.name AS "이름",
    d.dname AS "부서이름",
    e.position AS "직급"
FROM emp e
JOIN dept d ON e.deptno = d.deptno
WHERE d.dname IN ('영업부', '총무부')
ORDER BY e.name ASC;

--6번
SELECT 
    e.name       AS "사원 이름",
    m.name       AS "상사 이름"
FROM emp e
LEFT JOIN emp m ON e.pempno = m.empno
ORDER BY e.name;

--7번
SELECT
    d.dname AS "부서 이름",
    ROUND(AVG(e.pay), 2) AS "평균 급여"
FROM emp e
JOIN dept d ON e.deptno = d.deptno
GROUP BY d.dname
HAVING AVG(e.pay) >= 350;

--8번
SELECT
    e.name AS "사원이름",
    d.dname AS "부서이름",
    e.pay AS "급여"
FROM emp e
JOIN dept d ON e.deptno = d.deptno
WHERE (e.deptno, e.pay) IN (
    SELECT deptno, MAX(pay)
    FROM emp
    GROUP BY deptno)
ORDER BY d.dname;

--9번
SELECT
    e.name AS "사원이름",
    d.dname AS "부서이름"
FROM emp e
JOIN dept d on e.deptno = d.deptno
WHERE e.deptno = (
    SELECT deptno
    FROM emp
    WHERE name = '이성규');
    
--10번
SELECT
    name AS "사원이름",
    position AS "직급",
    pay AS "급여"
FROM emp
WHERE pay > (
    SELECT MIN(pay)
    FROM emp
    WHERE position = '과장');
