--liquibase formatted sql
--changeset kaur:3
CREATE TABLE client(
	id IDENTITY NOT NULL PRIMARY KEY,
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
	username VARCHAR UNIQUE NOT NULL,
	email VARCHAR,
	address VARCHAR NOT NULL,
	country_code VARCHAR NOT NULL,
	srini_user_id BIGINT NOT NULL,
	FOREIGN KEY (country_code) references country(code),
	FOREIGN KEY (srini_user_id) references srini_user(id)
);