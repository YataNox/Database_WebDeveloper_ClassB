-- sum() : 특정 필드의 합계
select sum(rentprice) as "대여가격 합계" from booklist;
select sum(rentprice) as "대여가격 합계" from booklist where inprice >= 18000;

-- count() : 필드내의 데이터 갯수(레코드 갯수)
select count(*) as "회원 인원수" from memberlist;
select count(*) as "회원 인원수" from MEMBERLIST where bpoint >= 2000;

-- avg() : 평균
select avg(inprice) from booklist;