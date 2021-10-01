-- DML (Data Management Language)
-- 데이터 조작 언어

-- 테이블에 레코드를 조작(추가, 수정, 삭제, 조회)하기 위한 명령어들
-- INSERT(추가)
-- UPDATE(수정)
-- DELETE(삭제)
-- SELECT(조회 및 선택)

-- [1] 샘플 데이블 생성
DROP TABLE exam01 purge;

create table exam01(
	deptno number(2), -- 부서번호
	dname varchar2(14), -- 부서명
	loc varchar2(14) -- 위치
);