--liquibase formatted sql

--changeset stewie:1
create table sources (
    id int primary key,
    time real not null default 0.0,
    request_amount int not null default 0,
    refuse_amount int not null default 0
);

create type device_state as enum('FREE', 'BUSY');

create table devices (
  id int primary key,
  state device_state not null default 'FREE',
  request_amount int not null default 0
);

create table buffer (
    position int primary key,
    time real not null default 0.0,
    source_id int references sources(id),
    request_number int unique
)