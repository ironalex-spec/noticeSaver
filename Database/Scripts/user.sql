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
		login varchar(40),
		password varchar(500),
		role_id int REFERENCES role (id)
	);

INSERT USER(id, firstname, lastname, email, login) VALUES
	(
		1, 'Alex', 'Prostio', "a@mail.ru", "Alex"
	) 
	ON DUPLICATE KEY UPDATE 
    firstname="Alex", lastname='Prostio', email="a@mail.ru", login="Alex";

INSERT USER(id, firstname, lastname, email, login) VALUES
	(
		2, 'Fred', 'Jecky', "frej@gmail.com", "Fred"
	) 
	ON DUPLICATE KEY UPDATE 
    firstname="Fred", lastname='Jecky', email="frej@gmail.com", login="Fred";

SELECT * FROM user;
SHOW TABLES;
SHOW DATABASES;