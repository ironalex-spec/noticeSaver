/*
mysql -u root -p
source D:\Education\Own\JAVA\Projects\noticeSaver\Database\Scripts\user.sql
*/


CREATE DATABASE IF NOT EXISTS noticesaver;
USE noticesaver;

DROP TABLE user;
CREATE TABLE IF NOT EXISTS user 
	(
		id bigint auto_increment primary key,
		firstname varchar(40),
		lastname varchar(40),
		email varchar(40),
		password varchar(40)
	);

INSERT USER(id, firstname, lastname, email) VALUES
	(
		1, 'Alex', 'Prostio', "a@mail.ru"
	) 
	ON DUPLICATE KEY UPDATE 
    firstname="Alex", lastname='Prostio', email="a@mail.ru";

INSERT USER(id, firstname, lastname, email) VALUES
	(
		2, 'Fred', 'Jecky', "frej@gmail.com"
	) 
	ON DUPLICATE KEY UPDATE 
    firstname="Fred", lastname='Jecky', email="frej@gmail.com";

SELECT * FROM user;
SHOW TABLES;
SHOW DATABASES;