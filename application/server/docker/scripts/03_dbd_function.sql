drop function if exists add_keyword;
drop function if exists add_stock;
drop function if exists add_user;
drop function if exists apply_keyword_stock;

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

-- Function for applying stock if not exists
CREATE OR REPLACE FUNCTION apply_stock(_email varchar, _stockname varchar) 
RETURNS boolean AS $$ 
DECLARE 
	did_insert boolean := false;
	stockId integer;
	userId integer;
BEGIN 
	SELECT id INTO stockId
	FROM stocks s
	WHERE s.stockname = _stockname
	LIMIT 1;

	IF stockId IS NULL THEN 
		INSERT INTO stocks (stockname)
		VALUES (_stockname) 
		RETURNING id INTO stockId;

		did_insert := true;
	END IF;
	
	IF stockId IS NOT NULL THEN
		SELECT id INTO userId 
		FROM users u
		WHERE u.email = _email;

		CALL add_user_stock(userId, stockId);
	END IF;

	RETURN did_insert;
END;
$$ LANGUAGE plpgsql;

-- Function for applying a keyword if not exists
CREATE OR REPLACE FUNCTION apply_keyword(_email varchar, _keyword varchar) 
RETURNS boolean AS $$ 
DECLARE 
	did_insert boolean := false;
	keywordId integer;
	userId integer;
BEGIN 
	SELECT id INTO keywordId
	FROM keywords k
	WHERE k.keyword = _keyword
	LIMIT 1;

	IF keywordId IS NULL THEN 
		INSERT INTO keywords (keyword)
		VALUES (_keyword) 
		RETURNING id INTO keywordId;

		did_insert := true;
	END IF;
	
	IF keywordId IS NOT NULL THEN
		SELECT id INTO userId 
		FROM users u
		WHERE u.email = _email;

		CALL add_user_keyword(userId, keywordId);
	END IF;

	RETURN did_insert;
END;
$$ LANGUAGE plpgsql;

-- Function for applying a keyword if not exists
CREATE OR REPLACE FUNCTION apply_keyword_stock(_userId int, _keywordName varchar, _stockName varchar) 
RETURNS boolean AS $$ 
DECLARE 
	did_insert boolean := false;
	keywordId integer;
	userId integer :=_userId;
	stockId integer;
BEGIN 
	SELECT id INTO keywordId
	FROM keywords k
	WHERE k.keyword = _keywordName
	LIMIT 1;

	IF keywordId IS NULL THEN 
		INSERT INTO keywords (keyword)
		VALUES (_keywordName) 
		RETURNING id INTO keywordId;
		
		did_insert := true;
	END IF;

SELECT id INTO stockId
	FROM stocks s
	WHERE s.stockname = _stockName
	LIMIT 1;

	IF stockId IS NULL THEN 
		INSERT INTO stocks (stockname)
		VALUES (_stockName) 
		RETURNING id INTO stockId;
	
		did_insert := true;
	END IF;

	CALL add_user_keyword_stock(userId, keywordId, stockId);

	RETURN did_insert;
END;
$$ LANGUAGE plpgsql;