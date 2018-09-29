DROP SEQUENCE IF EXISTS t_attachment_id_seq CASCADE;
CREATE SEQUENCE t_attachment_id_seq;
DROP TABLE IF EXISTS t_attachment CASCADE;
create table t_attachment
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('seq_attachment'),
  contract_id    INTEGER not null ,
  type INTEGER ,
  content     varchar(500),
  url    varchar(500),
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP SEQUENCE IF EXISTS t_contract_id_seq CASCADE;
CREATE SEQUENCE t_contract_id_seq;
DROP TABLE IF EXISTS t_contract CASCADE;
create table t_contract
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('seq_contract'),
  content     varchar(500),
  company_name     varchar(500),
  contact_id     varchar(500),
  phone     varchar(500),
  fax     varchar(500),
  saleman_id     varchar(500),
  amount numeric(22,2),
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
