CREATE DATABASE `testsdb` DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `testsdb`.*
TO testsdb_user@localhost
IDENTIFIED BY 'tests_password';

GRANT SELECT,INSERT,UPDATE,DELETE
ON `testsdb`.*
TO testsdb_user@'%'
IDENTIFIED BY 'tests_password';