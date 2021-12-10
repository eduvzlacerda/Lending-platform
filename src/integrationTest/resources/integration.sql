-- Users
INSERT INTO users (name, email, password) VALUES ('Admin', 'admin@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
SELECT setval(pg_get_serial_sequence('users', 'id'), (SELECT MAX(id) FROM users));
