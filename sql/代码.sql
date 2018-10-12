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

DROP SEQUENCE IF EXISTS t_manga_id_seq CASCADE;
CREATE SEQUENCE t_manga_id_seq;
DROP TABLE IF EXISTS t_manga CASCADE;
create table t_manga
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('t_manga_id_seq'),
  title     varchar(50),
  cover     varchar(500),
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP SEQUENCE IF EXISTS t_manga_attach_id_seq CASCADE;
CREATE SEQUENCE t_manga_attach_id_seq;
DROP TABLE IF EXISTS t_manga_attach CASCADE;
create table t_manga_attach
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('t_manga_attach_id_seq'),
  url varchar (500) not null ,
  manga_id    INTEGER references t_manga(id) ,
  sort     INTEGER ,
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP SEQUENCE IF EXISTS t_video_id_seq CASCADE;
CREATE SEQUENCE t_video_id_seq;
DROP TABLE IF EXISTS t_video CASCADE;
create table t_video
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('t_video_id_seq'),
  title varchar(50) not null ,
  url varchar (500) not null ,
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
	last                          TIMESTAMP(0)            DEFAULT NULL,
	time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
