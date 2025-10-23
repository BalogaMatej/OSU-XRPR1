-- Create car table
CREATE TABLE car (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    production_year INT NOT NULL,
    vin VARCHAR(255) NOT NULL UNIQUE
);