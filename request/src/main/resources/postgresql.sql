CREATE DATABASE db_request; -- Creates the new database
CREATE USER springuser WITH PASSWORD 'ThePassword'; -- Creates the user
GRANT ALL PRIVILEGES ON DATABASE db_request TO springuser; -- Gives all privileges to the new user on the newly created database
\c db_request;
CREATE TABLE usr (user_id bigserial PRIMARY KEY, username varchar(20) NOT NULL, password varchar(20) NOT NULL, active boolean);
ALTER TABLE usr OWNER TO springuser;
INSERT INTO usr (username, password, active) VALUES ('user1', 'pass1', true);
INSERT INTO usr (username, password, active) VALUES ('user2', 'pass2', true);
INSERT INTO usr (username, password, active) VALUES ('user3', 'pass3', true);

CREATE TABLE user_role (user_id bigint NOT NULL, roles varchar(20) NOT NULL, FOREIGN KEY (user_id) REFERENCES usr(user_id) ON DELETE CASCADE);
ALTER TABLE user_role OWNER TO springuser;
INSERT INTO user_role (user_id, roles) VALUES (1, 'ADMIN');
INSERT INTO user_role (user_id, roles) VALUES (2, 'USER');
INSERT INTO user_role (user_id, roles) VALUES (3, 'OPERATOR');






