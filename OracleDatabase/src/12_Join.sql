-- JOIN
-- 두 개 이상의 테이블에 나눠져 있는 관련 데이터들을 하나의 테이블로 모아서 조회하고자 할 때
-- 사용하는 명령입니다.

select * from employees;
select * from departments;
select * from tab;
-- [1] 이름 Douglas Grant가 근무하는 부서명, 상급부서를 출력하고자 한다면...
select department_id from employees where emp_name = 'Douglas Grant';
-- 위 명령을 실행 후 얻어진 부서번호로 아래와 같이 부서번호 검색하여 부서명과 상급부서를 알아냅니다.
select department_name, parent_id from departments where department_id = 50;
-- 위의 두 개의 명령을 하나의 명령으로 합해주는 역할을 join 명령이 실행합니다.
-- [2] join : 두 개 이상의 테이블에 나뉘어져 있는 데이터를 한 번 의 sql문으로 원하는 결과를 얻습니다.


-- cross join : 두 개 이상의 테이블이 조인될 때 where절에 의해 공통되는 컬럼에 의한 결합이
-- 발생하지 않는 경우
-- * 가장 최악의 결과를 얻는 조인 방식
-- A 테이블과 B 테이블의 cross join된다면
-- A 테이블의 1번 레코드와 B 테이블의 모든 레코드와 하나하나 모두 조합
-- A 테이블의 2번 레코드와 B 테이블의 모든 레코드와 하나하나 모두 조합
-- A 테이블의 3번 레코드와 B 테이블의 모든 레코드와 하나하나 모두 조합
-- ....

-- A 테이블의 레코드가 8개, B테이블의 레코드가 7개라면 총 크로스조인의 결과 레코드 수는 8x7 = 56
-- A 테이블의 필드가 8개, B테이블의 필드가 3개라면 총 크로스조인의 결과 레코드 수는 8+3 = 11
select * from dept; -- 4레코드 3필드
select * from emp; -- 14레코드 8필드
-- 크로스 조인
select * from dept, emp -- 레코드 56 필드 11

-- 동등 조인 : 조인 대상이 되는 두 테이블에서 공통적으로 존재하는 칼럼의 값이 일치하는 행을 연결하여 결과를 생성
select * from dept, emp where emp.deptno = dept.deptno;

-- 각 사원의 이름, 부서번호, 부서명, 지역을 출력하세요
select emp.ename, emp.deptno, dept.loc, dept.dname from dept, emp where emp.deptno = dept.deptno;

-- 이름이 scott인 사원의 이름, 부서번호 , 위치 출력
select emp.ename, emp.deptno, dept.loc, dept.dname from dept, emp
where emp.deptno = dept.deptno and emp.ename = 'SCOTT';
-- 컬럼 명 앞에 테이블 명을 기술하여 컬럼의 소속을 명확히 해주어야 오류가 없습니다.

-- 테이블 명에 별칭을 부여한 후 컬럼 앞에 소속 테이블을 지정
-- 테이블명으로 소속을 기술할 때는 한 쪽에만 있는 필드에 생략이 가능하지만 아래와 같이
-- 별칭 부여시에는 모든 필드 앞에 반드시 별칭을 기술해야함
select a.ename, b.dname, b.loc, a.deptno from emp a, dept b
where a.deptno = b.deptno and a.ename='SCOTT';

-- non-동등조인
-- 동일 컴럼이 없어서 다른 조건을 사용하여 조인
-- 조인 조건에 특정 범위내에 있는지를 조사하기 위해 조걸절에 조인 조건을 '='연산자 이외의 비교
-- 연산자를 이용
select * from emp;
select * from SALGRADE;

select a.ename, a.sal, b.grade from emp a, SALGRADE b
where a.sal >= b.losal and a.sal<=b.hisal;

select a.ename, a.sal, b.grade from emp a, SALGRADE b
where a.sal between b.losal and b.hisal;

-- 세 개의 테이블을 하나로 join(equi, nonequi 조인의 조합)
select a.ename, a.sal, b.dname, c.grade from emp a, SALGRADE c, dept b
where a.deptno = b.deptno and a.sal between c.losal and c.hisal;


-- 연습 문제
-- rentlist 테이블의 rentdate, booknum, membernum을 조회하되,
-- booklist와 memberlist 테이블을 조인해서 책 제목과 대여가격, 회원 이름과 사은 포인트를 출력하세요
-- 출력 순서 - 대여일자, 도서제목, 회원 이름, 포인트, 대여금액
-- 테이블의 별칭은 a,b,c로하세요
select * from RENTLIST;
select * from bookLIST;
select * from memberLIST;

select a.rentdate, b.subject, c.name, c.Bpoint, b.rentprice  from RENTLIST a, BOOKLIST b, MEMBERLIST c
where a.booknum = b.booknum and a.membernum = c.membernum;


-- outer join
-- 조인 조건에 만족하지 못해서 해당 결과를 출력시에 누락이 되는 문제점이 발생할 때
-- 해당 레코드를 출력하는 조인







