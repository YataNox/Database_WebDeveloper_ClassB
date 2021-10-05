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
