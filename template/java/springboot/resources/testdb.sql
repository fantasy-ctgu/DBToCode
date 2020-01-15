drop database if exists testdb;
create database testdb default character set utf8;

use testdb;

create table user(
	id int(11) auto_increment primary key,
	username varchar(20) not null unique,
	password varchar(20) not null,
	nickname varchar(10) not null,
	name varchar(10) not null,
	sex varchar(1) check(sex='男' or sex='Ů'),
	age int(2),
	school int(2),
	studentid varchar(20) not null unique,
	summary varchar(100),
	datetime timestamp not null default current_timestamp,
	status int(2) not null default 1
)engine=InnoDB auto_increment=1 default charset=utf8;
insert into user values(null,'15871577021','15871577021','fantasy','fantasy','男',20,1,'2016112231','hello world!!',null,1);

-- 招聘公司
CREATE TABLE if not exists company(
	id int(11) auto_increment primary key,
	username varchar(255) not null unique comment "用户名",
	password varchar(255) not null,
	company_name varchar(255) not null comment "公司名称",
	avatar text comment "头像",
	province varchar(64) comment "省份",
	city varchar(64) comment "城市",
	address text comment "详细地址",
	contacts VARCHAR(255) comment "联系人",
	phone varchar(16) comment "联系电话",
	email varchar(255),
	introduce text comment "公司介绍",
	last_login timestamp comment "最后登陆时间",
	last_ip VARCHAR(255) comment "最后登陆IP",
	perfect SMALLINT default 0 comment "是否完善信息:0 未完善，1 已完善",
	updatetime timestamp default current_timestamp on update current_timestamp,
	createtime timestamp default current_timestamp
)engine=InnoDB auto_increment=1 default charset=utf8;
INSERT INTO company 
	VALUES(null,"fantasy","fantasy","HUAWEI",null,"湖北","武汉","武汉大学南门口","chenxiang","15871577021","776474961@qq.com","没有最好，只有更好",now(),"192.168.9.211",0,now(),now());
	
-- 岗位信息
CREATE TABLE if not exists positions(
	id int(11) auto_increment primary key,
	cid int(11) not null comment "发布公司",
	positions_name VARCHAR(64) not null comment "岗位名称",
	min_wages VARCHAR(8) not null comment "最低薪资",
	max_wages VARCHAR(8) not null comment "最高薪资",
	province varchar(64) comment "省份",
	city varchar(64) comment "城市",
	seniority SMALLINT default 0 comment "资历要求",
	education VARCHAR(64) not null comment "学历要求",
	requirement_num int(3) not null comment "招聘人数",
	welfare text comment "福利",
	positions_info text not null comment "岗位信息",
	main_type_id int not null comment "岗位分类",
	keyword VARCHAR(255) not null comment "关键字",
	address text not null comment "详细工作地址",
	positions_status_id SMALLINT DEFAULT 2,
	createtime timestamp default current_timestamp
)engine=InnoDB auto_increment=1 default charset=utf8;
INSERT INTO positions(cid,min_wages,max_wages,province,city,seniority,education,requirement_num,welfare,positions_info,main_type_id,positions_name,keyword,address)
	VALUES(1,"3500","8000","湖北","宜昌",3,"本科",20,"13薪，带薪休假","熟练掌握Java各种框架",1,"JAVA工程师","JAVA,工程师","湖北宜昌ctgu");
