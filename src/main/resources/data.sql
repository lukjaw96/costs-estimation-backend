INSERT INTO user SELECT 1 as id_user,
 'adminfirst' as first_name,
 'adminlast' as last_name,
 '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu' as password,
 'ADMIN' as role,
 'admin' as username
FROM user
WHERE username = 'admin'
HAVING COUNT(*) = 0;

INSERT INTO role SELECT 1 as id_role,
 'ADMIN' as code,
 'Admin' as name
FROM role
WHERE code = 'ADMIN'
HAVING COUNT(*) = 0;

INSERT INTO role SELECT 2 as id_role,
 'PROJECT_MANAGER' as code,
 'Project manager' as name
FROM role
WHERE code = 'PROJECT_MANAGER'
HAVING COUNT(*) = 0;

INSERT INTO role SELECT 3 as id_role,
 'EXPERT' as code,
 'Expert' as name
FROM role
WHERE code = 'EXPERT'
HAVING COUNT(*) = 0;

INSERT INTO role SELECT 4 as id_role,
 'ANALYST' as code,
 'Analyst' as name
FROM role
WHERE code = 'ANALYST'
HAVING COUNT(*) = 0;