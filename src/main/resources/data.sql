
-- Users
INSERT INTO users (id, name, email, password) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a7', 'Admin', 'admin@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
INSERT INTO users (id, name, email, password) VALUES ('729aca4f-6d3a-474d-8b90-6eb73b02f003', 'Leon', 'leon@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
INSERT INTO users (id, name, email, password) VALUES ('85855a34-f6d5-4419-829c-0b4c231dbc76', 'Christian', 'christian@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
INSERT INTO users (id, name, email, password) VALUES ('885044de-d105-4621-99ba-d0966d0b2c50', 'Eduardo', 'eduardo@mail.com', '$2a$10$9gSGhKccHJxLZROOiUByrOVTJh0dVsRIrYt//5HRRd7e7NiKlX6b6');
-- INSERT INTO users (id, name, email, password) VALUES ('885044de-d105-4621-99ba-d0966d0b2c51', 'Stefan', 'stefan@mail.com', '$2a$10$N9.0Fr3gRz1FPjg.zQiD7uogmmU1PN3cqi/j2.QOo.a/NEw.ILf4O');

-- Roles
INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'MODERATOR');
INSERT INTO roles (id, name) VALUES (3, 'USER');

-- User Roles mapping
INSERT INTO users_roles (user_id, role_id) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a7', 1);
INSERT INTO users_roles (user_id, role_id) VALUES ('729aca4f-6d3a-474d-8b90-6eb73b02f003', 3);
INSERT INTO users_roles (user_id, role_id) VALUES ('85855a34-f6d5-4419-829c-0b4c231dbc76', 3);
INSERT INTO users_roles (user_id, role_id) VALUES ('885044de-d105-4621-99ba-d0966d0b2c50', 3);
-- INSERT INTO users_roles (user_id, role_id) VALUES ('885044de-d105-4621-99ba-d0966d0b2c51', 3);

-- Articles
INSERT INTO articles (id, title, description, article_status, owner_id) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a7', 'Book: Harry Potter (1)', 'Harry Potter and the Philosopher''s Stone', 'AVAILABLE', '729aca4f-6d3a-474d-8b90-6eb73b02f003');
INSERT INTO articles (id, title, description, article_status, owner_id) VALUES ('729aca4f-6d3a-474d-8b90-6eb73b02f003', 'Book: Harry Potter (2)', 'Harry Potter and the Chamber of Secrets', 'AVAILABLE', '85855a34-f6d5-4419-829c-0b4c231dbc76');
INSERT INTO articles (id, title, description, article_status, owner_id) VALUES ('85855a34-f6d5-4419-829c-0b4c231dbc76', 'Book: Harry Potter (3)', 'Harry Potter and the Prisoner of Azkaban', 'AVAILABLE', '885044de-d105-4621-99ba-d0966d0b2c50');
INSERT INTO articles (id, title, description, article_status, owner_id) VALUES ('885044de-d105-4621-99ba-d0966d0b2c50', 'Book: Harry Potter (4)', 'Harry Potter and the Goblet of Fire', 'AVAILABLE', '729aca4f-6d3a-474d-8b90-6eb73b02f003');

-- LendingProcess
-- INSERT INTO lending_processes(id, lending_process_state, article_id, borrower_id, lender_id) VALUES ('933606a4-506b-4749-ac53-3f07a958a8a7', 'PENDING', '933606a4-506b-4749-ac53-3f07a958a8a7', '729aca4f-6d3a-474d-8b90-6eb73b02f003', '85855a34-f6d5-4419-829c-0b4c231dbc76');
-- INSERT INTO lending_processes(id, lending_process_state, article_id, borrower_id, lender_id) VALUES ('634506a4-506b-4749-ac53-3f07a958a8a7', 'ACTIVE', '933606a4-506b-4749-ac53-3f07a958a8a7', '729aca4f-6d3a-474d-8b90-6eb73b02f003', '85855a34-f6d5-4419-829c-0b4c231dbc76');
-- INSERT INTO lending_processes(id, lending_process_state, article_id, borrower_id, lender_id) VALUES ('534206a4-506b-4749-ac53-3f07a958a8a7', 'REJECTED', '933606a4-506b-4749-ac53-3f07a958a8a7', '729aca4f-6d3a-474d-8b90-6eb73b02f003', '85855a34-f6d5-4419-829c-0b4c231dbc76');
-- INSERT INTO lending_processes(id, lending_process_state, article_id, borrower_id, lender_id) VALUES ('435543a4-506b-4749-ac53-3f07a958a8a7', 'PENDING', '933606a4-506b-4749-ac53-3f07a958a8a7', '85855a34-f6d5-4419-829c-0b4c231dbc76', '729aca4f-6d3a-474d-8b90-6eb73b02f003');
-- INSERT INTO lending_processes(id, lending_process_state, article_id, borrower_id, lender_id) VALUES ('943446a4-506b-4749-ac53-3f07a958a8a7', 'PENDING', '933606a4-506b-4749-ac53-3f07a958a8a7', '729aca4f-6d3a-474d-8b90-6eb73b02f003', '85855a34-f6d5-4419-829c-0b4c231dbc76');
