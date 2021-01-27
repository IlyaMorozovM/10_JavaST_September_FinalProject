CREATE DATABASE `testsdb` DEFAULT CHARACTER SET utf8;

CREATE USER testsdb_user@localhost IDENTIFIED BY 'tests_password';
CREATE USER testsdb_user@'%' IDENTIFIED BY 'tests_password';

GRANT SELECT,INSERT,UPDATE,DELETE ON `testsdb`.* TO testsdb_user@localhost;
GRANT SELECT,INSERT,UPDATE,DELETE ON `testsdb`.* TO testsdb_user@'%';