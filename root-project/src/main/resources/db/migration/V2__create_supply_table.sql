CREATE TABLE fornecedor
(
    id     serial              NOT null,
    nome   varchar(255)        NOT NULL,
    status char(1) DEFAULT '0' NOT NULL,
    CONSTRAINT pk_fornecedor PRIMARY KEY (id)
);