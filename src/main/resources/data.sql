INSERT INTO user(id_user, first_name, last_name, password, role, username) VALUES(1, 'adminfirst', 'adminlast', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'ADMIN', 'admin');
INSERT INTO user(id_user, first_name, last_name, password, role, username) VALUES(2, 'managerfirst', 'managerlast', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'PROJECT_MANAGER', 'manager');
INSERT INTO user(id_user, first_name, last_name, password, role, username) VALUES(3, 'analystfirst', 'analystlast', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'ANALYST', 'analyst');
INSERT INTO user(id_user, first_name, last_name, password, role, username) VALUES(4, 'expertfirst1', 'expertlast1', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'EXPERT', 'expert1');
INSERT INTO user(id_user, first_name, last_name, password, role, username) VALUES(5, 'expertfirst2', 'expertlast2', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'EXPERT', 'expert2');
INSERT INTO user(id_user, first_name, last_name, password, role, username) VALUES(6, 'expertfirst3', 'expertlast3', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'EXPERT', 'expert3');


INSERT INTO project(id_project, author, description, end_date, name, start_date, status) VALUES(1, 'authorfirst', 'cool project', null, 'First Project', 'grudzien', 'open');
INSERT INTO requirement(id_requirement, author, name) VALUES ('1', 'authorfirst', 'first requirement');

