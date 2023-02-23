DROP TABLE IF EXISTS brands;

CREATE TABLE brands(
                        id bigint NOT NULL,
                        name varchar(100),
                        PRIMARY KEY (id)
);

DROP TABLE IF EXISTS prices;

CREATE TABLE prices(
                       price_list_id bigint NOT NULL,
                       start_date timestamp,
                       end_date timestamp,
                       currency varchar(100),
                       product_id bigint NOT NULL,
                       priority bigint NOT NULL,
                       price float(25) NOT NULL,
                       brand_id int NOT NULL,
                       foreign key (brand_id) references brands(id),
                       PRIMARY KEY (price_list_id)
);