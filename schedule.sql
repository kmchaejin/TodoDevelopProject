use tododev;

CREATE TABLE user
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(10) UNIQUE,
    email VARCHAR(30),
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(10),
    title VARCHAR(20),
    contents VARCHAR(100),
    created_at DATETIME,
    updated_at DATETIME,
    FOREIGN KEY (user_name) REFERENCES user(name)
);