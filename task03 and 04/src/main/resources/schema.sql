create table student
(
    id  serial primary key,
    first_name varchar(20),
    last_name varchar(20),
    age integer check (age > 18 and age < 100)
);
create table course(
    id serial primary key,
    title varchar(20),
    start_time date,
    finish_time date
);
