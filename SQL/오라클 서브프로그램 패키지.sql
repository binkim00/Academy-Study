-------------------------------오라클 서브프로그램---------------------------------
--패키지 : Oracle에서 PACKAGE는 관련된 프로시저, 함수, 변수, 예외, 커서 등을 하나로 묶은 PL/SQL 단위 프로그램
--패키지를 사용하는 이유
--논리적으로 관련된 프로시저/함수를 하나의 단위로 묶어 관리
--한 번 정의해두면 여러 곳에서 호출 가능
--처음 호출 시 패키지가 전체 메모리에 로드되어, 이후에는 빠르게 실행
--헤더(PACKAGE)에는 외부에 공개할 항목만 넣고, 본체(PACKAGE BODY)에는 내부 구현을 감춤

--예시
--PACKAGE 선언부
CREATE OR REPLACE PACKAGE member_mg
IS
    -- 이름을 통해 성별을 조회하는 프로시저
    PROCEDURE find_sex (
        v_name member.name%TYPE
    );

    -- 이름과 주민번호로 ID를 찾는 프로시저
    PROCEDURE find_id (
        v_name member.name%TYPE,
        v_no   member.jumin%TYPE
    );

    -- 아이디와 답변으로 비밀번호 찾기
    PROCEDURE find_pwd (
        v_id IN member.id%TYPE,
        v_an IN member.an_key_dap%TYPE
    );
END member_mg;
/

--PACKAGE BODY부
CREATE OR REPLACE PACKAGE BODY member_mg
AS
    -- [1] 이름을 입력받아 성별을 출력하는 프로시저
    PROCEDURE find_sex (
        v_name member.name%TYPE
    )
    IS
        v_name2    member.name%TYPE;     -- 조회된 이름 저장 변수
        v_gender   CHAR(6);              -- 성별 문자열 저장 변수
        v_count    NUMBER := 0;          -- 이름 존재 여부 확인용 카운터
        exc_noname EXCEPTION;            -- 이름이 없을 때 발생시킬 사용자 정의 예외
        exc_many   EXCEPTION;            -- 이름이 둘 이상일 때 발생시킬 사용자 정의 예외
    BEGIN
        -- 이름이 몇 명 있는지 조회
        SELECT COUNT(*) INTO v_count
        FROM member
        WHERE name = v_name;

        IF v_count = 0 THEN
            RAISE exc_noname;  -- 이름이 없으면 예외 발생
        ELSIF v_count >= 2 THEN
            RAISE exc_many;    -- 동명이인이 여러 명이면 예외 발생
        ELSE
            -- 주민번호에서 7번째 자리를 기준으로 성별 판별
            SELECT name,
                   CASE WHEN SUBSTR(jumin, 7, 1) IN ('1', '3') THEN 'MAN'
                        ELSE 'WOMAN'
                   END
            INTO v_name2, v_gender
            FROM member
            WHERE name = v_name;

            -- 결과 출력
            DBMS_OUTPUT.PUT_LINE(v_name2 || '의 성별은 ' || v_gender || '입니다.');
        END IF;
    EXCEPTION
        WHEN exc_noname THEN
            -- 이름이 없을 때 에러 메시지 출력
            RAISE_APPLICATION_ERROR(-20001, 'No Name !');
        WHEN exc_many THEN
            -- 이름이 여러 명일 때 에러 메시지 출력
            RAISE_APPLICATION_ERROR(-20002, 'Many name !! Check Your Name!');
    END find_sex;

    -- [2] 이름과 주민번호를 입력받아 ID를 출력하는 프로시저
    PROCEDURE find_id (
        v_name member.name%TYPE,
        v_no   member.jumin%TYPE
    )
    IS
        v_count     NUMBER := 0;         -- 이름 존재 여부 확인용
        v_count2    NUMBER := 0;         -- 주민번호 존재 여부 확인용
        v_name2     member.name%TYPE;    -- 최종 조회된 이름
        v_id2       member.id%TYPE;      -- 최종 조회된 ID
        exc_noname  EXCEPTION;           -- 이름 없을 때 예외
        exc_nojumin EXCEPTION;           -- 주민번호 없을 때 예외
    BEGIN
        -- 이름 존재 여부 확인
        SELECT COUNT(*) INTO v_count
        FROM member
        WHERE name = v_name;

        IF v_count = 0 THEN
            RAISE exc_noname;  -- 이름이 없으면 예외
        ELSE
            -- 주민번호 존재 여부 확인
            SELECT COUNT(*) INTO v_count2
            FROM member
            WHERE jumin = v_no;

            IF v_count2 = 0 THEN
                RAISE exc_nojumin;  -- 주민번호 없으면 예외
            ELSE
                -- 이름과 주민번호가 모두 일치하는 회원의 이름과 ID 조회
                SELECT name, id INTO v_name2, v_id2
                FROM member
                WHERE name = v_name AND jumin = v_no;

                -- 결과 출력
                DBMS_OUTPUT.PUT_LINE(v_name2 || '의 ID는 ' || v_id2 || '입니다.');
            END IF;
        END IF;
    EXCEPTION
        WHEN exc_noname THEN
            -- 이름 없을 때 에러
            RAISE_APPLICATION_ERROR(-20001, 'No Name !');
        WHEN exc_nojumin THEN
            -- 주민번호 없을 때 에러
            RAISE_APPLICATION_ERROR(-20002, 'No Number !');
    END find_id;

    -- [3] ID와 연관 단어를 입력받아 비밀번호를 출력하는 프로시저
    PROCEDURE find_pwd (
        v_id IN member.id%TYPE,
        v_an IN member.an_key_dap%TYPE
    )
    IS
        v_count   NUMBER := 0;              -- ID 존재 여부 확인용
        v_id2     member.id%TYPE;           -- 최종 조회된 ID
        v_an_dap  member.an_key_dap%TYPE;   -- 실제 저장된 연관 단어
        v_pw      member.passwd%TYPE;       -- 비밀번호
        exc_noid  EXCEPTION;                -- ID가 없을 때 예외
        exc_noan  EXCEPTION;                -- 연관 단어가 다를 때 예외
    BEGIN
        -- ID 존재 여부 확인
        SELECT COUNT(*) INTO v_count
        FROM member
        WHERE id = v_id;

        IF v_count = 0 THEN
            RAISE exc_noid;  -- ID가 없으면 예외 발생
        ELSE
            -- 해당 ID의 연관 단어 조회
            SELECT an_key_dap INTO v_an_dap
            FROM member
            WHERE id = v_id;

            IF v_an_dap = v_an THEN
                -- 연관 단어 일치 시 비밀번호 조회
                SELECT id, passwd INTO v_id2, v_pw
                FROM member
                WHERE id = v_id;

                DBMS_OUTPUT.PUT_LINE(v_id2 || '의 비밀번호는 ' || v_pw || '입니다.');
            ELSE
                RAISE exc_noan;  -- 연관 단어 불일치 시 예외
            END IF;
        END IF;
    EXCEPTION
        WHEN exc_noid THEN
            -- ID 없을 때 에러 메시지
            RAISE_APPLICATION_ERROR(-20003, 'No ID !');
        WHEN exc_noan THEN
            -- 연관 단어 다를 때 에러 메시지
            RAISE_APPLICATION_ERROR(-20004, 'No Answer !');
    END find_pwd;

