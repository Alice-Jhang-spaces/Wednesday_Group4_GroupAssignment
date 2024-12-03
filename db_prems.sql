/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : db_prems

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2024-11-29 14:42:23
*/
CREATE DATABASE IF NOT EXISTS db_prems;
USE db_prems;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `administrative`
-- ----------------------------
DROP TABLE IF EXISTS `administrative`;
CREATE TABLE `administrative` (
  `a_id` varchar(100) NOT NULL,
  `a_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of administrative
-- ----------------------------
INSERT INTO `administrative` VALUES ('11', 'Surgery');
INSERT INTO `administrative` VALUES ('12322', 'Vaccine Department');

-- ----------------------------
-- Table structure for `doctor`
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `dd_id` varchar(100) NOT NULL,
  `a_id` varchar(100) DEFAULT NULL,
  `dd_name` varchar(100) DEFAULT NULL,
  `dd_sex` varchar(100) DEFAULT NULL,
  `dd_age` varchar(100) DEFAULT NULL,
  `dd_type` varchar(100) DEFAULT NULL,
  `dd_price` varchar(100) DEFAULT NULL,
  `dd_type1` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`dd_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('111', '12111', 'John Smith', 'Male', '35', 'Senior Surgeon', 'Harvard University', '10 years');

-- ----------------------------
-- Table structure for `dss`
-- ----------------------------
DROP TABLE IF EXISTS `dss`;
CREATE TABLE `dss` (
  `D_id` varchar(100) NOT NULL,
  `D_name` varchar(100) DEFAULT NULL,
  `D_type` varchar(100) DEFAULT NULL,
  `D_number` varchar(100) DEFAULT NULL,
  `D_id_id` varchar(100) DEFAULT NULL,
  `D_unit` varchar(100) DEFAULT NULL,
  `D_spec` varchar(100) DEFAULT NULL,
  `D_price` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`D_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of dss
-- ----------------------------
INSERT INTO `dss` VALUES ('11', 'Paxlovid', 'Pfizer', '118', '121111', '1 dose', '12*22', '168');
INSERT INTO `dss` VALUES ('77785587', 'Molnupiravir', 'Merck', '117', '1211', '1 bottle', '12*10', '6.8');
INSERT INTO `dss` VALUES ('12345', 'Remdesivir', 'Gilead Sciences', '116', '1312', '1 vial', '12*15', '7.2');
INSERT INTO `dss` VALUES ('54321', 'Favipiravir', 'Glenmark Pharmaceuticals', '115', '1223', '10 tablets', '12*20', '5.5');
INSERT INTO `dss` VALUES ('67890', 'Areplivir', 'Promomed', '114', '1244', '20 tablets', '12*30', '10.5');
INSERT INTO `dss` VALUES ('98765', 'Ensitrelvir', 'Medicines Patent Pool', '113', '1255', '1 dose', '12*25', '8.4');
INSERT INTO `dss` VALUES ('13579', 'Skyvira', 'Promomed', '112', '1266', '1 pack', '12*35', '6.9');
INSERT INTO `dss` VALUES ('97531', 'Esperavir', 'Promomed', '111', '1277', '1 bottle', '12*40', '7.8');


-- ----------------------------
-- Table structure for `marketing`
-- ----------------------------
DROP TABLE IF EXISTS `marketing`;
CREATE TABLE `marketing` (
  `m_id` varchar(100) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `productname` varchar(100) DEFAULT NULL,
  `m_data` varchar(100) DEFAULT NULL,
  `m_result` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of marketing
-- ----------------------------
INSERT INTO `marketing` VALUES ('23444', 'James Brown', 'Moderna', '2024-11-25', 'Promoting effective vaccination');
INSERT INTO `marketing` VALUES ('555788', 'Michael Johnson', 'Novavax', '2024-11-29', 'Marketing campaign initiated');

-- ----------------------------
-- Table structure for `mrescription`
-- ----------------------------
DROP TABLE IF EXISTS `mrescription`;
CREATE TABLE `mrescription` (
  `m_id` varchar(100) NOT NULL,
  `r_id` varchar(100) DEFAULT NULL,
  `m_number` varchar(100) DEFAULT NULL,
  `m_data` varchar(100) DEFAULT NULL,
  `m_result` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of mrescription
-- ----------------------------
INSERT INTO `mrescription` VALUES ('1111', 'Cleveland Clinic', '123 Health Avenue', '1991', 'One of the top hospitals in the U.S.');

-- ----------------------------
-- Table structure for `patient`
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `p_id` varchar(100) NOT NULL,
  `p_name` varchar(100) NOT NULL,
  `p_sex` ENUM('Male', 'Female') NOT NULL,
  `p_age` INT DEFAULT NULL,
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('12232','Emily Davis','Female','28');
INSERT INTO `patient` VALUES ('468795125','Robert Johnson','Male','45');

-- ----------------------------
-- Table structure for `prevention`
-- ----------------------------
DROP TABLE IF EXISTS `prevention`;
CREATE TABLE `prevention` (
  `m_id` varchar(100) NOT NULL,
  `r_id` varchar(100) DEFAULT NULL,
  `m_number` varchar(100) DEFAULT NULL,
  `m_data` varchar(100) DEFAULT NULL,
  `m_result` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of prevention
-- ----------------------------
INSERT INTO `prevention` VALUES ('11', 'June Pandemic', '33', '2024-06', 'The pandemic is relatively stable');
INSERT INTO `prevention` VALUES ('1123', 'July Pandemic', '99', '2024-07', 'The situation is manageable');
INSERT INTO `prevention` VALUES ('432', 'August Pandemic', '77', '2024-08', 'The trend looks good');

-- ----------------------------
-- Table structure for `registration`
-- ----------------------------
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration` (
  `r_id` varchar(100) NOT NULL,
  `p_id` varchar(100) DEFAULT NULL,
  `dd_id` varchar(100) DEFAULT NULL,
  `r_data` varchar(100) DEFAULT NULL,
  `r_price_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of registration
-- ----------------------------
INSERT INTO `registration` VALUES ('7788444554', '88777211220001', 'Tylenol', '2024-11-29', 'Distributed to patients');
INSERT INTO `registration` VALUES ('S34321212', '45567889457844', 'Pfizer Vaccine', '2024-11-27', 'Vaccination distribution to patients');

-- ----------------------------
-- Table structure for `seedling`
-- ----------------------------
DROP TABLE IF EXISTS `seedling`;
CREATE TABLE `seedling` (
  `m_id` varchar(100) NOT NULL,
  `r_id` varchar(100) DEFAULT NULL,
  `m_number` varchar(100) DEFAULT NULL,
  `m_data` varchar(100) DEFAULT NULL,
  `m_result` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of seedling
-- ----------------------------
INSERT INTO `seedling` VALUES ('SD232232', 'Moderna', 'New York', '2024-10-21', 'One of the largest vaccine producers globally');
INSERT INTO `seedling` VALUES ('SD332224', 'Novavax', 'San Francisco', '2024-10-21', 'Focused on innovative vaccine development');
INSERT INTO `seedling` VALUES ('SD45343', 'Pfizer', 'Boston', '2024-10-21', 'Leader in vaccine market share globally');
INSERT INTO `seedling` VALUES ('SD566777', 'AstraZeneca', 'Los Angeles', '2024-10-21', 'Specializes in vaccines and biotech medicines');
INSERT INTO `seedling` VALUES ('SD77878767', 'Johnson & Johnson', 'Washington D.C.', '2024-10-21', 'Known for its high technical capacity in vaccine production');

-- ----------------------------
-- Table structure for `transport`
-- ----------------------------
DROP TABLE IF EXISTS `transport`;
CREATE TABLE `transport` (
  `t_id` varchar(100) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `t_status` varchar(100) DEFAULT NULL,
  `t_data` varchar(100) DEFAULT NULL,
  `t_result` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of transport
-- ----------------------------
INSERT INTO `transport` VALUES ('411544784', 'Pfizer Vaccine', 'In transit', '2024-11-28', 'Estimated delivery in 5 days');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  `orginization` varchar(40) DEFAULT NULL,
  `enterprise` varchar(40) DEFAULT NULL,
  `network` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Updated Table structure for `WorkRequests`
-- ----------------------------
DROP TABLE IF EXISTS `WorkRequests`;
CREATE TABLE `WorkRequests` (
  `WorkRequestID` int(11) NOT NULL AUTO_INCREMENT,
  `RequestType` varchar(100) NOT NULL,
  `RequesterRole` varchar(100) NOT NULL,
  `RequesterOrganization` varchar(100) NOT NULL,
  `RequesterEnterprise` varchar(100) NOT NULL,
  `ReceiverRole` varchar(100) NOT NULL,
  `ReceiverOrganization` varchar(100) NOT NULL,
  `ReceiverEnterprise` varchar(100) NOT NULL,
  `Status` ENUM('Pending', 'In Progress', 'Completed') DEFAULT 'Pending',
  `Description` text NOT NULL,
  PRIMARY KEY (`WorkRequestID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Updated Records of WorkRequests
-- ----------------------------
INSERT INTO `WorkRequests` (RequestType, RequesterRole, RequesterOrganization, RequesterEnterprise, ReceiverRole, ReceiverOrganization, ReceiverEnterprise, Status, Description)
VALUES
('Lab Test Request', 'Employee', 'Pharmacy', 'Merck', 'Organization Admin', 'Research & Development', 'Merck', 'Pending', 'Request for lab test on new medicine.'),
('Equipment Request', 'Manager', 'Healthcare Operations', 'WHO', 'Organization Admin', 'Logistics', 'Fedex', 'In Progress', 'Request to deliver medical equipment to healthcare center.'),
('Pandemic Data Update', 'Network Admin', 'IT Department', 'WHO', 'Manager', 'Healthcare Operations', 'WHO', 'Completed', 'Request to update pandemic statistics and reports.'),
('Transport Coordination', 'Organization Admin', 'Logistics', 'Fedex', 'Employee', 'Transportation', 'Fedex', 'Pending', 'Request to arrange vaccine delivery logistics.');
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  `orginization` varchar(40) DEFAULT NULL,
  `enterprise` varchar(40) DEFAULT NULL,
  `network` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` (`id`, `name`, `password`, `role`, `orginization`, `enterprise`, `network`) VALUES
(1, 'Alice', '123Alice!', 'Admin', 'Headquarters', 'FDA', 'GlobalHealthNet'),
(2, 'Bob', '123Bob!', 'Network Admin', 'IT Department', 'WHO', 'GlobalHealthNet'),
(3, 'Charlie', '123Charlie!', 'Enterprise Admin', 'Regulatory Affairs', 'FDA', 'GlobalHealthNet'),
(4, 'David', '123David!', 'Organization Admin', 'Logistics', 'Fedex', 'GlobalHealthNet'),
(5, 'Eve', '123Eve!', 'Manager', 'Healthcare Operations', 'WHO', 'GlobalHealthNet'),
(6, 'Frank', '123Frank!', 'Employee', 'Pharmacy', 'Merck', 'GlobalHealthNet'),
(7, 'Grace', '123Grace!', 'Auditor', 'Compliance', 'Pfizer', 'GlobalHealthNet'),
(8, 'Hank', '123Hank!', 'Organization Admin', 'Research & Development', 'Merck', 'GlobalHealthNet'),
(9, 'Ivy', '123Ivy!', 'Employee', 'Transportation', 'Fedex', 'GlobalHealthNet'),
(10, 'Jack', '123Jack!', 'Guest', 'Public Relations', 'Pfizer', 'GlobalHealthNet');

-- ----------------------------
-- Table structure for `userorder`
-- ----------------------------
DROP TABLE IF EXISTS `userorder`;
CREATE TABLE `userorder` (
  `o_id` varchar(100) NOT NULL,
  `productname` varchar(100) DEFAULT NULL,
  `num` varchar(100) DEFAULT NULL,
  `o_data` varchar(100) DEFAULT NULL,
  `o_result` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`o_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of userorder
-- ----------------------------
INSERT INTO `userorder` VALUES ('6565', 'Moderna', '2', '2024-11-29', 'Very effective');


-- ----------------------------
-- Table structure for `vaccinate`
-- ----------------------------
DROP TABLE IF EXISTS `vaccinate`;
CREATE TABLE `vaccinate` (
  `v_id` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `productname` varchar(100) DEFAULT NULL,
  `v_data` varchar(100) DEFAULT NULL,
  `v_result` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`v_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of vaccinate
-- ----------------------------
INSERT INTO `vaccinate` VALUES ('66', 'Emily Davis', 'Pfizer Vaccine', '2024-11-27', 'Weekly dose');
