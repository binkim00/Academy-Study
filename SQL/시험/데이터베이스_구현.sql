--데이터베이스 구현 문제

--1번
--Oracle Home은 오라클 데이터베이스가 설치(저장)된 기본 디렉터리 위치
--이 디렉터리에는 Oracle 실행 파일, 라이브러리, 설정 파일, 로그 파일 등이 포함
--Oracle 툴들이 해당 경로에 설치된 라이브러리 및 설정 파일을 참조할 수 있도록 도와줌
--여러 버전의 Oracle을 하나의 시스템에 설치할 경우, 각각의 ORACLE_HOME을 분리해서 관리함으로써 충돌을 방지

--2번

--3번
CREATE TABLE dept (
    deptno VARCHAR2(6) PRIMARY KEY,
    dname  VARCHAR2(10) NOT NULL UNIQUE,
    area   VARCHAR2(10)
);

--4번
CREATE TABLE emp (
    empno   NUMBER PRIMARY KEY,
    name    VARCHAR2(10) NOT NULL UNIQUE,
    deptno  VARCHAR2(6),
    position VARCHAR2(10),
    pay     NUMBER NOT NULL,
    pempno  NUMBER,
    CONSTRAINT fk_deptno FOREIGN KEY (deptno) REFERENCES dept(deptno),
    CONSTRAINT chk_position CHECK (position IN ('사원', '대리', '과장', '부장'))
);

--5번
INSERT INTO dept VALUES ('101', '영업부', NULL);
INSERT INTO dept VALUES ('102', '총무부', NULL);
INSERT INTO dept VALUES ('103', '기획부', NULL);
INSERT INTO dept VALUES ('104', '홍보부', NULL);
commit;

INSERT INTO emp VALUES (1001, '홍길동', '101', '부장', 450, NULL);
INSERT INTO emp VALUES (1002, '김연아', '102', '부장', 400, NULL);
INSERT INTO emp VALUES (1003, '박지성', '101', '과장', 350, 1001);
INSERT INTO emp VALUES (1004, '김태근', '103', '과장', 410, NULL);
INSERT INTO emp VALUES (1005, '서찬수', '101', '대리', 300, 1003);
INSERT INTO emp VALUES (1006, '김수현', '103', '대리', 400, 1004);
INSERT INTO emp VALUES (1007, '정동민', '102', '대리', 320, 1002);
INSERT INTO emp VALUES (1008, '이성규', '102', '사원', 380, 1007);
INSERT INTO emp VALUES (1009, '임진영', '103', '사원', 250, 1006);
INSERT INTO emp VALUES (1010, '서진수', '101', '사원', 200, 1005);
commit;
