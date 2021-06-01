drop procedure if exists add_user_keyword;
drop procedure if exists add_user_stock;
drop procedure if exists add_user_keyword_stock;

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

-- STORED PROCEDURE FOR ADD NEW USER_KEYWORD AND STOCK
CREATE OR REPLACE PROCEDURE add_user_keyword_stock(user_id int, keyword_id int, stock_id int) 
	AS $$
	BEGIN 
		INSERT INTO users_keywords_stocks (user_id, keyword_id, stock_id) values(user_id, keyword_id, stock_id);
	END$$
language plpgsql;