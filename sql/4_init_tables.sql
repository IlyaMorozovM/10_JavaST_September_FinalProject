INSERT INTO `users` (
	`id`,
	`login`,
	`pass_hash`,
	`name`,
	`lastname`,
	`email`,
	`role`
) VALUES (
	1,
	"admin",
	"e10adc3949ba59abbe56e057f20f883e", /* MD5 хэш пароля "admin" 123456*/
	"Admin",
	"Admin",
	"admin@gmail.com",
	3
);