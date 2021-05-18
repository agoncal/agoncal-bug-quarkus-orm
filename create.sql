create sequence hibernate_sequence start 1 increment 1
create table t_artists
(
    id           int8 not null,
    bio          varchar(3000),
    created_date timestamp,
    name         varchar(100),
    primary key (id)
)
