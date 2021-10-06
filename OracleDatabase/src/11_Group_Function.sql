-- sum() : 특정 필드의 합계
select sum(rentprice) as "대여가격 합계" from booklist;
select sum(rentprice) as "대여가격 합계" from booklist where inprice >= 18000;

-- count() : 필드내의 데이터 갯수(레코드 갯수)
select count(*) as "회원 인원수" from memberlist;
select count(*) as "회원 인원수" from MEMBERLIST where bpoint >= 2000;

-- avg() : 평균
select round(avg(inprice), 0) from booklist;

-- MAX : 최대 값
select MAX(inprice) from booklist;

-- MIN : 최소 값
select MIN(inprice) from booklist;

-- variance(분산) stddev(표준편차)
select variance(salary), stddev(salary) from EMPLOYEES;
select salary from EMPLOYEES;


-- group by : 하나의 필드를 지목해서 같은 값끼리 그룹을 형성한 결과를 도출합니다.
select * from RENTLIST;
-- 도서별 대여 건수
select booknum, count(*) from rentlist group by booknum;
-- 날짜별 할인 금액의 평균
select rentdate, avg(discount) from rentlist group by rentdate order by rentdate desc;
-- rentlist 날짜별 대여 건수
select rentdate, count(*) from rentlist group by rentdate;
-- employees 테이블의 부서 id별 급여의 평균
select * from EMPLOYEES;
select department_id, avg(salary) from employees group by DEPARTMENT_id;
-- kor_loan_status 테이블의 period(년도와 월)을 1차 그룹으로 region(지역)을 2차 그룹으로한
-- 대출 잔액(loan_jan_amt)의 합계
select * from kor_loan_status;
select period, region, sum(loan_jan_amt) from KOR_LOAN_STATUS
group by period, region;


-- HAVING절 : 그룹핑된 내용들에 조건을 붙일 때
-- 평균 금액이 180미만인 데이터의 날짜별 할인금액의 평균
select avg(discount) from rentlist group by rentdate having avg(discount) < 180;

-- kor_loan_status 테이블의 날짜별 대출 잔액의 합계 중 period가 2013년 11월인 데이터 출력
select period, region, sum(loan_jan_amt) from KOR_LOAN_STATUS
group by period, region having period = '201311';

-- ** group by 절에는 select와 from 사이에 기술된 함수를 제외한 모든 필드를 반드시 써야합니다.


