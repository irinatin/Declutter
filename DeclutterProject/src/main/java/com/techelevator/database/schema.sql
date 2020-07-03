BEGIN;

DROP TABLE if exists users;
DROP TABLE if exists stuff;
DROP TABLE if exists family;
DROP TABLE if exists user_info;


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
 
 CREATE TABLE user_info (
  user_info_id serial PRIMARY KEY,
  user_id int NOT NULL,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) NOT NULL,
  family_id int NOT NULL,

  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT fk_family_id FOREIGN KEY (family_id) REFERENCES family (family_id)
);
 
 

COMMIT;