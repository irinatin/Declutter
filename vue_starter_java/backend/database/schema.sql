
-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here

DROP TABLE IF EXISTS user_prize;
DROP TABLE IF EXISTS prize;
DROP TABLE IF EXISTS user_book;
DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS user_info;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS family;



CREATE TABLE family (
  family_id serial NOT NULL, 
  family_name varchar(100) NOT NULL,

  CONSTRAINT pk_family_id PRIMARY KEY(family_id)
);


CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,
  password varchar(32) NOT NULL, 
  salt varchar(256) NOT NULL,   
  role varchar(255) NOT NULL default('user')
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


CREATE TABLE friends (
  user_id int NOT NULL,
  friend_id int NOT NULL,
  
  CONSTRAINT fk_friend_id FOREIGN KEY (friend_id) REFERENCES users (id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE book (
  book_id serial NOT NULL,
  isbn BIGINT,
  title varchar(100) NOT NULL,
  author varchar(100) NOT NULL,
  family_id int NOT NULL, 

  CONSTRAINT pk_book_id PRIMARY KEY(book_id),
  CONSTRAINT fk_family_id FOREIGN KEY (family_id) REFERENCES family (family_id),
  CONSTRAINT UC_book UNIQUE (isbn, family_id)
);

CREATE TABLE user_book (
  reading_event_id serial NOT NULL,
  user_id int NOT NULL,
  book_id int NOT NULL,
  reading_time int NOT NULL,
  reading_date date NOT NULL,
  reading_format varchar(100) NOT NULL,
  completed boolean NOT NULL,

  CONSTRAINT pk_reading_event_id PRIMARY KEY(reading_event_id),
  CONSTRAINT fk_id FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES book (book_id)
);

CREATE TABLE prize (
  prize_id serial NOT NULL,
  prize_name varchar(100) NOT NULL,
  prize_description varchar(100) NOT NULL,
  milestone varchar(100) NOT NULL,
  user_group varchar(100) NOT NULL,
  max_prizes int NOT NULL,
  start_date date,
  end_date date,
  family_id int NOT NULL,

  CONSTRAINT pk_prize_id PRIMARY KEY(prize_id),
  CONSTRAINT fk_family_id FOREIGN KEY (family_id) REFERENCES family (family_id) 
);

CREATE TABLE user_prize (
  prize_id int NOT NULL,
  user_id int NOT NULL,
  award_date DATE NULL,

CONSTRAINT fk_prize_id FOREIGN KEY (prize_id) REFERENCES prize (prize_id),
CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
CONSTRAINT UC_user_prize UNIQUE (prize_id, user_id)
);

COMMIT;