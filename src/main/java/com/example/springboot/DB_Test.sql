use project_md6;

insert into users (username, password, email, first_name, last_name, phone_number, profile_image, address, role, created_at, apply_host, is_blocked)
values ('admin','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','haido@gmail.com','Do','Trong Hai','0354666699','https://as2.ftcdn.net/v2/jpg/03/31/69/91/1000_F_331699188_lRpvqxO5QRtwOM05gR50ImaaJgBx68vi.jpg','','ROLE_ADMIN',NOW(),0,0),
       ('haingo','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','haingo@gmail.com','Ngo','Hoang Hai','0987654321','https://as2.ftcdn.net/v2/jpg/03/31/69/91/1000_F_331699188_lRpvqxO5QRtwOM05gR50ImaaJgBx68vi.jpg','','ROLE_USER',NOW(),0,0),
       ('nam','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','nam@gmail.com','Nguyen','Hoai Nam','0147258369','https://as2.ftcdn.net/v2/jpg/03/31/69/91/1000_F_331699188_lRpvqxO5QRtwOM05gR50ImaaJgBx68vi.jpg','','ROLE_HOST',NOW(),0,0),
       ('tuan','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','tuan@gmail.com','Pham','Minh Tuan','0789456123','https://as2.ftcdn.net/v2/jpg/03/31/69/91/1000_F_331699188_lRpvqxO5QRtwOM05gR50ImaaJgBx68vi.jpg','','ROLE_HOST',NOW(),0,0),
       ('cong','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','cong@gmail.com','Nguyen','Minh Cong','0369852147','https://as2.ftcdn.net/v2/jpg/03/31/69/91/1000_F_331699188_lRpvqxO5QRtwOM05gR50ImaaJgBx68vi.jpg','','ROLE_HOST',NOW(),0,0),
       ('hai123','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','gathidungru@gmail.com','Do','Trong Hai','0921345678','https://as2.ftcdn.net/v2/jpg/03/31/69/91/1000_F_331699188_lRpvqxO5QRtwOM05gR50ImaaJgBx68vi.jpg','','ROLE_USER',NOW(),0,0),
       ('tronghai','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','tronghai1710@gmail.com','Do','Trong Hai','0123987654','https://as2.ftcdn.net/v2/jpg/03/31/69/91/1000_F_331699188_lRpvqxO5QRtwOM05gR50ImaaJgBx68vi.jpg','','ROLE_HOST',NOW(),0,0),
       ('hai456','$2a$10$ro7PhjN6Cgn.Skvp8KkGeucFUdJ.6berWJyrvOw8btfDr4bttGOZ6','hai456@gmail.com','Do','Trong Hai','0567489321','https://as2.ftcdn.net/v2/jpg/03/31/69/91/1000_F_331699188_lRpvqxO5QRtwOM05gR50ImaaJgBx68vi.jpg','','ROLE_USER',NOW(),0,0);


