-- JOIN
-- 두 개 이상의 테이블에 나눠져 있는 관련 데이터들을 하나의 테이블로 모아서 조회하고자 할 때
-- 사용하는 명령입니다.

-- [1] 이름 scott이 근무하는 부서명, 부서의 지역명 출력하고자 한다면...
select deptno from employees where ename = 'SCOTT';
-- 위 명령을 실행 후 얻어진 부서번호로 아래와 같이 부서번호 검색하여 부서명과 지역명을 알아냅니다.
select dname, loc from dept where deptno = 20;
-- 위의 두 개의 명령을 하나의 명령으로 합해주는 역할을 join 명령이 실행합니다.
-- [2] join : 두 개 이상의 테이블에 나뉘어져 있는 데이터를 한 번 의 sql문으로 원하는 결과를 얻습니다.
