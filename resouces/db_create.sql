CREATE DATABASE deribit;
CREATE TABLE balance (
                         username varchar(200) NOT NULL,
                         currency varchar(200) NOT NULL,
                         totalAmount DOUBLE precision NOT NULL,
                         reservedAmount DOUBLE precision NOT NULL,
                         UNIQUE (username, currency)
);
