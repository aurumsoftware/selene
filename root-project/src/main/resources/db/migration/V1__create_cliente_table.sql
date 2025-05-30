CREATE TABLE cliente
(
    id               serial                not NULL,
    nome             varchar(255)          NOT NULL,
    numero_serie     varchar(15)           NOT NULL,
    uuid             varchar(50)           NOT NULL,
    api_key          varchar(255),
    status           char(1)   DEFAULT '0' NOT NULL,
    data_criacao     timestamp DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao timestamp,
    sincronizado     char(1)   DEFAULT '0' NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (id),
    CONSTRAINT uk_numero_serie UNIQUE (numero_serie)
);