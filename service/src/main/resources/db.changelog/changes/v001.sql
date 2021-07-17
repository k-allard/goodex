create table profile
(
    id         uuid         not null unique,
    firstName  varchar(255) not null,
    secondName varchar(255) not null,
    image      varchar(255) not null unique,
    username   varchar(255) not null unique,
    email      varchar(255) not null unique,
    primary key (id)
);

create table profile_friends
(
    profile_id uuid not null,
    friend_id  uuid not null,
    foreign key (profile_id) references profile (id),
    foreign key (friend_id) references profile (id)
);

create table post
(
    id          uuid not null unique,
    user_id     uuid not null,
    description varchar,
    image       varchar unique,
    rating      int check ( rating >= 0 ),
    foreign key (user_id) references profile (id),
    primary key (id)
);

create table chat
(
    id    uuid         not null unique,
    image varchar      not null unique,
    title varchar(255) not null,
    primary key (id)
);

create table profile_chat
(
    profile_id uuid not null,
    chat_id    uuid not null,
    foreign key (profile_id) references profile (id),
    foreign key (chat_id) references chat (id)
);

create table message
(
    id uuid not null unique,
    profile_id uuid not null,
    chat_id    uuid not null,
    content varchar not null,
    foreign key (profile_id) references profile (id),
    foreign key (chat_id) references chat (id),
    primary key (id)
);

create table file
(
    id uuid not null unique,
    uri varchar not null unique,
    message_id uuid not null,
    foreign key (message_id) references message (id),
    primary key (id)
);
