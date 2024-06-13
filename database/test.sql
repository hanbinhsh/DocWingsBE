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