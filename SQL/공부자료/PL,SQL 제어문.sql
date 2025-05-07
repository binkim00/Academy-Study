---------------------------------PL/SQL 제어문-----------------------------------
--SET SERVEROUTPUT ON; 설정
--IF~END IF 문장 : 조건이 2개 이상인 경우
DECLARE
    -- 교수 테이블에서 데이터를 담을 변수 선언
    v_profno   professor.profno%TYPE;   -- 교수 번호 저장
    v_name     professor.name%TYPE;     -- 교수 이름 저장
    v_deptno   professor.deptno%TYPE;   -- 부서 번호 저장
    v_dname    VARCHAR2(30);             -- 부서명 저장 (직접 문자열 지정)
BEGIN
    -- 1. professor 테이블에서 교수번호 1001번의 정보를 읽어와 변수에 저장
    SELECT profno, name, deptno
    INTO v_profno, v_name, v_deptno
    FROM professor
    WHERE profno = 1001;
    
    -- 2. 부서번호에 따라 부서명을 직접 지정
    -- (조건을 각각 IF ~ THEN ~ END IF로 분리해서 작성)
    
    IF v_deptno = 101 THEN
        v_dname := 'Computer Engineering';  -- 부서번호가 101이면
    END IF;
    
    IF v_deptno = 102 THEN
        v_dname := 'Multimedia Engineering'; -- 부서번호가 102이면
    END IF;
    
    IF v_deptno = 103 THEN
        v_dname := 'Software Engineering';   -- 부서번호가 103이면
    END IF;
    
    -- (주의: 이 방식은 101, 102, 103 중 하나만 일치한다고 가정해야 한다.
    --       만약 부서번호가 여러 조건에 동시에 걸릴 수 있으면 위험할 수 있음)

    -- 3. 최종 결과를 한 줄로 출력
    DBMS_OUTPUT.PUT_LINE(
        '교수번호: ' || v_profno || ', ' ||
        '교수이름: ' || v_name || ', ' ||
        '부서번호: ' || v_deptno || ', ' ||
        '부서명: ' || v_dname
    );
END;
/

--IF THEN ELSIF END IF문장 : 조건이 여러 개일 경우 많이 사용
DECLARE
    -- 교수 번호를 저장할 변수
    v_profno professor.profno%TYPE;
    -- 교수 이름을 저장할 변수
    v_name professor.name%TYPE;
    -- 교수 부서 번호를 저장할 변수
    v_deptno professor.deptno%TYPE;
    -- 교수 부서명을 저장할 변수 (문자열로 직접 지정)
    v_dname VARCHAR2(30);
BEGIN
    -- professor 테이블에서 교수번호 1001인 교수의 정보를 가져와 변수에 저장
    SELECT profno, name, deptno
    INTO v_profno, v_name, v_deptno
    FROM professor
    WHERE profno = 1001;
    
    -- 부서 번호에 따라 부서명을 직접 지정
    IF v_deptno = 101 THEN
        v_dname := 'Computer Engineering';  -- 부서번호가 101이면
    ELSIF v_deptno = 102 THEN
        v_dname := 'Multimedia Engineering'; -- 부서번호가 102이면
    ELSIF v_deptno = 103 THEN
        v_dname := 'Software Engineering';   -- 부서번호가 103이면
    ELSE
        v_dname := 'Unknown Department';      -- 위 조건이 모두 아니면
    END IF;
    
    -- 교수정보를 한 줄로 출력
    DBMS_OUTPUT.PUT_LINE(
        '교수번호: ' || v_profno || ', ' ||
        '교수이름: ' || v_name || ', ' ||
        '부서번호: ' || v_deptno || ', ' ||
        '부서명: ' || v_dname
    );
END;
/

--CASE문 : 비교 조건이 여러 가지일 겨우 간결하고 간단하게 조건을 파악
--CASE문 예시1
DECLARE
    -- 교수 번호를 저장할 변수
    v_profno   professor.profno%TYPE; 
    -- 교수 이름을 저장할 변수
    v_name     professor.name%TYPE;
    -- 교수 부서 번호를 저장할 변수
    v_deptno   professor.deptno%TYPE;
    -- 부서명을 저장할 변수
    v_dname    VARCHAR2(30);
BEGIN
    -- 1. professor 테이블에서 교수번호 1001번 정보를 가져옴
    SELECT profno, name, deptno
    INTO v_profno, v_name, v_deptno
    FROM professor
    WHERE profno = 1001;
    
    -- 2. 부서 번호에 따라 부서명을 CASE문으로 지정
    v_dname := CASE v_deptno
                  WHEN 101 THEN 'Computer Engineering'   -- 부서번호 101이면
                  WHEN 102 THEN 'Multimedia Engineering' -- 부서번호 102이면
                  WHEN 103 THEN 'Software Engineering'   -- 부서번호 103이면
                  ELSE 'Unknown Department'              -- 그 외는 'Unknown Department'
               END;
    
    -- 3. 최종 결과를 한 줄로 출력
    DBMS_OUTPUT.PUT_LINE(
        '교수번호: ' || v_profno || ', ' ||
        '교수이름: ' || v_name || ', ' ||
        '부서번호: ' || v_deptno || ', ' ||
        '부서명: ' || v_dname
    );
END;
/

--CASE문 예시2
DECLARE
    -- 사용자 입력용 변수
    v_empno   emp.empno%TYPE;    -- 사원 번호
    
    -- 조회 결과를 저장할 변수들
    v_ename   emp.ename%TYPE;    -- 사원 이름
    v_sal     emp.sal%TYPE;      -- 현재 급여
    v_deptno  emp.deptno%TYPE;   -- 부서 번호
    v_up_sal  NUMBER(10,2);      -- 인상 후 급여
