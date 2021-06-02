--DROP DATABASE IF EXISTS postgres;

DROP TABLE IF EXISTS users_keyword_stocks;
DROP TABLE IF EXISTS users_keyword;
DROP TABLE IF EXISTS users_stocks;
DROP TABLE IF EXISTS keywords CASCADE;
DROP TABLE IF EXISTS stocks CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS log_keywords;
DROP TABLE IF EXISTS log_stocks;
DROP TABLE IF EXISTS log_users;

--CREATE DATABASE postgres;


CREATE TABLE IF NOT EXISTS users (
	id SERIAL PRIMARY KEY,
	username varchar(100) NOT NULL,
	email varchar(100) NOT null UNIQUE,
	pwd varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS stocks (
	id SERIAL PRIMARY key,
	stockname varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS keywords (
	id SERIAL PRIMARY KEY,
	keyword varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_stocks(
	user_id SERIAL REFERENCES users (id) ON DELETE CASCADE,
	stock_id SERIAL REFERENCES stocks (id) ON DELETE CASCADE,
	PRIMARY KEY (user_id, stock_id)
);

CREATE TABLE IF NOT EXISTS users_keywords(
	user_id SERIAL REFERENCES users (id) ON DELETE CASCADE,
	keyword_id SERIAL REFERENCES keywords (id) ON DELETE CASCADE,
	PRIMARY KEY (user_id, keyword_id)
);

CREATE TABLE IF NOT EXISTS users_keywords_stocks(
	user_id SERIAL REFERENCES users (id) ON DELETE CASCADE,
	keyword_id SERIAL REFERENCES keywords (id) ON DELETE CASCADE,
	stock_id SERIAL REFERENCES stocks (id) ON DELETE CASCADE,
	PRIMARY KEY (user_id, keyword_id, stock_id)
);

CREATE TABLE IF NOT EXISTS log_keywords (
	id SERIAL PRIMARY KEY,
	keyword varchar(100) NOT NULL,
	db_username varchar(100) NOT NULL,
	adding_time varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS log_stocks (
	id SERIAL PRIMARY KEY,
	stockname varchar(100) NOT NULL,
	db_username varchar(100) NOT NULL,
	adding_time varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS log_users (
	id SERIAL PRIMARY KEY,
	username varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	db_username varchar(100) NOT NULL,
	adding_time varchar(100) NOT NULL
);

