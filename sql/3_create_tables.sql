create table roles
(
    id   int auto_increment,
    name varchar(10) not null,
    constraint id_UNIQUE
        unique (id),
    constraint name_UNIQUE
        unique (name)
)
    collate = utf8_bin;

alter table roles
    add primary key (id);

create table subjects
(
    id   int auto_increment,
    name varchar(50) not null,
    constraint id_UNIQUE
        unique (id)
);

alter table subjects
    add primary key (id);

create table tests
(
    id         int auto_increment,
    subject_id int         not null,
    title      varchar(50) not null,
    constraint id_UNIQUE
        unique (id),
    constraint subject
        foreign key (subject_id) references subjects (id)
            on delete cascade
);

create index subject_idx
    on tests (subject_id);

alter table tests
    add primary key (id);

create table questions
(
    id       int auto_increment,
    test_id  int          not null,
    question varchar(500) not null,
    constraint id_UNIQUE
        unique (id),
    constraint test
        foreign key (test_id) references tests (id)
            on delete cascade
);

create index test_idx
    on questions (test_id);

alter table questions
    add primary key (id);

create table answers
(
    id          int auto_increment,
    question_id int          not null,
    answer      varchar(255) not null,
    is_right    tinyint      not null,
    constraint id_UNIQUE
        unique (id),
    constraint question
        foreign key (question_id) references questions (id)
            on delete cascade
);

create index question_idx
    on answers (question_id);

alter table answers
    add primary key (id);

create table users
(
    id        int auto_increment,
    login     varchar(50)  not null,
    pass_hash varchar(255) not null,
    name      varchar(50)  not null,
    lastname  varchar(50)  not null,
    email     varchar(50)  not null,
    role      int          not null,
    constraint id_UNIQUE
        unique (id),
    constraint login_UNIQUE
        unique (login),
    constraint users_email_uindex
        unique (email),
    constraint role
        foreign key (role) references roles (id)
);

create table results
(
    id            int auto_increment
        primary key,
    test_id       int         not null,
    student_login varchar(50) not null,
    points        int         not null,
    constraint results_tests_id_fk
        foreign key (test_id) references tests (id),
    constraint results_users_login_fk
        foreign key (student_login) references users (login)
);

create index role_idx
    on users (role);

alter table users
    add primary key (id);

