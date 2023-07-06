create table product(
    id serial primary key,
    name varchar(40),
    price integer,
    quantity integer
);

create table customer(
    id serial primary key,
    first_name varchar(20),
    last_name varchar(20),
    company varchar(20),
    age integer check (age > 12 and age < 110)
);

create table shop(
    id serial primary key,
    name varchar(20),
    adres varchar(30),
    profitability varchar(4)
);

create table product_shop(
    product_id integer,
    shop_id integer,
    foreign key (product_id) references product (id),
    foreign key (shop_id) references shop(id)
);

create table orders(
    shop_id integer,
    product_id integer,
    customer_id integer,
    foreign key (shop_id) references shop(id),
    foreign key (product_id) references product (id),
    foreign key (customer_id) references customer (id)
);