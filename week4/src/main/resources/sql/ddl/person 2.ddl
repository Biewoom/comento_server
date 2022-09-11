CREATE TABLE person
(
    person_Id NUMBER(255) NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    age NUMBER(10),
    height NUMBER(10),
    weight NUMBER(10),
    company VARCHAR(100),
    country VARCHAR(100) NOT NULL,
    PRIMARY KEY (person_Id)
);