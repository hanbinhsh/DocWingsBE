use docwingsdb;
-- TEST --
INSERT INTO `files` (`file_name`, `parent_id`, `upload_time`, `file_size`, `file_type`, `uploader_id`, `last_modifier_id`, `tag`, `last_modify_time`, `is_deleted`, `path`) VALUES
('logo.jpg', 3, '2024-06-01 11:30:00', 2048.0, 'image/png', 2, 2, '个人图片', '2024-06-01 11:30:00', false, 'C:\\DocWings\\2180a7df-7b72-4351-93e3-53301dcf1079.png');

select * from files where file_id = 6;

SELECT * FROM `user`
WHERE `user_name` = 'admin'
AND `psw` = SHA2('admin123', 256);


-- 插入示例数据
INSERT INTO folders (folder_name, parent_id, create_time, creater_id, last_modifier_id, last_modify_time) 
VALUES ('1', 2, NOW(), 1, 1, NOW());

INSERT INTO folders (folder_name, parent_id, create_time, creater_id, last_modifier_id, last_modify_time) 
VALUES ('11', 6, NOW(), 1, 1, NOW());

INSERT INTO folders (folder_name, parent_id, create_time, creater_id, last_modifier_id, last_modify_time) 
VALUES ('111', 6, NOW(), 1, 1, NOW());

INSERT INTO folders (folder_name, parent_id, create_time, creater_id, last_modifier_id, last_modify_time) 
VALUES ('2', 2, NOW(), 1, 1, NOW());

INSERT INTO files (file_name, parent_id, upload_time, file_size, file_type, uploader_id, last_modifier_id, last_modify_time, path) 
VALUES ('111', 2, NOW(), 100, 'txt', 1, 1, NOW(), 'C:\\files\\file1.txt');

INSERT INTO files (file_name, parent_id, upload_time, file_size, file_type, uploader_id, last_modifier_id, last_modify_time, path) 
VALUES ('222', 6, NOW(), 200, 'txt', 1, 1, NOW(), 'C:\\files\\file2.txt');

-- 设置根文件夹及其内容为已删除
call update_node_status(2,true);
-- 取消删除根文件夹及其所有子内容
call update_node_status(2,false);

SELECT * FROM docwingsdb.folders;

SELECT * FROM `user` WHERE `user_name` = 'editor1' AND `psw` = SHA2('editor123', 256);

SELECT
	u.user_name,
	ug.group_name,
	u.email,
	l.act,
	l.importance,
	l.log_time
FROM logs l 
JOIN user u ON l.user_id = u.user_id 
JOIN usergroup ug ON u.group_id = ug.group_id;

SELECT 
    f.folder_name,
    f.create_time,
    u1.user_name AS creator_name,
    f.tag,
    u2.user_name AS last_modifier_name,
    f.last_modify_time
FROM folders f
JOIN user u1 ON f.creater_id = u1.user_id
JOIN user u2 ON f.last_modifier_id = u2.user_id
WHERE f.parent_id = 0;

SELECT 
    f.file_name,
    f.tag,
    f.file_size,
    f.file_type,
    u1.user_name AS uploader_name,
    f.upload_time,
    u2.user_name AS last_modifier_name,
    f.last_modify_time
FROM
    files f
JOIN
    user u1 ON f.uploader_id = u1.user_id
JOIN
    user u2 ON f.last_modifier_id = u2.user_id
WHERE
    f.parent_id = 0;
    
SELECT
		(SELECT COUNT(*) FROM files WHERE parent_id = 0) +
		(SELECT COUNT(*) FROM folders WHERE parent_id = 0) AS total_count;
        
UPDATE files 
SET parent_id = 1 
WHERE file_id = 2;

ALTER TABLE `files` MODIFY COLUMN `uploader_id` int NOT NULL COMMENT '上传者ID';
ALTER TABLE `files` MODIFY COLUMN `last_modifier_id` int NOT NULL COMMENT '上次修改者ID';
ALTER TABLE `folders` MODIFY COLUMN `creater_id` int NOT NULL COMMENT '创建者ID';
ALTER TABLE `folders` MODIFY COLUMN `last_modifier_id` int NOT NULL COMMENT '上次修改者ID';
ALTER TABLE `shares` ADD COLUMN `due_time` datetime COMMENT '到期日期';
ALTER TABLE `user` MODIFY COLUMN `phone` varchar(32) UNIQUE	NOT NULL COMMENT '电话号码';

ALTER TABLE `shares`
ADD COLUMN `accept_group_id` int COMMENT '接受用户组ID',
ADD CONSTRAINT `fk_accept_group_id` FOREIGN KEY (`accept_group_id`) REFERENCES `usergroup`(`group_id`);

ALTER TABLE `shares` MODIFY COLUMN  `file_id` int COMMENT '文件ID';
ALTER TABLE `shares` MODIFY COLUMN  `folder_id` int COMMENT '文件夹ID';
ALTER TABLE `shares` MODIFY COLUMN  `accepter_id` int COMMENT '接受者ID';

INSERT INTO `shares` (`file_id`, `folder_id`, `sharer_id`, `auth`, `share_time`, `due_time`, `accepter_id`, `is_folder`) VALUES
(1, 2, 7, 1, '2024-06-02 09:00:00', '2024-06-22 09:00:00', 2, false);

ALTER TABLE `user`
ADD COLUMN `failed_attempts` INT DEFAULT 0 COMMENT '登录失败次数',
ADD COLUMN `account_locked` BOOLEAN DEFAULT FALSE COMMENT '是否冻结',
ADD COLUMN `lock_time` TIMESTAMP NULL COMMENT '冻结时间';

INSERT INTO `user` (`user_name`, `psw`, `email`, `group_id`, `is_admin`, `phone`) VALUES
('hanbin', '3', 'admin@example.com', 1, true, '1234567890');
