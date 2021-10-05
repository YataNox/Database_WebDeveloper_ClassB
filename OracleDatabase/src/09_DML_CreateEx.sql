select * from tab;

create table bonus(
	ENAME varchar2(10),
	JOB varchar2(9),
	SAL number,
	COMM number
);

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




