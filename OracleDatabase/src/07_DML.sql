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

-- 두 번째 방식의 레코드 추가
insert into exam01 values (30, 'SALES', 'CHICHAGO');

-- 두 가지 방법 모두 null 값을 입력할 수 있습니다.
insert into exam01(deptno, dname) values(20, 'MARKETING'); -- 첫 번째 방법
insert into exam01 values(40, 'OPERATION', NULL); -- 두 번째 방법

select * from exam01;

-- 두 가지 방법 중 자유롭게 선택하여서, booklist 테이블에 10개의 레코드를 추가해주세요.
-- booknum은 시퀀스를 이용합니다.
-- grade는 'all' '13' '18' 세 가지만 골라서 입력해주세요
insert into booklist(booknum, subject, makeyear, inprice, rentprice, grade)
values(book_seq.nextVal, '좀비아이', 2020, 12000, 2500, 'all');

insert into booklist(booknum, subject, makeyear, inprice, rentprice, grade)
values(book_seq.nextVal, '오징어게임', 2021, 30000, 2500, 'all');

insert into booklist(booknum, subject, makeyear, inprice, rentprice, grade)
values(book_seq.nextVal, '봉제인형 살인사건', 2012, 12000, 2500, 'all');

insert into booklist(booknum, subject, makeyear, inprice, rentprice, grade)
values(book_seq.nextVal, 'A-특공대', 2010, 12000, 5500, '13');

insert into booklist(booknum, subject, makeyear, inprice, rentprice, grade)
values(book_seq.nextVal, '정직한후보', 2020, 11000, 2000, '13');

insert into booklist(booknum, subject, makeyear, inprice, rentprice, grade)
values(book_seq.nextVal, '모가디슈', 2021, 12000, 3500, '18');

insert into booklist(booknum, subject, makeyear, inprice, rentprice, grade)
values(book_seq.nextVal, '일곱해의 마지막', 2020, 12150, 2500, 'all');

insert into booklist(booknum, subject, makeyear, inprice, rentprice, grade)
values(book_seq.nextVal, '콘스탄틴', 2005, 15000, 2500, '18');

insert into booklist(booknum, subject, makeyear, inprice, rentprice, grade)
values(book_seq.nextVal, '씽', 2016, 11000, 3000, 'all');

insert into booklist(booknum, subject, makeyear, inprice, rentprice, grade)
values(book_seq.nextVal, '소울', 2020, 11000, 3500, 'all');

select * from MEMBERLIST;
delete from BOOKLIST;

insert into MEMBERLIST(membernum, name, phone, birth, Bpoint, joindate, gender, age)
VALUES(member_seq.nextVal, '추신수', '010-5656-1234', '84/07/07', 240, '20/10/01', 'M', 24);

insert into MEMBERLIST(membernum, name, phone, birth, Bpoint, joindate, gender, age)
VALUES(member_seq.nextVal, '류현진', '010-6464-5421', '87/03/25', 250, '21/1/11', 'M', 34);

insert into MEMBERLIST(membernum, name, phone, birth, Bpoint, joindate, gender, age)
VALUES(member_seq.nextVal, '손흥민', '010-1594-1451', '92/07/08', 110, '20/2/21', 'M', 29);

insert into MEMBERLIST(membernum, name, phone, birth, Bpoint, joindate, gender, age)
VALUES(member_seq.nextVal, '이청용', '010-6666-1234', '81/06/14', 440, '20/10/01', 'M', 36);

insert into MEMBERLIST(membernum, name, phone, birth, Bpoint, joindate, gender, age)
VALUES(member_seq.nextVal, '이영표', '010-1356-1234', '84/07/07', 240, '20/10/01', 'M', 31);

insert into MEMBERLIST(membernum, name, phone, birth, Bpoint, joindate, gender, age)
VALUES(member_seq.nextVal, '최지만', '010-7756-1224', '91/04/14', 240, '20/10/01', 'M', 29);

select * from RENTLIST;
-- rentlist 테이블도 rent_seq를 이용해서 10개의 데이터를 추가해주세요
insert into rentlist values('2021/10/01', rent_seq.nextVal, 6, 2, 100);
insert into rentlist values('2021/10/01', rent_seq.nextVal, 7, 3, 100);
insert into rentlist values('2021/10/02', rent_seq.nextVal, 8, 4, 200);
insert into rentlist values('2021/10/02', rent_seq.nextVal, 9, 5, 100);
insert into rentlist values('2021/10/03', rent_seq.nextVal, 10, 6, 200);
insert into rentlist values('2021/10/03', rent_seq.nextVal, 6, 6, 300);
insert into rentlist values('2021/10/04', rent_seq.nextVal, 7, 7, 100);
insert into rentlist values('2021/10/04', rent_seq.nextVal, 10, 8, 300);
insert into rentlist values('2021/10/05', rent_seq.nextVal, 9, 9, 100);
insert into rentlist values('2021/10/05', rent_seq.nextVal, 9, 10, 200);

