-- drop table 완전삭제(삭제테이블 복구불가)
drop table movie purge;
drop table reservation purge;
drop table member purge;

-- create table
create table member (
	member_id varchar2(30),
	member_pw varchar2(20) not null,
	name varchar2(20),
	birth varchar2(8) not null,
	mobile varchar2(13) not null,
	email varchar2(30) not null,
	entry_date varchar2(10) not null,
	grade varchar2(1) not null,
	mileage number(6)
);

-- constraint add : pk
alter table member
add constraint pk_memberid primary key (member_id);

-- constraint add : mobile UK
alter table member 
add constraint UK_mobile unique (mobile);

-- constraint add : email UK
alter table member 
add constraint UK_email unique (email);
	 

-- detail : movie table
CREATE table movie (
    title varchar2(50) not null,
    rating number(2) not null,
	director number(2) not null,
	actor varchar2(30) not null,
    seat number(10) not null
);

-- constraint add : pk
alter table movie
add CONSTRAINT PK_movie_title PRIMARY KEY (title);


-- detail : reservation table
create table reservation(
	reserve_no varchar2(6),
	memberId varchar2(30),
	title varchar2(50)
	rating number(2)
	seat_no varchar2(3)
); 

-- constraint add : pk
alter table reservation
add CONSTRAINT PK_reservation_reserve_no PRIMARY KEY (reserve_no);

-- constraint add : fk
alter table reservation
add constraint fk_memberid foreign key (MEMBER_ID) references member(member_id);
add constraint fk_memberid foreign key (title, rating, seat) references reservation(title, rating, seat);



-- init insert : member
insert into member(member_id, member_pw, name, birth, mobile, email, entry_date, grade, mileage)
values('user01', 'password01', '홍길동', 20041111, '010-1234-1111', 'user01@work.com	', '2017.05.05', 'G', 7500);

insert into member
values('user02', 'password02', '강감찬', 19990311, '010-1234-1112', 'user02@work.com', '2017.05.06', 'G', 95000);

insert into member
values('user03', 'password03', '이순신', 19881109, '010-1234-1113', 'user03@work.com', '2017.05.07', 'V', 150000);

insert into member
values('user04', 'password04', '김유신', 19690424, '010-1234-1114', 'user04@work.com', '2017.05.08', 'VV', 300000);

insert into member
values('user05', 'password05', '유관순', 19900501, '010-1234-1115', 'user05@work.com', '2017.05.09', 'A', null);

-- db 반영
commit;

-- init insert : movie
insert into movie
values(크루엘라, 12, '크레이그 질레스피', '엠마 스톤, 엠마 톰슨, 마크 스트롱', NULL);

-- db 반영
commit;

-- init insert : reservation
insert into reservation
values( TTTTTT, user01, , 크루엘라, 12, '크레이그 질레스피', '엠마 스톤, 엠마 톰슨, 마크 스트롱', C3);

-- db 반영
commit;

