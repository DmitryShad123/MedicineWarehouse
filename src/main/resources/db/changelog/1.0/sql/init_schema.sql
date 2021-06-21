CREATE TABLE address
(
    address_id bigint,
    city varchar (30),
    street varchar (100),
    house int
);
alter table address add constraint address_pk primary key (address_id);


CREATE TABLE category
(
    category_id bigint,
    name varchar (30)
);
alter table category add constraint category_pk primary key (category_id);


CREATE TABLE client
(
    client_id bigint,
    first_name varchar (30),
    last_name varchar (30),
    middle_name varchar (30),
    inn bigint,
    phone bigint
);
alter table client add constraint client_pk primary key (client_id);


CREATE TABLE manufacturer
(
    manufacturer_id bigint,
    name varchar (50),
    inn bigint,
    bic bigint,
    phone bigint,
    address_legal_id bigint,
    address_actual_id bigint
);
alter table manufacturer add constraint manufacturer_pk primary key (manufacturer_id);
alter table manufacturer add constraint manufacturer_address_legal_fk foreign key (address_legal_id) references address (address_id);
alter table manufacturer add constraint manufacturer_address_actual_fk foreign key (address_actual_id) references address (address_id);



CREATE TABLE supplier
(
    supplier_id bigint,
    name varchar (50),
    inn bigint,
    bic bigint,
    phone bigint,
    address_legal_id bigint,
    address_actual_id bigint
);
alter table supplier add constraint supplier_pk primary key (supplier_id);
alter table supplier add constraint supplier_address_legal_fk foreign key (address_legal_id) references address (address_id);
alter table supplier add constraint supplier_address_actual_fk foreign key (address_actual_id) references address (address_id);



CREATE TABLE delivery_preparation
(
    delivery_preparation_id bigint,
    date_delivery date,
    supplier_id bigint
);
alter table delivery_preparation add constraint delivery_preparation_pk primary key (delivery_preparation_id);
alter table delivery_preparation add constraint delivery_preparation_supplier_fk foreign key (supplier_id) references supplier (supplier_id);

CREATE TABLE quantity_delivery
(
    quantity_delivery_id bigint,
    name varchar (50),
    quantity_packaging int,
    delivery_preparation_id bigint

);
alter table quantity_delivery add constraint quantity_delivery_pk primary key (quantity_delivery_id);
alter table quantity_delivery add constraint quantity_delivery_delivery_preparation_fk foreign key (delivery_preparation_id) references delivery_preparation (delivery_preparation_id);

CREATE TABLE preparations
(
    preparations_id bigint,
    name varchar (50),
    price int,
    country varchar (30),
    stock_balance int,
    dosage numeric(18, 4),
    expiration_date date,
    category_id bigint,
    manufacturer_id bigint,
    delivery_preparation_id bigint
);
alter table preparations add constraint preparations_pk primary key (preparations_id);
alter table preparations add constraint preparations_category_fk foreign key (category_id) references category (category_id);
alter table preparations add constraint preparations_manufacturer_fk foreign key (manufacturer_id) references manufacturer (manufacturer_id);
alter table preparations add constraint preparations_delivery_preparation_fk foreign key (delivery_preparation_id) references delivery_preparation (delivery_preparation_id);


CREATE TABLE surrender_delivery
(
    surrender_delivery_id bigint,
    date_surrender_delivery date,
    client_id bigint
);
alter table surrender_delivery add constraint surrender_delivery_pk primary key (surrender_delivery_id);
alter table surrender_delivery add constraint surrender_delivery_client_fk foreign key (client_id) references client (client_id);

CREATE TABLE quantity_surrender
(
    quantity_surrender_id bigint,
    quantity_packaging int,
    preparations_id bigint,
    surrender_delivery_id bigint

);
alter table quantity_surrender add constraint quantity_surrender_pk primary key (quantity_surrender_id);
alter table quantity_surrender add constraint quantity_surrender_preparation_id_fk foreign key (preparations_id) references preparations (preparations_id);
alter table quantity_surrender add constraint surrender_delivery_preparation_id_fk foreign key (surrender_delivery_id) references surrender_delivery (surrender_delivery_id);