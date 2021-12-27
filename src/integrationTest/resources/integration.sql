-- Users
INSERT INTO users (id, name, email, password) VALUES ('afe19162-047b-473a-9552-0c254cac753a', 'Admin', 'admin@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
INSERT INTO users (id, name, email, password) VALUES ('afe19162-047b-473a-9552-0c254cac753b', 'Tester', 'tester@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
INSERT INTO users (id, name, email, password) VALUES ('afe19162-047b-473a-9552-0c254cac753c', 'Tester2', 'tester2@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');

--Roles
INSERT INTO roles (id, name) VALUES (1, 'USER');

-- Articles
INSERT INTO articles (id, title, description, article_status, owner_id) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a7','Article1', 'Description of article 1', 'AVAILABLE', 'afe19162-047b-473a-9552-0c254cac753a');
