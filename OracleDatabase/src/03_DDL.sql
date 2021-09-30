-- DDL(Database Definition Language) - 데이터 정의어

-- 테이블의 생성(Create)

-- Create TABLE 테이블 이름(
-- 		필드명 1 DATATYPE [DEFAULT값 OR 제약조건 및 형식],
-- 		필드명 2 DATATYPE [DEFAULT값 OR 제약조건 및 형식],
-- 		필드명 3 DATATYPE [DEFAULT값 OR 제약조건 및 형식],
--		...
-- );

-- Create Table 명령의 세부 규칙
-- 테이블의 이름은 객체를 의미할 수 있는 적절한 이름을 사용합니다.(자바 변수 이름 규칙과 거의 동일)
-- 다른 테이블과 중복되지 않게 테이블 이름을 지정합니다.
-- 한 테이블 내에서 필드 이름도 중복되지 않게 합니다.
-- 각 필드들은 ","(컴마)로 구분하여 생성합니다.
-- create 를 비롯한 모든 SQL 명령은 ";"으로 끝납니다.
-- 필드명 뒤에 DATATYPE은 반드시 지정하고, []안의 내용은 해당 내용이 있을 때 작성하며 생략 가능합니다.
-- 테이블명과 필드명은 반드시 문자로 시작해야 하고 예약어 명령어 등을 테이블명과 필드명으로 쓸 수 없습니다.
-- 테이블 생성시 대/소문자 구분은 하지 않습니다. (기본적으로 테이블이나 컬럼명은 대문자로 만들어집니다.)
-- DATE(날짜) 데이터 형식은 유형을 별도로 크기를 지정하지 않습니다.
-- 문자 데이터의 DATATYPE -> varchar(10), number(4)
-- 문자 데이터 유형은 반드시 가질 수 있는 최대 길이를 표시해야 합니다.
-- 컬럼과 컬럼의 구분은 콤마로 하되, 마지막 컬럼은 콤마를 찍지 않습니다.

-- 도서 대여점의 도서목록 테이블의 생성
-- 필드 : booknum(도서번호), subject(도서제목), makeyear(출판년도), inprice(입고가격)
-- 			outprice(대여가격)
-- 자료형 : booknum(문자 5자리), subject(문자 30), makeyear(숫자 4), inprice(숫자 6)
-- 			outprice(숫자 4)
-- 제약조건 : booknum(not null), subject(not null), makeyear(), inprice(), outprice()
-- 기본키 : booknum

CREATE TABLE booklist(
	booknum varchar2(5) not null,
	subject varchar2(30) NOT NULL,
	makeyear NUMBER(4),
	inprice NUMBER(6),
	outprice NUMBER(4), -- 필드명 옆에 현재 필드에만 적용하는 제약조건을 필드 수준의 제약이라고 합니다.
	
	-- 아래는 테이블 수준의 제약조건입니다.
	constraint booknum_pk primary key(booknum)
	-- constraint : 테이블 수준의 제약조건을 지정하는 키워드
	-- booknum_pk : 테이블 외부에서 현재 제약조건을 컨트롤 하기 위한 제약조건의 고유이름
	-- primary key(booknum) : 기본키로 booknum을 지정하겠다는 뜻입니다.
);

select *
from BOOKLIST; -- 테이블의 내용 전체를 조회하는 명령

-- 제약조건(CONSTRAINT)
-- PRIMARY KEY
-- - 테이블에 저장된 레코드를 고유하게 식별하기 위한 키, 하나의 테이블에 하나의 기본키만 정의할 수 있습니다.
-- - 여러 필드가 조합된 기본키 생성 가능합니다.
-- - 기본키는 중복된 값을 갖을 수 없으며 빈칸도 있을 수 없습니다.
-- - PRIMARY KEY = UNIQUE KEY + NOT NULL
-- UNIQUE KEY
-- - 테이블에 저장된 행 데이터를 고유하게 식별하기 위한 고유키를 정의합니다.
-- - 단 NULL은 고유키 제약의 대상이 아니므로, NULL값을 가진 행이 여러개가 UNIQUE KEY제약에 위반하지는 않습니다.
-- NOT NULL
-- - 비어있는 상태, 아무것도 없는 상태를 허용하지 않습니다. - 입력 필수
-- CHECK
-- - 입력할 수 있는 값의 범위를 제한한다. CHECK 제약으로는 TRUE or FALSE로 평가할 수 있는 논리식을 지정합니다.
-- FOREGIN KEY
-- - 관계형 데이터베이스에서 테이블간에 관계를 정의하기 위해 기본키를 다른 테이블의 외래키로
-- 복사하는 경우 외래키가 생성됩니다. - 참조 무결성 제약 옵션이 생성


-- 테이블 생성 2
-- 테이블 이름 : MemberList(회원리스트)
-- 필드 : memberNum, memberName, Phone, Birth, Bpoint
-- 데이터 형식 : memberNum : VARCHAR2(5), memberName : VARCHAR2(12),
-- Phone : VARCHAR2(13), Birth : DATE, Bpoint : NUMBER(6)
-- 제약 조건 : memberNum, memberName, Phone 세 개의 필드 NOT NULL - 필드 레벨로 설정
-- memberNum : Primary Key - 테이블 레벨로 설정
CREATE TABLE MemberList(
	memberNum VARCHAR2(5) NOT NULL,
	memberName VARCHAR2(12) NOT NULL,
	Phone VARCHAR2(13) NOT NULL,
	Birth DATE,
	joindate date default sysdate, -- sysdate : 오늘 날짜를 표시하는 오라클의 키워드
	Bpoint NUMBER(6) default 0, -- 필드 기본 값 추가
	
	CONSTRAINT memberList_pk PRIMARY KEY(memberNum)
);

alter table memberlist add joindate date default sysdate; 
select * from memberlist;
drop table memberlist;

-- 테이블 생성 3
-- 테이블 이름 : rentlist
-- 필드 : rent_date(date), indexk(number(3)), booknum(varchar2(5)),
-- membernum(varchar2(5)), discount(number(6))
-- 제약조건 : booknum, membernum :not null
-- 기본 값 : rent_date : 오늘 날짜

create table rentlist(
	rent_date date default sysdate,
	indexk number(3),
	booknum varchar2(5) not null,
	membernum varchar2(5) not null,
	discount number(6),
	
	constraint rent_pk primary key(booknum)
);
