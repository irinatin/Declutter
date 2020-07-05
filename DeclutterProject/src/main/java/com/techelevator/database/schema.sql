BEGIN;

DROP TABLE if exists users CASCADE;
DROP TABLE if exists stuff CASCADE;
DROP TABLE if exists family CASCADE;
DROP TABLE if exists user_info CASCADE;

 CREATE TABLE family (
 family_id serial NOT NULL,
 family_name varchar(255) NOT NULL,
 
 CONSTRAINT pk_family_id PRIMARY KEY(family_id)
 
 );

 
CREATE TABLE users (
 id serial PRIMARY KEY,
 username varchar(255) NOT NULL UNIQUE,
 password varchar(32) NOT NULL,
 salt varchar(256) NOT NULL
 
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
 
 CREATE TABLE stuff (
 stuff_id serial primary key,
 item varchar(255) NOT NULL,
 
CONSTRAINT pk_stuff_id PRIMARY KEY (stuff_id)
 --add foregn keys for category id and family id
 );
 

 

 
--ALTER table stuff
--ADD column 

COMMIT;