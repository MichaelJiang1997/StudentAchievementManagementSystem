//数据库
create database xscjglxt default charset utf8;

//学生表
create table stu_info(
	sid varchar(8) primary key,
	sname varchar(10) not null,
	sgender varchar(2) not null
	);

//成绩表
create table stu_score(
	sid varchar(8) not null,
	cid varchar(8) not null,
	grade int
	);

//课程表
create table stu_course(
	cid varchar(8) primary key,
	cname varchar(20) not null
	);

//管理员表
create table admin(
	aid varchar(8) primary key,
	apsw varchar(20) not null
	);
	
	
	
select stu_info.sid,sname,sgender,stu_score.cid,cname,grade 
from stu_info,stu_score,stu_course 
where stu_score.cid="201" and stu_info.sid = stu_score.sid and stu_score.cid = stu_course.cid
and grade > 30;




