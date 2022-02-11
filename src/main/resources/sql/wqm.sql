/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : wqm

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2022-02-11 10:44:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for monitor_point
-- ----------------------------
DROP TABLE IF EXISTS `monitor_point`;
CREATE TABLE `monitor_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lat` double(20,6) NOT NULL COMMENT '纬度',
  `lng` double(20,6) NOT NULL COMMENT '经度',
  `wq_type_name` varchar(50) NOT NULL COMMENT '监测点当前的水质类别名称',
  `city` varchar(10) DEFAULT NULL COMMENT '市',
  `district` varchar(20) DEFAULT NULL COMMENT '区',
  `site` varchar(32) DEFAULT NULL COMMENT '地点',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `node_id` int(11) NOT NULL COMMENT '唯一节点序号',
  `latest_depth` double(10,2) DEFAULT NULL COMMENT '最新的深度',
  `pollution_status` tinyint(2) DEFAULT NULL COMMENT '0正常 1污染',
  `condition_info` varchar(255) DEFAULT NULL COMMENT '周围环境状况',
  `water_info` varchar(255) DEFAULT NULL COMMENT '水域信息',
  `depth_list` varchar(255) DEFAULT NULL COMMENT '深度列表',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `editor_id` int(11) DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='监测点表';

-- ----------------------------
-- Table structure for monitor_point_data
-- ----------------------------
DROP TABLE IF EXISTS `monitor_point_data`;
CREATE TABLE `monitor_point_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wq_type_name` varchar(50) NOT NULL COMMENT '监测点当前的水质类别名称',
  `monitor_point_id` int(11) NOT NULL COMMENT '监测点id',
  `montior_point_node_id` int(11) DEFAULT NULL COMMENT '监测点节点序号',
  `pollution_record_id` int(11) DEFAULT NULL COMMENT '污染记录id',
  `lat` double(20,6) NOT NULL COMMENT '纬度',
  `lng` double(20,6) NOT NULL COMMENT '经度',
  `city` varchar(10) DEFAULT NULL COMMENT '市',
  `district` varchar(20) DEFAULT NULL COMMENT '区',
  `depth` double(10,2) DEFAULT NULL COMMENT '深度值',
  `turbidity` double(5,2) DEFAULT NULL COMMENT '浑浊度',
  `temperature` double(5,2) DEFAULT NULL COMMENT '温度',
  `ph` double(5,2) DEFAULT NULL COMMENT 'PH值',
  `dissolved_oxygen` double(5,2) DEFAULT NULL COMMENT '溶氧量',
  `conductivity` double(5,2) DEFAULT NULL COMMENT '导电率',
  `redox_potential` double(5,2) DEFAULT NULL COMMENT '氧化还原电位',
  `nh3n` double(5,2) DEFAULT NULL COMMENT '氨氮',
  `mno4` double(5,2) DEFAULT NULL COMMENT '高猛酸盐植数',
  `single_is_pollution` tinyint(1) DEFAULT NULL COMMENT '单参数判定方式是否污染',
  `is_pollution` tinyint(1) DEFAULT NULL COMMENT '综合判定是否污染',
  `site` varchar(32) DEFAULT NULL COMMENT '地点',
  `is_handled` tinyint(1) DEFAULT NULL COMMENT '是否已经处理',
  `is_ph_out` tinyint(1) DEFAULT NULL COMMENT 'PH值是否超标',
  `is_dissolved_oxygen_out` tinyint(1) DEFAULT NULL COMMENT '溶氧量是否超标',
  `is_conductivity_out` tinyint(1) DEFAULT NULL COMMENT '导电率是否超标',
  `is_temperature_out` tinyint(1) DEFAULT NULL COMMENT '温度是否超标',
  `is_redox_potential_out` tinyint(1) DEFAULT NULL COMMENT '氧化还原电位是否超标',
  `is_turbidity_out` tinyint(1) DEFAULT NULL COMMENT '浑浊度是否超标',
  `is_nh3n_out` tinyint(1) DEFAULT NULL COMMENT '氨氮是否超标',
  `is_mno4_out` tinyint(1) DEFAULT NULL COMMENT '高猛酸盐是否超标',
  `is_checked` tinyint(1) DEFAULT NULL COMMENT '数据是否被查看',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `editor_id` int(11) DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='监测点数据表';

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_type` tinyint(2) NOT NULL COMMENT '0新节点通知 1污染记录通知',
  `site_id` int(11) NOT NULL COMMENT '监测点id或污染记录id',
  `title` varchar(255) NOT NULL COMMENT '通知标题',
  `content` varchar(255) NOT NULL COMMENT '通知内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `editor_id` int(11) DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='消息通知表';

-- ----------------------------
-- Table structure for pollution_record
-- ----------------------------
DROP TABLE IF EXISTS `pollution_record`;
CREATE TABLE `pollution_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL COMMENT '标题 保存超标参数',
  `start_time` datetime DEFAULT NULL COMMENT '污染开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '污染结束时间',
  `level` tinyint(2) DEFAULT NULL COMMENT '污染等级',
  `pollution_analyse` varchar(255) DEFAULT NULL COMMENT '污染源分析',
  `measure` varchar(255) DEFAULT NULL COMMENT '治理措施',
  `measure_effect` varchar(255) DEFAULT NULL COMMENT '治理效果',
  `monitor_point_node_id` int(11) DEFAULT NULL COMMENT '监测点节点id',
  `monitor_point_id` int(11) DEFAULT NULL COMMENT '监测点id',
  `depth` double(10,2) DEFAULT NULL COMMENT '深度值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `editor_id` int(11) DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='污染记录表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限：资源ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Table structure for wq_param
-- ----------------------------
DROP TABLE IF EXISTS `wq_param`;
CREATE TABLE `wq_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wq_type_id` int(11) NOT NULL COMMENT '水质类别id',
  `wq_param_type_id` int(11) DEFAULT NULL COMMENT '水质参数类别id',
  `max_concentration` double(5,2) DEFAULT NULL COMMENT '最大浓度阈值',
  `min_concentration` double(5,2) DEFAULT NULL COMMENT '最小浓度阈值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `editor_id` int(11) DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='水质参数表';

-- ----------------------------
-- Table structure for wq_param_type
-- ----------------------------
DROP TABLE IF EXISTS `wq_param_type`;
CREATE TABLE `wq_param_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '水质参数名称 (浑浊度，ph，溶氧量，深度值，导电率，氧化还原电位等)',
  `unit` varchar(8) DEFAULT NULL COMMENT '单位',
  `sense` varchar(255) DEFAULT NULL COMMENT '水质参数意义',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `editor_id` int(11) DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='水质参数种类';

-- ----------------------------
-- Table structure for wq_type
-- ----------------------------
DROP TABLE IF EXISTS `wq_type`;
CREATE TABLE `wq_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '水质类别名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `editor_id` int(11) DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='水质类别表';
