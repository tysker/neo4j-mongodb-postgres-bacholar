drop trigger if EXISTS keyword_adding_trigger on keywords;
drop trigger if EXISTS stock_adding_trigger on stocks;
drop trigger if EXISTS user_adding_trigger on users;

-- LOG FUNCTIONS TIL BRUG I TRIGGERS
CREATE OR REPLACE FUNCTION keyword_adding_log()
  RETURNS trigger AS
$$
BEGIN
    INSERT INTO "log_keywords"("id", "keyword", "db_username", "adding_time")
    VALUES(new."id",NEW."keyword",current_user,current_date);
RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION stock_adding_log()
  RETURNS trigger AS
$$
BEGIN
    INSERT INTO "log_stocks"("id", "stockname", "db_username", "adding_time")
    VALUES(NEW."id",NEW."stockname",current_user,current_date);
RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION user_adding_log()
  RETURNS trigger AS
$$
BEGIN
    INSERT INTO "log_users"("id", "username", "email", "db_username", "adding_time")
    VALUES(NEW."id",NEW."username",NEW."email",current_user,current_date);
RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';


-- TRIGGERS
CREATE TRIGGER keyword_adding_trigger
  AFTER INSERT
  ON "keywords"
  FOR EACH ROW
  EXECUTE PROCEDURE keyword_adding_log();
 
CREATE TRIGGER stock_adding_trigger
  AFTER INSERT
  ON "stocks"
  FOR EACH ROW
  EXECUTE PROCEDURE stock_adding_log();

CREATE TRIGGER user_adding_trigger
  AFTER INSERT
  ON "users"
  FOR EACH ROW
  EXECUTE PROCEDURE user_adding_log();



