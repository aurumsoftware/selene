CREATE TABLE movimentacao_credito
(
    id                    serial NOT NULL,
    id_servico_contratado int4   NOT NULL,
    tipo                  varchar(255),
    qtde                  int4   NOT NULL,
    data_criacao          timestamp DEFAULT CURRENT_TIMESTAMP,
    sincronizacao         char(1)   DEFAULT '0',
    data_sincronizacao    timestamp,
    origem                varchar(50),
    observacao            varchar(4000),
    id_movimento_origem   int4,
    CONSTRAINT pk_movimentacao_credito PRIMARY KEY (id),
    CONSTRAINT fk_movimentacao_credito_servico_co FOREIGN KEY (id_servico_contratado) REFERENCES servico_contratado (id) ON DELETE RESTRICT
);