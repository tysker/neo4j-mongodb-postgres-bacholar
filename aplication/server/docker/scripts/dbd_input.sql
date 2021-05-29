call add_keyword('keyword1');
call add_keyword('keyword2');
call add_keyword('keyword3');

call add_stock('stock1');
call add_stock('stock2');
call add_stock('stock3');

call add_user('username1', 'pwd1', 'email1');
call add_user('username2', 'pwd2', 'email2');
call add_user('username3', 'pwd3', 'email3');


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
