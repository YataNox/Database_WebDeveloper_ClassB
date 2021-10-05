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




