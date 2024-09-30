BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS emails;
DROP TABLE IF EXISTS bands;
DROP TABLE IF EXISTS user_band;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS band_genre;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS events;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

--CREATE TABLE emails (
--    email_id SERIAL,
--    email varchar(50) NOT NULL UNIQUE,
--    email_user int NOT NULL,
--    CONSTRAINT FK_email_user FOREIGN KEY (email_user) REFERENCES users(user_id)
--);

CREATE TABLE bands (
    band_id SERIAL,
    band_name varchar(50) NOT NULL UNIQUE,
    band_description varchar(10000) NOT NULL,
    band_manager_id int NOT NULL,
    band_hero_image varchar(500) NOT NULL,
    CONSTRAINT PK_band PRIMARY KEY (band_id),
    CONSTRAINT FK_band_manager FOREIGN KEY (band_manager_id) REFERENCES users(user_id)
);

CREATE TABLE images (
    image_id SERIAL,
    band_id int NOT NULL,
    image_link VARCHAR(500) NOT NULL,
    CONSTRAINT PK_image PRIMARY KEY (image_id),
    CONSTRAINT FK_image_band FOREIGN KEY (band_id) REFERENCES bands(band_id)
);

CREATE TABLE user_band (
    user_id int NOT NULL,
    band_id int NOT NULL,
    CONSTRAINT FK_user_band_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_user_band_band FOREIGN KEY (band_id) REFERENCES bands(band_id)
);

CREATE TABLE genres (
    genre_id SERIAL,
    genre_name VARCHAR(20) NOT NULL UNIQUE,
    CONSTRAINT PK_genre PRIMARY KEY (genre_id)
);

CREATE TABLE band_genre (
    band_id int NOT NULL,
    genre_id int NOT NULL,
    CONSTRAINT FK_band_genre_band FOREIGN KEY (band_id) REFERENCES bands(band_id),
    CONSTRAINT FK_band_genre_genre FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);

CREATE TABLE messages (
    message_id SERIAL,
    message_content varchar(200) NOT NULL,
    message_time timestamp NOT NULL,
    message_sender int NOT NULL,
    CONSTRAINT FK_message_sender FOREIGN KEY (message_sender) REFERENCES bands(band_id)
);

CREATE TABLE events (
    event_id SERIAL,
    event_name varchar(100) NOT NULL,
    event_time timestamp NOT NULL,
    event_address varchar(100) NOT NULL,
    event_host int NOT NULL,
    CONSTRAINT FK_event_host FOREIGN KEY (event_host) REFERENCES bands(band_id)
);

COMMIT TRANSACTION;
