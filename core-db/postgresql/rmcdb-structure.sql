/*

CREATE TABLE rmcsys.t_empresa (
                id_empresa NUMERIC NOT NULL,
                cnpj VARCHAR(14) NOT NULL,
                razao_social VARCHAR NOT NULL,
                CONSTRAINT id_empresa_pk PRIMARY KEY (id_empresa)
);


CREATE TABLE rmcsys.t_perfil (
                id_perfil NUMERIC NOT NULL,
                nome VARCHAR NOT NULL,
                ativado BOOLEAN NOT NULL,
                CONSTRAINT id_perfil_idx PRIMARY KEY (id_perfil)
);


CREATE TABLE rmcsys.t_usuario (
                id_usuario NUMERIC NOT NULL,
                email VARCHAR NOT NULL,
                ativado BOOLEAN NOT NULL,
                cargo VARCHAR NOT NULL,
                senha VARCHAR NOT NULL,
                nome_contato VARCHAR NOT NULL,
                funcao VARCHAR,
                id_perfil NUMERIC NOT NULL,
                CONSTRAINT id_usuario_idx PRIMARY KEY (id_usuario)
);


CREATE TABLE rmcsys.t_usuario_empresa (
                id_usuario_empresa NUMERIC NOT NULL,
                id_empresa NUMERIC NOT NULL,
                id_usuario NUMERIC NOT NULL,
                CONSTRAINT id_usuario_empresa_pk PRIMARY KEY (id_usuario_empresa)
);


ALTER TABLE rmcsys.t_usuario_empresa ADD CONSTRAINT t_empresa_t_usuario_empresa_fk
FOREIGN KEY (id_empresa)
REFERENCES rmcsys.t_empresa (id_empresa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE rmcsys.t_usuario ADD CONSTRAINT t_perfil_t_usuario_fk
FOREIGN KEY (id_perfil)
REFERENCES rmcsys.t_perfil (id_perfil)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE rmcsys.t_usuario_empresa ADD CONSTRAINT t_usuario_t_usuario_empresa_fk
FOREIGN KEY (id_usuario)
REFERENCES rmcsys.t_usuario (id_usuario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

*/

