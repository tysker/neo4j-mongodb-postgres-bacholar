select add_user('username1', 'pwd1', 'email1');
select add_user('username2', 'pwd2', 'email2');
select add_user('username3', 'pwd3', 'email3');

SELECT add_keyword('keyword1');
SELECT add_keyword('keyword2');
SELECT add_keyword('keyword3');

SELECT add_stock('stock1');
SELECT add_stock('stock2');
SELECT add_stock('stock3');

insert into users_keywords values (1,1);
insert into users_keywords values (1,2);
insert into users_keywords values (1,3);

insert into users_keywords values (2,1);
insert into users_keywords values (2,2);
insert into users_keywords values (2,3);

insert into users_keywords values (3,2);
insert into users_keywords values (3,3);

insert into users_stocks values (1,1);
insert into users_stocks values (1,2);

insert into users_stocks values (2,2);
insert into users_stocks values (2,3);

insert into users_stocks values (3,3);

--USER ID, KEYWORD ID, STOCK ID
insert into users_keywords_stocks values (1,1,1);
insert into users_keywords_stocks values (1,3,1);

insert into users_keywords_stocks values (2,2,1);

insert into users_keywords_stocks values (3,1,1);
insert into users_keywords_stocks values (3,2,1);
insert into users_keywords_stocks values (3,2,2);
insert into users_keywords_stocks values (3,3,2);
insert into users_keywords_stocks values (3,1,3);
insert into users_keywords_stocks values (3,3,3);