BEGIN
    -- 사용자로부터 사원 번호 입력
    v_empno := &입력할_사원번호;

    -- emp 테이블에서 사원의 기본 정보를 조회
    SELECT ename, sal, deptno
    INTO v_ename, v_sal, v_deptno
    FROM emp
    WHERE empno = v_empno;
    
    -- 부서번호에 따라 연봉 인상률을 CASE문으로 결정
    CASE 
        WHEN v_deptno = 10 THEN
            v_up_sal := v_sal * 1.3;   -- 부서 10: 30% 인상
        WHEN v_deptno IN (20, 30) THEN
            v_up_sal := v_sal * 1.5;   -- 부서 20 또는 30: 50% 인상
        WHEN v_deptno > 30 THEN
            v_up_sal := v_sal * 1.2;   -- 부서번호 30 초과: 20% 인상
        ELSE
            v_up_sal := v_sal;         -- 그 외: 인상 없음
    END CASE;
    
    -- 결과를 출력
    DBMS_OUTPUT.PUT_LINE(
        '사원번호: ' || v_empno || ', ' ||
        '사원이름: ' || v_ename || ', ' ||
        '현재연봉: ' || TO_CHAR(v_sal, 'FM$999,999.00') || ', ' ||
        '부서번호: ' || v_deptno || ', ' ||
        '인상후연봉: ' || TO_CHAR(v_up_sal, 'FM$999,999.00')
    );
END;
/

--CASE문 예시2 -> IF문으로 작성
DECLARE
    -- 사용자 입력용 변수
    v_empno   emp.empno%TYPE;    -- 사원 번호
    
    -- 조회 결과를 저장할 변수들
    v_ename   emp.ename%TYPE;    -- 사원 이름
    v_sal     emp.sal%TYPE;      -- 현재 급여
    v_deptno  emp.deptno%TYPE;   -- 부서 번호
    v_up_sal  NUMBER(10,2);      -- 인상 후 급여
BEGIN
    -- 사용자로부터 사원 번호 입력
    v_empno := &입력할_사원번호;

    -- emp 테이블에서 입력한 사원 번호로 사원의 기본 정보를 조회
    SELECT ename, sal, deptno
    INTO v_ename, v_sal, v_deptno
    FROM emp
    WHERE empno = v_empno;
    
    -- 부서번호에 따라 연봉 인상률을 IF ~ ELSIF ~ ELSE로 결정
    IF v_deptno = 10 THEN
        v_up_sal := v_sal * 1.3;   -- 부서번호가 10번이면 30% 인상
    ELSIF v_deptno = 20 OR v_deptno = 30 THEN
        v_up_sal := v_sal * 1.5;   -- 부서번호가 20번 또는 30번이면 50% 인상
    ELSIF v_deptno > 30 THEN
        v_up_sal := v_sal * 1.2;   -- 부서번호가 30번 초과면 20% 인상
    ELSE
        v_up_sal := v_sal;         -- 그 외 부서번호는 인상 없음
    END IF;
    
    -- 결과 출력
    DBMS_OUTPUT.PUT_LINE(
        '사원번호: ' || v_empno || ', ' ||
        '사원이름: ' || v_ename || ', ' ||
        '현재연봉: ' || TO_CHAR(v_sal, 'FM$999,999.00') || ', ' ||
        '부서번호: ' || v_deptno || ', ' ||
        '인상후연봉: ' || TO_CHAR(v_up_sal, 'FM$999,999.00')
    );
END;
/

----------------------------------반복문-----------------------------------------
--BASIC LOOP문 : 주어진 조건이 참일 경우 반복을 중단할 경우 많이 사용
DECLARE
    v_num NUMBER := 10;  -- 시작 숫자를 10으로 초기화
BEGIN
    LOOP -- 반복을 시작
        -- 현재 숫자 출력
        DBMS_OUTPUT.PUT_LINE('현재 숫자: ' || v_num);
        
        -- 다음 숫자로 증가
        v_num := v_num + 1;
        
        -- v_num이 16이 되면 반복 종료 (15까지 출력하고 멈춤)
        EXIT WHEN v_num > 15;
    END LOOP; --반복문 끝냄
END;
/

--WHILE 반복문 : 조건이 참일 동안 계속 반복하는 반복문
--BASIC LOOP처럼 EXIT WHEN을 따로 쓰지 않아도 되고, 반복 시작할 때 조건을 먼저 검사
DECLARE
    v_num NUMBER := 10;  -- 시작 숫자를 10으로 초기화
BEGIN
    -- v_num이 15 이하인 동안 반복
    WHILE v_num <= 15 LOOP
        -- 현재 숫자 출력
        DBMS_OUTPUT.PUT_LINE('현재 숫자: ' || v_num);
        
        -- 다음 숫자로 증가
        v_num := v_num + 1;
    END LOOP;
END;
/

--FOR 반복문 : 정해진 범위를 처음부터 끝까지 알아서 반복하는 반복문
--변수를 따로 증가시키거나 탈출 조건을 만들 필요 없음
--가장 편하고 깔끔한 반복문
BEGIN
    -- v_num이라는 반복 변수를 10부터 15까지 자동으로 증가시키면서 반복
    FOR v_num IN 10..15 LOOP
        -- 현재 숫자 출력
        DBMS_OUTPUT.PUT_LINE('현재 숫자: ' || v_num);
    END LOOP;
END;
/
--역순 출력
BEGIN
    -- v_num을 15부터 10까지 거꾸로 감소시키면서 반복
    FOR v_num IN REVERSE 10..15 LOOP
        -- 현재 숫자 출력
        DBMS_OUTPUT.PUT_LINE('현재 숫자: ' || v_num);
    END LOOP;
END;
/

