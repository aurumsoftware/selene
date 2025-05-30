CREATE TABLE servico_contratado
(
    id         serial         NOT NULL,
    id_servico int4           NOT NULL,
    id_cliente int4           NOT NULL,
    qtde       int4 DEFAULT 0 NOT NULL,
    CONSTRAINT pk_servico_contratado PRIMARY KEY (id),
    CONSTRAINT fk_servico_fornecedor_servico
        FOREIGN KEY (id_servico) REFERENCES servico (id) ON DELETE RESTRICT,
    CONSTRAINT fk_servico_fornecedor_cliente
        FOREIGN KEY (id_cliente) REFERENCES cliente (id) ON DELETE RESTRICT
);
