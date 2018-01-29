/*CREATE DATABASE DemoDB;*/
CREATE TABLE user_userinfo
(
	user_id varchar(255) primary key not null ,
	username varchar(255),
	usergroup int,
	ingredients int,
	otheringredients varchar(255),
	isvote int,
	taskcardenable int,
	timestampstart varchar(255),
	timestampend varchar(255),
	timeused varchar(255),
	iscorrect int,
	quizusergroup int
);

CREATE TABLE usergroup
(
	group_id int primary key not null ,
	groupname varchar(255),
	votecount int,
	otheringredients varchar(255)
);

CREATE TABLE ingredients
(
	ingredients_id int primary key not null ,
	ingredientsname varchar(255),
	usergroup int,
	isused int,
	description varchar(500)
);

CREATE TABLE questionnaire
(
	questionnaire_id int primary key not null ,
	answername varchar(255),
	answer varchar(255),
	usergroup int
);