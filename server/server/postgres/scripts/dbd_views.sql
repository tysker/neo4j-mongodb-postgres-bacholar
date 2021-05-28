DROP VIEW IF EXISTS view_users;
DROP VIEW IF EXISTS view_users_stocks;
DROP VIEW IF EXISTS view_users_keywords;

CREATE OR REPLACE VIEW view_users AS 
SELECT * FROM users;

CREATE OR REPLACE VIEW view_users_stocks AS 
SELECT user_id, stock_id, stockname, username, email, pwd 
FROM users_stocks 
join stocks on users_stocks.stock_id = stocks.id 
join users on users_stocks.user_id  = users.id;

CREATE OR REPLACE VIEW view_users_keywords AS 
SELECT user_id, keyword_id, keyword, username, email, pwd 
FROM users_keywords 
join keywords on users_keywords.keyword_id = keywords.id 
join users on users_keywords.user_id  = users.id;

/* VIEWS
CREATE OR REPLACE VIEW CATS AS SELECT P.*, C.lifeCount FROM PET_DATA AS P JOIN CAT_DATA as C on P.id = C.id;
CREATE OR REPLACE VIEW DOGS AS SELECT P.*, D.barkPitch FROM PET_DATA AS P JOIN DOG_DATA as D on P.id = D.id;

CREATE OR REPLACE VIEW PETS AS	SELECT P.*, C.lifeCount, D.barkPitch FROM PET_DATA as P 
	LEFT OUTER JOIN CAT_DATA as C on P.id = C.id
	LEFT OUTER JOIN DOG_DATA as D on P.id = D.id; */

