DROP SEQUENCE IF EXISTS t_code_id_seq CASCADE;
CREATE SEQUENCE t_code_id_seq;
DROP TABLE IF EXISTS t_code CASCADE;
create table t_code
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('t_code_id_seq'),
  title     varchar(50),
  content     varchar(500),
  user_id    INTEGER  NOT NULL ,
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