-- 10월 4일 수업 시작 -------------------------------------------------------------------
select * from RENTLIST;
select * from MEMBERLIST;
select * from BOOKLIST;

commit;
-- DBMS에서의 데이터 운영은 실시간으로 데이터 원본으로 작업하고 변경사항이 저장되는 것이 아니라
-- 가상의 사본을 대상으로 작업하게 됩니다.
-- 이를 원본에 적용하기 위해서는 COMMIT이라는 명령으로 원본에 적용해주어야 다른 접근에서 변경이
-- 적용된 데이터를 볼 수 있습니다.
-- 다만 이클립스에서의 데이터베이스 운영은 Auto Commit이 적용되어 실행 즉시 적용되고 있습니다.

--[3] 레코드 변경 - 수정(UPDATE)
-- UPDATE 테이블명 SET 변경내용 WHERE 검색 조건

-- update member set age = 30 where membernum=8;
-- 회원번호가 3인 회원의 나이를 30으로 수정하세요

-- 명령문에 where 이 후 구문은 생략이 가능합니다.
-- 다만 이 부분을 생략하면 모든 레코드를 대상으로 해서 UPDATE 명령이 실행되어 모든 레코드가
-- 수정됩니다.
-- 검색 조건 : 필드명 (비교-관계연산자) 조건 값으로 이루어진 조건 연산이며, 흔히 자바에서 if()로 사용되며
-- 괄호 안에 사용했던 연산을 그대로 사용하는게 보통입니다.
-- 나이가 29세 이상 -> where age>=29

-- 데이터 수정의 실예
-- exam01 테이블에서 deptno 값을 모두 30으로 수정
update exam01 set deptno = 30;

-- exam01 테이블에서 dname이 'ACCOUNT'인 레코드의 deptno를 10으로 수정
update exam01 set deptno=10 where dname = 'ACCOUNT';

-- exam01 테이블에서 dname이 'SALES'인 레코드의 DEPTNO를 20으로 수정
update exam01 set deptno=20 where dname = 'SALES';

-- exam01 테이블에서 dname이 'OPERATION'인 레코드의 deptno를 30으로 수정
update exam01 set deptno=30 where dname = 'OPERATION';

-- exam01 테이블에서 dname이 'MARKETING'인 레코드의 deptno를 40으로 수정
update exam01 set deptno=40 where dname = 'MARKETING';

-- exam01 테이블에서 deptno이 30인 레코드의 loc를 'BOSTON'으로 수정
UPDATE exam01 set loc='BOSTON' where deptno=30;

-- exam01 테이블에서 deptno이 40인 레코드의 loc를 'LA'로 수정
UPDATE exam01 set loc='LA' where deptno=40;

select * from exam01;

select * from tabs; -- 현재 로그인된 사용자가 소유하고 잇는 테이블의 목록을 볼 수 있습니다.


-- booklist 테이블의 제목 '봉제인형 살인사건' 도서의 grade를 '18'으로 수정
-- update booklist set subject='봉제인형 살인사건' where subject='늑대아이';
update booklist set grade = 18 where subject='봉제인형 살인사건';
select * from emp;

-- emp 테이블의 모든 사원의 sal 값을 10%씩 인상
update emp set sal = sal*1.1;

-- emp 테이블에서 sal값이 3000이상인 사원의 급여 10% 삭감
update emp set sal = sal*0.9 where sal>=3000;

-- hiredate가 2002년 이전인 사원의 급여를 + 2000 -> (2001-12-31보다 작거나 같은)
update emp set sal = sal +2000 where hiredate <= '2001-12-31';

-- ename이 j로 시작하는 사원의 job을 manager로 변경
update emp set job = 'manager' where ename like 'j%';

-- memberlist 테이블에서 bpoint가 200이 넘는 사람만 bpoint*100으로 변경
update memberlist set bpoint = bpoint*100 where bpoint > 200;

-- rentlist 테이블에서 할인 금액이 100원이 넘으면 모두 할인 금액을 90으로 변경
update RENTLIST set discount = 90 where discount>=100;




-- [4] 레코드의 삭제
-- delete from 테이블명 where 조건식

-- rentlist 테이블에서 discount가 100이하의 레코드를 삭제
delete from rentlist where discount <= 100;
--where절이 없으면 테이블 내의 모든 레코드를 삭제합니다.

-- 삭제의 제한
-- delete from booklist where subject = '봉제인형 살인사건';





