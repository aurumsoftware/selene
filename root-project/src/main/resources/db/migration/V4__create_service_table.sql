CREATE TABLE servico
(
    id               serial       NOT null,
    nome             varchar(255) NOT NULL,
    id_fornecedor    int4         NOT NULL,
    id_item_contrato int4,
    CONSTRAINT pk_servico PRIMARY KEY (id),
    CONSTRAINT fk_servico_fornecedor FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id) ON DELETE RESTRICT
);
