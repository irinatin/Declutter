BEGIN;

DROP TABLE if exists users;
DROP TABLE if exists stuff;
DROP TABLE if exists family;


CREATE TABLE users (
 id serial primary key,
 username varchar(255) NOT NULL UNIQUE,
 password varchar(32) NOT NULL,
 salt varchar(256) NOT NULL
 
 );
 
 CREATE TABLE stuff (
 stuff_id serial primary key,
 item varchar(255) NOT NULL
 --add foregn keys for category id and family id
 );
 
 CREATE TABLE family (
 family_id serial NOT NULL,
 family_name varchar(255) NOT NULL,
 
 CONSTRAINT pk_family_id PRIMARY KEY(family_id)
 
 );

COMMIT;