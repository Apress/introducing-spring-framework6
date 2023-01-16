CREATE TABLE document (
    id identity primary key,
    name varchar(255) not null,
    type varchar(16) not null,
    location varchar(255) not null
);
