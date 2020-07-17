-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

INSERT INTO family
VALUES (DEFAULT, 'TE Fam');


INSERT INTO users
VALUES (DEFAULT, 'BethCampbell', 'z1HHvE71QP5LOfdDT1TjTg==', 'T876nbvOrAo4NgFJBi2wtrnlXmik4WshKHzeey0DEAA/dvG2hqGXYg9DPHBgohYfZob3O0+d47A6vKM86eHGi6irgF3AQzGgLbbkVdBTWbiLon6ZONBqb5RE5CKvUeMdlyNfY1FR6Nfl4E3hEgv0MZ7UqWOMOn8qqD2072LqlkE=', 'user'),
(DEFAULT, 'TomAnderson', 'z1HHvE71QP5LOfdDT1TjTg==', 'T876nbvOrAo4NgFJBi2wtrnlXmik4WshKHzeey0DEAA/dvG2hqGXYg9DPHBgohYfZob3O0+d47A6vKM86eHGi6irgF3AQzGgLbbkVdBTWbiLon6ZONBqb5RE5CKvUeMdlyNfY1FR6Nfl4E3hEgv0MZ7UqWOMOn8qqD2072LqlkE=', 'user'),
(DEFAULT, 'TomMedvitz', 'z1HHvE71QP5LOfdDT1TjTg==', 'T876nbvOrAo4NgFJBi2wtrnlXmik4WshKHzeey0DEAA/dvG2hqGXYg9DPHBgohYfZob3O0+d47A6vKM86eHGi6irgF3AQzGgLbbkVdBTWbiLon6ZONBqb5RE5CKvUeMdlyNfY1FR6Nfl4E3hEgv0MZ7UqWOMOn8qqD2072LqlkE=', 'child'),
(DEFAULT, 'WaltImpellicceiri', 'z1HHvE71QP5LOfdDT1TjTg==', 'T876nbvOrAo4NgFJBi2wtrnlXmik4WshKHzeey0DEAA/dvG2hqGXYg9DPHBgohYfZob3O0+d47A6vKM86eHGi6irgF3AQzGgLbbkVdBTWbiLon6ZONBqb5RE5CKvUeMdlyNfY1FR6Nfl4E3hEgv0MZ7UqWOMOn8qqD2072LqlkE=', 'user');



INSERT INTO user_info 
VALUES (DEFAULT, (SELECT id FROM users WHERE username = 'BethCampbell'), 'Beth', 'Campbell', (SELECT family_id FROM family WHERE family_name = 'TE Fam')),
(DEFAULT, (SELECT id FROM users WHERE username = 'TomAnderson'), 'Tom', 'Anderson', (SELECT family_id FROM family WHERE family_name = 'TE Fam')),
(DEFAULT, (SELECT id FROM users WHERE username = 'TomMedvitz'), 'Tom', 'Medvitz', (SELECT family_id FROM family WHERE family_name = 'TE Fam')),
(DEFAULT, (SELECT id FROM users WHERE username = 'WaltImpellicceiri'), 'Walt', 'Impellicceiri', (SELECT family_id FROM family WHERE family_name = 'TE Fam'));


INSERT INTO book
VALUES (DEFAULT, '1234567890', 'Java for Dummies', 'Beth Campbell', (SELECT family_id FROM family WHERE family_name = 'TE Fam')),
(DEFAULT, '1234567891', 'C Sharp for Dummies', 'Tom Anderson', (SELECT family_id FROM family WHERE family_name = 'TE Fam')),
(DEFAULT, '1234567892', 'CSS for Dummies', 'Leigh Hartmann', (SELECT family_id FROM family WHERE family_name = 'TE Fam'));



INSERT INTO user_book
VALUES (DEFAULT, (SELECT id FROM users WHERE username = 'BethCampbell'), (SELECT book_id FROM book WHERE title = 'CSS for Dummies'), '180', '2020-04-14', 'Digital/Kindle', true),
(DEFAULT, (SELECT id FROM users WHERE username = 'TomAnderson'), (SELECT book_id FROM book WHERE title = 'CSS for Dummies'), '180', '2020-04-14', 'Digital/Kindle', true),
(DEFAULT, (SELECT id FROM users WHERE username = 'TomMedvitz'), (SELECT book_id FROM book WHERE title = 'Java for Dummies'), '60', '2020-04-14', 'Digital/Kindle', false),
(DEFAULT, (SELECT id FROM users WHERE username = 'TomMedvitz'), (SELECT book_id FROM book WHERE title = 'C Sharp for Dummies'), '60', '2020-04-14', 'Digital/Kindle', false),
(DEFAULT, (SELECT id FROM users WHERE username = 'TomMedvitz'), (SELECT book_id FROM book WHERE title = 'CSS for Dummies'), '60', '2020-04-14', 'Digital/Kindle', false);

INSERT INTO prize
VALUES (DEFAULT, 'Booze', 'Whatever you want', '120', 'user', '10', '2020-01-01', '2020-12-31', (SELECT family_id FROM family WHERE family_name = 'TE Fam')),
(DEFAULT, 'Video Games', 'An hour of video games', '120', 'child', '10', '2020-01-01', '2020-12-31', (SELECT family_id FROM family WHERE family_name = 'TE Fam')),
(DEFAULT, 'Candy', 'YAY Candy!!!', '30', 'child', '10', '2020-01-01', '2020-12-31', (SELECT family_id FROM family WHERE family_name = 'TE Fam'));


INSERT INTO user_prize
VALUES((SELECT prize_id FROM prize WHERE prize_name = 'Booze'), (SELECT id FROM users WHERE username = 'BethCampbell')),
((SELECT prize_id FROM prize WHERE prize_name = 'Booze'), (SELECT id FROM users WHERE username = 'TomAnderson')),
((SELECT prize_id FROM prize WHERE prize_name = 'Video Games'), (SELECT id FROM users WHERE username = 'TomMedvitz'));


COMMIT;