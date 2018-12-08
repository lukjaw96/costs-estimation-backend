INSERT INTO user(id_user, first_name, last_name, password, role, username) VALUES(1, 'adminfirst', 'adminlast', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'ADMIN', 'admin');
INSERT INTO user(id_user, first_name, last_name, password, role, username) VALUES(2, 'managerfirst', 'managerlast', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'PROJECT_MANAGER', 'manager');
INSERT INTO user(id_user, first_name, last_name, password, role, username) VALUES(3, 'analystfirst', 'analystlast', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'ANALYST', 'analyst');

INSERT INTO project(id_project, author, description, end_date, name, start_date, status) VALUES(1, 'authorfirst', 'cool project', null, 'First Project', 'grudzien', 'open');

