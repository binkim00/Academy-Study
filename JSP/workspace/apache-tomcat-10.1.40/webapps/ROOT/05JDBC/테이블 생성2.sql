--system 계정에서 실행
--레코드를 삽입하기 전 테이블 스페이스 설정을 위해 실행합니다. 
--테이블 스페이스를 조회
select tablespace_name, status, contents from dba_tablespaces;

--테이블 스페이스별 가용 공간을 확인
select tablespace_name, sum(bytes), max(bytes) from dba_free_space
group by tablespace_name;

--musthave 계정이 사용하는 테이블 스페이스도 확인
select username, default_tablespace from dba_users
where username in upper('musthave');

--musthave 계정이 USERS 테이블 스페이스에 데이터를 입력할 수 있도록 5m의 용량 할당
alter user musthave quota 5m on users;