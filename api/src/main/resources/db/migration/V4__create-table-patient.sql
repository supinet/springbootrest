create table patient (
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    uid varchar(14) not null unique,
    street varchar(100) not null,
    neighborhood varchar(100) not null,
    zipcode varchar(9) not null,
    complement varchar(100),
    number varchar(20),
    uf char(2) not null,
    city varchar(100) not null,
    telephone varchar(20) not null,
    active tinyint not null,

    primary key(id)

);