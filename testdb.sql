-- 1.上传物品(item)要审核，申请物品(apply)要审核,两者都混杂了审核过程表(audit_type) 其余审核的还有：用户表, 互动表, 求助活动表
-- 2.帮扶记录(support)和互动(interactive)都混杂了活动和物品
-- 3.物品和活动都有img图片
-- 4.求助活动和官方活动都有富文本编辑器, actinfo 和offinfo有开始和结束时间

CREATE DATABASE `campus_system`;
USE `campus_system`;


-- 管理员
CREATE TABLE if not exists admin(
	id int(11) auto_increment primary key,
	username varchar(255) not null unique comment "用户名",
	password varchar(255) not null,
    adminname varchar(255) not null comment "管理员名称"
)engine=InnoDB auto_increment=1 default charset=utf8;

-- 用户表
CREATE TABLE if not exists user(
	id int(11) auto_increment primary key,
	username varchar(255) not null unique comment "用户名",
	password varchar(255) not null,
    actualname varchar(255) comment "真实姓名",
    sex varchar(10) comment "性别",
	province varchar(64) comment "省份",
	city varchar(64) comment "城市",
	address text comment "详细地址",
	phone varchar(16) unique comment "联系电话",
	email varchar(255) UNIQUE,
	introduce text comment "个人简介",
    identity varchar(18) unique comment "身份证号",
    is_actualname int(1) default 0,
	createtime timestamp default current_timestamp
)engine=InnoDB auto_increment=1 default charset=utf8;

-- 物品类型表
CREATE TABLE if not exists item_type(
	id int(11) auto_increment primary key,
	itemname varchar(255) not null unique comment "物品种类"
)engine=InnoDB auto_increment=1 default charset=utf8;


-- 物品表
CREATE TABLE if not exists item(
	id int(11) auto_increment primary key,
	itemname varchar(255) not null comment "物品名",
    itemimg varchar(255) comment "物品图片",
    itemcount int(11) not null comment "物品数量",
    introduce text comment "物品简介",
    userid int(11) not null comment "上传人ID",
    typeid int(11) not null comment "物品类型ID",
    auditid int(11) not null comment "申请情况ID",
	createtime timestamp default current_timestamp comment "上传时间"
)engine=InnoDB auto_increment=1 default charset=utf8;


-- 帮扶记录 A向B捐赠C物品名字叫D 或 A举办B活动B是接受者，名称叫C
CREATE TABLE if not exists support(
	id int(11) auto_increment primary key,
	donorid int(11) not null comment "发起者",
	itemid int(11) not null comment "物品活动ID",
    itemnum int(11) not null default 1 comment "物品活动数量",
    receiveid int(11) not null comment "接收者",
    remarks varchar(255) not null comment "备注",
    distin int(1) not null comment "区别 0代表捐赠,1代表活动",
    createtime timestamp default current_timestamp comment "帮扶时间"
)engine=InnoDB auto_increment=1 default charset=utf8;



-- 互动表
CREATE TABLE if not exists interactive(
	id int(11) auto_increment primary key,
	itemid int(11) not null comment "物品活动ID",
	donorid int(11) not null comment "发起者",
    receiveid int(11) not null comment "接收者",
    content varchar(255) not null comment "评论内容",
    distin int(1) not null comment "区别 0代表捐赠，1代表活动" ,
	createtime timestamp default current_timestamp comment "评论时间"
)engine=InnoDB auto_increment=1 default charset=utf8;

-- 留言表
CREATE TABLE if not exists leaveidea(
	id int(11) auto_increment primary key,
    ideaid int(11) not null comment "问题ID",
	user1id int(11) not null comment "用户ID",
    comment varchar(255) not null comment "评论内容",
    is_solve int(1) not null default 0 comment "解决 0代表非成功解决，1代表成功解决" ,
	createtime timestamp default current_timestamp comment "评论时间"
)engine=InnoDB auto_increment=1 default charset=utf8;

