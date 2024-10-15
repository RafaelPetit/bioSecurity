create table if not exists posts (
    id serial primary key,
    title varchar(255) not null,
    content text not null,
    status boolean default true,
    userId integer not null,
    createdAt timestamp not null default current_timestamp,
    updatedAt timestamp not null default current_timestamp,

    foreign key (userId) references users(id)
);