DROP VIEW IF EXISTS view_users;
DROP VIEW IF EXISTS view_users_stocks;
DROP VIEW IF EXISTS view_users_keywords;
DROP VIEW IF EXISTS view_users_keywords_stocks;

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

CREATE OR REPLACE VIEW view_users_keywords_stocks AS 
SELECT user_id, keyword_id, stock_id, keyword, stockname, username, email, pwd 
FROM users_keywords_stocks 
join keywords on users_keywords_stocks.keyword_id = keywords.id 
join stocks on users_keywords_stocks.stock_id = stocks.id
join users on users_keywords_stocks.user_id = users.id;

