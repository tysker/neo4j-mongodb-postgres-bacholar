-- QUERY FOR KEYWORDS TABLE
select * from keywords;

select * from log_keywords;

call add_keyword('Hej1');

-- QUERY FOR STOCKS TABLE
select * from stocks;

select * from log_stocks;

call add_stock('Stock1');

-- QUERY FOR USERS TABLE
select * from users;

select * from log_users;

call add_user('username1', 'pwd1', 'email1');

select * from view_users;

-- QUERY FOR USERS_KEYWORDS TABLE
insert into users_keywords values (1,1);
select * from view_users_keywords;

-- QUERY FOR USERS_STOCKS TABLE
insert into users_stocks values (1,1);
select * from view_users_stocks;

