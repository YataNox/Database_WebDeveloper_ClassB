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