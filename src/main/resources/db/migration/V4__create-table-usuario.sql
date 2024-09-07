CREATE TABLE usuario (
	id BIGINT NOT NULL,
	data_atual_senha date NOT NULL,
	login varchar(255) NOT NULL,
	senha varchar(255) NOT NULL,
	pessoa_id BIGINT NOT NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE seq_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;