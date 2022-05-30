/*
mysql -u root -p
123456
source D:\Education\Own\JAVA\Projects\noticeSaver\Database\Scripts\role.sql
*/


CREATE DATABASE IF NOT EXISTS noticesaver;
USE noticesaver;

DROP TABLE role;
CREATE TABLE IF NOT EXISTS role 
	(
		id int auto_increment primary key,
		name varchar(40)
	);

INSERT role(id, name) VALUES
	(
		1, 'ROLE_ADMIN'
	) 
	ON DUPLICATE KEY UPDATE 
    name="ROLE_ADMIN";

INSERT role(id, name) VALUES
	(
		2, 'ROLE_USER'
	) 
	ON DUPLICATE KEY UPDATE 
    name="ROLE_USER";

SELECT * FROM role;
SHOW TABLES;
SHOW DATABASES;