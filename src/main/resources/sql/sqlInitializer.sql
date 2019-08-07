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

create table if not exists request_activities
(
  id             SERIAL      NOT NULL,
  user_id        int         NOT NULL,
  activity_id    int         NOT NULL,
  date_review    timestamp,
  request_action varchar(45) NOT NULL,
  request_status varchar(45) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT request_activities_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES users (id),
  CONSTRAINT request_activities_activity_id_fkey FOREIGN KEY (activity_id)
    REFERENCES activities (id)
);