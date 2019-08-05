create table if not exists users
(
  id          SERIAL       NOT NULL,
  first_name  varchar(250) NOT NULL,
  last_name   varchar(250) NOT NULL,
  middle_name varchar(250) NOT NULL,
  login       varchar(250) NOT NULL,
  password    varchar(250) NOT NULL,
  email       varchar(250) NOT NULL UNIQUE,
  phone       varchar(20)  NOT NULL,
  role        varchar(45)  NOT NULL,
  PRIMARY KEY (id)
);

create table if not exists activities
(
  id   SERIAL       NOT NULL,
  name varchar(250) NOT NULL,
  PRIMARY KEY (id)
);
