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

-- 성별이 null인 것을 모두 M으로 수정해주세요
-- update memberlist set gender = 'M' where gender is null;

-- emp 테이블에서 deptno가 10, 20, 30, 40 중 하나인 데이터
select * from emp where deptno = 10 or deptno = 20 or deptno = 30 or deptno = 40;

-- 조건식(ANY, SOME, ALL, (IN))
-- where절에서 사용하는 그룹 내 해당 요소 찾기 함수들
-- 1. ANY
select * from emp where deptno = any(10,20,30);
-- ANY() 괄호안에 나열된 내용 중 어느 하나라도 해당하는 것이 있따면 검색 대상으로 함

-- 2. --SOME 조건식 - ANY와 동일 - IN과도 동일
SELECT * FROM emp WHERE deptno = some(10,20,40);

-- 3. ALL
select empno, sal from emp where sal = all(2000, 3000, 4000);
select empno, sal from emp where sal <> all(2000, 3000, 4000);
-- 괄호안의 모든 값이 동시 만족해야하는 조건이므로 해당하는 레코드가 없을 때가 대부분입니다.
-- 두 번째 사용 예처럼 모두와 다를 때를 위해 사용되곤 합니다.
-- 사용빈도 수도 낮습니다.

-- 4. 논리 조건식 NOT
select empno, sal from emp where deptno not in(10,20,30,40);
-- in()안에 있는 것과 하나도 매칭되지 않은 값이 검색대상


-- 정렬(Sort) - where 구문 뒤에, 또는 구문의 맨 끝에 Order by 필드명 [desc]
-- select 명령의 결과를 특정 필드 값의 오름차순이나 내림차순으로 정렬하라는 명령
-- asc : 오름차순 정렬, 쓰지 않으면 기본 오름차순 정렬로 실행됩니다.
-- desc : 내림차순 정렬, 내림차순 정렬을 위해서는 반드시 필드명 뒤에 써야하는 키워드 입니다.

-- emp 테이블에서
-- sal이 1000 이상인 데이터를 ename의 오름차순으로 정렬하여 조회
select * from emp where sal>=1000 order by ename; -- 오름차순 asc는 생략
-- sal이 1000 이상인 데이터를 ename의 내림차순으로 정렬하여 조회
select * from emp where sal>=1000 order by ename desc;
-- job으로 내림차순 정렬 
select * from emp emp order by job desc;
-- job으로 내림차순 정렬 후 같은 job_id 사이에서는 순서를 hiredate의 내림차순으로 정렬
select * from emp emp order by job desc, hiredate desc;
-- 두 가지 이상의 정렬 기준이 필요하다면 위와 같이 (,)으로 구분해서 두 가지 기준을 지정해주며,
-- 위의 예제로 봤을 때 job으로 1차 내림차순 정렬하고, 같은 job 값들 사이에 hiredate로
-- 내림차순 정렬합니다.

-- 그 외 활용하기 좋은 select에 대한 예제
-- 부서 번호가 10이 아닌 사원( 아래 두 문장은 같은 의미의 명령입니다. )
select * from emp where not (deptno = 10);
select * from emp where deptno <> 10;

-- 특정 필드 값이 NULL인 레코드 또는 NULL이 아닌 레코드
select * from emp hwere comm is null -- comm 필드가 null 인 레코드
select * from emp hwere comm is not null -- comm 필드가 null 이 아닌 레코드

-- 사원의 연봉 출력
select deptno, ename, sal*12 as 연봉 from emp;




