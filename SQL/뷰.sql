---------- 뷰에 대해서 배우기 ------------
-- 뷰(View)의 기본 개념
-- 가상의 테이블로, 실제 데이터를 저장하지 않음
-- SELECT 문을 기반으로 만들어짐
-- 보안이나 복잡한 쿼리 단순화 목적으로 사용됨
-- 기본 테이블이 변경되면 뷰 결과도 자동으로 반영됨

-- 뷰의 장점 요약
-- 복잡한 쿼리 단순화 → 자주 쓰는 SELECT문을 뷰로 만들어두면 편함
-- 보안 유지 → 민감한 컬럼은 제외하고 공개할 수 있음
-- 논리적 독립성 → 실제 테이블 구조 변경 시 뷰만 수정하면 됨

-- 뷰를 통한 DML 작업 수행
create table o_table
(a NUMBER, b NUMBER);

create view view1
as
    SELECT a, b
    from o_table;
    
insert into view1 VALUES(1,2);

SELECT * from view1;
select * from o_table;
ROLLBACK;

-- 읽기 전용 뷰를 생성하는 옵션
CREATE VIEW view2
AS
    SELECT a, b
    FROM o_table
WITH READ ONLY;

insert into view2 VALUES(3,4); -- 읽기 전용이라 에러
insert into view1 VALUES(3,4); -- 앞선 view 1에 입력

SELECT * from view2;
select * from o_table;



