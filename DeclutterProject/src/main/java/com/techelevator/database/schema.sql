BEGIN;

DROP TABLE if exists users;
DROP TABLE if exists stuff;


CREATE TABLE users (
 id serial primary key,
 username varchar(255) NOT NULL UNIQUE,
 password varchar(32) NOT NULL,
 salt varchar(256) NOT NULL
 
 );
 
 CREATE TABLE stuff (
 id serial primary key,
 item varchar(255) NOT NULL
 --add foregn keys for category id and family id
 );
 

COMMIT;