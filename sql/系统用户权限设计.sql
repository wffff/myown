--------用户表
DROP SEQUENCE IF EXISTS t_user_id_seq CASCADE;
CREATE SEQUENCE t_user_id_seq;
DROP TABLE IF EXISTS t_user CASCADE;
create table t_user
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('t_user_id_seq'),
  username varchar(100)   unique                                       not null,
  password varchar(100)                                         ,
  mobile   varchar(20),
  email    varchar(50),
  fullname     varchar(50),
  enabled  boolean not null  default true,
  expired  boolean not null   default false,
  locked   boolean not null   default false,
  limited  boolean  not null  default false,
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
  last                          TIMESTAMP(0)            DEFAULT NULL,
  time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--------角色表
DROP SEQUENCE IF EXISTS t_role_id_seq CASCADE;
CREATE SEQUENCE t_role_id_seq;
DROP TABLE IF EXISTS t_role CASCADE;
create table t_role
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('t_role_id_seq'),
  name     varchar(50),
  description    varchar(50),
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
  last                          TIMESTAMP(0)            DEFAULT NULL,
  time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--------用户角色表
DROP TABLE IF EXISTS t_user_role CASCADE;
create table t_user_role
(
  user_id        INTEGER NOT NULL ,
  role_Id        INTEGER NOT NULL
);


--------资源表
DROP SEQUENCE IF EXISTS t_permission_id_seq CASCADE;
CREATE SEQUENCE t_permission_id_seq;
DROP TABLE IF EXISTS t_permission CASCADE;
create table t_permission
(
  id        INTEGER PRIMARY KEY     DEFAULT NEXTVAL('t_permission_id_seq'),
  name     varchar(50),
  description    varchar(50),
  del                           BOOLEAN        NOT NULL DEFAULT FALSE,
  last                          TIMESTAMP(0)            DEFAULT NULL,
  time                          TIMESTAMP(0)   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--------用户资源表
DROP TABLE IF EXISTS t_role_permission CASCADE;
create table t_role_permission
(
  role_id        INTEGER NOT NULL ,
  permission_id        INTEGER NOT NULL
);

DROP SEQUENCE IF EXISTS t_organization_id_seq CASCADE;
CREATE SEQUENCE t_organization_id_seq;
DROP TABLE IF EXISTS t_organization CASCADE;
CREATE TABLE t_organization (
  id                          INTEGER PRIMARY KEY   DEFAULT nextval('t_organization_id_seq'),
  parent_id                   INTEGER,
  organization_type        INTEGER      NOT NULL,
  name                        VARCHAR(500) NOT NULL,
  company_name                VARCHAR(500),
  description                 VARCHAR(500),
  memo                        VARCHAR(500),
  device_name                        VARCHAR(500),
  del                         BOOLEAN      NOT NULL DEFAULT FALSE,
  last                        TIMESTAMP(0)          DEFAULT NULL,
  time                        TIMESTAMP(0) NOT NULL DEFAULT CURRENT_TIMESTAMP
);
--表约束、索引
CREATE INDEX ON t_organization (parent_id);
CREATE INDEX ON t_organization (organization_type);




insert  into t_user(username, password, mobile, email)values ('admin','$2a$10$3dusHzT1ZdVW2Bqa.7qtE.1hx..zPyUYOSg0UvRP8Mboa2nheRyEC'
                                                                       ,null ,null );

