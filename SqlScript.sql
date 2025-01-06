-- MySQL Script to Create the Database and Tables

-- Create the database
CREATE DATABASE IF NOT EXISTS inventory_management_system;
USE inventory_management_system;

-- Create the Supplier table
CREATE TABLE Supplier (
                          id CHAR(36) NOT NULL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          photo TEXT
);

-- Create the Retailer table
CREATE TABLE Retailer (
                          id CHAR(36) NOT NULL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          photo TEXT
);

-- Create the Product table
CREATE TABLE Product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         supplierId CHAR(36) NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         stockQuantity INT NOT NULL CHECK (stockQuantity >= 0),
                         price DOUBLE NOT NULL,
                         discount DOUBLE DEFAULT 0,
                         description TEXT NOT NULL,
                         isDeleted VARCHAR(255) DEFAULT "NOT_DELETED",
                         FOREIGN KEY (supplierId) REFERENCES Supplier(id)
);



-- Create the ProductImage table
CREATE TABLE ProductImage(
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             productId BIGINT NOT NULL,
                             imageUrl TEXT,
                             FOREIGN KEY (productId) REFERENCES Product(id)
);

-- Create the Cart table

CREATE TABLE Cart (
                      id CHAR(36) NOT NULL PRIMARY KEY,
                      retailerId CHAR(36) NOT NULL,
                      FOREIGN KEY (retailerId) REFERENCES Retailer(id)
);


-- Create the CartItem table
CREATE TABLE CartItem (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          cartId CHAR(36)  NOT NULL,
                          productId BIGINT NOT NULL,
                          quantity INT NOT NULL,
                          FOREIGN KEY (cartId) REFERENCES Cart(id),
                          FOREIGN KEY (productId) REFERENCES Product(id)
);

-- Create the Bill table
CREATE TABLE Bill (
                      id CHAR(36) NOT NULL PRIMARY KEY,
                      retailerId CHAR(36) NOT NULL,
                      FOREIGN KEY (retailerId) REFERENCES Retailer(id)
);

-- Create the BillItem table
CREATE TABLE BillItem (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          billId CHAR(36) NOT NULL,
                          productId BIGINT NOT NULL,
                          productName VARCHAR(255) NOT NULL,
                          price DOUBLE NOT NULL,
                          quantity INT NOT NULL,
                          confirm VARCHAR(255) NOT NULL,
                          FOREIGN KEY (billId) REFERENCES Bill(id),
                          FOREIGN KEY (productId) REFERENCES Product(id)
);


