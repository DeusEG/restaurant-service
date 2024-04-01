INSERT INTO role_data (name) VALUES 
('MODER'),
('USER'),
('ADMIN');

INSERT INTO user_data (name, password, telegram, id_role)
VALUES
('qqq', '$2a$10$H2u5Ljr9Xp2ACuVEbmjaLuLTsy99GjG36zoKjiiq0b0I0O8WIc4t6', 'qqq', 3),
('qqq', '$2a$10$H2u5Ljr9Xp2ACuVEbmjaLuLTsy99GjG36zoKjiiq0b0I0O8WIc4t6', 'qqq1', 3),
('aaa', '$2a$10$H2u5Ljr9Xp2ACuVEbmjaLuLTsy99GjG36zoKjiiq0b0I0O8WIc4t6', 'aaa', 2),
('vvv', '$2a$10$kvzEnY8FWWMaww1IAbsTAuxxgKFgljU8pgZaVU1nIh9Bzc37HhowS', 'vvv', 1);

INSERT INTO restaurant (address, id_admin)
VALUES ('Первомайский проспект 131', 1),
       ('Ленинского Комсомола 34', 2);
	   
INSERT INTO table_data (id_restaurant, number_of_seats)
VALUES (1, 10),
       (1, 4),
       (2, 6),
       (2, 8);

INSERT INTO comment_data (id_user, id_restaurant, comment_text)
VALUES (3, 2, 'Отличное место, хорошая еда!'),
       (3, 1, 'ДЕЛИШЕС');