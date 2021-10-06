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




