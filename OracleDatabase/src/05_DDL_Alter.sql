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
-- create로 테이블을 만들며 생성한 기본키와 외래키 등등은 필드명이 바뀌어도 바뀐이름으로 자동 적용됩니다.
