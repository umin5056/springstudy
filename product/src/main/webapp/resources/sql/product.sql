drop sequence product_seq;
create sequence product_seq nocache;

drop table product;
create table product (
    prod_no number not null primary key,
    prod_name varchar2(100 byte) not null,
    prod_price number,
    prod_made_at date    
);