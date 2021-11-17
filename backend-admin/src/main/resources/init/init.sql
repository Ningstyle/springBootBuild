/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`scuser` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `scuser`;

/*Table structure for table `small_dict` */

DROP TABLE IF EXISTS `small_dict`;

CREATE TABLE `small_dict` (
  `id` varchar(32) NOT NULL COMMENT '字典ID',
  `name` varchar(64) DEFAULT NULL COMMENT '字典名称',
  `description` varchar(255) DEFAULT NULL COMMENT '字典描述',
  `sort_order` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识：0、正常   -1、 删除',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

/*Data for the table `small_dict` */

/*Table structure for table `small_dict_data` */

DROP TABLE IF EXISTS `small_dict_data`;

CREATE TABLE `small_dict_data` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `dict_id` varchar(32) DEFAULT NULL COMMENT '所属字典表ID',
  `name` varchar(255) DEFAULT NULL COMMENT '数据名称',
  `value` varchar(255) DEFAULT NULL COMMENT '字典数据值',
  `status` int(1) DEFAULT NULL COMMENT '是否启用 0、启用  -1、 禁用',
  `sort_order` int(11) DEFAULT NULL COMMENT '排序值',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识：0、正常   -1、 删除',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';

/*Data for the table `small_dict_data` */

/*Table structure for table `small_permission` */

DROP TABLE IF EXISTS `small_permission`;

CREATE TABLE `small_permission` (
  `id` varchar(32) NOT NULL COMMENT '权限ID',
  `name` varchar(64) DEFAULT NULL COMMENT '权限名称',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `type` int(1) DEFAULT NULL COMMENT '权限类型: 0、页面 1、 操作',
  `level` int(1) DEFAULT NULL COMMENT '菜单层级 0,1,2,3',
  `icon` varchar(255) DEFAULT NULL COMMENT '按钮',
  `path` varchar(255) DEFAULT NULL COMMENT '页面路径/资源链接url',
  `action_method` varchar(10) DEFAULT NULL COMMENT 'request 请求方式：GET，POST,DELETE,PUT,HEAD',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父资源ID',
  `sort_order` int(11) DEFAULT NULL COMMENT '排序值',
  `status` int(1) DEFAULT NULL COMMENT '是否启用：0、启用 -1、 不启用',
  `show_always` bit(1) DEFAULT NULL COMMENT '总是展示: 0、展示 -1、不展示',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识：0、正常   -1、 删除',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

/*Data for the table `small_permission` */

insert  into `small_permission`(`id`,`name`,`description`,`type`,`level`,`icon`,`path`,`action_method`,`parent_id`,`sort_order`,`status`,`show_always`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values
('190631826178000000','用户管理子系统','用户管理子系统',-1,0,'md-home','',NULL,'',0,0,'',0,'admin','2019-04-04 00:50:22','admin','2019-07-31 20:24:57'),
('190631826178235675','测试获取用户数据','测试获取用户数据',1,3,'','/test/index','GET','190631826178445460',5,0,'',0,'admin','2018-06-05 22:54:18','admin','2018-10-23 12:34:51'),
('190631826178445300','用户权限管理','用户权限管理',0,1,'ios-settings','/sys',NULL,'190631826178000000',1,0,'',0,'admin','2018-06-04 19:02:29','admin','2018-09-29 23:11:56'),
('190631826178445301','用户管理','用户管理',0,2,'md-person','user-manage',NULL,'190631826178445300',1,0,'',0,'admin','2018-06-04 19:02:32','admin','2018-08-13 15:15:33'),
('190631826178445302','角色权限管理','角色权限管理',0,2,'md-contacts','role-manage',NULL,'190631826178445300',2,0,'',0,'admin','2018-06-04 19:02:35','admin','2018-10-13 13:51:36'),
('190631826178445303','菜单权限管理','菜单权限管理',0,2,'md-menu','menu-manage',NULL,'190631826178445300',3,0,'',0,'admin','2018-06-04 19:02:37','admin','2018-09-23 23:32:02'),
('190631826178445330','查询用户列表','根据条件查询用户列表',1,3,'','/users','GET','190631826178445301',1,0,'',0,'admin','2019-09-29 15:19:39','admin','2019-09-29 15:19:46'),
('190631826178445331','获取指定用户信息','获取指定用户信息',1,3,NULL,'/users/*','GET','190631826178445301',2,0,'',0,'admin','2019-09-29 15:21:43','admin','2019-09-29 15:21:46'),
('190631826178445332','获取指定用户角色','获取指定用户角色',1,3,NULL,'/users/*/roles','GET','190631826178445301',3,0,'',0,'admin','2019-09-29 15:22:53','admin','2019-09-29 15:22:57'),
('190631826178445333','添加用户','添加用户',1,3,'','/users','POST','190631826178445301',4,0,'',0,'admin','2018-06-03 22:04:06','admin','2018-09-19 22:16:44'),
('190631826178445334','编辑用户','编辑用户',1,3,'','/users/*','PUT','190631826178445301',5,0,'',0,'admin','2018-06-06 14:45:16','admin','2018-09-19 22:16:48'),
('190631826178445335','修改用户角色','用户授权：修改用户角色',1,3,NULL,'/users/*/roles','PUT','190631826178445301',6,0,'',0,'admin','2019-09-29 15:26:00','admin','2019-09-29 15:26:03'),
('190631826178445336','删除用户','删除用户',1,3,'','/users/*','DELETE','190631826178445301',7,0,'',0,'admin','2018-06-06 14:46:32','admin','2018-08-10 21:41:16'),
('190631826178445337','获取指定用户的权限','获取指定用户的权限',1,3,NULL,'/users/*/uiPermissions','GET','190631826178445301',8,0,'',0,'admin','2019-09-29 15:28:57','admin','2019-09-29 15:29:01'),
('190631826178445338','重置密码','重置密码',1,3,'','/users/*/password','PUT','190631826178445301',9,0,'',0,'admin','2019-06-27 01:51:39','admin','2019-06-27 01:51:39'),
('190631826178445339','启用用户','启用用户',1,3,'','/users/open','PUT','190631826178445301',10,0,'',0,'admin','2018-06-03 22:33:52','admin','2018-06-28 16:44:48'),
('190631826178445340','禁用用户','禁用用户',1,3,'','/users/disable','PUT','190631826178445301',11,0,'',0,'admin','2018-06-03 22:06:09','admin','2018-06-06 14:46:51'),
('190631826178445400','配置管理','配置管理',0,1,'md-lock','/conf',NULL,'190631826178000000',2,0,'\0',0,'admin','2018-06-05 19:50:06','admin','2019-06-26 20:38:41'),
('190631826178445430','查询角色列表','查询角色列表',1,3,NULL,'/roles','GET','190631826178445302',1,0,'',0,'admin','2019-09-29 15:42:29','admin','2019-09-29 15:42:34'),
('190631826178445431','查询角色信息','根据ID查询角色信息',1,3,NULL,'/roles/*','GET','190631826178445302',2,0,'',0,'admin','2019-09-29 15:50:01','admin','2019-09-29 15:50:05'),
('190631826178445432','查询角色权限','根据ID查询角色权限',1,3,NULL,'/roles/*/uiPermissions','GET','190631826178445302',3,0,'',0,'admin','2019-09-29 15:51:33','admin','2019-09-29 15:51:38'),
('190631826178445433','添加角色','添加角色',1,3,'','/roles','POST','190631826178445302',4,0,'',0,'admin','2018-06-06 15:22:03','admin','2018-09-19 22:07:34'),
('190631826178445434','编辑角色','编辑角色',1,3,'','/roles/*','PUT','190631826178445302',5,0,'',0,'admin','2018-06-06 15:30:59','admin','2018-09-19 22:07:37'),
('190631826178445435','分配角色权限','分配角色权限',1,3,NULL,'/roles/*/uiPermissions','PUT','190631826178445302',6,0,'',0,'admin','2018-06-06 15:31:59','admin','2018-06-06 15:31:59'),
('190631826178445436','删除角色','删除角色',1,3,'','/roles/*','DELETE','190631826178445302',7,0,'',0,'admin','2018-06-06 15:31:26','admin','2018-08-10 21:41:23'),
('190631826178445437','设为默认角色','设为默认角色',1,3,'','/roles/setDefault/*','PUT','190631826178445302',8,0,'',0,'admin','2018-06-06 15:33:41','admin','2018-09-19 22:07:46'),
('190631826178445450','行政区域管理','行政区域管理',0,2,'md-lock','region_manage',NULL,'190631826178445400',1,0,'',0,'admin','2018-06-05 19:51:21','admin','2018-10-23 12:34:38'),
('190631826178445460','自定义区域管理','自定义区域管理',0,2,'md-git-branch','custom_region_manage',NULL,'190631826178445400',2,0,'',0,'admin','2018-08-10 15:06:10','admin','2018-08-10 15:06:10'),
('190631826178445530','查询UI权限列表','查询菜单权限列表',1,3,NULL,'/uiPermissions','GET','190631826178445303',1,0,'',0,'admin','2019-09-29 16:03:18','admin','2019-09-29 16:03:55'),
('190631826178445531','获取指定UI信息','获取指定UI信息',1,3,NULL,'/uiPermissions/*','GET','190631826178445303',2,0,'',0,'admin','2019-09-29 16:04:55','admin','2019-09-29 16:04:59'),
('190631826178445532','添加UI权限','添加UI权限',1,3,'','/uiPermissions','POST','190631826178445303',3,0,'',0,'admin','2018-06-06 15:51:46','admin','2018-09-19 22:07:52'),
('190631826178445533','编辑UI权限','编辑UI权限',1,3,'','/uiPermissions/*','PUT','190631826178445303',4,0,'',0,'admin','2018-06-06 15:52:44','admin','2018-09-19 22:07:57'),
('190631826178445534','删除UI权限','删除UI权限',1,3,'','/uiPermissions/*','DELETE','190631826178445303',5,0,'',0,'admin','2018-06-06 15:53:17','admin','2018-08-10 21:41:33');

/*Table structure for table `small_role` */

DROP TABLE IF EXISTS `small_role`;

CREATE TABLE `small_role` (
  `id` varchar(32) NOT NULL COMMENT '角色ID',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(255) DEFAULT NULL COMMENT '角色说明',
  `default_role` bit(1) DEFAULT NULL COMMENT '是否默认role',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识：0、正常   -1、 删除',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Data for the table `small_role` */

insert  into `small_role`(`id`,`role_name`,`role_description`,`default_role`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values
('189554222960742452','ROLE_ADMIN','超级管理员 拥有所有权限',NULL,0,'184467039522525196','2019-09-26 15:54:27','184467039522525196','2019-09-26 15:54:31'),
('189554222960742453','ROLE_TEST','普通用户权限','',0,'184467039522525196','2019-09-26 15:54:29','184467039522525196','2019-09-26 15:54:33');

/*Table structure for table `small_role_permission` */

DROP TABLE IF EXISTS `small_role_permission`;

CREATE TABLE `small_role_permission` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `permission_id` varchar(32) DEFAULT NULL COMMENT '权限ID',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识：0、正常   -1、 删除',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系表';

/*Data for the table `small_role_permission` */

insert  into `small_role_permission`(`id`,`role_id`,`permission_id`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values
('190651806093676549','189554222960742452','190631826178000000',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676550','189554222960742452','190631826178235675',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676551','189554222960742452','190631826178445300',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676552','189554222960742452','190631826178445301',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676553','189554222960742452','190631826178445302',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676554','189554222960742452','190631826178445303',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676555','189554222960742452','190631826178445330',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676556','189554222960742452','190631826178445331',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676557','189554222960742452','190631826178445332',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676558','189554222960742452','190631826178445333',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676559','189554222960742452','190631826178445334',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676560','189554222960742452','190631826178445335',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676561','189554222960742452','190631826178445336',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676562','189554222960742452','190631826178445337',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676563','189554222960742452','190631826178445338',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676564','189554222960742452','190631826178445339',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676565','189554222960742452','190631826178445340',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676566','189554222960742452','190631826178445400',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676567','189554222960742452','190631826178445430',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676568','189554222960742452','190631826178445431',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676569','189554222960742452','190631826178445432',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676570','189554222960742452','190631826178445433',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676571','189554222960742452','190631826178445434',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676572','189554222960742452','190631826178445435',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676573','189554222960742452','190631826178445436',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676574','189554222960742452','190631826178445437',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676575','189554222960742452','190631826178445450',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676576','189554222960742452','190631826178445460',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676577','189554222960742452','190631826178445530',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676578','189554222960742452','190631826178445531',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676579','189554222960742452','190631826178445532',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676580','189554222960742452','190631826178445533',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190651806093676581','189554222960742452','190631826178445534',0,'anonymous','2019-09-29 16:34:34','anonymous','2019-09-29 16:34:34'),
('190652898076528649','189554222960742453','190631826178235675',0,'anonymous','2019-09-29 16:38:54','anonymous','2019-09-29 16:38:54'),
('190652898080722948','189554222960742453','190631826178000000',0,'anonymous','2019-09-29 16:38:54','anonymous','2019-09-29 16:38:54'),
('190652898080722949','189554222960742453','190631826178445450',0,'anonymous','2019-09-29 16:38:54','anonymous','2019-09-29 16:38:54'),
('190652898080722950','189554222960742453','190631826178445460',0,'anonymous','2019-09-29 16:38:54','anonymous','2019-09-29 16:38:54');

/*Table structure for table `small_role_rule` */

DROP TABLE IF EXISTS `small_role_rule`;

CREATE TABLE `small_role_rule` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `rule_id` varchar(32) DEFAULT NULL COMMENT '规则ID',
  `rule_json` varchar(255) DEFAULT NULL COMMENT '规则json',
  `rule_sql` varchar(255) DEFAULT NULL COMMENT '规则sql',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识：0、正常   -1、 删除',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色规则关系表';

/*Data for the table `small_role_rule` */

/*Table structure for table `small_rule` */

DROP TABLE IF EXISTS `small_rule`;

CREATE TABLE `small_rule` (
  `id` varchar(32) DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识：0、正常   -1、 删除',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据规则表';

/*Data for the table `small_rule` */

/*Table structure for table `small_user` */

DROP TABLE IF EXISTS `small_user`;

CREATE TABLE `small_user` (
  `id` varchar(32) NOT NULL COMMENT '用户ID',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户登录名',
  `password` varchar(128) DEFAULT NULL COMMENT '用户密码',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `real_name` varchar(50) DEFAULT NULL COMMENT '用户真实姓名',
  `mobile` varchar(30) DEFAULT NULL COMMENT '用户手机',
  `email` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别 男  or 女',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `user_type` int(1) DEFAULT NULL COMMENT '用户类型：0、普通用户  1、管理员',
  `user_status` int(1) DEFAULT NULL COMMENT '用户状态： 0、启用  -1、禁用',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识：0、正常   -1、 删除',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `small_user` */

insert  into `small_user`(`id`,`user_name`,`password`,`nick_name`,`real_name`,`mobile`,`email`,`sex`,`avatar`,`description`,`user_type`,`user_status`,`del_flag`,`last_login_time`,`create_by`,`create_time`,`update_by`,`update_time`) values
('184467039522525196','admin','$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy','xiaoming','luhanlin','110','test@qq.com','男',NULL,NULL,1,0,0,'2019-09-12 15:00:13','admin','2019-09-12 15:00:22','admin','2019-09-12 15:00:28'),
('184467039522525197','test','$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy','xiaoming','wang','120','test@qq.com','女',NULL,NULL,0,0,0,'2019-09-12 15:02:27','admin','2019-09-12 15:02:32','admin','2019-09-12 15:02:37'),
('184501081739497472','luhanlin','$2a$10$9YQPyDgI72m4IZfIulCKD.J9ID2/zQffruPb7y8wUKy03k96mwCQK',NULL,NULL,NULL,NULL,NULL,'https://i.loli.net/2019/04/28/5cc5a71a6e3b6.png',NULL,0,0,0,NULL,'admin',NULL,'admin',NULL),
('188855212012736518','test01','$2a$10$y3m0Cux1DBnvxBN.VdJDDu52Hgm8VYxiPZR4XP0xBnJoJssvo.XdC','xiaofang','xiaoming','110','111@qq.com','男','test','desc',0,0,0,NULL,'admin',NULL,'admin',NULL),
('188857780495454212','test02','$2a$10$sB46guGl2pymwiDGy2MpqulAIUymeh0UKCHsh75a1T3JejKl43RF.','xiaofang','xiaoming','110','111@qq.com','男','test','desc',0,0,0,NULL,'admin',NULL,'admin',NULL),
('188859573656883203','test03','$2a$10$xHdp4WPg1A6aur05VeBfkOYUHXTlt479eyNaEEJWiiqXMdk0Qukwy','xiaofang','xiaoming','110','111@qq.com','男','test','desc',0,0,0,NULL,'admin','2019-09-24 17:52:52','admin','2019-09-24 17:52:52'),
('190626540038393857','test0001','$2a$10$D8/O/8rLilfHVSupqLKUCujZoFJ/MpvLkRYDvfu2G7MqHntHPueSy','test','test','110','test@qq.com','男','http://243dsds.img','测试权限',0,0,0,NULL,'admin','2019-09-29 14:54:10','admin','2019-09-29 14:54:10'),
('190626841130700805','test0002','$2a$10$1QXoJJ0S1mpYTCdqSb4InuLJAi2ziXDYXsBTw/cUiYht0WDlMl9Im','test','test','110','test@qq.com','男','http://243dsds.img','测试权限',0,0,0,NULL,'test','2019-09-29 14:55:22','test','2019-09-29 14:55:22');

/*Table structure for table `small_user_role` */

DROP TABLE IF EXISTS `small_user_role`;

CREATE TABLE `small_user_role` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识：0、正常   -1、 删除',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

/*Data for the table `small_user_role` */

insert  into `small_user_role`(`id`,`user_id`,`role_id`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values
('189554222960742454','184467039522525196','189554222960742452',0,'184467039522525196','2019-09-26 15:58:25','184467039522525196','2019-09-26 15:58:31'),
('189554222960742455','184467039522525196','189554222960742453',0,'184467039522525196','2019-09-26 15:58:27','184467039522525196','2019-09-26 15:58:33'),
('189554222960742456','184467039522525197','189554222960742453',0,'184467039522525196','2019-09-26 15:58:29','184467039522525196','2019-09-26 15:58:35'),
('190626540889837569','190626540038393857','189554222960742453',0,'admin','2019-09-29 14:54:10','admin','2019-09-29 14:54:10'),
('190626841768235011','190626841130700805','189554222960742453',0,'test','2019-09-29 14:55:22','test','2019-09-29 14:55:22');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
