CREATE TABLE account
(
    account_number NUMBER(200) NOT NULL,
    bank TINYINT NOT NULL, -- 0 = 신한은행, 1 = 농협, 2 = KB은행
    balance BIGINT NOT NULL,
    PRIMARY KEY (account_number, bank)
);