-- V1__create_arquivo_table.sql
CREATE TABLE arquivo (
    id BIGINT NOT NULL, -- int8 é o equivalente a BIGINT
    arquivo_binario BYTEA NOT NULL,
    dt_carregamento TIMESTAMP NOT NULL,
    nome VARCHAR(50) NOT NULL,
    tamanho BIGINT NOT NULL, -- int8 é o equivalente a BIGINT
    tipo VARCHAR(50) NOT NULL,
    CONSTRAINT arquivo_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE seq_arquivo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
