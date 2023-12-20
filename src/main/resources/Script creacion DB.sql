create database prueba_sic;

use prueba_sic;

create table person(
    identification_number BIGINT PRIMARY KEY,
	identification_type VARCHAR(50),
    name VARCHAR(100),
    lastname VARCHAR(100),
    phone BIGINT,
    address VARCHAR(200),
    email VARCHAR(100),
    password VARCHAR(255),
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table employee(
	identification_number BIGINT PRIMARY KEY,
    dependence VARCHAR(100),
    admission_date TIMESTAMP,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (identification_number) REFERENCES person(identification_number)
);

create table process(
	filing_number BIGINT AUTO_INCREMENT PRIMARY KEY,
    filing_year VARCHAR(4),
    process_name VARCHAR(100),
    description TEXT,
    filing_person BIGINT,
    official_received BIGINT,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (filing_person) REFERENCES person(identification_number),
    FOREIGN KEY (official_received) REFERENCES employee(identification_number)
);

show tables;

select * from person;
select * from employee;
select * from process;