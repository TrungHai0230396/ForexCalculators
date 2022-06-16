CREATE TABLE pip_value_per_lot
(
  id BIGINT not null AUTO_INCREMENT,
  currency_pair VARCHAR(7) not null,
  pip_value FLOAT not null,
  primary key (ID)
);

Insert into pip_value_per_lot(id,currency_pair,pip_value) values (1, "EUR/USD",10);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (2, "USD/JPY",12.21);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (3, "GBP/USD",10);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (4, "USD/CHF",10.77);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (5, "EUR/CHF",10.77);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (6, "AUD/USD",10);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (7, "USD/CAD",10.14);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (8, "EUR/GBP",16.16);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (9, "EUR/JPY",12.21);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (10,"GBP/JPY",12.21);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (11,"EUR/CAD",10.14);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (12,"EUR/AUD",10.03);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (13,"GBP/CHF",10.78);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (14,"CHF/JPY",12.21);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (15,"AUD/CAD",10.14);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (16,"AUD/JPY",12.21);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (17,"NZD/USD",10);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (18,"NZD/JPY",12.21);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (19,"CAD/JPY",12.21);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (20,"GBP/AUD",10.03);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (21,"AUD/NZD",7.45);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (22,"USD/HKD",1.28);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (23,"USD/SGD",7.81);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (24,"USD/ZAR",1.41);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (25,"ZAR/JPY",12.21);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (26,"USD/MXN",0.82);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (27,"XAU/USD",10);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (28,"XAU/EUR",13.75);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (29,"XAG/USD",10);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (30,"XAG/EUR",13.75);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (31,"AUD/CHF",10.78);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (32,"GBP/CAD",10.15);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (33,"GBP/NZD",7.46);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (34,"USD/DKK",1.85);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (35,"EUR/DKK",1.85);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (36,"EUR/PLN",3.45);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (37,"USD/PLN",3.45);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (38,"EUR/TRY",6.22);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (39,"EUR/SEK",1.56);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (40,"EUR/NOK",1.78);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (41,"USD/NOK",1.78);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (42,"USD/SEK",1.56);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (43,"EUR/TRY",6.22);
Insert into pip_value_per_lot(id,currency_pair,pip_value) values (44,"EUR/NZD",7.46);


-- DROP TABLE IF EXISTS calculator
-- Create table
CREATE TABLE order_info
(
  id              BIGINT not null AUTO_INCREMENT,
  currency_pair_id   BIGINT not null ,
  stop_loss       FLOAT(10,5) not null,
  entry           FLOAT(10,5) not null,
  take_profit     FLOAT(10,5),
  lot             FLOAT(10,3) not null,
  pip             FLOAT(10,3) not null,
  account_balance FLOAT(10,3),
  risk_by_percent FLOAT(10,3),
  amout_to_risk   FLOAT(10,3),
  pip_value       FLOAT(10,3),
  created_at      DateTime,
  updated_at      DateTime,
  result          FLOAT(10,3),
  primary key (ID),
  FOREIGN KEY (currency_pair_id) REFERENCES pip_value_per_lot (id)
);

--  
-- ALTER TABLE calculator
-- add constraint Calculator_PK primary key (ID);
-- ADD FOREIGN KEY ("currency_pair_id") REFERENCES "pip_value_per_lot" ("id");



