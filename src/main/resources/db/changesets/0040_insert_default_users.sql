--liquibase formatted sql
--changeset kaur:4
INSERT INTO srini_user 
	(username, password, enabled) 
	values 
	('user1', '{bcrypt}$2a$10$ILrCezRTExZKIiODQX9F3eGDEY5L9QHi/4HWzVmv.UcqpYyEuvpaS', true),
	('user2', '{bcrypt}$2a$10$F0j2A7BbMi0U6U1Fm7FRCu0764z2VBZhLUNNmwxVK8dAYXyX9ypde', true),
	('user3', '{bcrypt}$2a$10$yuRo7eQsyEEsqu8wgXF8EObVlJhIaq.yfSJILp/g47dymkLNRgUji', true);