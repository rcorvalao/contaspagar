CREATE TABLE rmcsys.contas_pagar (
       id integer NOT NULL,
       nome character varying(100) NOT NULL,
       valor_original NUMERIC (14, 2) NOT NULL,
       data_vencimento timestamp without time zone NOT NULL,
       data_pagamento timestamp without time zone NOT NULL,
       multa NUMERIC (5, 2) NOT NULL,
       juros_dia NUMERIC (5, 2) NOT NULL,
       CONSTRAINT id_contaspagar_pk PRIMARY KEY (id)
);

create sequence rmcsys.cntpag_SEQ minvalue 1 maxvalue 999999999999 increment by 1 start with 1 no cycle;

