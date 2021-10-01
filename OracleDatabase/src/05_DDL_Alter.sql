-- 테이블의 수정(ALTER)

--  1. 필드명의 변경
-- ALTER TABLE 테이블이름 RENAME COLUMN 변경 전 이름 TO 변경 후 이름
-- 테이블이름 : 변경하고자하는 필드명이 있는 테이블의 이름
-- booklist 테이블의 subject필드명을 title로 수정하세요
alter table booklist rename column subject to title;
select * from booklist;
-- 반대로 title 필드명을 subject 필드명으로 수정합니다.
alter table booklist rename column title to subject;

-- booklist의 outprice필드의 이름을 rentprice로 수정하세요
alter table booklist rename column outprice to rentprice;

-- memberlist 테이블의 membername필드를 name으로 변경하세요
alter table memberlist rename column membername to name;

-- rentlist 테이블의 rent_date 필드를 rentdate로 변경하세요
alter table rentlist rename column rent_date to rentdate;

-- rentlist의 indexk를  numseq로 변경하세요
alter table rentlist rename column indexk to numseq;

select * from RENTLIST;
select * from booklist;
-- create로 테이블을 만들며 생성한 기본키와 외래키 등등은 필드명이 바뀌어도 바뀐이름으로 자동 적용됩니다.


-- 2. 필드 자료형의 변경
-- ALTER TABLE 테이블명 MODIFY 필드명 자료형
-- varchar2(12)였던 memberlist테이블의 name 필드를 varchar2(30)으로 변경
alter table memberlist modify name varchar2(30);

-- booklist의 booknum 필드를 number(5)로 자료형 변경
alter table booklist modify booknum number(5);
-- memberlist의 membernum 필드를 number(5)로 자료형 변경
alter table memberlist modify membernum number(5);
-- rentlist의 booknm 필드를 number(5)로 자료형 변경
alter table rentlist modify booknum number(5);
-- rentlist의 membernum 필드를 number(5)로 자료형 변경
alter table rentlist modify membernum number(5);

-- 위의 쿼리들은 외래키로 연결되어 참조되고, 참조하고 있는 필드들의 수정쿼리로 바로 실행이 불가능합니다.
-- 가능하게 하려면, 외래키 제약 조건을 수정하여 없애버리고, 참조되는 필드와 참조하는 필드를 모두 수정한 후
-- 외래키 제약 조건을 다시 설정합니다.
-- alter 명령에 의해서 제약조건을 수정하는 명령을 아래에서 학습합니다.


