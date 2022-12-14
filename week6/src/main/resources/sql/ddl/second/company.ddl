CREATE TABLE COMPANY
(
    COMPANY_ID      BIGINT       NOT NULL           AUTO_INCREMENT,
    FOUNDING_DATE   VARCHAR(100) NOT NULL,  -- YYYY-mm-DD 의 날짜 형식
    NAME            VARCHAR(100) NOT NULL   UNIQUE,
    COUNTRY         VARCHAR(100) NOT NULL,
    PRIMARY KEY (COMPANY_ID),
    CONSTRAINT FK_COUNTRY_IN_COMPANY
        FOREIGN KEY (COUNTRY) references COUNTRY (NAME)
);