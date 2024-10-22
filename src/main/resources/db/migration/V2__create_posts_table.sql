create table if not exists posts (
    id serial primary key,
    title varchar(255) not null,
    content text not null,
    author varchar(255) not null,
    accessLevel varchar(255) not null,
    status boolean default true,

    createdAt timestamp not null default current_timestamp,
    updatedAt timestamp not null default current_timestamp,

    userId bigint not null,
    foreign key (userId) references users(id)
);