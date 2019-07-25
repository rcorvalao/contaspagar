CREATE TABLE rmcsys.contaspagar (
       id NUMERIC NOT NULL,
       nome VARCHAR(100) NOT NULL,
       valor_original NUMERIC (14, 2) NOT NULL,
       data_vencimento TIMESTAMP NOT NULL,
       data_pagamento TIMESTAMP NOT NULL,
       CONSTRAINT id_contaspagar_pk PRIMARY KEY (id)
);