use docwingsdb;

INSERT INTO `usergroup` (`group_name`, `auth`) VALUES
('管理员', 10),
('编辑', 5),
('作者', 3),
('读者', 1);

INSERT INTO `user` (`user_name`, `psw`, `email`, `group_id`, `is_admin`, `phone`) VALUES
('admin', 'admin123', 'admin@example.com', 1, true, '1234567890'),
('editor1', 'editor123', 'editor1@example.com', 2, false, '0987654321'),
('author1', 'author123', 'author1@example.com', 3, false, '1122334455'),
('reader1', 'reader123', 'reader1@example.com', 4, false, '2233445566'),
('user2', 'user2123', 'user2@example.com', 2, false, '3344556677');

INSERT INTO `logs` (`user_id`, `act`, `log_time`, `importance`) VALUES
(1, '创建用户', '2024-06-01 10:00:00', 1),
(2, '编辑文章', '2024-06-01 11:00:00', 2),
(3, '发布文章', '2024-06-01 12:00:00', 3),
(4, '查看文章', '2024-06-01 13:00:00', 1),
(5, '评论文章', '2024-06-01 14:00:00', 2);

SET FOREIGN_KEY_CHECKS = 0;
INSERT INTO `folders` (`folder_id`,`folder_name`, `parent_id`, `create_time`, `creater_id`, `tag`, `last_modify_time`, `is_deleted`, `last_modifier_id`) VALUES
(0,'ROOT', 0, '2024-06-01 09:00:00', 1, 'system', '2024-06-01 09:00:00', false, 1);
UPDATE `folders` SET `folder_id` = '0' WHERE (`folder_name` = 'ROOT') and folder_id>=0;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `folders` (`folder_name`, `parent_id`, `create_time`, `creater_id`, `tag`, `last_modify_time`, `is_deleted`, `last_modifier_id`) VALUES
('子文件夹1', 0, '2024-06-01 10:00:00', 2, '工作', '2024-06-01 10:00:00', false, 2),
('子文件夹2', 0, '2024-06-01 11:00:00', 3, '个人', '2024-06-01 11:00:00', false, 3),
('子文件夹3', 0, '2024-06-01 12:00:00', 1, '项目', '2024-06-01 12:00:00', false, 3),
('子文件夹4', 0, '2024-06-01 13:00:00', 1, '文档', '2024-06-01 13:00:00', false, 1);

INSERT INTO `files` (`file_name`, `parent_id`, `upload_time`, `file_size`, `file_type`, `uploader_id`, `last_modifier_id`, `tag`, `last_modify_time`, `is_deleted`, `path`) VALUES
('文档1.txt', 2, '2024-06-01 10:30:00', 12.5, 'text/plain', 1, 1, '工作文档', '2024-06-01 10:30:00', false, 'C:\\files\\文档1.txt'),
('图片1.jpg', 3, '2024-06-01 11:30:00', 2048.0, 'image/jpeg', 2, 2, '个人图片', '2024-06-01 11:30:00', false, 'C:\\files\\图片1.jpg'),
('报告1.pdf', 4, '2024-06-01 12:30:00', 500.0, 'application/pdf', 3, 3, '项目报告', '2024-06-01 12:30:00', false, 'C:\\files\\报告1.pdf'),
('音乐1.mp3', 5, '2024-06-01 13:30:00', 3000.0, 'audio/mpeg', 4, 4, '文档音乐', '2024-06-01 13:30:00', false, 'C:\\files\\音乐1.mp3'),
('演示文稿1.ppt', 2, '2024-06-01 14:30:00', 1024.0, 'application/vnd.ms-powerpoint', 5, 5, '工作演示', '2024-06-01 14:30:00', false, 'C:\\files\\演示文稿1.ppt');

INSERT INTO `shares` (`file_id`, `folder_id`, `sharer_id`, `auth`, `share_time`, `accepter_id`, `is_folder`) VALUES
(1, 2, 1, 1, '2024-06-02 09:00:00', 2, false),
(2, 3, 2, 2, '2024-06-02 10:00:00', 3, false),
(3, 0, 3, 3, '2024-06-02 11:00:00', 4, false),
(3, 2, 4, 1, '2024-06-02 12:00:00', 5, true),
(4, 5, 5, 1, '2024-06-02 13:00:00', 1, false);

INSERT INTO `groupauth` (`group_id`, `folder_id`, `auth`) VALUES
(1, 2, 10),
(2, 2, 5),
(3, 3, 3),
(4, 4, 1),
(2, 5, 5);