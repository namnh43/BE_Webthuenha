use project_md6;

insert into users (id, username, password, email, first_name, last_name, phone_number, profile_image, address, role, create_at, apply_host, is_blocked)
values ('1','haido','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','haido@gmail.com','Do','Trong Hai','0354666699','123456.jgp','','ADMIN',NOW(),0,0),
       ('2','haingo','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','haingo@gmail.com','Ngo','Hoang Hai','0987654321','123456.jgp','','USER',NOW(),0,0),
       ('3','nam','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','nam@gmail.com','Nguyen','Hoai Nam','0147258369','123456.jgp','','HOST',NOW(),0,0),
       ('4','tuan','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','tuan@gmail.com','Pham','Minh Tuan','0789456123','123456.jgp','','HOST',NOW(),0,0),
       ('5','cong','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','cong@gmail.com','Nguyen','Minh Cong','0369852147','123456.jgp','','HOST',NOW(),0,0),
       ('6','hai123','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','gathidungru@gmail.com','Do','Trong Hai','0354666699','123456.jgp','','USER',NOW(),0,0),
       ('7','tronghai','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','tronghai1710@gmail.com','Do','Trong Hai','0354666699','123456.jgp','','USER',NOW(),0,0),
       ('8','hai456','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','hai456@gmail.com','Do','Trong Hai','0354666699','123456.jgp','','USER',NOW(),0,0);


INSERT INTO houses (name, total_bedrooms, total_bathrooms, address, price, description, user_id, rating_score, number_of_rented, status_house, create_at)
VALUES ('tuan House', 3, 2, 'ThaiBinh', 1000, 'A beautiful house', 4, 4.5, 0, 'Active', NOW()),
       ('tuan2 House', 3, 2, 'HaNoi', 1000, 'A beautiful house', 4, 4.5, 0, 'Active', NOW()),
       ('nam House', 3, 2, 'HaNoi', 1000, 'A beautiful house', 3, 4.5, 0, 'Active', NOW()),
       ('nam2 House', 3, 2, 'HaNoi', 1000, 'A beautiful house', 3, 4.5, 0, 'Active', NOW()),
       ('nam3 House', 3, 2, 'HaNoi', 1000, 'A beautiful house', 3, 4.5, 0, 'Active', NOW()),
       ('cong House', 3, 1, 'BacGiang', 1000, 'A beautiful house', 5, 4.5, 0, 'Active', NOW());