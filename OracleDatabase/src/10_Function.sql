-- 라이브러리 함수 요약
-- * 오라클 명령어 : 내장 함수
-- [1] 샘플 테이블인 dual 테이블

select * from tab;
select * from dual;
-- dual : 테이블이 대상이 아닌 연산을 하려고 할 때 from 다음에 형식적으로 붙이는 없는 테이블의 이름

-- [2] 임시 데이터 출력 
select 1234*1234 from dual;

-- ** 문자열 처리 관련 함수 **
-- [3] lower() : 모든 문자를 소문자로 변환
select lower('Hong Kil Dong') as "소문자" from dual;
-- [4] upper() : 모든 문자를 대문자로 변환
select upper('Hong Kil Dong') as "대문자" from dual;
-- [5] initcap() : 첫 자만 대문자로 변환
select initcap('Hong Kil Dong') as "첫 글자만 대문자" from dual;

-- [6] concat() : 문자열 연결
select concat('이젠 IT', '아카데미') from dual;
-- [7] length() : 문자열의 길이
select length('이젠 아이티 아카데미'), length('The ezen IT') from dual;

-- [8] substr() : 문자열 추출(데이터, 인덱스(1), 카운트)
select substr('홍길동 만세', 2, 4) from dual;
-- substr의 경우 자바의 substring처럼 시작 번째 부터 끝 번째 +1이 아닌
-- 시작번째부터 글자 수를 나타냅니다. 위의 경우 2번째 글자부터 4개의 글자를 표시

-- [9] instr() : 문자열 시작 위치
select instr('홍길동 만세 동그라미', '동') from dual;

-- [10] lpad(), rpad() : 자리 채우기
select lpad('Oracle', 20, '#') from dual; -- ##############Oracle
select rpad('Oracle', 20, '*') from dual; -- Oracle**************

-- [11] trim() : 컬럼이나 대상 문자열에서 특정 문자가 첫 번째 글자이거나 마지막 글자이면 잘라내고 남은 문자열 반환.
select trim('a' from 'aaaOracleaaaaaaaa') as result from dual; -- Oracle
select trim(' ' from '      Oracle     ') as result from dual; -- Oracle

-- *** 수직 처리 관련 함수 ***
-- [12] round() : 반올림( 음수 : 소수점 이상 자리)
select round(12.3456, 3) from dual;
-- 12.3456 : 반올림하려는 대상 숫자 3 : 반올림하여 표시하고자하는 마지막 자릿 수

-- 3 : 소수점 넷째자리에서 반올림하여 셋째자리까지 남김
-- 2 : 소수점 셋째자리에서 반올림하여 둘째자리까지 남김
-- 1 : 소수점 둘째자리에서 반올림하여 첫째자리까지 남김
-- 0 : 소수점 첫째자리에서 반올림하여 소수점 자릿수 없앰
-- -1 : 소수점 1의 자리에서 반올림하여 10의 자리까지 남김
-- -2 : 소수점 10의 자리에서 반올림하여 100의 자리자리까지 남김
-- -3 : 소수점 100의 자리에서 반올림하여 1000의 자리까지 남김
select round(1728.9382, 3) from dual; -- 1728.938
select round(1728.9382, 2) from dual; -- 1728.94
select round(1728.9382, 1) from dual; -- 1728.9
select round(1728.9382, 0) from dual; -- 1729
select round(1728.9382, -1) from dual; -- 1730
select round(1728.9382, -2) from dual; -- 1700
select round(1728.9382, -3) from dual; -- 2000

-- [13] abs() : 절대값
select abs(-10) from dual; -- 10
-- [14] floor() : 소수점 아래 절삭 - 반올림 없음
select floor(12.94567) from dual -- 12
-- [15] trunc() : 특정 자리 자르기 - 반올림 없음, 3은 남기고 싶은 소수점 아래 자리수
select trunc(12.34567, 3) from dual; -- 12.345
-- [16] mod() : 나머지 -- 8을 5로 나눈 나머지
select mod(8,5) from dual; -- 3

-- *** 날짜 처리 관련 함수 ***
-- [17] sysdate : 날짜
select sysdate from dual; -- 오늘 날짜와 현재 시간

-- [18] months_between() : 개월 수 구하지
select floor(months_between('2021-12-31', '2020-07-10')) from dual; -- 17.677419

-- [19] add_months() : 개월 수 더하기
select add_months(sysdate, 200) from dual; -- 2038-06-05 17:13:38.0

-- [20] next_day() : 다가올 요일에 해당하는 날짜 - 오늘 날짜(sysdate)에서 가장 가까운 일요일
select next_day(sysdate, '일요일' ) from dual;

-- [21] last_day() : 해당 달의 마지막 일 수
select last_day(sysdate) from dual;
-- select last_day(2020-12-15) from dual;

-- [22] to_char() : 문자열로 반환
select to_char(sysdate, 'yyyy-mm-dd') from dual;
-- [23] to_date() : 날짜형(date)으로 변환
select to_date('2019/12/31', 'yyyy/mm/dd') from dual;

-- 그 외 활용 가능한 함수들...

-- [24] nvl() : NULL인 데이터를 다른 데이터로 변환
select comm/100 as comm_pct from emp;
-- comm 필드 값에 따라서 일부 레코드는 계산 결과가 null이 나올 수 있음
select nvl(comm, 1)/100 as comm_pct from emp;
-- 해당 필드 값이 null이면 1로 바꿔서 계산에 참여

-- PoWER 함수
select power(3,2), power(3,3), power(3, 3.0001) from dual;
-- 첫 번째 요소 값을 두 번째 요소만큼 거듭 제곱

-- 제곱근 SQRT
select SQRT(2), SQRT(5) from dual;

select * from DEPARTMENTS;
select * from EMPLOYEES;
select * from tab;

-- [25] decode() : switch문과 같은 기능
select employee_id, emp_name, decode(department_id,
	10, 'ACCOUNT',
	20, 'RESEARCH',
	30, 'SALES',
	40, 'OPERATION',
	50, 'SH_CHECK',
	60, 'IT_PRPG',
	70, 'PR_REP',
	80, 'SA_REP',
	90, 'AD_PRES',
	100, 'FI_ACCOUNT',
	110, 'AC_ACCOUNT'
) as "부서명" 
from employees;

-- [26] case() : if~ else if ~ 와 비슷한 구조
select employee_id, emp_name, department_id,
	case when department_id = 10 then 'ACCOUNT'
			when department_id = 20 then 'RESEARCH'
			when department_id = 30 then 'SALES'
			when department_id = 40 then 'OPERATIONS'
			when department_id = 50 then 'SH_CHECK'
			when department_id = 60 then 'IT_PROG'
			when department_id = 70 then 'PR_REP'
			when department_id = 80 then 'SA_REP'
			when department_id = 90 then 'AD_PRES'
			when department_id = 100 then 'FI_ACCOUNT'
			when department_id = 110 then 'AC_ACCOUNT'
	end as "부서명"
from employees;
			

-- mod와 remainder
-- 둘 다 첫 번째 요소를 두 번째 요소로 나눈 나머지를 계산하지만 내부적 
-- 계산법이 조금 다름
select mod(19,4), mod(19.123, 4.2) from dual;
select remainder(19,4), remainder(19.123, 4.2) from dual;
-- mod : 19 - 4 * floor(19/4)
-- remainder : 19 - 4 * round(19/4)

-- 10.5를 4.2로 나눈 나머지 : 10.5 - (4.2 * 2) = 2.1
