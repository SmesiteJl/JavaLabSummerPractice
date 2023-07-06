insert into product (name, price, quantity)
values ('ultra gaming keyboard', 12000, 234),
       ('ultra gaming mouse', 10000, 324),
       ('super cooler system', 7000, 195),
       ('the brightest monitor', 120000, 45),
       ('not a hyper pc', '250000', 74);

insert into customer(first_name, last_name, company, age)
values ('Jeffrey', 'Bezos', 'Amazon', 59),
       ('Arslanov', 'Marat', 'MIT', 79),
       ('Typical', 'Gamer', 'Google', 19);

insert into shop(name, adres, profitability)
values ('TechStop', 'Kazan-Pushkina-32', '70%'),
       ('ProgrammersOnly', 'New-York-Broadway-1', '100%'),
       ('AllForPC', 'Mosсow-RedSquare-10', '60%');

insert into product_shop(product_id, shop_id)
values (1,1),
       (2,1),
       (3,1),
       (1,2),
       (2,2),
       (3,2),
       (4,2),
       (5,2),
       (5,3),
       (2,3),
       (1,3);
insert into orders (shop_id, product_id, customer_id)
values (2,1,5),
       (2,1,4),
       (2,1,1),
       (1,2,1),
       (1,2,2),
       (1,2,3),
       (3,3,1),
       (3,3,2),
       (3,3,3),
       (3,3,4),
       (3,3,5);




