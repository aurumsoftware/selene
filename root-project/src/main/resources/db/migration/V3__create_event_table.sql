CREATE TABLE evento
(
    id            serial NOT NULL,
    id_cliente    int4   NOT NULL,
    id_fornecedor int4   NOT NULL,
    tipo          varchar(255),
    feature       varchar(255),
    data_criacao  timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_evento PRIMARY KEY (id),
    CONSTRAINT fk_evento_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id) ON DELETE restrict,
    CONSTRAINT fk_evento_fornecedor FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id) ON DELETE RESTRICT
);