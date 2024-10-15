create table if not exists users (
    id serial primary key,
    biometricId integer not null,
    username varchar(255) not null unique,
    password varchar(255) not null,
    status boolean default true,
    role varchar(255) not null
);

