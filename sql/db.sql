//���ݿ�
create database xscjglxt default charset utf8;

//ѧ����
create table stu_info(
	sid varchar(8) primary key,
	sname varchar(10) not null,
	sgender varchar(2) not null
	);

//�ɼ���
create table stu_score(
	sid varchar(8) not null,
	cid varchar(8) not null,
	grade int
	);

//�γ̱�
create table stu_course(
	cid varchar(8) primary key,
	cname varchar(20) not null
	);

//����Ա��
create table admin(
	aid varchar(8) primary key,
	apsw varchar(20) not null
	);
	
	
	
select stu_info.sid,sname,sgender,stu_score.cid,cname,grade 
from stu_info,stu_score,stu_course 
where stu_score.cid="201" and stu_info.sid = stu_score.sid and stu_score.cid = stu_course.cid
and grade > 30;




