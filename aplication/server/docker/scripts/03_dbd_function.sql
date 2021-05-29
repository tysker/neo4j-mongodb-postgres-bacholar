drop function if exists add_keyword;
drop function if exists add_stock;
drop function if exists add_user;

-- Function FOR ADD NEW KEYWORD
CREATE OR REPLACE FUNCTION add_keyword(keyword_name varchar)
    RETURNS INTEGER
	AS $$
    DECLARE
    newId INTEGER;
	BEGIN 
		INSERT INTO keywords (keyword) values(keyword_name) returning id into newid;
	RETURN newId;
	END$$
language plpgsql;

-- Function FOR ADD NEW STOCK
CREATE OR REPLACE FUNCTION add_stock(stock_name varchar)
    RETURNS INTEGER
	AS $$
    DECLARE
    newId INTEGER;
	BEGIN 
		INSERT INTO stocks (stockname) values(stock_name) returning id into newid;
    RETURN newId;
	END$$
language plpgsql;

-- Function FOR ADD NEW USER
CREATE OR REPLACE FUNCTION add_user(username varchar, pwd varchar, email varchar) 
	RETURNS INTEGER
	AS $$
	DECLARE
	newId INTEGER;
	BEGIN 
		INSERT INTO users (username, pwd, email) values(username, pwd, email) returning id into newid;
	RETURN newId;
	END$$
language plpgsql;