INSERT INTO houses (name, total_bedrooms, total_bathrooms, address, price, description, user_id, rating_score, number_of_rented, house_status, created_at, featured_image)
VALUES ('tuan House', 3, 2, 'ThaiBinh', 1000, 'A beautiful house', 4, 4.5, 10, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-13903824/original/82d996fb-d7c4-46a8-a713-febd281cd69f.jpeg?im_w=1200'),
       ('tuan2 House', 3, 2, 'HaNoi', 1000, 'A beautiful house', 4, 4.5, 5, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-668146487515150072/original/8ff2a532-e0cd-41a2-9164-554c4d9eb28a.jpeg?im_w=1200'),
       ('nam House', 4, 4, 'HaNoi', 1000, 'A beautiful house', 3, 4.5, 3, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-51809333/original/0da70267-d9da-4efb-9123-2714b651c9fd.jpeg?im_w=720'),
       ('hai House', 6, 8, 'HaNoi', 1000, 'A beautiful house', 7, 4.5, 1, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-26300485/original/ee94e6c1-6ebc-496e-af84-1502edd1b759.jpeg?im_w=720'),
       ('hai3 House', 3, 2, 'ThaiBinh', 1000, 'A beautiful house', 3, 4.5, 8, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-13016398/original/6c59e24d-89f3-4475-aaca-80363792fb51.jpeg?im_w=720'),
       ('tuan homestay', 5, 4, 'HaNoi', 1000, 'A beautiful homestay', 5, 4.5, 9, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-13016398/original/6c59e24d-89f3-4475-aaca-80363792fb51.jpeg?im_w=720'),
       ('nam3 homestay', 5, 4, 'HaNoi', 5000, 'A beautiful homestay', 3, 4.5, 10, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/airflow/Hosting-714258423682866070/original/960755ad-ce79-4606-8f17-7c2d6c64fe41.jpg?im_w=720'),
       ('nam3 House', 7, 8, 'HaNoi', 2000, 'A beautiful house', 4, 4.5, 10, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-39974109/original/4bf3e6ff-555a-4553-973e-a371d4331e43.jpeg?im_w=720'),
       ('hai do homestay', 3, 2, 'BacNinh', 1000, 'A beautiful homestay', 3, 4.5, 10, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-746919020186561409/original/98f1a8c7-a263-46a8-8757-1f5d9d39f8bb.jpeg?im_w=720'),
       ('Ngo Hai House', 3, 2, 'HaNoi', 1000, 'A beautiful house', 7, 4.5, 6, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/airflow/Hosting-640811493743055902/original/d89957f7-0735-418f-b717-d99cf5fbd69a.jpg?im_w=720'),
       ('nam3 House', 3, 2, 'BacGiang', 1000, 'A beautiful house', 5, 4.5, 7, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/5ce54384-cf87-483b-ba46-bada14fa9bc1.jpg?im_w=720'),
       ('tuan hotel', 3, 2, 'HaNoi', 1000, 'A beautiful hotel', 5, 4.5, 8, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-39793877/original/a0d92972-40f3-46af-a507-f93f5b945702.jpeg?im_w=720'),
       ('nam3 House', 3, 2, 'BacNinh', 1000, 'A beautiful house', 3, 4.5, 9, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-609081347838263236/original/1dd85f1a-8c3a-40f2-a815-9ad2bbc5f0bb.jpeg?im_w=720'),
       ('tuan 2 homestay', 3, 2, 'HaNoi', 1000, 'A beautiful house', 4, 4.5, 8, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-749944387202007594/original/4c8947f5-ff4c-481c-b78e-c46cf6111272.jpeg?im_w=720'),
       ('cong House', 3, 1, 'BacGiang', 1000, 'A beautiful house', 5, 4.5, 6, 'EMPTY', NOW(),'https://a0.muscache.com/im/pictures/miso/Hosting-813137457313942137/original/34eaa638-9027-4e9a-9e91-239db6f2e844.jpeg?im_w=720');

INSERT INTO bookings (user_id, house_id, start_date, end_date, price, total, created_at, updated_at, booking_status)
VALUES
    (1, 1, '2023-08-01', '2023-08-07', 100, 700, NOW(), NOW(), 'BOOKING'),
    (3, 2, '2023-09-10', '2023-09-15', 120, 600, NOW(), NOW(), 'CHECKED_IN'),
    (7, 3, '2023-10-05', '2023-10-10', 90, 450, NOW(), NOW(), 'CHECKED_OUT'),
    (2, 4, '2023-11-20', '2023-11-25', 80, 400, NOW(), NOW(), 'CANCELLED'),
    (2, 5, '2023-08-01', '2023-08-07', 100, 700, NOW(), NOW(), 'BOOKING'),
    (2, 6, '2023-09-10', '2023-09-15', 120, 600, NOW(), NOW(), 'CHECKED_IN'),
    (2, 7, '2023-10-05', '2023-10-10', 90, 450, NOW(), NOW(), 'CHECKED_OUT'),
    (2, 8, '2023-11-20', '2023-11-25', 80, 400, NOW(), NOW(), 'CANCELLED'),
    (2, 9, '2023-08-01', '2023-08-07', 100, 700, NOW(), NOW(), 'BOOKING'),
    (8, 10, '2023-09-10', '2023-09-15', 120, 600, NOW(), NOW(), 'CHECKED_IN'),
    (8, 11, '2023-10-05', '2023-10-10', 90, 450, NOW(), NOW(), 'CHECKED_OUT'),
    (8, 12, '2023-11-20', '2023-11-25', 80, 400, NOW(), NOW(), 'CANCELLED'),
    (8, 13, '2023-08-01', '2023-08-07', 100, 700, NOW(), NOW(), 'BOOKING'),
    (8, 14, '2023-09-10', '2023-09-15', 120, 600, NOW(), NOW(), 'CHECKED_IN'),
    (8, 15, '2023-10-05', '2023-10-10', 90, 450, NOW(), NOW(), 'CHECKED_OUT'),
    (5, 2, '2023-11-20', '2023-11-25', 80, 400, NOW(), NOW(), 'CANCELLED'),
    (5, 5, '2023-08-01', '2023-08-07', 100, 700, NOW(), NOW(), 'BOOKING'),
    (5, 8, '2023-09-10', '2023-09-15', 120, 600, NOW(), NOW(), 'CHECKED_IN'),
    (5, 9, '2023-10-05', '2023-10-10', 90, 450, NOW(), NOW(), 'CHECKED_OUT'),
    (5, 15, '2023-11-20', '2023-11-25', 80, 400, NOW(), NOW(), 'CANCELLED'),
    (5, 4, '2023-08-01', '2023-08-07', 100, 700, NOW(), NOW(), 'BOOKING'),
    (5, 2, '2023-09-10', '2023-09-15', 120, 600, NOW(), NOW(), 'CHECKED_IN'),
    (5, 8, '2023-10-05', '2023-10-10', 90, 450, NOW(), NOW(), 'CHECKED_OUT'),
    (5, 7, '2023-11-20', '2023-11-25', 80, 400, NOW(), NOW(), 'CANCELLED')
;
