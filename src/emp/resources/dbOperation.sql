USE emp_db;
SHOW DATABASES;
DROP TABLE IF EXISTS user_tb;
DROP TABLE IF EXISTS emp_tb;
DROP TABLE IF EXISTS dep_tb;
CREATE TABLE user_tb (
  USER_NAME VARCHAR(50) PRIMARY KEY,
  PASSWORD  VARCHAR(50)   NOT NULL,
  NICKNAME  VARCHAR(50)   NOT NULL,
  SALARY    DOUBLE(10, 2) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE dep_tb (
  ID       INT SERIAL DEFAULT VALUE PRIMARY KEY,
  DEP_NAME VARCHAR(50) UNIQUE NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE emp_tb (
  ID       INT SERIAL DEFAULT VALUE,
  EMP_NAME VARCHAR(50)   NOT NULL,
  GENDER   VARCHAR(50)   NOT NULL,
  SALARY   DOUBLE(10, 2) NOT NULL,
  DEP_ID   INT           NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT FOREIGN KEY (DEP_ID) REFERENCES dep_tb (ID)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8;

SHOW TABLES;
DESC user_tb;
DESC dep_tb;
DESC emp_tb;


INSERT INTO user_tb VALUE ('admin', '123', '管理员', 88888.88);
SELECT * FROM user_tb;
SELECT count(*)
FROM user_tb;


ALTER TABLE dep_tb MODIFY DEP_NAME VARCHAR(50) UNIQUE NOT NULL;
ALTER TABLE dep_tb CHANGE DEP_ID ID INT SERIAL DEFAULT VALUE;
DESC dep_tb;



DESC emp_tb;
SHOW CREATE TABLE emp_tb;

show tables;
desc user_tb;
SELECT count(*) FROM user_tb;
SELECT * FROM user_tb;
SELECT * FROM user_tb WHERE USER_NAME = 'kevin';
DELETE FROM user_tb WHERE USER_NAME = 'kevin2';
UPDATE user_tb SET PASSWORD = '8888', NICKNAME = '超级管理员', SALARY = 9898.98 WHERE USER_NAME = 'admin';



