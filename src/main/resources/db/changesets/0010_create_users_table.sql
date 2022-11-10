--liquibase formatted sql
--changeset kaur:1
CREATE TABLE srini_user(
	id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	username VARCHAR UNIQUE NOT NULL,
	password VARCHAR NOT NULL,
	enabled BOOLEAN NOT NULL
);