CREATE ROLE dao WITH login PASSWORD 'dao';

GRANT CONNECT ON DATABASE postgres TO dao;

GRANT USAGE ON ALL SEQUENCES IN SCHEMA public TO dao;

GRANT SELECT ON view_users TO dao;
GRANT SELECT ON view_users_stocks TO dao;
GRANT SELECT ON view_users_keywords TO dao;
GRANT SELECT ON view_users_keywords_stocks TO dao;

GRANT INSERT, SELECT ON keywords TO dao;
GRANT INSERT, SELECT ON log_keywords TO dao;
GRANT INSERT, SELECT ON stocks TO dao;
GRANT INSERT, SELECT ON log_stocks TO dao;
GRANT INSERT, SELECT ON users TO dao;
GRANT INSERT, SELECT ON log_users TO dao;
GRANT INSERT, SELECT ON users_keywords TO dao;
GRANT INSERT, SELECT ON users_stocks TO dao;
GRANT INSERT, SELECT ON users_keywords_stocks TO dao;

GRANT ALL ON FUNCTION add_keyword(keyword_name varchar) TO dao;
GRANT ALL ON FUNCTION add_stock(stock_name varchar) TO dao;
GRANT ALL ON FUNCTION add_user(username varchar, pwd varchar, email varchar) TO dao;

GRANT ALL ON FUNCTION apply_stock(_email varchar, _stockname varchar) TO dao;
GRANT ALL ON FUNCTION apply_keyword(_email varchar, _keyword varchar) TO dao;
GRANT ALL ON FUNCTION apply_keyword_stock(_userId int, _keywordName varchar, _stockName varchar) TO dao;

GRANT ALL ON PROCEDURE add_user_keyword(user_id int, keyword_id int) TO dao;
GRANT ALL ON PROCEDURE add_user_keyword_stock(user_id int, keyword_id int, stock_id int) TO dao;
GRANT ALL ON PROCEDURE add_user_stock(user_id int, stock_id int) TO dao;