-- 审核过程表
CREATE TABLE if not exists audit_type(
	id int(11) auto_increment primary key,
	audit_name varchar(255) not null comment "审核过程"
)engine=InnoDB auto_increment=1 default charset=utf8;

-- 物品申请表
CREATE TABLE if not exists apply(
	id int(11) auto_increment primary key,
    itemid int(11) not null comment "物品ID",
    userid int(11) not null comment "申请人ID",
    auditid int(11) not null comment "申请情况ID",
    createtime timestamp default current_timestamp comment "申请时间"
)engine=InnoDB auto_increment=1 default charset=utf8;

-- 活动类别表
CREATE TABLE if not exists acttype(
	id int(11) auto_increment primary key,
    actname varchar(255) not null comment "活动名称"    
)engine=InnoDB auto_increment=1 default charset=utf8;

-- 求助活动信息表
CREATE TABLE if not exists actinfo(
	id int(11) auto_increment primary key,
    actid int(11) not null comment "活动ID",
    userid int(11) not null comment "申请人ID",
    auditid int(11) not null comment "申请情况ID",
    actimg varchar(255) comment "活动图片",
    introduce text not null comment "活动介绍",
    acttypeid varchar(255) not null comment "活动类型",
    starttime datetime not null comment "开始时间",
    endtime Datetime not null comment "结束时间"
)engine=InnoDB auto_increment=1 default charset=utf8;


-- 官方活动信息表
CREATE TABLE if not exists offinfo(
	id int(11) auto_increment primary key,
    actid int(11) not null comment "活动ID",
    userid int(11) not null comment "申请人ID",
    actimg varchar(255) comment "活动图片",
    introduce text not null comment "活动介绍",
    acttypeid varchar(255) not null comment "活动类型",
    starttime datetime not null comment "开始时间",
    endtime Datetime not null comment "结束时间"
)engine=InnoDB auto_increment=1 default charset=utf8;

INSERT INTO admin VALUES(null,'admin','123456','author');

INSERT INTO user VALUES(null,'user1','123456','Help','男','湖北省','宜昌市','湖北省宜昌市三峡大学', '12345678901', '1234567890@qq.com', '这是新用户', '421023199999999999',0,null);
INSERT INTO user VALUES(null,'user2','654321','Boss','女', '湖北省','武汉市','湖北省武汉市武汉大学', '12456789456', '12345612310@qq.com', '武汉大学毕业生', '421023199999999989',0,null);


INSERT INTO item_type VALUES(null,'学习资料');
INSERT INTO item_type VALUES(null,'生活用品');
INSERT INTO item_type VALUES(null,'金币');

INSERT INTO item VALUES(null,'张宇18讲','C:\Users\Empire\Pictures\images', '2', '张宇老师带你学习高数','1','1','1',null);

INSERT INTO support VALUES(null,'1','1', '2','2', '捐赠行为','0',null);

INSERT INTO interactive VALUES(null,'1','1', '2', '书山有路勤为径，学海无涯苦作舟','0',null);
INSERT INTO interactive VALUES(null,'1','2', '1', '多谢捐赠','0',null);

INSERT INTO leaveidea VALUES(null,'1','1', '请问还有英语书吗?', '0',null);

INSERT INTO audit_type VALUES(null, '审核中');
INSERT INTO audit_type VALUES(null, '审核成功');
INSERT INTO audit_type VALUES(null, '审核失败');

-- 用户2申请张宇18讲的书
INSERT INTO apply VALUES(null, '1', '2', '1', null);

INSERT INTO acttype VALUES(null, '公益献血');
INSERT INTO acttype VALUES(null, '保护动物');
INSERT INTO acttype VALUES(null, '筹钱治病');

INSERT INTO actinfo VALUES(null, '1', '1', '1', 'D:\Empire\Pictures\help', '公益献血，从你我做起', '1', 0, 0);

INSERT INTO offinfo VALUES(null, '1', '1', 'E:\Empire\Pictures\protect', '保护动物，从你我做起', '2', "2019-1-1 00:00:00", "2019-1-3 00:00:00");