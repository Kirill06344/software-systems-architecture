--liquibase formatted sql

--changeset stewie:1
create table events (
    id int primary key
)