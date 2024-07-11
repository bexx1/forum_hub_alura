create table topics (
    id bigint not null auto_increment primary key,
    title varchar(100) not null unique,
    message varchar(255) not null unique,
    creation_date timestamp not null,
    status varchar(20)
);