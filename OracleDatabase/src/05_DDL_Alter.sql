-- 테이블의 수정(ALTER)

--  1. 필드명의 변경
-- ALTER TABLE 테이블이름 RENAME COLUMN 변경 전 이름 TO 변경 후 이름
-- 테이블이름 : 변경하고자하는 필드명이 있는 테이블의 이름
-- booklist 테이블의 subject필드명을 title로 수정하세요
alter table booklist rename column subject to title;
select * from booklist;
-- 반대로 title 필드명을 subject 필드명으로 수정합니다.
alter table booklist rename column title to subject;

-- booklist의 outprice필드의 이름을 rentprice로 수정하세요
alter table booklist rename column outprice to rentprice;

-- memberlist 테이블의 membername필드를 name으로 변경하세요
alter table memberlist rename column membername to name;

-- rentlist 테이블의 rent_date 필드를 rentdate로 변경하세요
alter table rentlist rename column rent_date to rentdate;

-- rentlist의 indexk를  numseq로 변경하세요
alter table rentlist rename column indexk to numseq;

select * from RENTLIST;
select * from booklist;
-- create로 테이블을 만들며 생성한 기본키와 외래키 등등은 필드명이 바뀌어도 바뀐이름으로 자동 적용됩니다.


-- 2. 필드 자료형의 변경
-- ALTER TABLE 테이블명 MODIFY 필드명 자료형
-- varchar2(12)였던 memberlist테이블의 name 필드를 varchar2(30)으로 변경
alter table memberlist modify name varchar2(30);

-- booklist의 booknum 필드를 number(5)로 자료형 변경
alter table booklist modify booknum number(5); -- 수정 실패
-- memberlist의 membernum 필드를 number(5)로 자료형 변경
alter table memberlist modify membernum number(5); -- 수정 실패
-- rentlist의 booknm 필드를 number(5)로 자료형 변경
alter table rentlist modify booknum number(5); -- 수정 실패 
-- rentlist의 membernum 필드를 number(5)로 자료형 변경
alter table rentlist modify membernum number(5); -- 수정 실패
-- 위의 쿼리들은 외래키로 연결되어 참조되고, 참조하고 있는 필드들의 수정쿼리로 바로 실행이 불가능합니다.
-- 가능하게 하려면, 외래키 제약 조건을 수정하여 없애버리고, 참조되는 필드와 참조하는 필드를 모두 수정한 후
-- 외래키 제약 조건을 다시 설정합니다.
-- alter 명령에 의해서 제약조건을 수정하는 명령을 아래에서 학습합니다.

-- 3. 필드의 추가
-- ALTER TABLE 테이블명 ADD 필드명 자료형

-- booklist에 구매등급을 저장할 수 있는 grade 필드를 varcher(15)로 추가
alter table booklist add grade varchar(15);

-- memberlist에 성별(gender)필드를 varchar2(3)으로 추가
alter table memberlist add gender varchar2(3);

-- memberlist에 나이(age)필드를 number(2)로 추가
alter table memberlist add age number(2);

-- 4. 필드의 삭제
-- ALTER TABLE 테이블 명 DROP COLUMN 필드명
-- memberlist 테이블에서 성별 필드 제거
alter table memberlist drop column gender;

-- booklist에 grade 필드 제거
alter table booklist drop column grade;

-- memberlist에 나이(age)필드 제거
alter table memberlist drop column age;

-- 다음 항목을 위해 삭제됐던 필드들 다시 생성
alter table booklist add grade varchar(15);
alter table memberlist add gender varchar2(3);
alter table memberlist add age number(2);




-- 5. 제약조건의 추가/제거
-- ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명 제약조건식
-- 필드 LEVEL(수준)의 제약조건은 필드를 MODIFY하여서 같이 수정, 생성합니다.
-- 테이블 LEVEL의 제약조건은 위의 명령 형식으로 제약조건 이름과 함께 추가합니다.

