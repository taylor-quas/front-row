BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user@example.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin@example.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('drake@drake.com', '5f4dcc3b5aa765d61d8327deb882cf99', 'ROLE_BAND');
INSERT INTO users (username,password_hash,role) VALUES ('rihanna@rihanna.com', '5f4dcc3b5aa765d61d8327deb882cf99', 'ROLE_BAND');
INSERT INTO users (username,password_hash,role) VALUES ('thebeatles@thebeatles.com', '5f4dcc3b5aa765d61d8327deb882cf99', 'ROLE_BAND');
INSERT INTO users (username,password_hash,role) VALUES ('taylorswift@taylorswift.com', '5f4dcc3b5aa765d61d8327deb882cf99', 'ROLE_BAND');

INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Drake', 'Aubrey Drake Graham (born October 24, 1986), known mononymously as Drake, is a Canadian rapper, singer, and actor. An influential figure in popular music, he has been credited with popularizing R&B sensibilities in hip hop artists. Gaining recognition by starring as Jimmy Brooks in the CTV teen drama series Degrassi: The Next Generation (2001–2008), Drake began his recording career in 2006 with the release of his debut mixtape, Room for Improvement (2006). He followed up with the mixtapes Comeback Season (2007) and So Far Gone (2009) before signing with Young Money Entertainment.', 3, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727715299/iwussu1yrp4nlpml4efa_kajbzq.webp');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Rihanna', 'Robyn Rihanna Fenty (/riˈænə/ ⓘ ree-AN-ə; born February 20, 1988) is a Barbadian singer, businesswoman and actress based in the United States. She was cited as the best-selling female recording artist of the 21st century by Guinness World Records and is the wealthiest female musical artist, with an estimated net worth of $1.4 billion as of 2024. Rihanna is the highest-certified female digital single artist and has six U.S. diamond certified singles (seven as a featured artist). She has achieved 14 number-one singles on the Billboard Hot 100. The recipient of various accolades, Rihanna is one of the best-selling recording artists ever, with sales estimated at 250 million units worldwide.', 4, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727715794/Rihanna-Fenty-Hair-Launch-GettyImages-2157052912-H-2024_ocf8ih.webp');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('The Beatles', 'The Beatles were an English rock band formed in Liverpool in 1960. The core lineup of the band comprised John Lennon, Paul McCartney, George Harrison and Ringo Starr. They are widely regarded as the most influential band of all time and were integral to the development of 1960s counterculture and the recognition of popular music as an art form. Rooted in skiffle, beat and 1950s rock n roll, their sound incorporated elements of classical music and traditional pop in innovative ways. The band also explored music styles ranging from folk and Indian music to psychedelia and hard rock. As pioneers in recording, songwriting and artistic presentation, the Beatles revolutionized many aspects of the music industry and were often publicized as leaders of the era youth and sociocultural movements.', 5, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727716027/the-beatles-rehearse-for-that-nights-royal-variety-performance-at-the-prince-of-wales-theatre-4th-november-1963-the-queen-mother-will-attend-photo-by-central-press_hulton-archive_getty-images_uk6oyh.jpg');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Taylor Swift', 'Taylor Alison Swift (born December 13, 1989) is an American singer-songwriter. Known for her autobiographical songwriting, artistic reinventions, and cultural impact, Swift is a leading figure in popular music and the subject of widespread public interest.', 6, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727716174/taylor-swift-red-carpet_hqtg3z.jpg');
INSERT INTO bands (band_name, band_description, band_manager_id, band_hero_image) VALUES ('Morgan Wallen', 'Morgan Cole Wallen (born May 13, 1993) is an American country pop singer. He competed in the sixth season of The Voice, originally as a member of Ushers team, but later as a member of Adam Levines team. After being eliminated in the playoffs of that season, he signed to Panacea Records, releasing his debut EP, Stand Alone, in 2015.', 6, 'https://res.cloudinary.com/dfxvcpj23/image/upload/v1727716353/2203-morgan-wallen.990x660_jk8vmj.jpg');

INSERT INTO user_band (user_id, band_id) VALUES (1, 3), (1, 5), (1, 1);

INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('Hello', '2024-10-01 13:12:00', '2024-10-14 13:12:00', 3);
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('Cheerio, g-day mate,', '2024-10-01 13:12:00', '2024-10-31 13:12:00', 2);
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('Howdy padner', '2024-10-01 13:12:00', '2024-10-11 13:12:00', 3);
INSERT INTO messages (message_content, message_time_sent, message_time_expiration, message_sender) VALUES ('This is an expired message. It should not be visible.', '2024-09-01 13:12:00', '2024-10-01 13:12:00', 3);

INSERT INTO genres (genre_name) VALUES ('blues'), ('classical'), ('country'), ('electronic'), ('folk'), ('hip-hop'), ('jazz'), ('latin'), ('new age'), ('pop'), ('r&b'), ('reggae'), ('rock');

INSERT INTO band_genre (band_id, genre_id) VALUES (1, 6), (2, 6), (2, 10), (3, 5), (3, 10), (3, 13), (4, 3), (4, 5), (4, 10), (5, 3);

COMMIT TRANSACTION;
