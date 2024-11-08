create table if not exists users (
    id serial primary key,
    biometric_id integer not null,
    username varchar(255) not null unique,
    password varchar(255) not null,
    status boolean default true,
    access_level int not null
);

