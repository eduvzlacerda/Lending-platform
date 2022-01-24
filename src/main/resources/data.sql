
-- Users
INSERT INTO users (id, name, email, password) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a7', 'Admin', 'admin@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
INSERT INTO users (id, name, email, password) VALUES ('729aca4f-6d3a-474d-8b90-6eb73b02f003', 'User2', 'user2@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
INSERT INTO users (id, name, email, password) VALUES ('85855a34-f6d5-4419-829c-0b4c231dbc76', 'User3', 'user3@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
INSERT INTO users (id, name, email, password) VALUES ('885044de-d105-4621-99ba-d0966d0b2c50', 'User4', 'user4@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
INSERT INTO users (id, name, email, password) VALUES ('885044de-d105-4621-99ba-d0966d0b2c51', 'Stefan', 'stefan@mail.com', '$2a$10$N9.0Fr3gRz1FPjg.zQiD7uogmmU1PN3cqi/j2.QOo.a/NEw.ILf4O');

-- Roles
INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'MODERATOR');
INSERT INTO roles (id, name) VALUES (3, 'USER');

-- User Roles mapping
INSERT INTO users_roles (user_id, role_id) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a7', 1);
INSERT INTO users_roles (user_id, role_id) VALUES ('729aca4f-6d3a-474d-8b90-6eb73b02f003', 3);
INSERT INTO users_roles (user_id, role_id) VALUES ('85855a34-f6d5-4419-829c-0b4c231dbc76', 3);
INSERT INTO users_roles (user_id, role_id) VALUES ('885044de-d105-4621-99ba-d0966d0b2c50', 3);
INSERT INTO users_roles (user_id, role_id) VALUES ('885044de-d105-4621-99ba-d0966d0b2c51', 1);

-- Articles
INSERT INTO articles (id, title, description, article_status, owner_id) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a7','Article1', 'Content of article 1', 'AVAILABLE', '729aca4f-6d3a-474d-8b90-6eb73b02f003');
INSERT INTO articles (id, title, description, article_status, owner_id) VALUES ('729aca4f-6d3a-474d-8b90-6eb73b02f003', 'Article2', 'Content of article 2', 'AVAILABLE', '85855a34-f6d5-4419-829c-0b4c231dbc76');
INSERT INTO articles (id, title, description, article_status, owner_id) VALUES ('85855a34-f6d5-4419-829c-0b4c231dbc76','Article3', 'Content of article 3', 'AVAILABLE', '885044de-d105-4621-99ba-d0966d0b2c50');
INSERT INTO articles (id, title, description, article_status, owner_id) VALUES ('885044de-d105-4621-99ba-d0966d0b2c50', 'Article4','Content of article 4', 'HIDDEN', '729aca4f-6d3a-474d-8b90-6eb73b02f003');

-- LendingProcess
INSERT INTO lending_processes(id, lending_process_state, article_id, borrower_id, lender_id) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a7', 'PENDING', '933606a4-506b-4749-ac53-3f07a958a8a7', '729aca4f-6d3a-474d-8b90-6eb73b02f003', '85855a34-f6d5-4419-829c-0b4c231dbc76');
