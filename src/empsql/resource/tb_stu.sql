CREATE TABLE tbl_student (
  id int(10) PRIMARY KEY NOT NULL,
  name VARCHAR(50)
);

ALTER TABLE tbl_student MODIFY id int AUTO_INCREMENT;
DESC tbl_student;

ALTER TABLE tbl_student CHARACTER SET utf8;
ALTER DATABASE emp_db CHARACTER SET utf8;
ALTER TABLE tbl_student CHANGE name stu_name VARCHAR(50) CHARACTER SET utf8;
ALTER TABLE tbl_student CHANGE id stu_id int;
ALTER TABLE tbl_student MODIFY stu_id int AUTO_INCREMENT;
INSERT INTO tbl_student VALUES (NULL, '赫敏');
INSERT INTO tbl_student VALUES (NULL, '罗恩');
DELETE FROM tbl_student WHERE stu_id=4;
SELECT * FROM tbl_student;
show variables like 'collation_%';