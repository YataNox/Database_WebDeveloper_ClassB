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

-- [2] 레코드 추가
-- 레코드 추가 명령 사용1
-- insert into 테이블 이름(필드명1, 필드명2,.....) values(값1, 값2,....)
-- 값은 문자('123')와 숫자(123)를 구분하여 입력합니다.

-- 레코드 추가 명령 사용2
-- insert into 테이블 이름 values (전체 column(필드, 열)에 넣을 값들);

-- 첫 번째 방식은 필드명과 입력되어야 하는 값을 1:1로 매핑하여 입력합니다.
-- 널 값이 있어도 되는 필드는 필드명, 또는 기본 값이 있는 필드명은 값을 생략하고 입력가능합니다.
-- 두 번째 방식은 모든 필드에 해당하는 데이터를 모두 입력하는 경우로서 필드명들을 명령어 속에
-- 나열하지 않아도 되지만, 필드의 순서대로 빠짐없이 데이터가 나열되어야 하는 불편함도 있습니다.

-- 첫 번째 방식의 레코드 추가
insert into exam01(deptno, dname, loc) values(10, 'ACCOUNT','NEW YORK');