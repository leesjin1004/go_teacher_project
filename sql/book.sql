mariadb

drop table book;
CREATE TABLE Book (
  bookno      int(4) PRIMARY KEY auto_increment,
  title       VARCHAR(40),
  publisher   VARCHAR(40),
  price       int(8) ,
  img		  VARCHAR(200)
);

insert into book (title,publisher,price)
values ('자바','한빛',900);

insert into book (title,publisher,price) values ('java programming','한빛',900);
insert into book (title,publisher,price) values ('java ','한빛',900);
insert into book (title,publisher,price) values ('html5','이지스',900);
insert into book (title,publisher,price) values ('HTML5 CSS JavaScript','에이콘',900);

select * from book order by bookno desc;
select * from book order by bookno desc limit 1 , 3;

delete from book where bookno = 1;

select * from Book where title like '%html%' order by bookno desc;

select * from Book where publisher like '%명지%' order by bookno desc;

select * from Book where publisher like '%'||?||'%' " +" order by bookno desc";
commit;
/* user table */
drop table user;
create table user(
	id varchar(10)  primary key,
	password varchar(10)  not null,
	name varchar(20),
	role varchar(10) default 'user' check(role in ('user','admin'))
);
insert into user (id,password,name,role) values ('admin','1234','관리자','admin');
insert into user (id,password,name) values ('java01','1234','홍길동');
select * from user;
delete from user where id = 'java01';
UPDATE user SET password = '1234'  WHERE id ='java01';
select * from user where id='admin' and password='1234';
select * from user where id='java01' and password='1234';
########################################################
