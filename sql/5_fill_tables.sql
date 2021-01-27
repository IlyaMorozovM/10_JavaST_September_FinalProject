INSERT INTO `users` (
	`id`,
	`login`,
	`pass_hash`,
	`name`,
	`lastname`,
	`email`,
	`role`
) VALUES (
	2,
	"student",
	"e10adc3949ba59abbe56e057f20f883e", /* MD5 хэш пароля "student" 123456*/
	"Student",
	"Student",
	"student@gmail.com",
	2
), (
	3,
	"tutor",
	"e10adc3949ba59abbe56e057f20f883e", /* MD5 хэш пароля "tutor" 123456*/
	"Tutor",
	"Tutor",
	"tutor@gmail.com",
	1
);

INSERT INTO testsdb.tests (id, subject, title) VALUES (1, 1, 'Test1');
INSERT INTO testsdb.tests (id, subject, title) VALUES (2, 2, 'Authors');
INSERT INTO testsdb.tests (id, subject, title) VALUES (3, 2, 'test1');
INSERT INTO testsdb.tests (id, subject, title) VALUES (6, 4, 'Belarus');
INSERT INTO testsdb.tests (id, subject, title) VALUES (7, 5, 'Anatomy');

INSERT INTO testsdb.subjects (id, name) VALUES (1, 'Math');
INSERT INTO testsdb.subjects (id, name) VALUES (2, 'Russian');
INSERT INTO testsdb.subjects (id, name) VALUES (4, 'History');
INSERT INTO testsdb.subjects (id, name) VALUES (5, 'Biology');

INSERT INTO testsdb.results (id, test, student_login, points) VALUES (3, 1, 'vasya', 1);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (4, 6, 'vasya', 2);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (5, 1, 'Dasha', 2);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (6, 1, 'vasya', 0);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (7, 1, 'vasya', 1);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (8, 1, 'vasya', 2);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (9, 7, 'vasya', 0);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (10, 2, 'vasya', 0);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (11, 2, 'vasya', 0);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (12, 1, 'vasya', 2);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (13, 1, 'vasya', 2);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (14, 1, 'vasya', 2);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (15, 1, 'vasya', 2);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (16, 1, 'vasya', 0);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (17, 2, 'vasya', 0);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (18, 7, 'vasya', 0);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (19, 2, 'vasya', 0);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (20, 1, 'vasya', 2);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (21, 1, '<div><p>aa</p></div><div><p>aa</p></div>', 1);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (22, 7, 'vasya', 2);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (23, 7, 'vasya', 2);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (24, 7, 'vasya', 2);
INSERT INTO testsdb.results (id, test, student_login, points) VALUES (25, 7, 'vasya', 1);

INSERT INTO testsdb.questions (id, test, question) VALUES (1, 1, '2 * 2 = ?');
INSERT INTO testsdb.questions (id, test, question) VALUES (2, 1, '3 * 3 = ?');
INSERT INTO testsdb.questions (id, test, question) VALUES (6, 6, 'Year');
INSERT INTO testsdb.questions (id, test, question) VALUES (7, 6, 'storona?');
INSERT INTO testsdb.questions (id, test, question) VALUES (8, 7, 'How much bones in human?');
INSERT INTO testsdb.questions (id, test, question) VALUES (9, 7, 'What is proizvodnaya skin?');
INSERT INTO testsdb.questions (id, test, question) VALUES (12, 2, 'lll');

INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (1, 1, '5', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (2, 1, '6', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (3, 1, '4', 1);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (4, 1, '7', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (5, 2, '10', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (6, 2, '9', 1);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (13, 6, '987', 1);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (14, 6, '1000', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (15, 6, '500', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (16, 7, 'North', 1);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (17, 7, 'South', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (18, 7, 'West', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (19, 7, 'East', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (20, 8, '206', 1);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (21, 8, '5', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (22, 8, '100', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (23, 8, '960', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (24, 9, 'hair', 1);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (25, 9, 'nails', 1);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (26, 9, 'head', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (27, 9, 'claws', 1);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (28, 9, 'eyes', 0);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (30, 12, 'kk', 1);
INSERT INTO testsdb.answers (id, question, answer, is_right) VALUES (31, 12, 'kkkk', 0);