-- memberlist 테이블의 성별(gender) 필드에 'F', 'M' 두글자만 입력되도록 제약조건을 추가하세요
alter table memberlist add constraint chk_gender check( gender in('F', 'M'));
-- in() 함수 : 괄호 안의 항목 중 하나에 해당하면 true가 리턴되는 함수입니다.
-- 위의 내용은 check 함수의 의해 gender  필드에 들어갈 값이 in()함수 안의 항목 중 하나와 같다면
-- 입력 허용하고, 아니면 불허하는 제약조건 입니다. 

-- memberlist 테이블의 나이(age) 필드에 120살이 초과 되는 나이는 입력되지 못하게 제약 조건 추가
alter table memberlist add constraint check_age check(age <= 120);

-- 삭제 : ALTER TABLE 테이블명 DROP CONSTRAINT 제약조건명
-- rentlist 테이블의 booknum에 걸려있는 외래키 제약조건 제거
alter table rentlist drop constraint fk1;
-- rentlist 테이블의 membernum에 걸려있는 외래키 제약조건 제거
alter table rentlist drop constraint fk2;
-- rentlist 테이블의 기본키 제거
alter table rentlist drop constraint rent_pk;

-- 위에서 실패했던 필드의 자료형 수정 재실행
-- booklist의 booknum 필드를 number(5)로 자료형 변경
alter table booklist modify booknum number(5); 
-- memberlist의 membernum 필드를 number(5)로 자료형 변경
alter table memberlist modify membernum number(5); 
-- rentlist의 booknm 필드를 number(5)로 자료형 변경
alter table rentlist modify booknum number(5); 
-- rentlist의 membernum 필드를 number(5)로 자료형 변경
alter table rentlist modify membernum number(5);

-- 위에 삭제되었던 fk1, fk2, rent_pk 제약조건을 다시 설정하세요
alter table rentlist add constraint rent_pk primary key(rentdate, numseq);
alter table rentlist add constraint fk1 foreign key (booknum) references booklist(booknum);
alter table rentlist add constraint fk2 foreign key (membernum) references memberlist(membernum);
select * from RENTLIST;





-- 테이블 생성 Ex
-- 테이블 명 orders
-- 필드 : order_id number(12,0) order_date date
-- order_mode varchar2(8) customer_id number(6,0)
-- order_status number(2,0) order_total number(8,2)
-- sales_rep_idnumber(6,0) promotion_id number(6,0)
-- 제약 사항 : 기본키는 order_id - 테이블 레벨
-- order_mode에는 'direct', 'online'만 입력 가능 - 테이블 레벨
-- order_total의 디폴트 값은 0 - 필드레벨

CREATE TABLE ORDERS1(
	ORDER_ID NUMBER(12,0),
	ORDER_DATE DATE,
	ORDER_MODE VARCHAR2(8),
	CUSTOMER_ID NUMBER(6,0),
	ORDER_STATUS NUMBER(2,0),
	ORDER_TOTAL NUMBER(8,2) DEFAULT 0,
	SALES_REP_ID NUMBER(6,0),
	PROMOTION_ID NUMBER(6,0),
	
	CONSTRAINT ORDER_PK PRIMARY KEY(ORDER_ID),
	CONSTRAINT CHK_MODE CHECK(ORDER_MODE IN('direct', 'online'))
);

-- 테이블 수정
-- customer_id 필드명을 customer_number로 수정
alter table orders1 rename column customer_id to customer_number;
-- Promotion_id 값은 10000~99999 사이의 값만 저장 가능
-- check( promotion_id between 10000 and 99999);
alter table orders1 add constraint pro_check check( promotion_id between 10000 and 99999);

-- 테이블의 복사
create table orders2 as select * from orders1;
-- as select 구분은 select 구문 이후에 다시 학습합니다.

-- 테이블의 제거
drop table orders2 purge; 
-- purge는 생략 가능
-- purge가 없이 삭제된 테이블은 나중에 휴지통과 같이 복구가 가능
-- purge를 사용하면 완전 삭제
