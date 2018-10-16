DROP TABLE users CASCADE CONSTRAINTS;
DROP TABLE accounts CASCADE CONSTRAINTS;
DROP TABLE asset_pricing CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE securities CASCADE CONSTRAINTS;
DROP TABLE trades CASCADE CONSTRAINTS;

CREATE TABLE users (
username VARCHAR2(100),
pass VARCHAR2(100),
fname VARCHAR2(100),
lname VARCHAR2(100),
address VARCHAR2(100),
city VARCHAR2(100),
state CHAR(2),
zip NUMBER(10),
ssn NUMBER(10),
dob DATE,
phone NUMBER(10),
approval_flag NUMBER(10) DEFAULT 0,
CONSTRAINT user_pk PRIMARY KEY (username)
);

CREATE TABLE accounts (
account_id NUMBER(38),
account_type VARCHAR(100),
account_name VARCHAR(100),
username VARCHAR(100),
balance NUMBER(*,2),
CONSTRAINT account_pk PRIMARY KEY (account_id),
CONSTRAINT account_user_fk FOREIGN KEY (username)
REFERENCES users (username)
);

CREATE TABLE asset_pricing(
   ticker_symbol VARCHAR2(10),
   price NUMBER(*,2),

   CONSTRAINT ticker_symbol_pk PRIMARY KEY (ticker_symbol)
);

CREATE TABLE orders(
   order_id NUMBER(38),
   account_id NUMBER(38),
   ticker_symbol VARCHAR2(10),
   amount NUMBER(38),
   broker_status VARCHAR2(32),

   CONSTRAINT order_id_pk PRIMARY KEY (order_id),

   CONSTRAINT account_id_fk FOREIGN KEY (account_id)
       REFERENCES accounts (account_id) ON DELETE CASCADE

);

CREATE TABLE securities(
   security_id NUMBER(38),
   security_type VARCHAR2(100),
   security_name VARCHAR2(100),
   ticker_symbol VARCHAR2(10),
   amount NUMBER(38),
   account_id NUMBER(38),

   CONSTRAINT security_id_pk PRIMARY KEY (security_id),

   CONSTRAINT account_id2_fk FOREIGN KEY (account_id)
       REFERENCES accounts (account_id) ON DELETE CASCADE

);

CREATE TABLE trades (
   trade_id NUMBER(38),
   requestor_username VARCHAR2 (32),
   receiver_username VARCHAR2 (32),
   broker_status NUMBER (10),
   receiver_approval NUMBER (10),
   security_id_requestor NUMBER (10),
   security_id_receiver NUMBER (10),
   amount_requester NUMBER (10),
   amount_receiver NUMBER (10),
   CONSTRAINT trade_id_pk PRIMARY KEY (trade_id ),
   CONSTRAINT requestor_username_fk FOREIGN KEY ( requestor_username )
       REFERENCES users ( username ) ON DELETE CASCADE,
   CONSTRAINT receiver_username_fk FOREIGN KEY ( receiver_username )
       REFERENCES users ( username )ON DELETE CASCADE,
   CONSTRAINT security_id_requestor_fk FOREIGN KEY ( security_id_requestor )
       REFERENCES securities ( security_id ) ON DELETE CASCADE,
   CONSTRAINT security_id_receiver_fk FOREIGN KEY ( security_id_receiver )
       REFERENCES securities ( security_id )
);
