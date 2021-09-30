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

CREATE TABLE 도서목록(
	booknum char(5) primary key,
	subject char(30) constraint not null,
	makeyear int(4),
	inprice int(6),
	outprice int(4)
)