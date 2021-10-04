-- 오라클 명령어 : select 문 (검색)
-- [1] scott 사용자가 관리하고 있는 테이블 목록
select * from tab;

-- [2] 특정 테이블의 구조 조화(필드리스트/ 데이터 형식)
desc dept; -- 커맨드 창(sqlplus)에서 확인 요망
desc PERSON; -- 커맨드 창(sqlplus)에서 확인 요망

-- SELECT : select와 from 사이에 조회하고자 하는 필드명들을 ,로 구분해여 지목
	-- select booknum, subject, outprice from....
	-- 모든 필드를 한 번에 지목하려면 *을 써줍니다. -- select * from
	-- from 뒤에는 대상 테이블 명을 써줍니다.
	-- where절을 붙여서 조건에 맞는 행만 골라내기도 합니다.
	-- select ... from... where....
	-- 위와 같이 연산식을 써서 연산된 결과를 필드로 조회하고자 할 땐 as와 함께 만들어진 필드명을 지어주기도 합니다.
	-- select empno || '-' || ename as emp_info from
	-- empno || '-' || ename : empno값과 ename 값을 '-'와 함께 이어붙이기 하고 그렇게
	-- 만들어진 필드의 이름을 emp_info로 설정합니다.
	-- 필드명에 공백이 있거나 기술하기 어려운 필드명일때도 as로 별칭을 붙이기도 합니다.

