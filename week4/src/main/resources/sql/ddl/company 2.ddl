CREATE TABLE company
(
    company_id NUMBER(255) NOT NULL AUTO_INCREMENT,
    founding_date VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    country VARCHAR(100) NOT NULL,
    PRIMARY KEY (company_id)
);