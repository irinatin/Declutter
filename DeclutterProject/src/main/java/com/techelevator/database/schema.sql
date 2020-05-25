BEGIN;

DROP TABLE if exists users;


CREATE TABLE users (
 id serial primary key,
 username varchar(255) NOT NULL UNIQUE,
 password varchar(32) NOT NULL,
 salt varchar(256) NOT NULL
 
 );

COMMIT;