drop procedure if exists add_keyword;
drop procedure if exists add_stock;
drop procedure if exists add_user;
drop procedure if exists add_user_keyword;
drop procedure if exists add_user_stock;

-- STORED PROCEDURE FOR ADD NEW KEYWORD
CREATE OR REPLACE PROCEDURE add_keyword(keyword_name varchar) 
	AS $$
	BEGIN 
		INSERT INTO keywords (keyword) values(keyword_name);
	END$$
language plpgsql;

-- STORED PROCEDURE FOR ADD NEW STOCK
CREATE OR REPLACE PROCEDURE add_stock(stock_name varchar) 
	AS $$
	BEGIN 
		INSERT INTO stocks (stockname) values(stock_name);
	END$$
language plpgsql;

-- STORED PROCEDURE FOR ADD NEW USER
CREATE OR REPLACE PROCEDURE add_user(username varchar, pwd varchar, email varchar)
	AS $$
	BEGIN
		INSERT INTO users (username, email, pwd) values(username, email, pwd);
    END$$
language plpgsql;

-- STORED PROCEDURE FOR ADD NEW USER_KEYWORD
CREATE OR REPLACE PROCEDURE add_user_keyword(user_id int, keyword_id int) 
	AS $$
	BEGIN 
		INSERT INTO users_keywords (user_id, keyword_id) values(user_id, keyword_id);
	END$$
language plpgsql;

-- STORED PROCEDURE FOR ADD NEW USER_KEYWORD
CREATE OR REPLACE PROCEDURE add_user_stock(user_id int, stock_id int) 
	AS $$
	BEGIN 
		INSERT INTO users_stocks (user_id, stock_id) values(user_id, stock_id);
	END$$
language plpgsql;