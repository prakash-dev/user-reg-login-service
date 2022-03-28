CREATE TABLE users (
  accountid INTEGER PRIMARY KEY,
  name VARCHAR(30),
  email  VARCHAR(50),
  phone VARCHAR(11)
);

CREATE TABLE accounts (
  id INTEGER PRIMARY KEY,
  username VARCHAR(30),
  password  VARCHAR(50)
);