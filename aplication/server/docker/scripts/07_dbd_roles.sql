CREATE ROLE dao WITH login PASSWORD 'dao';

GRANT CONNECT ON DATABASE dbd_exam TO dao;

GRANT USAGE ON ALL SEQUENCES IN SCHEMA public TO dao;

GRANT SELECT ON view_users TO dao;
GRANT SELECT ON view_users_stocks TO dao;
GRANT SELECT ON view_users_keywords TO dao;

GRANT INSERT, SELECT ON keywords TO dao;
GRANT INSERT, SELECT ON log_keywords TO dao;
GRANT INSERT, SELECT ON stocks TO dao;
GRANT INSERT, SELECT ON log_stocks TO dao;
GRANT INSERT, SELECT ON users TO dao;
GRANT INSERT, SELECT ON log_users TO dao;
GRANT INSERT, SELECT ON users_keywords TO dao;
GRANT INSERT, SELECT ON users_stocks TO dao;

GRANT ALL ON FUNCTION add_keyword(keyword_name varchar) TO dao;
GRANT ALL ON FUNCTION add_stock(stock_name varchar) TO dao;
GRANT ALL ON FUNCTION add_user(username varchar, pwd varchar, email varchar) TO dao;
GRANT ALL ON PROCEDURE add_user_keyword(user_id int, keyword_id int) TO dao;
GRANT ALL ON PROCEDURE add_user_stock(user_id int, stock_id int) TO dao;