END member_mg;
/

--생성된 PACKAGE 테스트
--EXEC는 단순 실행이지만, 예외가 발생하면 메시지만 출력되고 끝
--BEGIN ... END; 구문은 익셉션이 발생한 라인을 잡아내고 디버깅하기에 더 유용
EXEC member_mg.find_sex('Nicholas Cage');   -- 존재하는 이름, 1명 → 정상 출력
EXEC member_mg.find_sex('Jodie Foster');    -- 존재하는 이름, 1명 → 정상 출력

EXEC member_mg.find_sex('Meg Ryan');        -- 이름이 여러 명일 수도 있음 → 예외 처리
BEGIN member_mg.find_sex('Meg Ryan'); END;  -- BEGIN~END로 감싸서 예외 확인 가능
EXEC member_mg.find_id('Nicholas Cage','7510231234567');  -- 이름+주민번호 일치 → 정상 출력

EXEC member_mg.find_id('Nicholas Cage','1234567123456');  -- 주민번호 틀림 → 예외
BEGIN member_mg.find_id('Nicholas Cage','1234567123456'); END;

EXEC member_mg.find_id('Sharon','7510231234567');         -- 이름이 없음 → 예외

BEGIN member_mg.find_id('Sharon','7510231234567'); END;
EXEC member_mg.find_pwd('simson','Jodie Foster');         -- 아이디와 연관단어 일치 → 정상 출력

EXEC member_mg.find_pwd('abcd','Jodie Foster');           -- 아이디 없음 → 예외
BEGIN member_mg.find_pwd('abcd','Jodie Foster'); END;

EXEC member_mg.find_pwd('simson','Nicholas Cage');        -- 연관단어 틀림 → 예외
BEGIN member_mg.find_pwd('simson','Nicholas Cage'); END;
