create table if not exists posts (
    id serial primary key,
    title varchar(255) not null,
    content text not null,
    author varchar(255) not null,
    access_level int not null,
    status boolean default true,

    createdAt timestamp not null default current_timestamp,
    updatedAt timestamp not null default current_timestamp,

    user_id bigint not null,
    foreign key (user_id) references users(id)
);