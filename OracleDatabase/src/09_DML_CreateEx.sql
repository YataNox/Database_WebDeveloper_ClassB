select * from tab;

create table bonus(
	ENAME varchar2(10),
	JOB varchar2(9),
	SAL number,
	COMM number
);

alter table bonus modify sal number(6);
alter table bonus modify comm number(6);

create table channels(
	CHANNEL_ID NUMBER(6) NOT NULL,
	CHANNEL_DESC VARCHAR2(20) NOT NULL,
	CHANNEL_CLASS VARCHAR2(20),
	CHANNEL_CLASS_ID NUMBER(6),
	CHANNEL_TOTAL VARCHAR2(15),
	CHANNEL_TOTAL_ID NUMBER(6),
	CREATE_DATE DATE,
	UPDATE_DATE DATE
);

create table countries(
	country_id number(6) not null,
	country_iso_code char(2) not null,
	country_name varchar2(40) not null,
	country_subregion varchar2(30),
	country_subregion_id number(6),
	country_region varchar2(20),
	country_region_id number(6),
	country_total varchar2(11),
	country_total_id number(6),
	country_name_hist varchar2(40),
	create_date date,
	update_date date
);

create table customers(
	cust_id number(6) not null,
	cust_name varchar2(100) not null,
	cust_gender char(1),
	cust_year_of_birth number(4),
	cust_marital_status varchar2(20),
	cust_street_address varchar2(100),
	cust_postal_code varchar2(10),
	cust_city varchar2(30),
	cust_city_id number(6),
	cust_state_province varchar2(40),
	cust_state_province_id number(6),
	country_id number(6),
	cust_main_phone_number varchar2(25),
	cust_income_level varchar2(30),
	cust_credit_limit number,
	cust_email varchar2(30),
	cust_total varchar2(20),
	cust_total_id number(6),
	cust_src_id number(6),
	cust_eff_from date,
	cust_eff_to date,
	cust_valid char(1),
	create_date date,
	update_date date
);

alter table customers modify cust_credit_limit number(6);

create table departments(
	department_id number(6) not null,
	department_name varchar2(80) not null,
	parent_id number(6),
	manager_id number(6),
	create_date date,
	update_date date
);

create table employees(
	employee_id number(6) not null,
	employee_name varchar2(80) not null,
	email varchar2(50),
	phone_number varchar2(30),
	hire_date date not null,
	salary number(8,2),
	manager_id number(6),
	commission_pct number(2,2),
	retire_date date,
	department_id number(6),
	job_id varchar2(10),
	create_date date,
	update_date date
);



