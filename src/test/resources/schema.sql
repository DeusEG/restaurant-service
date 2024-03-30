DROP TABLE IF EXISTS role_data CASCADE;
CREATE TABLE role_data
(
    id_role bigserial PRIMARY KEY,
    name varchar(20) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS user_data CASCADE;
CREATE TABLE user_data
(
    id_user bigserial PRIMARY KEY,
    name varchar(20),
	password varchar(500) NOT NULL,
	telegram varchar(30) NOT NULL UNIQUE,
	id_role bigint,
	
	FOREIGN KEY (id_role) REFERENCES role_data (id_role)
);

DROP TABLE IF EXISTS restaurant CASCADE;
CREATE TABLE restaurant
(
    id_restaurant bigserial PRIMARY KEY,
    address varchar(50) NOT NULL UNIQUE,
	id_admin bigint,
	
	FOREIGN KEY (id_admin) REFERENCES user_data (id_user)
);

DROP TABLE IF EXISTS comment_data CASCADE;
CREATE TABLE comment_data
(
    id_comment bigserial PRIMARY KEY,
    id_user bigint,
	id_restaurant bigint,
	comment_text varchar(300) NOT NULL,
	
	FOREIGN KEY (id_user) REFERENCES user_data (id_user) ON DELETE CASCADE,
	FOREIGN KEY (id_restaurant) REFERENCES restaurant (id_restaurant) ON DELETE CASCADE
);

DROP TABLE IF EXISTS table_data CASCADE;
CREATE TABLE table_data
(
    id_table bigserial PRIMARY KEY,
	id_restaurant bigint,
	number_of_seats integer NOT NULL,
	
	FOREIGN KEY (id_restaurant) REFERENCES restaurant (id_restaurant) ON DELETE CASCADE
);

DROP TABLE IF EXISTS reservation CASCADE;
CREATE TABLE reservation
(
    id_reservation bigserial PRIMARY KEY,
	id_table bigint,
	id_user bigint,
	comment character varying(300),
	number_of_seats integer NOT NULL,
	date_time timestamp with time zone NOT NULL,
	
	FOREIGN KEY (id_table) REFERENCES table_data (id_table),
	FOREIGN KEY (id_user) REFERENCES user_data (id_user) ON DELETE CASCADE
);


