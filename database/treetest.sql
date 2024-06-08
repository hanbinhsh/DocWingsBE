DROP TABLE IF EXISTS tree;
CREATE TABLE tree (
    id INT PRIMARY KEY,
    parent_id INT,
    name VARCHAR(50)
);
SET @@max_sp_recursion_depth = 32;
DELIMITER //
DROP PROCEDURE IF EXISTS delete_node //
CREATE PROCEDURE delete_node(node_id INT)
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE child_id INT;
    DECLARE cur CURSOR FOR
        SELECT id FROM tree WHERE parent_id = node_id;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO child_id;
        IF done THEN
            LEAVE read_loop;
        END IF;
        CALL delete_node(child_id);
    END LOOP;
    CLOSE cur;
    DELETE FROM tree WHERE id = node_id;
END //
DELIMITER ;

INSERT INTO `docwingsdb`.`tree` (`id`, `parent_id`, `name`) VALUES ('1', '0', 'ROOT');
INSERT INTO `docwingsdb`.`tree` (`id`, `parent_id`, `name`) VALUES ('2', '1', 'c1');
INSERT INTO `docwingsdb`.`tree` (`id`, `parent_id`, `name`) VALUES ('3', '1', 'c2');
INSERT INTO `docwingsdb`.`tree` (`id`, `parent_id`, `name`) VALUES ('4', '2', 'c3');
INSERT INTO `docwingsdb`.`tree` (`id`, `parent_id`, `name`) VALUES ('5', '4', 'c4');

CALL delete_node(2);