CREATE TABLE IF NOT EXISTS item (
  id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  price BIGINT NOT NULL,
  title VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL,
  img VARCHAR(200) NOT NULL
) ENGINE=InnoDB;

insert into item (id, description, img, price, title) values (1, "descriptionika", "image", 100,  "title");
insert into item (id, description, img, price, title) values (2, "descriptiontako", "image", 100,  "title");
insert into item (id, description, img, price, title) values (3, "descriptionuni", "image", 100,  "title");

