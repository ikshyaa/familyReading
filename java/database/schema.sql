BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE family (
    family_id serial NOT NULL,
    family_name varchar(50) unique NOT NULL,
    CONSTRAINT PK_family PRIMARY KEY (family_id)
);

CREATE TABLE family_member (
    user_id int NOT NULL,
    family_id int NOT NULL,
    is_Parent boolean NOT NULL,
    CONSTRAINT PK_family_member PRIMARY KEY (user_id),
    CONSTRAINT FK_family_member_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_family_member_family FOREIGN KEY (family_id) REFERENCES family (family_id)
);

CREATE TABLE book (
    book_id serial NOT NULL,
    title varchar(50) NOT NULL,
    author varchar(100) NOT NULL,
    isbn varchar(50),
    CONSTRAINT PK_book PRIMARY KEY (book_id)
);

CREATE TABLE user_book (
    book_id int NOT NULL,
    user_id int NOT NULL,
    CONSTRAINT PK_book_log PRIMARY KEY (book_id, user_id),
    CONSTRAINT FK_book_log_id FOREIGN KEY (book_id)  REFERENCES book (book_id),
    CONSTRAINT FK_book_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE reading_log (
    readingLog_id serial NOT NULL,
    minutes int  NOT NULL,
    format varchar(50)  NOT NULL,
    date date NOT NULL ,
    starting_page int,
    end_page int,
    book_id int NOT NULL,
    user_id int NOT NULL,
    notes varchar(280),
    CONSTRAINT PK_reading_log_id PRIMARY KEY (readingLog_id),
    CONSTRAINT FK_book_reading_log_id FOREIGN KEY (book_id)  REFERENCES book (book_id),
    CONSTRAINT FK_book_reading_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE prize (
    prize_id serial NOT NULL,
    prize_name VARCHAR(50) NOT NULL,
    description VARCHAR (280) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    milestone INT NOT NULL,
    isActive boolean NOT NULL,
    numberOfWinners INT NOT NULL,
    CONSTRAINT PK_prize PRIMARY KEY (prize_id)
);

CREATE TABLE prize_user (
    prize_id INT NOT NULL,
    user_id INT NOT NULL,
    reachedGoal boolean not null,
    reachedDate date,
    CONSTRAINT pk_prize_user PRIMARY KEY (
        prize_id, user_id
    ),
    CONSTRAINT fk_prize_user_prize FOREIGN KEY (prize_id) REFERENCES prize(prize_id),
    CONSTRAINT fk_prize_user_reading_log FOREIGN KEY (user_id) REFERENCES family_member(user_id)

);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO users (username,password_hash,role) VALUES ('Parent','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Sam','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Lakshmi','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Cymanthia','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Julia','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO family(family_name) VALUES('My Family');

INSERT INTO family_member(user_id,family_id,is_Parent) VALUES(3,1,true);
INSERT INTO family_member(user_id,family_id,is_Parent) VALUES(4,1,true);
INSERT INTO family_member(user_id,family_id,is_Parent) VALUES(5,1,false);
INSERT INTO family_member(user_id,family_id,is_Parent) VALUES(6,1,false);
INSERT INTO family_member(user_id,family_id,is_Parent) VALUES(7,1,false);

INSERT INTO book (title, author, isbn)
VALUES('Curious George My Favorite Things', 'Rey, H. A.', '9780547428932');
INSERT INTO book (title, author, isbn)
VALUES('Dino Duckling',  'Murray, Alison', '9780316513159');
INSERT INTO book (title, author, isbn)
VALUES('Chugga-Chugga Choo-Choo', 'Lewis, Kevin Kirk', '9780786804290');
INSERT INTO book (title, author, isbn)
VALUES('If Animals Kissed Good Night'  ,'Paul, Ann Whitford Walker' ,'9780374300210');
INSERT INTO book (title, author, isbn)
VALUES('I Love You, Little Pookie' , 'Boynton, Sandra', '9781534437234');
INSERT INTO book (title, author, isbn)
VALUES('Mac And Cheese', 'Weeks, Sarah' , '9780061170812');
INSERT INTO book (title, author, isbn)
VALUES('Numbers, Shapes and Colors', 'Priddy, Roger', '9780312510817');
INSERT INTO book (title, author, isbn)
VALUES('The Pout-Pout Fish' ,'Diesen, Deborah', '9780374360979');
INSERT INTO book (title, author, isbn)
VALUES('Its Only One!', 'Corderoy, Neal', '9781680102277');
INSERT INTO book (title, author, isbn)
VALUES('The Little Prince', 'Exupery, Antoine De', '9780156012195');
INSERT INTO book (title, author, isbn)
VALUES('Biscuit Goes To School', 'Capucilli, Alyssa Satin' ,'9780064436168');
INSERT INTO book (title, author, isbn)
VALUES('Big Red Barn', 'Brown, Margaret Wise' ,'9780694006243');
INSERT INTO book (title, author, isbn)
VALUES('Geography From A To Z' , 'Knowlton, Jack' , '9780064460996');
INSERT INTO book (title, author, isbn)
VALUES('Tractors and Things That Go' , 'Greening, Rosie' ,'9781783932528');
INSERT INTO book (title, author, isbn)
VALUES('Strike Three, Marley!' , 'Grogan, John' , '9780061853869');


INSERT INTO user_book(user_id,book_id) VALUES (4,1);
INSERT INTO user_book(user_id,book_id) VALUES (5,1);
INSERT INTO user_book(user_id,book_id) VALUES (5,2);
INSERT INTO user_book(user_id,book_id) VALUES (6,2);
INSERT INTO user_book(user_id,book_id) VALUES (6,4);
INSERT INTO user_book(user_id,book_id) VALUES (7,3);
INSERT INTO user_book(user_id,book_id) VALUES (7,5);

-- sam's logs
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(1,4,30,'Paper','6-4-22',1,30,'listening');
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(1,4,45,'Paper','6-9-22',1,30,'listening');
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(1,4,30,'Paper','6-16-22',1,30,'listening');

-- Lakshmi's logs
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(1,5,15,'Paper','6-4-22',1,30,'my first log!');
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(2,5,30,'Paper','6-7-22',1,30,'listening');
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(2,5,60,'Paper','6-15-22',1,30,'lots of reading');

-- Cymanthia's logs
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(2,6,45,'Paper','6-9-22',1,30,'a good book');
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(4,6,15,'Paper','6-2-22',1,30,'a little more reading today');
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(4,6,30,'Paper','6-17-22',1,30,'another log');

-- Julia's logs
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(3,7,30,'Paper','6-8-22',1,30,'listening');
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(5,7,30,'Paper','6-3-22',1,30,'listening');
INSERT INTO reading_log(book_id, user_id, minutes,format, date, starting_page, end_page, notes)
VALUES(5,7,60,'Paper','6-14-22',1,30,'a little more reading today');

-- prizes
INSERT INTO prize (prize_name, description, start_date, end_date, milestone, isActive, numberOfWinners)
VALUES ('My First Prize', 'the very first prize', '2022-06-05', '2022-06-11', 30, true, 2);
INSERT INTO prize (prize_name, description, start_date, end_date, milestone, isActive, numberOfWinners)
VALUES ('My Second Prize', 'the second prize', '2022-06-12', '2022-06-18', 45, true, 2);

-- prize_user
INSERT INTO prize_user (prize_id, user_id, reachedGoal)
VALUES (1, 3, false);
INSERT INTO prize_user (prize_id, user_id, reachedGoal)
VALUES (1, 4, false);
INSERT INTO prize_user (prize_id, user_id, reachedGoal)
VALUES (1, 5, false);
INSERT INTO prize_user (prize_id, user_id, reachedGoal)
VALUES (1, 6, false);
INSERT INTO prize_user (prize_id, user_id, reachedGoal)
VALUES (1, 7, false);

INSERT INTO prize_user (prize_id, user_id, reachedGoal)
VALUES (2, 3, false);
INSERT INTO prize_user (prize_id, user_id, reachedGoal)
VALUES (2, 4, false);
INSERT INTO prize_user (prize_id, user_id, reachedGoal)
VALUES (2, 5, false);
INSERT INTO prize_user (prize_id, user_id, reachedGoal)
VALUES (2, 6, false);
INSERT INTO prize_user (prize_id, user_id, reachedGoal)
VALUES (2, 7, false);


COMMIT TRANSACTION;
