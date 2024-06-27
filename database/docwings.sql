create database IF NOT EXISTS docwingsdb;
use docwingsdb;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `usergroup`;
CREATE TABLE `usergroup`  (
  `group_id` int AUTO_INCREMENT PRIMARY KEY		NOT NULL	COMMENT '用户组ID',
  `group_name` varchar(20) UNIQUE				NOT NULL	COMMENT '用户组名',
  `auth` tinyint 								NOT NULL	COMMENT '权限'
);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int AUTO_INCREMENT PRIMARY KEY  	NOT NULL	COMMENT '用户ID',
  `user_name` varchar(20) UNIQUE				NOT NULL	COMMENT '用户名',
  `psw` varchar(64)  							NOT NULL	COMMENT '用户密码',
  `email` varchar(32) UNIQUE 					NOT NULL	COMMENT '电子邮件',
  `group_id` int DEFAULT 0						NOT NULL	COMMENT '用户组ID',
  `is_admin` boolean DEFAULT false	 			NOT NULL	COMMENT '是否是管理员',
  `phone` varchar(32) UNIQUE				 	NOT NULL	COMMENT '电话号码',
  `failed_attempts` INT DEFAULT 0                           COMMENT '登录失败次数',
  `account_locked` BOOLEAN DEFAULT FALSE                    COMMENT '是否冻结',
  `lock_time` TIMESTAMP NULL                                COMMENT '冻结时间',
  FOREIGN KEY (`group_id`) REFERENCES usergroup(`group_id`)
);

DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs`  (
  `log_id` int AUTO_INCREMENT PRIMARY KEY  		NOT NULL	COMMENT '日志ID',
  `user_id` int									NOT NULL	COMMENT '操作者ID',
  `act` text									NOT NULL	COMMENT '行为',
  `importance` int								NOT NULL	COMMENT '重要性',
  `log_time` datetime				 			NOT NULL	COMMENT '时间',
  FOREIGN KEY (`user_id`) REFERENCES user(`user_id`)
);

DROP TABLE IF EXISTS `folders`;
CREATE TABLE `folders`  (
  `folder_id` int AUTO_INCREMENT PRIMARY KEY 	NOT NULL	COMMENT '文件夹ID',
  `folder_name` varchar(100) 					NOT NULL	COMMENT '文件夹名',
  `parent_id` int 								NOT NULL	COMMENT '父文件夹ID',
  `create_time` datetime 						NOT NULL	COMMENT '创建日期',
  `creater_id` int								NOT NULL	COMMENT '创建者ID',
  `tag` varchar(100)										COMMENT '标记',
  `is_deleted` boolean DEFAULT false 			NOT NULL	COMMENT '已删除',
  `last_modifier_id` int						NOT NULL	COMMENT '上次修改者ID',
  `last_modify_time` datetime					NOT NULL	COMMENT '上次修改时间',
  FOREIGN KEY (`parent_id`) REFERENCES folders(`folder_id`),
  FOREIGN KEY (`creater_id`) REFERENCES user(`user_id`),
  FOREIGN KEY (`last_modifier_id`) REFERENCES user(`user_id`)
);

DROP TABLE IF EXISTS `files`;
CREATE TABLE `files`  (
  `file_id` int AUTO_INCREMENT PRIMARY KEY  	NOT NULL	COMMENT '文件ID',
  `file_name` varchar(100) 						NOT NULL	COMMENT '文件名',
  `parent_id` int 								NOT NULL	COMMENT '父文件夹ID',
  `upload_time` datetime 						NOT NULL	COMMENT '上传时间',
  `file_size` double		 					NOT NULL	COMMENT '文件大小',
  `file_type` varchar(100) 						NOT NULL	COMMENT '文件类型',
  `uploader_id` int								NOT NULL	COMMENT '上传者ID',
  `last_modifier_id` int						NOT NULL	COMMENT '上次修改者ID',
  `tag` varchar(100)										COMMENT '标记',
  `last_modify_time` datetime					NOT NULL	COMMENT '上次修改时间',
  `is_deleted` boolean DEFAULT false 			NOT NULL	COMMENT '已删除',
  `path` text									NOT NULL	COMMENT '文件路径（Windows）',
  FOREIGN KEY (`uploader_id`) REFERENCES user(`user_id`),
  FOREIGN KEY (`parent_id`) REFERENCES folders(`folder_id`),
  FOREIGN KEY (`last_modifier_id`) REFERENCES user(`user_id`)
);

DROP TABLE IF EXISTS `shares`;
CREATE TABLE `shares`  (
  `share_id` int  AUTO_INCREMENT PRIMARY KEY  	NOT NULL	COMMENT '分享ID',
  `file_id` int  											COMMENT '文件ID',
  `folder_id` int  											COMMENT '文件夹ID',
  `sharer_id` int							 	NOT NULL	COMMENT '分享者ID',
  `auth` tinyint DEFAULT 0						NOT NULL	COMMENT '权限',     -- 其中auth包含了是否公分享
  `share_time` datetime	 			 			NOT NULL	COMMENT '共享日期',
  `due_time` datetime	 			 						COMMENT '到期日期',
  `accepter_id` int											COMMENT '接受者ID',
  `accept_group_id` int 									COMMENT '接受用户组ID',
  `is_folder` boolean DEFAULT false				NOT NULL	COMMENT '是否是文件夹',
  FOREIGN KEY (`sharer_id`) REFERENCES user(`user_id`),
  FOREIGN KEY (`file_id`) REFERENCES files(`file_id`),
  FOREIGN KEY (`folder_id`) REFERENCES folders(`folder_id`),
  FOREIGN KEY (`accepter_id`) REFERENCES user(`user_id`),
  FOREIGN KEY (`accept_group_id`) REFERENCES usergroup(`group_id`)
);

DROP TABLE IF EXISTS `collections`;
CREATE TABLE `collections`  (
  `col_id` int AUTO_INCREMENT PRIMARY KEY 		NOT NULL	COMMENT '收藏ID',
  `user_id` int 								NOT NULL	COMMENT '用户ID',
  `file_id` int 											COMMENT '文件ID',
  `folder_id` int 											COMMENT '文件夹ID',
  `is_folder` boolean DEFAULT false 			NOT NULL	COMMENT '是否文件夹',
  FOREIGN KEY (`user_id`) REFERENCES user(`user_id`),
  FOREIGN KEY (`folder_id`) REFERENCES folders(`folder_id`),
  FOREIGN KEY (`file_id`) REFERENCES files(`file_id`)
);

SET FOREIGN_KEY_CHECKS = 1;

SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO `usergroup` (`group_id`,`group_name`, `auth`) VALUES ('1','管理员', 10);
INSERT INTO `usergroup` (`group_id`,`group_name`, `auth`) VALUES ('2','默认用户', 3);
INSERT INTO `usergroup` (`group_id`,`group_name`, `auth`) VALUES ('-1','已注销账户', -1);
INSERT INTO `user` (`user_id`, `user_name`, `psw`, `email`, `group_id`, `is_admin`, `phone`) VALUES ('1', 'admin', 'admin', '', 1, true, '');
INSERT INTO `user` (`user_id`, `user_name`, `psw`, `email`, `group_id`, `is_admin`, `phone`) VALUES ('-1', '已注销账户', '', '-1', -1, false, '-1');
INSERT INTO `folders` (`folder_id`,`folder_name`, `parent_id`, `create_time`, `creater_id`, `tag`, `last_modify_time`, `is_deleted`, `last_modifier_id`)
VALUES (0,'ROOT', 0, '2024-06-01 09:00:00', 1, null, '2024-06-01 09:00:00', false, 1);
UPDATE `folders` SET `folder_id` = '0' WHERE (`folder_name` = 'ROOT') and folder_id>=0;
SET FOREIGN_KEY_CHECKS = 1;

-- TRIGGER --
-- 用户密码加密
DELIMITER $$
DROP TRIGGER IF EXISTS before_user_insert $$
CREATE TRIGGER before_user_insert
BEFORE INSERT ON `user`
FOR EACH ROW
BEGIN
  SET NEW.psw = SHA2(NEW.psw, 256);
END;
$$
DELIMITER ;

-- 文件夹放入取出回收站
SET global max_sp_recursion_depth = 64;
SET @@max_sp_recursion_depth = 64;
DELIMITER //
DROP PROCEDURE IF EXISTS update_node_status //
CREATE PROCEDURE update_node_status(node_id INT, new_status BOOLEAN)
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE child_id INT;
    DECLARE cur CURSOR FOR
        SELECT folder_id FROM folders WHERE parent_id = node_id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    -- 更新当前文件夹
    UPDATE folders SET is_deleted = new_status WHERE folder_id = node_id;
    -- 递归更新文件
    UPDATE files SET is_deleted = new_status WHERE parent_id = node_id;
    -- 递归更新文件夹
    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO child_id;
        IF done THEN
            LEAVE read_loop;
        END IF;
        CALL update_node_status(child_id, new_status);
    END LOOP;
    CLOSE cur;
END //
DELIMITER ;

-- 文件夹被删除
DELIMITER //
DROP PROCEDURE IF EXISTS delete_node //
CREATE PROCEDURE delete_node(node_id INT)
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE child_folder_id INT;
    DECLARE cur CURSOR FOR
        SELECT folder_id FROM folders WHERE parent_id = node_id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    -- 删除文件夹中的文件
    DELETE FROM files WHERE parent_id = node_id;
    DELETE FROM collections
    WHERE file_id IN (SELECT file_id FROM files WHERE parent_id = node_id);
    DELETE FROM shares
    WHERE file_id IN (SELECT file_id FROM files WHERE parent_id = node_id);
    -- 递归删除文件夹
    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO child_folder_id;
        IF done THEN
            LEAVE read_loop;
        END IF;
        CALL delete_node(child_folder_id);
    END LOOP;
    CLOSE cur;
    -- 删除文件夹
    DELETE FROM collections WHERE folder_id = node_id;
    DELETE FROM shares WHERE folder_id = node_id;
    DELETE FROM folders WHERE folder_id = node_id;
END //
DELIMITER ;

-- 用户删除前将含有其id项设为-1
DELIMITER $$
DROP TRIGGER IF EXISTS BeforeUserLogout $$
CREATE TRIGGER BeforeUserLogout
    BEFORE DELETE ON user
    FOR EACH ROW
BEGIN
    UPDATE folders
    SET creater_id = CASE WHEN creater_id = OLD.user_id THEN -1 ELSE creater_id END,
        last_modifier_id = CASE WHEN last_modifier_id = OLD.user_id THEN -1 ELSE last_modifier_id END
    WHERE creater_id = OLD.user_id OR last_modifier_id = OLD.user_id;
    UPDATE files
    SET uploader_id = CASE WHEN uploader_id = OLD.user_id THEN -1 ELSE uploader_id END,
        last_modifier_id = CASE WHEN last_modifier_id = OLD.user_id THEN -1 ELSE last_modifier_id END
    WHERE uploader_id = OLD.user_id OR last_modifier_id = OLD.user_id;
    DELETE FROM collections
    WHERE user_id = OLD.user_id;
    DELETE FROM shares
    where user_id = OLD.user_id;
    END$$
    DELIMITER ;