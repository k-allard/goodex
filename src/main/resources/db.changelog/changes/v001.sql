create table users
(
    id       uuid unique  not null,
    username varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255) not null unique,
    img      varchar(255) not null,
    primary key (id)
);

create table posts
(
    id          uuid unique not null,
    user_id     uuid        not null,
    description varchar     not null,
    img         varchar     not null,
    foreign key (user_id) references users (id),
    primary key (id)
);

create table roles
(
    role_id uuid unique         not null,
    name    varchar(255) unique not null,
    primary key (role_id)
);

create table users_roles
(
    role_id uuid not null,
    user_id uuid not null,
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (role_id)
);
