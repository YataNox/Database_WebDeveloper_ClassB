-- 오라클 명령어 : select 문 (검색)
-- [1] scott 사용자가 관리하고 있는 테이블 목록
select * from tab;

-- [2] 특정 테이블의 구조 조화(필드리스트/ 데이터 형식)
desc dept; -- 커맨드 창(sqlplus)에서 확인 요망
desc memberlist; -- 커맨드 창(sqlplus)에서 확인 요망

-- SELECT : select와 from 사이에 조회하고자 하는 필드명들을 ,로 구분해여 지목
	-- select booknum, subject, outprice from....
	-- 모든 필드를 한 번에 지목하려면 *을 써줍니다. -- select * from
	-- from 뒤에는 대상 테이블 명을 써줍니다.
	-- where절을 붙여서 조건에 맞는 행만 골라내기도 합니다.
	-- select ... from... where....
	-- 위와 같이 연산식을 써서 연산된 결과를 필드로 조회하고자 할 땐 as와 함께 만들어진 필드명을 지어주기도 합니다.
	--	select empno || '-' || ename as emp_info from emp;
	-- empno || '-' || ename : empno값과 ename 값을 '-'와 함께 이어붙이기 하고 그렇게
	-- 만들어진 필드의 이름을 emp_info로 설정합니다.
	-- 필드명에 공백이 있거나 기술하기 어려운 필드명일때도 as로 별칭을 붙이기도 합니다.
	select empno as 사원번호, ename as 사원성명 from emp;
	
	select MGR as manager_empno from emp;
	
-- [3] 특정 테이블의 모든 DATA 표시
select * from rentlist;
 
-- [4] 모든 컬럼(필드명)이 아닌, 필요한 필드만 조회
select subject, makeyear, from booklist;

-- [5] 각각의 필드명에 별칭을 부여해서 출력
select subject as 도서제목, makeyear as 출판년도 from booklist;

-- [6] 중복제거
select booknum from rentlist;
-- rentlistd에서 중복을 제거 후 조회
select distinct booknum from rentlist;

-- [7] 검색 조건의 추가 : 입고가격이 2만원 이상인 book 목록
select * from booklist where inprice >= 20000;

-- [8] 이름이 '홍'으로 시작하는 회원의 모든 회원 정보 출력
select * from memberlist where name like '홍%';

-- [9] 1983년도 이후로 태어난 회원의 모든 회원 정보
select * from memberlist where birth >= '1983-01-01';

-- [10] 사은 포인트(Bpoint)가 250점 이상이고 1982년 이후로 태어난 회원의 모든 정보(and, or 연산자 사용)
select * from memberlist where Bpoint >= 250 and birth >= '1982-01-01';

-- [11] 제작년도가 2016년 이전이거나 입고가격(inprice)이 18000이하인 정보
select * from booklist where inprice <= 18000 or makeyear < 2016;

-- [12] 성명이 '이'로 시작하는 회원의 모든 정보
select * from memberlist where name like '이%';

-- [13] 이름이 '용'으로 끝나는 회원의 정보
select * from memberlist where name like '%용';

-- [14] 도서 제목에 '이'가 포함되는 도서 정보
select * from booklist where subject like '%이%';

-- [15] 사은포인트가 NULL이 아닌 회원의 이름과 전화번호
select name, phone from memberlist where Bpoint is not null;

-- [16] 도서 제목에 두 번째 글자가 '것'인 도서 정보
select * from booklist where subject like '_것%';