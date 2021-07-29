alter table users
    add active           bool    not null default true,
    add verificationCode varchar null;