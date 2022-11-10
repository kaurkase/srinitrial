--liquibase formatted sql
--changeset kaur:2
CREATE TABLE country(
	code varchar NOT NULL PRIMARY KEY,
	name VARCHAR UNIQUE NOT NULL
);