DROP TABLE users CASCADE CONSTRAINTS;
DROP TABLE accounts CASCADE CONSTRAINTS;
DROP TABLE asset_pricing CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE securities CASCADE CONSTRAINTS;
DROP TABLE trades CASCADE CONSTRAINTS;

CREATE TABLE users (
    username VARCHAR2(100),
    pass VARCHAR2(100) NOT NULL,
    fname VARCHAR2(100) NOT NULL,
    lname VARCHAR2(100) NOT NULL,
    address VARCHAR2(100) NOT NULL,
    city VARCHAR2(100) NOT NULL,
    state CHAR(2) NOT NULL,
    zip NUMBER(5) NOT NULL,
    ssn NUMBER(9) NOT NULL,
    dob DATE NOT NULL,
    phone NUMBER(10) NOT NULL,
    approval_flag NUMBER(1) DEFAULT 0,
    CONSTRAINT user_pk PRIMARY KEY (username),
    CONSTRAINT user_appro_flg_chk CHECK (approval_flag IN (-1, 0, 1))
);

CREATE TABLE accounts (
    account_id NUMBER(38),
    account_type VARCHAR(100) NOT NULL,
    account_name VARCHAR(100),
    username VARCHAR(100) NOT NULL,
    balance NUMBER(*,2) DEFAULT 0,
    CONSTRAINT account_pk PRIMARY KEY (account_id),
    CONSTRAINT account_user_fk FOREIGN KEY (username) REFERENCES users (username)
);

CREATE TABLE asset_pricing(
    ticker_symbol VARCHAR2(10),
    price NUMBER(*,2) DEFAULT 0,
    company_name VARCHAR2(100) NOT NULL,
    CONSTRAINT ticker_symbol_pk PRIMARY KEY (ticker_symbol),
    CONSTRAINT asset_pricing_price_chk CHECK (price >= 0)
);

CREATE TABLE orders(
    order_id NUMBER(38),
    account_id NUMBER(38) NOT NULL,
    ticker_symbol VARCHAR2(10) NOT NULL,
    amount NUMBER(38) DEFAULT 0,
    broker_status VARCHAR2(1) DEFAULT 0,
    CONSTRAINT order_id_pk PRIMARY KEY (order_id),
    CONSTRAINT account_id_fk FOREIGN KEY (account_id)
        REFERENCES accounts (account_id) ON DELETE CASCADE,
    CONSTRAINT ticker_symbol_order_fk FOREIGN KEY (ticker_symbol)
        REFERENCES asset_pricing (ticker_symbol) ON DELETE CASCADE,
    CONSTRAINT orders_chk CHECK (amount >= 0 AND broker_status IN (-1, 0, 1))
);

CREATE TABLE securities(
    security_id NUMBER(38),
    security_type VARCHAR2(100) NOT NULL,
    security_name VARCHAR2(100),
    ticker_symbol VARCHAR2(10) NOT NULL,
    amount NUMBER(38) DEFAULT 0,
    account_id NUMBER(38) NOT NULL,
    CONSTRAINT security_id_pk PRIMARY KEY (security_id),
    CONSTRAINT account_id2_fk FOREIGN KEY (account_id)
        REFERENCES accounts (account_id) ON DELETE CASCADE,
    CONSTRAINT ticker_symbol_sec_fk FOREIGN KEY (ticker_symbol)
        REFERENCES asset_pricing (ticker_symbol) ON DELETE CASCADE,
    CONSTRAINT securities_amount_chk CHECK(amount >= 0),
    CONSTRAINT securities_type_chk CHECK(security_type IN ('Stock', 'Dollar'))
);

CREATE TABLE trades (
    trade_id NUMBER(38),
    requester_account_id NUMBER(38) NOT NULL,
    receiver_account_id NUMBER(38),
    broker_status NUMBER (1) DEFAULT 0,
    receiver_approval NUMBER (1) DEFAULT 0,
    security_id_requester NUMBER (38) NOT NULL,
    security_id_receiver NUMBER (38),
    amount_requester NUMBER (10) NOT NULL,
    amount_receiver NUMBER (10),
    CONSTRAINT trade_id_pk PRIMARY KEY (trade_id ),
    CONSTRAINT requester_account_id_fk FOREIGN KEY ( requester_account_id )
        REFERENCES accounts ( account_id ) ON DELETE CASCADE,
    CONSTRAINT receiver_account_id_fk FOREIGN KEY ( receiver_account_id )
        REFERENCES accounts ( account_id )ON DELETE CASCADE,
    CONSTRAINT security_id_requester_fk FOREIGN KEY ( security_id_requester )
        REFERENCES securities ( security_id ) ON DELETE CASCADE,
    CONSTRAINT security_id_receiver_fk FOREIGN KEY ( security_id_receiver )
        REFERENCES securities ( security_id ),
    CONSTRAINT trades_chk CHECK (broker_status IN (-1, 0, 1)
        AND receiver_approval IN (-1, 0, 1) AND amount_requester >= 0)
);

SET DEFINE OFF
prompt "Start of control"
/
@AssetPricing.sql
prompt "End of control"
/
SET DEFINE ON
commit;
