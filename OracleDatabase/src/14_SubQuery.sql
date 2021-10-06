-- 서브쿼리
-- Sub Query : 하나의 select 문장의 절 안에 포함된 또 하나의 select 쿼리문

-- SCOTT 이 근무하는 곳의 부서명과 지역 출력
-- 단일 행 서브쿼리 : 서브 쿼리의 결과가 하나

select deptno from emp where ename = 'SCOTT'; -- 결과는 30
select dname, loc from dept where deptno = 30;

-- 위 select 명령의 결과를 다른 select 명령에 사용(서브쿼리 이용)
select dname, loc from dept
where deptno = (select deptno from emp where ename = 'SCOTT');

-- [연습문제] scott과 동일직업(job)을 가진 사원을 출력
select * from emp
where job = (select job from emp where ename = 'SCOTT');

-- [연습문제] scott과 급여가 동일하거나 더 많이 받는 사원 이름과 급여 출력
select * from emp
where sal >= (select sal from emp where ename = 'SCOTT');

select * from emp;

-- [서브쿼리 & 그룹함수]
-- 전체 사원 평균 평균급여보다 더 많은 급여를 받는 사원의 이름, 급여, job
select ename, sal, job from emp
where sal>=(select avg(sal) from emp);

-- 급여를 4000 이상 받는 사원이 소속된 부서와 소속된 부서에서 근무하는 사원들의 이름, 부서번호, job
-- 급여를 4000 이상 사원의 부서번호(중복제거);
-- distinct 필드명 : 필드 값의 중복된 값이 여러개라면 한 번만 출력
select * from EMP
where deptno in(select distinct deptno from emp where sal>=3000);



